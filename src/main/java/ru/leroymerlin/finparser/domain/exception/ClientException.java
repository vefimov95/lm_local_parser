package ru.leroymerlin.finparser.domain.exception;

import lombok.extern.slf4j.Slf4j;

import java.io.PrintWriter;
import java.io.StringWriter;

@Slf4j
public class ClientException extends RuntimeException{
    public ClientException(String message) {
        super(message);
        printLog();
    }

    public void printLog () {
        StringWriter trace = new StringWriter();
        printStackTrace(new PrintWriter(trace));
        log.error(trace.toString());
    }
}
