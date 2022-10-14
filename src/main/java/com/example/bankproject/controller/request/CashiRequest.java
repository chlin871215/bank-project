package com.example.bankproject.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CashiRequest {
    @Length(max = 20, message = "mgniId must less than 20 characters")
    private String mgniId;

    @Length(max = 7, message = "accNo less than 7 characters")
    private String accNo;

    @Length(max = 3, message = "ccy must less than 3 characters")
    private String ccy;
}
