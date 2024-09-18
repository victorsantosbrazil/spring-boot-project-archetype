package com.victorsantos.archetype.maven.springboot.infra.data.model;

import com.victorsantos.archetype.maven.springboot.domain.enums.BenefitCategory;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BalanceModelId {
    private String accountId;
    private BenefitCategory category;
}
