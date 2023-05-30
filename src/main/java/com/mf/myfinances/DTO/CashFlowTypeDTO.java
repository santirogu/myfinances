package com.mf.myfinances.DTO;

import com.mf.myfinances.entity.CashFlowTypeEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CashFlowTypeDTO {
    private Long cashFlowTypeId;
    private String name;

    public CashFlowTypeDTO(Long cashFlowTypeId, String name) {
        this.cashFlowTypeId = cashFlowTypeId;
        this.name = name;
    }

    public CashFlowTypeDTO() {
    }

    public CashFlowTypeDTO cashFlowTypeEntityToCashFlowTypeDTO(CashFlowTypeEntity cashFlowTypeEntity) {
        return new CashFlowTypeDTO(cashFlowTypeEntity.getCashFlowTypeId(), cashFlowTypeEntity.getName());
    }

    public CashFlowTypeEntity cashFlowTypeDTOToCashFlowTypeEntity(CashFlowTypeDTO cashFlowTypeDTO) {
        return CashFlowTypeEntity.builder(cashFlowTypeDTO.getCashFlowTypeId(), cashFlowTypeDTO.getName());
    }
}
