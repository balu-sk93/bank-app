package com.bank.app.service.controllers.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bank.app.service.model.Response;

@ControllerAdvice
public class CommonHandler {

	private static final Logger logger = LoggerFactory.getLogger(CommonHandler.class);

	@ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Response> handleException(MethodArgumentNotValidException exception) {
        String message = "";
        Response response;
        try {
            message = exception.getBindingResult().getFieldErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage).findFirst().orElse(exception.getMessage());

        } catch (Exception e) {
            message = "Invalid Request.";
        }
        response = new Response("Error", message);
        logger.error("Exception : Message : " + message);
        return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler({ BadCredentialsException.class })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Response> handleAuthException(BadCredentialsException exception) {
        String message = "";
        Response response;
        try {
            message = exception.getMessage();
        } catch (Exception e) {
            message = "Invalid Request.";
        }
        response = new Response("Error", message);
        logger.error("Exception : Message : " + message);
        return new ResponseEntity<Response>(response, HttpStatus.UNAUTHORIZED);
    }	
	
}
