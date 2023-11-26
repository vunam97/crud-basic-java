package com.vti.exam.configuration.exception;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
    // Lỗi chung
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception exception) {
        String message = "Lỗi server";
        String detailMessage = exception.getLocalizedMessage();
        ErrorResponse response = new ErrorResponse(1, message, detailMessage);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Lỗi đường dẫn không đúng
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException exception,
            HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        String message = "Đường dẫn không chính xác";
        String detailMessage = exception.getLocalizedMessage();
        ErrorResponse response = new ErrorResponse(2, message, detailMessage);
        return new ResponseEntity<>(response, headers, status);
    }

    // Lỗi không hỗ trợ method (Eg: hỗ trợ POST không hỗ trợ PUT)
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException exception,
            HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        String message = "Không hỗ trợ chức năng này";
        String detailMessage = exception.getLocalizedMessage();
        ErrorResponse response = new ErrorResponse(3, message, detailMessage);
        return new ResponseEntity<>(response, headers, status);
    }

    // Lỗi truyền body không đúng định dạng (Eg: phải truyền json nhưng lại truyền text)
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException exception,
            HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        String message = "Truyền không đúng định dạng";
        String detailMessage = exception.getLocalizedMessage();
        ErrorResponse response = new ErrorResponse(4, message, detailMessage);
        return new ResponseEntity<>(response, headers, status);
    }

    // Lỗi valid
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        String message = "Dữ liệu không hợp lệ";
        String detailMessage = exception.getLocalizedMessage();
        Map<String, String> errors = new HashMap<>();

        for (FieldError error : exception.getFieldErrors()) {
            String key = error.getField();
            String value = error.getDefaultMessage();
            errors.put(key, value);
        }
        ErrorResponse response = new ErrorResponse(5, message, detailMessage, errors);
        return new ResponseEntity<>(response, headers, status);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException exception) {
        String message = "Dữ liệu không hợp lệ";
        String detailMessage = exception.getLocalizedMessage();
        Map<String, String> errors = new HashMap<>();
        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            String key = violation.getPropertyPath().toString();
            String value = violation.getMessage();
            errors.put(key, value);
        }
        ErrorResponse response = new ErrorResponse(6, message, detailMessage, errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Lỗi thiếu tham số (parameter)
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException exception,
            HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        String message = "Thiếu tham số parameter";
        String detailMessage = exception.getLocalizedMessage();
        ErrorResponse response = new ErrorResponse(7, message, detailMessage);
        return new ResponseEntity<>(response, headers, status);
    }

    // Sai kiểu truyền dữ liệu
    @Override
    protected ResponseEntity<Object> handleTypeMismatch(
            TypeMismatchException exception,
            HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        if (exception instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException ex = (MethodArgumentTypeMismatchException) exception;
            Class<?> requiredType = ex.getRequiredType();
            String message = "Dữ liệu truyền không chính xác";
            String detailMessage = exception.getLocalizedMessage();
            ErrorResponse response = new ErrorResponse(8, message, detailMessage);
            return new ResponseEntity<>(response, headers, status);
        }
        return super.handleTypeMismatch(exception, headers, status, request);
    }
}
