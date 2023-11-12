package com.abhishek.exceptions;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomError {
    private String errorCode;
    private String errorMessage;
}
