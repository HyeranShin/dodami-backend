package com.soma.dodam.dodami.security;

import com.soma.dodam.dodami.dto.ExceptionDto;
import com.soma.dodam.dodami.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class SecurityControllerAdvice {
    public static final String FIELD = "content-type";

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<ExceptionDto> noResult(NoResultException exception) {
        log.debug("NoResultException is happened!");
        return new ResponseEntity<>(ExceptionDto.toExceptionDto(exception.getField(), exception.getMessage()), HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(NotMatchException.class)
    public ResponseEntity<ExceptionDto> notMatch(NotMatchException exception) {
        log.debug("NotMatchException is happened!");
        return new ResponseEntity<>(ExceptionDto.toExceptionDto(exception.getField(), exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnAuthenticationException.class)
    public ResponseEntity<ExceptionDto> unAuthentication(UnAuthenticationException exception) {
        log.debug("UnAuthenticationException is happened!");
        return new ResponseEntity<>(ExceptionDto.toExceptionDto(exception.getField(), exception.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<ExceptionDto> alreadyExists(AlreadyExistException exception) {
        log.debug("AlreadyExistException is happened!");
        return new ResponseEntity<>(ExceptionDto.toExceptionDto(exception.getField(), exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ExceptionDto>> invalidMethodArgument(MethodArgumentNotValidException exception) {
        log.debug("[MethodArgumentNotValidException] {}", exception.getBindingResult().getAllErrors());
        List<ExceptionDto> exceptionDtos = new ArrayList<>();

        exception.getBindingResult().getAllErrors()
                .forEach(validError -> exceptionDtos.add(ExceptionDto.toExceptionDto(validError)));

        return new ResponseEntity<>(exceptionDtos, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ExceptionDto> httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException exception) {
        log.debug("[HttpMediaTypeNotSupportedException] {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ExceptionDto.builder()
                        .field(FIELD)
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(NotExistException.class)
    public ResponseEntity<ExceptionDto> notExistException(NotExistException exception) {
        log.debug("[NotExistException] {}", exception.getMessage());
        return new ResponseEntity<>(ExceptionDto.toExceptionDto(exception.getField(), exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<ExceptionDto>> constraintViolationException(ConstraintViolationException exception) {
        List<ExceptionDto> exceptionDtos = new ArrayList<>();
        exception.getConstraintViolations()
                .forEach(constraintViolation -> exceptionDtos.add(buildExceptionDto(constraintViolation.getMessage(), constraintViolation.getInvalidValue().toString())));

        return new ResponseEntity<>(exceptionDtos, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidValueException.class)
    public ResponseEntity<ExceptionDto> invalidValue(InvalidValueException exception) {
        log.debug("InvalidValueException is happened!");
        return new ResponseEntity<>(ExceptionDto.toExceptionDto(exception.getField(), exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MultipartException.class, FileUploadBase.FileSizeLimitExceededException.class, java.lang.IllegalStateException.class})
    public ResponseEntity<ExceptionDto> sizeExceeded(MultipartException exception) {
        log.debug("FileSizeLimitExceededException is happened!");
        return new ResponseEntity<>(ExceptionDto.toExceptionDto(
                "photo",
                "사진 업로드 중 에러가 발생했습니다. (" + exception.getMessage() + ")"),
                HttpStatus.PAYLOAD_TOO_LARGE);
    }

    private ExceptionDto buildExceptionDto(String message, String field) {
        return ExceptionDto.builder()
                .message(message)
                .field(field)
                .build();
    }
}
