package com.example.bankproject.controller.request;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdRequest {
    @NotBlank(message = "mgniId is blank")
    @Length(max = 20, message = "mgniId must less than 20 characters")
    private String mgniId;
}
