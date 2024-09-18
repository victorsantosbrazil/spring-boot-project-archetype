package com.victorsantos.archetype.maven.springboot.application.service.benefit;

import com.victorsantos.archetype.maven.springboot.domain.enums.BenefitCategory;

public interface BenefitCategoryService {
    BenefitCategory findByMerchantNameAndMcc(String merchantName, String mcc);
}
