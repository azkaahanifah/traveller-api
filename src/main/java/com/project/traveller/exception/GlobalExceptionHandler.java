package com.project.traveller.exception;

import com.project.traveller.model.GeneralErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<GeneralErrorResponse> handleBusinessException(BusinessException exception, WebRequest request) {
        LOG.error("Failed to process request for endpoint: {} due to error: {} at: {}",
                request.getDescription(false), exception.getMessage(), LocalDateTime.now());

        GeneralErrorResponse errorResponse = errorResponse(exception,
                request, HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<GeneralErrorResponse> handleNullPointerException(NullPointerException exception, WebRequest request) {
        LOG.error("Error occurred for endpoint: {} due to error: {} at: {}",
                request.getDescription(false), exception.getMessage(), LocalDateTime.now());

        GeneralErrorResponse errorResponse = errorResponse(exception,
                request, HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private GeneralErrorResponse errorResponse(RuntimeException exception,
                                               WebRequest request, int httpStatus,
                                               String reasonPhrases) {
        return GeneralErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(httpStatus)
                .error(reasonPhrases)
                .message(exception.getMessage())
                .path(request.getDescription(false))
                .build();
    }
}
