package com.example.bankproject.controller.response;

import com.example.bankproject.model.entity.Cashi;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CashiResponse {

    private String message;

    private List<Cashi> cashiList;
}
