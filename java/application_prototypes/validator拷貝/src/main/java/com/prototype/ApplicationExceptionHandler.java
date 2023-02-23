package com.prototype;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public final class ApplicationExceptionHandler
	extends ResponseEntityExceptionHandler
{
    private static final Logger logger = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<Object>
        handleException(RuntimeException ex)
    {
        logger.error(	ex.getMessage(),
                        ex);

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}