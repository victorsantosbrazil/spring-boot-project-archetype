package com.victorsantos.archetype.maven.springboot.application.service.balance;

import com.victorsantos.archetype.maven.springboot.domain.entity.Balance;
import com.victorsantos.archetype.maven.springboot.domain.enums.BenefitCategory;
import java.util.Optional;

public interface BalanceService {

    Optional<Balance> findById(String accountId, BenefitCategory category);

    void save(Balance balance);
}
