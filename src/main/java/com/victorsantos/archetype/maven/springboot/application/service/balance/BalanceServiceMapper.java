package com.victorsantos.archetype.maven.springboot.application.service.balance;

import com.victorsantos.archetype.maven.springboot.domain.entity.Balance;
import com.victorsantos.archetype.maven.springboot.infra.data.model.BalanceModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface BalanceServiceMapper {

    Balance toEntity(BalanceModel model);

    BalanceModel toModel(Balance balance);
}
