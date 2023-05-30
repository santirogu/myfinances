package com.mf.myfinances.service.Impl;

import com.mf.myfinances.DTO.CashFlowTypeDTO;
import com.mf.myfinances.entity.CashFlowTypeEntity;
import com.mf.myfinances.exception.CashFlowTypeNotFoundException;
import com.mf.myfinances.exception.CashFlowTypeNotNullOrEmptyException;
import com.mf.myfinances.repository.ICashFlowTypeRepository;
import com.mf.myfinances.service.Impl.mother.CashFlowTypeMother;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CashFlowTypeServiceImplTest {

    @Mock
    private ICashFlowTypeRepository cashFlowTypeRepository;

    @InjectMocks
    private CashFlowTypeServiceImpl cashFlowTypeService;

    private CashFlowTypeEntity cashFlowTypeEntity;

    private CashFlowTypeDTO cashFlowTypeDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cashFlowTypeEntity = CashFlowTypeMother.validCashFlowTypeEntity();
        cashFlowTypeDTO = new CashFlowTypeDTO();
    }

    @AfterEach
    void tearDown() {
        cashFlowTypeRepository.deleteAll();
    }

    @Test
    void whenCreateCashFlowType_thenNotNull() {
        when(cashFlowTypeRepository.save(any(CashFlowTypeEntity.class))).thenReturn(cashFlowTypeEntity);
        Assertions.assertNotNull(cashFlowTypeService.createCashFlowType(new CashFlowTypeDTO(0L, "Test")));
    }

    @Test
    void whenCreateCashFlowTypeWithNameNull_thenReturnException() {
        Throwable throwable = assertThrows(CashFlowTypeNotNullOrEmptyException.class,
                () -> cashFlowTypeService.createCashFlowType(new CashFlowTypeDTO()));
        Assertions.assertEquals("The Cash Flow Type Name should not be null or empty", throwable.getMessage());
    }

    @Test
    void whenCreateCashFlowTypeWithNameEmpty_thenReturnException() {
        Throwable throwable = assertThrows(CashFlowTypeNotNullOrEmptyException.class,
                () -> cashFlowTypeService.createCashFlowType(new CashFlowTypeDTO(0L, "")));
        Assertions.assertEquals("The Cash Flow Type Name should not be null or empty", throwable.getMessage());
    }

    @Test
    void whenFindAll_thenNotNull() {
        when(cashFlowTypeRepository.findAll()).thenReturn(Collections.singletonList(cashFlowTypeEntity));
        Assertions.assertNotNull(cashFlowTypeService.findAll());
    }

    @Test
    void whenFindAll_thenReturnElements() {
        when(cashFlowTypeRepository.findAll()).thenReturn(Collections.singletonList(cashFlowTypeEntity));
        Assertions.assertTrue(cashFlowTypeService.findAll().size() > 0);
    }

    @Test
    void whenFindByCashFlowTypeId_thenNotNull() {
        when(cashFlowTypeRepository.findByCashFlowTypeId(any())).thenReturn(cashFlowTypeEntity);
        Assertions.assertNotNull(cashFlowTypeService.findByCashFlowTypeId(cashFlowTypeEntity.getCashFlowTypeId()));
    }

    @Test
    void whenFindByCashFlowTypeId_thenReturnExpectedCashFlowType() {
        when(cashFlowTypeRepository.findByCashFlowTypeId(any())).thenReturn(cashFlowTypeEntity);
        Assertions.assertEquals(cashFlowTypeService.findByCashFlowTypeId(cashFlowTypeEntity.getCashFlowTypeId()).getCashFlowTypeId(),
                cashFlowTypeEntity.getCashFlowTypeId());
    }

    @Test
    void whenFindByCashFlowTypeIdWithNotExistingCashFlowTypeId_thenReturnException() {
        Throwable throwable = assertThrows(CashFlowTypeNotFoundException.class, () -> cashFlowTypeService.findByCashFlowTypeId(1L));
        Assertions.assertEquals("Requested Cash Flow Type does not exist", throwable.getMessage());
    }

    @Test
    void whenDeleteCashFlowTypeByCashFlowTypeId_thenTheCashFlowTypeIsDeleted() {
        Long cashFlowTypeId = cashFlowTypeEntity.getCashFlowTypeId();
        when(cashFlowTypeRepository.findByCashFlowTypeId(cashFlowTypeId)).thenReturn(cashFlowTypeEntity);
        cashFlowTypeService.deleteCashFlowType(cashFlowTypeId);
        verify(cashFlowTypeRepository).findByCashFlowTypeId(cashFlowTypeId);
        verify(cashFlowTypeRepository).delete(cashFlowTypeEntity);
    }

    @Test
    void whenDeleteNotExistingCashFlowType_thenReturnNotFoundException() {
        Throwable throwable = assertThrows(CashFlowTypeNotFoundException.class, () -> cashFlowTypeService.deleteCashFlowType(1L));
        Assertions.assertEquals("Requested CashFlow Type to delete does not exist", throwable.getMessage());
    }

    @Test
    void whenUpdateCashFlowType_ThenReturnUpdatedDTO() {
        Long cashFlowTypeId = 1L;
        CashFlowTypeDTO cashFlowTypeDTO = new CashFlowTypeDTO();
        cashFlowTypeDTO.setName("Updated Income");

        CashFlowTypeEntity existingCashFlowType = new CashFlowTypeEntity();
        existingCashFlowType.setCashFlowTypeId(cashFlowTypeId);
        existingCashFlowType.setName("Old Income");

        CashFlowTypeEntity updatedCashFlowType = new CashFlowTypeEntity();
        updatedCashFlowType.setCashFlowTypeId(cashFlowTypeId);
        updatedCashFlowType.setName(cashFlowTypeDTO.getName());

        when(cashFlowTypeRepository.findByCashFlowTypeId(cashFlowTypeId)).thenReturn(existingCashFlowType);
        when(cashFlowTypeRepository.save(existingCashFlowType)).thenReturn(updatedCashFlowType);

        CashFlowTypeDTO resultDTO = cashFlowTypeService.updateCashFlowType(cashFlowTypeId, cashFlowTypeDTO);

        verify(cashFlowTypeRepository, times(1)).findByCashFlowTypeId(cashFlowTypeId);
        verify(cashFlowTypeRepository, times(1)).save(existingCashFlowType);
        Assertions.assertNotNull(resultDTO);
        Assertions.assertEquals(cashFlowTypeId, resultDTO.getCashFlowTypeId());
        Assertions.assertEquals(cashFlowTypeDTO.getName(), resultDTO.getName());
    }

    @Test
    void whenUpdateNonexistentCashFlowType_ThenThrowNotFoundException() {
        Long cashFlowTypeId = 1L;
        CashFlowTypeDTO cashFlowTypeDTO = new CashFlowTypeDTO();
        cashFlowTypeDTO.setName("Updated Income");

        when(cashFlowTypeRepository.findByCashFlowTypeId(cashFlowTypeId)).thenReturn(null);

        Assertions.assertThrows(CashFlowTypeNotFoundException.class,
                () -> cashFlowTypeService.updateCashFlowType(cashFlowTypeId, cashFlowTypeDTO));

        verify(cashFlowTypeRepository, times(1)).findByCashFlowTypeId(cashFlowTypeId);
        verify(cashFlowTypeRepository, never()).save(any(CashFlowTypeEntity.class));
    }

    @Test
    void whenUpdateCashFlowTypeWithNullOrEmptyName_ThenThrowNotNullOrEmptyException() {
        Long cashFlowTypeId = 1L;
        CashFlowTypeDTO cashFlowTypeDTO = new CashFlowTypeDTO();

        when(cashFlowTypeRepository.findByCashFlowTypeId(cashFlowTypeId)).thenReturn(new CashFlowTypeEntity());

        Assertions.assertThrows(CashFlowTypeNotNullOrEmptyException.class,
                () -> cashFlowTypeService.updateCashFlowType(cashFlowTypeId, cashFlowTypeDTO));

        verify(cashFlowTypeRepository, times(1)).findByCashFlowTypeId(cashFlowTypeId);
        verify(cashFlowTypeRepository, never()).save(any(CashFlowTypeEntity.class));
    }
}