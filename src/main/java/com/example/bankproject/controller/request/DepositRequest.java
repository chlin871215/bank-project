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
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepositRequest {
    @NotBlank(message = "cmNo is blank")
    @Length(max = 7, message = "cmNo must less than 7 characters")
    private String cmNo;//會員代號

    @NotBlank(message = "kacType is blank")
    @Length(max = 1, message = "kacType must less than 1 characters")
    private String kacType;

    @NotBlank(message = "bankNo is blank")
    @Length(max = 3, message = "bankNo must less than 3 characters")
    private String bankNo;//銀行代碼

    @NotBlank(message = "ccy is blank")
    @Length(max = 3, message = "ccy must less than 3 characters")
    private String ccy;//幣別

    @NotBlank(message = "bicaccNo is blank")
    @Length(max = 21, message = "bicaccNo must less than 21 characters")
    private String bicaccNo;//大帳號

    @NotNull(message = "acc is Null")
    private List<Acc> acc;//小帳號
//
//    @NotNull(message = "amt is null")
//    @DecimalMin(value = "0", inclusive = false, message = "amt must be greater than 0")
//    @Digits(integer = 20, fraction = 4, message = "digits of price is not correct")
//    private BigDecimal amt;//金額v

    @NotBlank(message = "ctName is blank")
    @Length(max = 120, message = "ctName must less than 120 characters")
    private String ctName;//姓名

    @NotBlank(message = "ctTel is blank")
    @Length(max = 30, message = "ctTel must less than 30 characters")
    private String ctTel;//電話

}
