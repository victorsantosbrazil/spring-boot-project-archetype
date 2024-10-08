package com.victorsantos.archetype.maven.springboot.application.usecase.authorize;

import static com.victorsantos.archetype.maven.springboot.application.util.BigDecimalUtil.isGreaterThanZero;

import com.victorsantos.archetype.maven.springboot.application.constant.AuthorizationCode;
import com.victorsantos.archetype.maven.springboot.application.exception.AuthorizeTransactionException;
import com.victorsantos.archetype.maven.springboot.application.service.balance.BalanceService;
import com.victorsantos.archetype.maven.springboot.application.service.benefit.BenefitCategoryService;
import com.victorsantos.archetype.maven.springboot.domain.entity.Balance;
import com.victorsantos.archetype.maven.springboot.domain.enums.BenefitCategory;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
@Slf4j
class AuthorizeUseCaseImpl implements AuthorizeUseCase {

    private final Validator validator;

    private final BalanceService balanceService;

    private final BenefitCategoryService benefitCategoryService;

    @Transactional
    @Override
    public AuthorizeUseCaseResponse run(AuthorizeUseCaseRequest request) {
        var transactionId = UUID.randomUUID().toString();

        log.info("Authorizing transaction... Id: {}, Request: {}", transactionId, request);

        try {
            validateRequest(request);
            debit(request);

            log.info("Transaction authorized. Id: {}", transactionId);
            return new AuthorizeUseCaseResponse(AuthorizationCode.APPROVED);

        } catch (AuthorizeTransactionException e) {
            log.error("Transaction failed. Id: {}, Cause: {}", transactionId, e.getMessage());
            return new AuthorizeUseCaseResponse(e.getCode());
        }
    }

    private void validateRequest(AuthorizeUseCaseRequest request) {
        var validationResult = validator.validateObject(request);
        if (validationResult.hasErrors()) {
            var errorMessage = String.format(
                    "Found one or more invalid fields in request, Errors: %s", validationResult.getAllErrors());
            throw new AuthorizeTransactionException(errorMessage, AuthorizationCode.OTHER);
        }
    }

    private void debit(AuthorizeUseCaseRequest request) {
        var accountId = request.getAccount();
        var category = benefitCategoryService.findByMerchantNameAndMcc(request.getMerchant(), request.getMcc());

        var balance = findBalance(accountId, category);

        var remainingAmount = balance.debit(request.getTotalAmount());
        if (isGreaterThanZero(remainingAmount)) {
            if (isCashBalance(balance)) {
                throw insufficientBalanceException(accountId, category);
            }
            debitCashBalance(accountId, remainingAmount);
        }

        balanceService.save(balance);
    }

    private void debitCashBalance(String accountId, BigDecimal remainingAmount) {
        var category = BenefitCategory.CASH;
        var balance = findBalance(accountId, category);
        var remainingAmountCashBalance = balance.debit(remainingAmount);
        if (isGreaterThanZero(remainingAmountCashBalance)) {
            throw insufficientBalanceException(accountId, category);
        }
        balanceService.save(balance);
    }

    private Balance findBalance(String accountId, BenefitCategory category) {
        var optionalBalance = balanceService.findById(accountId, category);

        if (optionalBalance.isEmpty()) {
            var errorMessage = String.format("Balance not found for account %s and category %s", accountId, category);
            throw new AuthorizeTransactionException(errorMessage, AuthorizationCode.OTHER);
        }

        return optionalBalance.get();
    }

    private static boolean isCashBalance(Balance balance) {
        return balance.getCategory().equals(BenefitCategory.CASH);
    }

    private AuthorizeTransactionException insufficientBalanceException(String accountId, BenefitCategory category) {
        var errorMessage = String.format("Insufficient balance for account %s and category %s", accountId, category);
        throw new AuthorizeTransactionException(errorMessage, AuthorizationCode.REFUSED);
    }
}
