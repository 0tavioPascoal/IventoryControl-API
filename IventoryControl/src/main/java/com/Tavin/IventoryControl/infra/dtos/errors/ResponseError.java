package com.Tavin.IventoryControl.infra.dtos.errors;

import org.springframework.http.HttpStatus;


import java.util.List;

public record ResponseError(String message, int statusCode, List<FieldErrors> errors) {


    public static ResponseError standardAnswer(String message) {
        return new ResponseError(message, HttpStatus.BAD_REQUEST.value(), List.of());
    }


}
