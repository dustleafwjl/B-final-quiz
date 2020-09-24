package com.thoughtworks.capability.gtb.finalquiz.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResult {
    private Integer status;
    private String timestamp;
    private String error;
    private String message;
}
