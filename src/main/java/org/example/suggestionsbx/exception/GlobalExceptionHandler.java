package org.example.suggestionsbx.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.example.suggestionsbx.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(SuggestionNotFoundException.class)
  public ResponseEntity<ErrorResponseDTO> handleNotFound(
      SuggestionNotFoundException ex, HttpServletRequest request) {
    ErrorResponseDTO error =
        ErrorResponseDTO.of(
            HttpStatus.NOT_FOUND.value(),
            HttpStatus.NOT_FOUND.getReasonPhrase(),
            ex.getMessage(),
            request.getRequestURI());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }

  @ExceptionHandler(AlreadyRatedException.class)
  public ResponseEntity<ErrorResponseDTO> handleConflict(
      AlreadyRatedException ex, HttpServletRequest request) {
    ErrorResponseDTO error =
        ErrorResponseDTO.of(
            HttpStatus.CONFLICT.value(),
            HttpStatus.CONFLICT.getReasonPhrase(),
            ex.getMessage(),
            request.getRequestURI());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorResponseDTO> handleBadRequest(
      IllegalArgumentException ex, HttpServletRequest request) {
    ErrorResponseDTO error =
        ErrorResponseDTO.of(
            HttpStatus.BAD_REQUEST.value(),
            HttpStatus.BAD_REQUEST.getReasonPhrase(),
            ex.getMessage(),
            request.getRequestURI());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseDTO> handleGenericException(
      Exception ex, HttpServletRequest request) {
    ErrorResponseDTO error =
        ErrorResponseDTO.of(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "An unexpected internal server error occurred",
            request.getRequestURI());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
  }
}
