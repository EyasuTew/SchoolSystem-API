package com.product.school.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentsRequestDto {

    private String fristName;
    private String lastName;
    private String midName;
    private int phoneNumber;
    private long studentid;
}
