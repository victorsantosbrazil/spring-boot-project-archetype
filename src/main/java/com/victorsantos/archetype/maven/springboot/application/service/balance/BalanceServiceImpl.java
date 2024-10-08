package com.victorsantos.archetype.maven.springboot.application.service.balance;

import com.victorsantos.archetype.maven.springboot.domain.entity.Balance;
import com.victorsantos.archetype.maven.springboot.domain.enums.BenefitCategory;
import com.victorsantos.archetype.maven.springboot.infra.data.BalanceRepository;
import com.victorsantos.archetype.maven.springboot.infra.data.model.BalanceModelId;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class BalanceServiceImpl implements BalanceService {

    private final BalanceRepository balanceRepository;

    private final BalanceServiceMapper serviceMapper;

    @Override
    public Optional<Balance> findById(String accountId, BenefitCategory category) {
        var modelId = new BalanceModelId(accountId, category);
        return balanceRepository.findById(modelId).map(serviceMapper::toEntity);
    }

    @Override
    public void save(Balance balance) {
        var model = serviceMapper.toModel(balance);
        balanceRepository.save(model);
    }
}
