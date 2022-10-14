package com.example.bankproject.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMgniRequest {

    @NotBlank(message = "mgniId is blank")
    @Length(max = 20, message = "mgniId must less than 20 characters")
    private String id;

    @NotNull(message = "depositRequest is null")
    private DepositRequest depositRequest;
}
