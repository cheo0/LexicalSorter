package org.example.exceptions;

public class SameFileException extends Exception {
    public SameFileException() {
        super("No se pude utilizar el mismo archivo como entrada y salida al mismo tiempo");
    }
}
