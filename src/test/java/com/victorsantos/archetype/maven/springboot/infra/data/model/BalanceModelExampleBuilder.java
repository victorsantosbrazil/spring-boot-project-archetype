package com.victorsantos.archetype.maven.springboot.infra.data.model;

import com.victorsantos.archetype.maven.springboot.domain.enums.BenefitCategory;
import java.math.BigDecimal;

public class BalanceModelExampleBuilder {

    private BalanceModelExampleBuilder() {}

    public static BalanceModel oneBalanceModel() {
        return builder().build();
    }

    public static BalanceModel.BalanceModelBuilder builder() {
        return BalanceModel.builder()
                .accountId("123")
                .category(BenefitCategory.FOOD)
                .totalAmount(BigDecimal.valueOf(1000));
    }
}
