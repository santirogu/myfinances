package com.mf.myfinances.service.Impl.mother;

import com.mf.myfinances.entity.CashFlowTypeEntity;

import java.util.List;

public class CashFlowTypeMother {
    public static CashFlowTypeEntity validCashFlowTypeEntity() {
        return CashFlowTypeEntity.builder(1L, "Expenses");
    }

    public static CashFlowTypeEntity anotherValidCashFlowTypeEntity() {
        return CashFlowTypeEntity.builder(2L, "Income");
    }

    public static List<CashFlowTypeEntity> validCashFlowTypeEntityList() {
        List<CashFlowTypeEntity> cashFlowTypeEntities = null;
        cashFlowTypeEntities.add(validCashFlowTypeEntity());
        cashFlowTypeEntities.add(anotherValidCashFlowTypeEntity());
        return cashFlowTypeEntities;
    }
}
