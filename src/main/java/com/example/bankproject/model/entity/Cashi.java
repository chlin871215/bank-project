package com.example.bankproject.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CASHI")
@IdClass(CashiPK.class)
@Entity
public class Cashi {

    @Id
    @Column(name = "CASHI_MGNI_ID")
    private String mgniId;

    @Id
    @Column(name = "CASHI_ACC_NO")
    private String accNo;

    @Id
    @Column(name = "CASHI_CCY")
    private String ccy;

    @Column(name = "CASHI_AMT")
    private BigDecimal amt;

}
