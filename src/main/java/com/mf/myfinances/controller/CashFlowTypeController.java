package com.mf.myfinances.controller;

import com.mf.myfinances.DTO.CashFlowTypeDTO;
import com.mf.myfinances.exception.ApiException;
import com.mf.myfinances.service.ICashFlowTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("mf-webservices/api/v1.0/cashflowtype")
public class CashFlowTypeController {
    @Autowired
    private ICashFlowTypeService cashFlowTypeService;

    @GetMapping("/")
    @Operation(summary = "Get all cash flow type")
    @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "List of cash flow type",
      content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CashFlowTypeDTO.class))})
    })
    public ResponseEntity<List<CashFlowTypeDTO>> getAllCashFlowType() {
        return new ResponseEntity<>(cashFlowTypeService.findAll(), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{cashFlowTypeId}")
    @Operation(summary = "Get a cash flow type given id")
    @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Cash flow type",
      content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CashFlowTypeDTO.class)) }),
      @ApiResponse(responseCode = "404", description = "Cash flow type not found",
      content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiException.class)) })
    })
    public ResponseEntity<CashFlowTypeDTO> getCashFlowType(@PathVariable("cashFlowTypeId") Long cashFlowTypeId) {
        return new ResponseEntity<>(cashFlowTypeService.findByCashFlowTypeId(cashFlowTypeId), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/")
    @Operation(summary = "Save a new cash flow type")
    @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Cash flow type saved successfully",
      content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CashFlowTypeDTO.class))}),
      @ApiResponse(responseCode = "409", description = "Cash flow type not null or empty",
      content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiException.class))})
    })
    public ResponseEntity<CashFlowTypeDTO> saveCashFlowType(@RequestBody CashFlowTypeDTO cashFlowTypeDTO) {
        return new ResponseEntity<>(cashFlowTypeService.createCashFlowType(cashFlowTypeDTO), new HttpHeaders(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{cashFlowTypeId}")
    @Operation(summary = "Delete a cash flow type given id")
    @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Cash flow type Deleted",
      content = { @Content(mediaType = "application/json", schema = @Schema()) }),
      @ApiResponse(responseCode = "404", description = "Cash flow type not found",
      content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiException.class)) })
    })
    public ResponseEntity<Void> deleteCashFlowType(@PathVariable("cashFlowTypeId") Long cashFlowTypeId) {
        cashFlowTypeService.deleteCashFlowType(cashFlowTypeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{cashFlowTypeId}", produces = "application/json")
    @Operation(summary = "Update a cash flow type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cash flow type updated successfully",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CashFlowTypeDTO.class))}),
            @ApiResponse(responseCode = "409", description = "Cash flow type not null or empty",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiException.class))}),
            @ApiResponse(responseCode = "404", description = "Cash flow type not found",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiException.class)) })
    })
    public ResponseEntity<CashFlowTypeDTO> updateCashFlowType(@PathVariable("cashFlowTypeId") Long cashFlowTypeId,
                                                              @RequestBody CashFlowTypeDTO cashFlowTypeDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        CashFlowTypeDTO cashFlowTypeDTOToReturn = cashFlowTypeService.updateCashFlowType(cashFlowTypeId, cashFlowTypeDTO);
        return new ResponseEntity<>(cashFlowTypeDTOToReturn, headers, HttpStatus.OK);
    }
}
