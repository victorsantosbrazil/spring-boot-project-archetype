package com.victorsantos.archetype.maven.springboot.infra.data;

import com.victorsantos.archetype.maven.springboot.infra.data.model.BalanceModel;
import com.victorsantos.archetype.maven.springboot.infra.data.model.BalanceModelId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends JpaRepository<BalanceModel, BalanceModelId> {}
