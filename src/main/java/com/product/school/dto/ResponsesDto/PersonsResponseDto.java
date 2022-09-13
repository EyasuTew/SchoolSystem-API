package com.product.school.dto.ResponsesDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonsResponseDto {
    private Long id;
    private String fristName;
    private String midName;
    private String lastName;
    private String phoneNumber;
    private String subcity;
    private String kebele;
    private String gender;
    private String emailAddress;
    private Date updatedOn;
    private Date createdOn;
}
