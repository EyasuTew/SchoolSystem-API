package com.product.school.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonsRequestDto {
    private String fristName;
    private String midName;
    private String lastName;
    private String phoneNumber;
    private String subcity;
    private String kebele;
    private String gender;
    private String emailAddress;
}
