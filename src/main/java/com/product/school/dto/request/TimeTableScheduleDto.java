package com.product.school.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class TimeTableScheduleDto {
    private String name;
    private Date startFrom;
    private Date endTo;
    private Long timeTimeId;
}
