package com.product.school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentsResponseDto {
    private long id;
    private String fristName;
    private String lastName;
    private String midName;
    private int phoneNumber;
    private long studentid;
}
