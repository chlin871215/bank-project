package com.example.bankproject.controller;

import com.example.bankproject.controller.request.*;
import com.example.bankproject.service.BankService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

@Service
public class Producer {

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private BankService bankService;

    public void sendMessage(Destination destination, final String message) {
        jmsMessagingTemplate.convertAndSend(destination, message);
    }

    @JmsListener(destination = "request.queue")
    @SendTo("response.queue")
    public String getClientRequest(String request) throws JsonProcessingException {
        objectMapper.findAndRegisterModules();
        String response = "";
        System.out.println("request : " + request);
        JSONObject jsonObject = new JSONObject(request);
        String requestType = jsonObject.getString("requestType");
        String requestBody = JSONObject.valueToString(jsonObject.getJSONObject("requestBody"));
        System.out.println("requestBody : " + requestBody);
        {
            switch (requestType) {
                case "create":
                    response = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(bankService.deposit(objectMapper.readValue(requestBody, DepositRequest.class)));
                    break;
                case "getCashi":
                    response = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(bankService.getCashi(objectMapper.readValue(requestBody, CashiRequest.class)));
                    break;
                case "getMgni":
                    response = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(bankService.getMgni(objectMapper.readValue(requestBody, MgniRequest.class)));
                    break;
                case "updateMgni":
                    response = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(bankService.updateMgni(objectMapper.readValue(requestBody, UpdateMgniRequest.class)));
                    break;
                case "updateCashi":
                    response = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(bankService.updateCashi(objectMapper.readValue(requestBody, UpdateCashiRequest.class)));
                    break;
                case "deleteMgni":
                    response = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(bankService.deleteMgni(objectMapper.readValue(requestBody, IdRequest.class)));
                    break;
                case "deleteCashi":
                    response = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(bankService.deleteCashi(objectMapper.readValue(requestBody, CashiRequest.class)));
                    break;
                default:
                    response = "requestType wrong";
            }
        }
        System.out.println("response : " + response);
        return response;
    }

}
