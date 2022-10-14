package com.example.bankproject.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCashiRequest {
    @NotBlank(message = "mgniId is blank")
    @Length(max = 20, message = "mgniId must less than 20 characters")
    private String mgniId;

    @NotBlank(message = "accNo is blank")
    @Length(max = 7, message = "accNo less than 7 characters")
    private String accNo;

    @NotBlank(message = "ccy is blank")
    @Length(max = 3, message = "ccy less than 3 characters")
    private String ccy;

    @NotNull(message = "amt is null")
    @DecimalMin(value = "0", inclusive = false, message = "amt must be greater than 0")
    @Digits(integer = 20, fraction = 4, message = "digits of price is not correct")
    private BigDecimal amt;
}
