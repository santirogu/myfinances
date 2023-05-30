package com.mf.myfinances.service;

import com.mf.myfinances.DTO.CashFlowTypeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICashFlowTypeService {

    CashFlowTypeDTO createCashFlowType(CashFlowTypeDTO cashFlowTypeDTO);

    CashFlowTypeDTO updateCashFlowType(Long id, CashFlowTypeDTO cashFlowTypeDTO);

    void deleteCashFlowType(Long id);

    List<CashFlowTypeDTO> findAll();

    CashFlowTypeDTO findByCashFlowTypeId(Long id);
}
