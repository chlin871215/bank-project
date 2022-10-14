package com.example.bankproject.model.entity;

import com.example.bankproject.model.configurator.DateAdapter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MGNI")
@Entity
@EntityListeners(AuditingEntityListener.class)
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Mgni {
    @Id
    @Column(name = "MGNI_ID")
    private String id;

    @CreatedDate
    @Column(name = "MGNI_TIME")
    @XmlJavaTypeAdapter(DateAdapter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime time;

    @Column(name = "MGNI_TYPE")
    private String type;

    @Column(name = "MGNI_CM_NO")
    private String cmNo;

    @Column(name = "MGNI_KAC_TYPE")
    private String kacType;

    @Column(name = "MGNI_BANK_NO")
    private String bankNo;

    @Column(name = "MGNI_CCY")
    private String ccy;

    @Column(name = "MGNI_PV_TYPE")
    private String pvType;

    @Column(name = "MGNI_BICACC_NO")
    private String bicaccNo;

    @Column(name = "MGNI_I_TYPE")
    private String iType;

    @Column(name = "MGNI_P_REASON")
    private String pReason;

    @Column(name = "MGNI_AMT")
    private BigDecimal amt;

    @Column(name = "MGNI_CT_NAME")
    private String ctName;

    @Column(name = "MGNI_CT_TEL")
    private String ctTel;

    @Column(name = "MGNI_STATUS")
    private String status;

    @LastModifiedDate
    @Column(name = "MGNI_U_TIME")
    @XmlJavaTypeAdapter(DateAdapter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime uTime;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "mgniId")
    private List<Cashi> cashiList;

}
