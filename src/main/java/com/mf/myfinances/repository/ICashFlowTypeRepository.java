package com.mf.myfinances.repository;

import com.mf.myfinances.entity.CashFlowTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICashFlowTypeRepository extends JpaRepository<CashFlowTypeEntity, Long> {

    CashFlowTypeEntity findByCashFlowTypeId(Long id);

}
