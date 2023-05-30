package com.mf.myfinances.service.Impl;

import com.mf.myfinances.DTO.CashFlowTypeDTO;
import com.mf.myfinances.entity.CashFlowTypeEntity;
import com.mf.myfinances.exception.CashFlowTypeNotFoundException;
import com.mf.myfinances.exception.CashFlowTypeNotNullOrEmptyException;
import com.mf.myfinances.repository.ICashFlowTypeRepository;
import com.mf.myfinances.service.ICashFlowTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashFlowTypeServiceImpl implements ICashFlowTypeService {

    private final CashFlowTypeDTO cashFlowTypeDTO = new CashFlowTypeDTO();

    @Autowired
    private ICashFlowTypeRepository cashFlowTypeRepository;

    @Override
    public CashFlowTypeDTO createCashFlowType(CashFlowTypeDTO cashFlowTypeDTO) {
        if (cashFlowTypeDTO.getName() == null || cashFlowTypeDTO.getName().isEmpty())
            throw new CashFlowTypeNotNullOrEmptyException("The Cash Flow Type Name should not be null or empty");
        return this.cashFlowTypeDTO.cashFlowTypeEntityToCashFlowTypeDTO(cashFlowTypeRepository
                .save(this.cashFlowTypeDTO.cashFlowTypeDTOToCashFlowTypeEntity(cashFlowTypeDTO)));
    }

    @Override
    public CashFlowTypeDTO updateCashFlowType(Long id, CashFlowTypeDTO cashFlowTypeDTO) {
        CashFlowTypeEntity existingCashFlowType = cashFlowTypeRepository.findByCashFlowTypeId(id);
        if (existingCashFlowType == null)
            throw new CashFlowTypeNotFoundException("Requested Cash Flow Type to update does not exist");

        if (cashFlowTypeDTO.getName() == null || cashFlowTypeDTO.getName().isEmpty())
            throw new CashFlowTypeNotNullOrEmptyException("The Cash Flow Type Name should not be null or empty");

        existingCashFlowType.setName(cashFlowTypeDTO.getName());
        CashFlowTypeEntity updatedCashFlowType = cashFlowTypeRepository.save(existingCashFlowType);
        return this.cashFlowTypeDTO.cashFlowTypeEntityToCashFlowTypeDTO(updatedCashFlowType);
    }

    @Override
    public void deleteCashFlowType(Long id) {
        CashFlowTypeEntity cashFlowTypeEntity = cashFlowTypeRepository.findByCashFlowTypeId(id);
        if (cashFlowTypeEntity == null)
            throw new CashFlowTypeNotFoundException("Requested CashFlow Type to delete does not exist");
        cashFlowTypeRepository.delete(cashFlowTypeEntity);
    }

    @Override
    public List<CashFlowTypeDTO> findAll() {
        List<CashFlowTypeEntity> cashFlowTypeEntities = cashFlowTypeRepository.findAll();
        return cashFlowTypeEntities.stream().map(cashFlowTypeDTO::cashFlowTypeEntityToCashFlowTypeDTO).toList();
    }

    @Override
    public CashFlowTypeDTO findByCashFlowTypeId(Long id) {
        if (cashFlowTypeRepository.findByCashFlowTypeId(id) == null)
            throw new CashFlowTypeNotFoundException("Requested Cash Flow Type does not exist");
        return cashFlowTypeDTO.cashFlowTypeEntityToCashFlowTypeDTO(cashFlowTypeRepository.findByCashFlowTypeId(id));
    }
}
