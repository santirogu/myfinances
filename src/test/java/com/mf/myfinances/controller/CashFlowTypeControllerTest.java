package com.mf.myfinances.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mf.myfinances.DTO.CashFlowTypeDTO;
import com.mf.myfinances.entity.CashFlowTypeEntity;
import com.mf.myfinances.repository.ICashFlowTypeRepository;
import com.mf.myfinances.service.Impl.CashFlowTypeServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CashFlowTypeController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class CashFlowTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CashFlowTypeServiceImpl cashFlowTypeService;

    @MockBean
    private ICashFlowTypeRepository cashFlowTypeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private CashFlowTypeEntity cashFlowTypeEntity;
    private CashFlowTypeDTO cashFlowTypeDTO;

    private final String URL = "/mf-webservices/api/v1.0/cashflowtype/";

    @BeforeEach
    void setUp() {
        cashFlowTypeEntity = CashFlowTypeEntity.builder(1L, "Income");
        cashFlowTypeDTO = new CashFlowTypeDTO(1L, "Income");
    }

    @AfterEach
    void tearDown() {
        cashFlowTypeRepository.deleteAll();
    }

    @Test
    void whenFindAllCashFlowType_thenReturnHttpStatusOk() throws Exception {
        List<CashFlowTypeDTO> cashFlowTypeList = Arrays.asList(
                new CashFlowTypeDTO(1L, "Income"),
                new CashFlowTypeDTO(2L, "Expense")
        );
        when(cashFlowTypeService.findAll()).thenReturn(cashFlowTypeList);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int statusCode = result.getResponse().getStatus();
        Assertions.assertEquals(HttpStatus.OK.value(), statusCode);
    }

    @Test
    void whenFindAllCashFlowType_thenReturnAListOfCashFlowTypeObject() throws Exception {
        List<CashFlowTypeDTO> cashFlowTypeList = Arrays.asList(
                new CashFlowTypeDTO(1L, "Income"),
                new CashFlowTypeDTO(2L, "Expense")
        );
        when(cashFlowTypeService.findAll()).thenReturn(cashFlowTypeList);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        List<CashFlowTypeDTO> responseList = objectMapper.readValue(responseContent,
                new TypeReference<>() {
                });
        Assertions.assertEquals(cashFlowTypeList.size(), responseList.size());
        for (int i = 0; i < cashFlowTypeList.size(); i++) {
            CashFlowTypeDTO expectedDTO = cashFlowTypeList.get(i);
            CashFlowTypeDTO actualDTO = responseList.get(i);
            Assertions.assertEquals(expectedDTO.getCashFlowTypeId(), actualDTO.getCashFlowTypeId());
            Assertions.assertEquals(expectedDTO.getName(), actualDTO.getName());
        }
    }

    @Test
    void whenFindAllCashFlowType_thenReturnValidContentType() throws Exception {
        List<CashFlowTypeDTO> cashFlowTypeList = Arrays.asList(
                new CashFlowTypeDTO(1L, "Income"),
                new CashFlowTypeDTO(2L, "Expense")
        );
        when(cashFlowTypeService.findAll()).thenReturn(cashFlowTypeList);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String contentType = result.getResponse().getContentType();
        Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, contentType);
    }

    @Test
    void whenFindCashFlowTypeByCashFlowTypeId_thenReturnHttpStatusOk() throws Exception {
        Long cashFlowTypeId = 1L;

        CashFlowTypeDTO cashFlowTypeDTO = new CashFlowTypeDTO(cashFlowTypeId, "Income");
        when(cashFlowTypeService.findByCashFlowTypeId(ArgumentMatchers.eq(cashFlowTypeId)))
                .thenReturn(cashFlowTypeDTO);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL + cashFlowTypeId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int statusCode = result.getResponse().getStatus();
        Assertions.assertEquals(HttpStatus.OK.value(), statusCode);
    }

    @Test
    void whenFindCashFlowTypeByCashFlowTypeId_thenReturnCashFlowTypeDTOObject() throws Exception {
        Long cashFlowTypeId = 1L;

        CashFlowTypeDTO cashFlowTypeDTO = new CashFlowTypeDTO(cashFlowTypeId, "Income");
        when(cashFlowTypeService.findByCashFlowTypeId(ArgumentMatchers.eq(cashFlowTypeId)))
                .thenReturn(cashFlowTypeDTO);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL + cashFlowTypeId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String responseDTO = result.getResponse().getContentAsString();
        CashFlowTypeDTO responseCashFlowTypeDTO = objectMapper.readValue(responseDTO, CashFlowTypeDTO.class);
        Assertions.assertEquals(cashFlowTypeDTO.getCashFlowTypeId(), responseCashFlowTypeDTO.getCashFlowTypeId());
        Assertions.assertEquals(cashFlowTypeDTO.getName(), responseCashFlowTypeDTO.getName());
    }

    @Test
    void whenSaveCashFlowType_thenCashFlowTypeCreated() throws Exception {
        CashFlowTypeDTO savedCashFlowType = new CashFlowTypeDTO(1L, "Income");
        when(cashFlowTypeService.createCashFlowType(ArgumentMatchers.any(CashFlowTypeDTO.class)))
                .thenReturn(savedCashFlowType);

        String contentString = objectMapper.writeValueAsString(cashFlowTypeDTO);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(contentString))
                .andReturn();

        int statusCode = result.getResponse().getStatus();
        Assertions.assertEquals(HttpStatus.CREATED.value(), statusCode);
    }

    @Test
    void whenSaveCashFlowType_thenCashFlowTypeDataIsTheSame() throws Exception {
        CashFlowTypeDTO savedCashFlowType = new CashFlowTypeDTO(1L, "Income");
        when(cashFlowTypeService.createCashFlowType(ArgumentMatchers.any(CashFlowTypeDTO.class)))
                .thenReturn(savedCashFlowType);

        String contentString = objectMapper.writeValueAsString(cashFlowTypeDTO);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(contentString))
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        CashFlowTypeDTO responseDTO = objectMapper.readValue(responseContent, CashFlowTypeDTO.class);
        Assertions.assertEquals(savedCashFlowType.getCashFlowTypeId(), responseDTO.getCashFlowTypeId());
        Assertions.assertEquals(savedCashFlowType.getName(), responseDTO.getName());
    }

    @Test
    void whenDeleteCashFlowType_thenReturnNotContentHttpStatus() throws Exception {
        Long cashFlowTypeId = 1L;

        MvcResult result =
                mockMvc.perform(MockMvcRequestBuilders.delete(URL + "{cashFlowTypeId}", cashFlowTypeId))
                .andExpect(status().isNoContent())
                .andReturn();

        int statusCode = result.getResponse().getStatus();
        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), statusCode);
    }

    @Test
    void whenDeleteCashFlowType_thenTheServiceIsCalledCorrectly() throws Exception {
        Long cashFlowTypeId = 1L;

        MvcResult result =
                mockMvc.perform(MockMvcRequestBuilders.delete(URL + "{cashFlowTypeId}", cashFlowTypeId))
                        .andExpect(status().isNoContent())
                        .andReturn();

        verify(cashFlowTypeService).deleteCashFlowType(cashFlowTypeId);
    }

    @Test
    void whenUpdateCashFlowType_thenTheStatusCodeIsOk() throws Exception {
        Long cashFlowTypeId = 1L;
        CashFlowTypeDTO cashFlowTypeDTO = new CashFlowTypeDTO();
        cashFlowTypeDTO.setName("Updated Income");

        CashFlowTypeDTO updatedCashFlowTypeDTO = new CashFlowTypeDTO();
        updatedCashFlowTypeDTO.setCashFlowTypeId(cashFlowTypeId);
        updatedCashFlowTypeDTO.setName(cashFlowTypeDTO.getName());

        when(cashFlowTypeService.updateCashFlowType(cashFlowTypeId, cashFlowTypeDTO))
                .thenReturn(updatedCashFlowTypeDTO);

        String contentString = objectMapper.writeValueAsString(cashFlowTypeDTO);

        mockMvc.perform(MockMvcRequestBuilders.put(URL + "{cashFlowTypeId}", cashFlowTypeId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(contentString))
                .andExpect(status().isOk());
    }

    @Test
    void whenUpdateCashFlowType_thenTheContentTypeIsApplicationJSON() throws Exception {
        Long cashFlowTypeId = 1L;
        CashFlowTypeDTO cashFlowTypeDTO = new CashFlowTypeDTO();
        cashFlowTypeDTO.setName("Updated Income");

        CashFlowTypeDTO updatedCashFlowTypeDTO = new CashFlowTypeDTO();
        updatedCashFlowTypeDTO.setCashFlowTypeId(cashFlowTypeId);
        updatedCashFlowTypeDTO.setName(cashFlowTypeDTO.getName());

        when(cashFlowTypeService.updateCashFlowType(cashFlowTypeId, cashFlowTypeDTO))
                .thenReturn(updatedCashFlowTypeDTO);

        String contentString = objectMapper.writeValueAsString(cashFlowTypeDTO);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(URL + "{cashFlowTypeId}", cashFlowTypeId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(contentString))
                .andReturn();

        String contentType = result.getResponse().getContentType();
        Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, contentType);
    }
}