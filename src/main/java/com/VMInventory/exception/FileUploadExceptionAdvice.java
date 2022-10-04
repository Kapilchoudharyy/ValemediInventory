package com.VMInventory.exception;

import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.VMInventory.message.ResponseMessage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class  FileUploadExceptionAdvice extends ResponseEntityExceptionHandler {

  @ExceptionHandler(MaxUploadSizeExceededException.class)
  public ResponseEntity<ResponseMessage> handleMaxSizeException(MaxUploadSizeExceededException exc) {
    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("File too large!"));
  }

  @ExceptionHandler(IOException.class)
  public ResponseEntity<String> handleIOException(IOException e){
    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
  }

  @ExceptionHandler(InventoryFetchException.class)
  public ResponseEntity<String> handleInventoryFetchException(InventoryFetchException e){
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
  }

  @ExceptionHandler(InventoryUploadException.class)
  public ResponseEntity<String> handleInventoryUploadException(InventoryFetchException e){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
  }


}
