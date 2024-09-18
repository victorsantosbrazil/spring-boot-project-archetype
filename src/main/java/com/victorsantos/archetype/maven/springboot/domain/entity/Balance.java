package com.victorsantos.archetype.maven.springboot.domain.entity;

import static com.victorsantos.archetype.maven.springboot.application.util.BigDecimalUtil.isGreaterThan;
import static com.victorsantos.archetype.maven.springboot.application.util.BigDecimalUtil.isLowerThanZero;

import com.victorsantos.archetype.maven.springboot.domain.enums.BenefitCategory;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class Balance {

    private String accountId;
    private BenefitCategory category;
    private BigDecimal totalAmount;

    private boolean hasEnoughBalance(BigDecimal amount) {
        return isGreaterThan(this.totalAmount, amount);
    }

    public BigDecimal debit(BigDecimal amount) {
        if (amount == null || isLowerThanZero(amount)) {
            throw new IllegalArgumentException("Amount cannot be null or negative");
        }

        if (!hasEnoughBalance(amount)) {
            var remainingAmount = amount.subtract(this.totalAmount);
            this.totalAmount = BigDecimal.ZERO;
            return remainingAmount;
        }

        this.totalAmount = this.totalAmount.subtract(amount);
        return BigDecimal.ZERO;
    }
}
