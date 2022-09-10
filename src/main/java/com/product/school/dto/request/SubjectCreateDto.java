package com.product.school.dto.request;

import lombok.Data;

@Data
public class SubjectCreateDto {
    private String name;
    private Long gradeId;
    private Long generalSubjectId;
}
