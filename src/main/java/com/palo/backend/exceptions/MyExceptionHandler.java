//package com.palo.backend.exceptions;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
////druhy sposob osefovania exception
//@ControllerAdvice
//public class MyExceptionHandler {
//
//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
//
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
//    }
//}
