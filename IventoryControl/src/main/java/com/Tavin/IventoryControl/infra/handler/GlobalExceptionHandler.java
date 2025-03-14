package com.Tavin.IventoryControl.infra.handler;

import com.Tavin.IventoryControl.infra.dtos.errors.FieldErrors;
import com.Tavin.IventoryControl.infra.exceptions.ResourceNotFoundException;
import com.Tavin.IventoryControl.infra.dtos.errors.ResponseError;
import com.Tavin.IventoryControl.infra.exceptions.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseError handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        List<FieldError> errorsField = exception.getFieldErrors();
        List<FieldErrors> errorsCollect = errorsField
                .stream()
                .map(fe -> new FieldErrors(fe.getField(), fe.getDefaultMessage()))
                .toList();
        return new ResponseError("Validation Error", HttpStatus.UNPROCESSABLE_ENTITY.value(), errorsCollect);
    }

    @ExceptionHandler(org.apache.coyote.BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BadRequestException handleBadRequest(org.apache.coyote.BadRequestException exception) {
        return new BadRequestException(exception.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResourceNotFoundException handleResourceNotFoundException(ResourceNotFoundException exception) {
        return new ResourceNotFoundException(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseError handleInternalException(RuntimeException exception) {
        return  new ResponseError(
                "An unexpected error has occurred, please contact the administration",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                List.of());
    }
}
