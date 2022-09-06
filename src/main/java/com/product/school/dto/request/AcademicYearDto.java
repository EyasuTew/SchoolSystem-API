package com.product.school.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Eyasu Tewodros
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcademicYearDto {
    private String year;
    private Boolean isCurrent;
    private Boolean isActive;
}