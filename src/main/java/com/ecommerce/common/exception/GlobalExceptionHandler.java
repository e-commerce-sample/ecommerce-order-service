package com.ecommerce.common.exception;

import com.ecommerce.common.logging.AutoNamingLoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.springframework.http.HttpStatus.valueOf;


@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = AutoNamingLoggerFactory.getLogger();

    @ExceptionHandler(AppException.class)
    @ResponseBody
    public ResponseEntity<?> handleAppException(AppException ex, HttpServletRequest request) {
        logger.error("App error:", ex);
        ErrorRepresentation representation = new ErrorRepresentation(ex, request.getRequestURI());
        return new ResponseEntity<>(representation, new HttpHeaders(), valueOf(ex.getError().getStatus()));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public ResponseEntity<ErrorRepresentation> handleInvalidRequest(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String path = request.getRequestURI();

        Map<String, Object> error = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, fieldError -> {
                    String message = fieldError.getDefaultMessage();
                    return isEmpty(message) ? "无错误提示" : message;
                }));

        logger.error("Validation error for [{}]:{}", ex.getParameter().getParameterType().getName(), error);
        ErrorRepresentation representation = new ErrorRepresentation(new RequestValidationException(error), path);
        return new ResponseEntity<>(representation, new HttpHeaders(), valueOf(representation.getError().getStatus()));
    }


    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ResponseEntity<?> handleGeneralException(Throwable ex, HttpServletRequest request) {
        String path = request.getRequestURI();
        logger.error("Error occurred while access[{}]:", path, ex);
        ErrorRepresentation representation = new ErrorRepresentation(new SystemException(ex), path);
        return new ResponseEntity<>(representation, new HttpHeaders(), valueOf(representation.getError().getStatus()));
    }


}
