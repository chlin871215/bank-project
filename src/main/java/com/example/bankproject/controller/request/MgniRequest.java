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
public class MgniRequest {
    @Length(max = 20, message = "mgniId must less than 20 characters")
    private String id;
    @Length(max = 3, message = "bankNo must less than 3 characters")
    private String bankNo;
    @Length(max = 3, message = "ccy must less than 3 characters")
    private String ccy;
    @Length(max = 21, message = "bicaccNo must less than 21 characters")
    private String bicaccNo;
}
