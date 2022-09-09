package com.product.school.dto;

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
public class ResponseDto {
    private boolean status;
    private String message;
}
