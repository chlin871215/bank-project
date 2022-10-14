package com.example.bankproject.controller;

import com.example.bankproject.controller.request.*;
import com.example.bankproject.controller.response.CashiResponse;
import com.example.bankproject.controller.response.DepositResponse;
import com.example.bankproject.controller.response.GetResponse;
import com.example.bankproject.model.MgniRepo;
import com.example.bankproject.model.entity.Mgni;
import com.example.bankproject.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value = "/api/v1")
@RestController
@Validated
public class BankController {
    @Autowired
    BankService bankService;

    @PostMapping(value = "/deposit", produces = {"application/xml", "application/json"}, consumes = {"application/xml", "application/json"})
    public DepositResponse deposit(@Valid @RequestBody DepositRequest request) {
        return bankService.deposit(request);
    }

    @PostMapping(value = "/mgni")
    public GetResponse getMgni(@Valid @RequestBody MgniRequest request) {
        return new GetResponse("", bankService.getMgni(request));
    }

    @PostMapping(value = "/cashi", produces = {"application/json"})
    public GetResponse getCashi(@Valid @RequestBody CashiRequest request) {
        return new GetResponse("", bankService.getCashi(request));
    }

    @PostMapping(value = "updatemgni")
    public Mgni updateMgni(@Valid @RequestBody UpdateMgniRequest request) {
        return bankService.updateMgni(request);
    }

    @PostMapping(value = "/updatecashi")
    public Mgni updateCashi(@Valid @RequestBody UpdateCashiRequest request) {
        return bankService.updateCashi(request);
    }

    @PostMapping(value = "deletemgni")
    public String deleteMgni(@Valid @RequestBody IdRequest request) {
        return bankService.deleteMgni(request);
    }

    @PostMapping(value = "deletecashi")
    public String deleteCashi(@Valid @RequestBody CashiRequest request) {
        return bankService.deleteCashi(request);
    }

}
