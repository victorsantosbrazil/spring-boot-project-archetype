package com.victorsantos.archetype.maven.springboot.application.service.benefit;

import com.victorsantos.archetype.maven.springboot.domain.enums.BenefitCategory;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
class MccBenefitCategory {

    static final Map<String, BenefitCategory> mccToCategoryMap = Map.of(
            "5411", BenefitCategory.FOOD,
            "5412", BenefitCategory.FOOD,
            "5811", BenefitCategory.MEAL,
            "5812", BenefitCategory.MEAL);

    public Optional<BenefitCategory> find(String mcc) {
        return Optional.ofNullable(mccToCategoryMap.get(mcc));
    }
}
