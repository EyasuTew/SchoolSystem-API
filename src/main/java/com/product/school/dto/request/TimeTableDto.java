package com.product.school.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class TimeTableDto {
    private String name;
    private Date startFrom;
    private Date endTo;
    private Long sectionId;
    private Long subjectId;
    private Long academicYearId;
}
