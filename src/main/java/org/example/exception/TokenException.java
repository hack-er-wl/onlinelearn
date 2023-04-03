package org.example.exception;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class TokenException extends RuntimeException{
    private String exception;
    public TokenException(String exception){
        this.exception = exception;
        log.info(this.exception);
    }
}
