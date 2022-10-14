package com.example.bankproject.controller.response;

import com.example.bankproject.model.entity.Mgni;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "depositResponse")
public class DepositResponse {

    private String message;

    private Mgni mgni;
}
