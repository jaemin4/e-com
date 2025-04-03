package com.ecom.exception;

import com.ecom.response.RestError;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RestError handleValidationExceptions(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }


        return new RestError("validation_error", "유효성 검사 실패", errors);
    }

    @ExceptionHandler(PointRuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public RestError handlePointRuntimeException(PointRuntimeException e) {
        return new RestError("PointRunTimeException", e.getMessage());
    }

    @ExceptionHandler(CouponRuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public RestError handleCouponRuntimeException(CouponRuntimeException e) {
        return new RestError("CouponRuntimeException", e.getMessage());
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public RestError handleNoHandlerFoundException(NoHandlerFoundException e) {
        return new RestError("not_found", "요청한 URL을 찾을 수 없습니다.");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED) // 405
    public RestError handleMethodNotAllowed(HttpRequestMethodNotSupportedException e) {
        return new RestError("method_not_allowed", "허용되지 않은 HTTP 메서드입니다.");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    public RestError handleGlobalException(Exception e) {
        return new RestError("exception", "서버 내부 오류가 발생했습니다.");
    }

}
