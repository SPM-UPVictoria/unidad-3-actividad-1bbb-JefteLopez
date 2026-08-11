package com.astrea.core.exceptions;

/**
 * Excepción base para todos los errores del dominio Astrea.
 * Es checked (extiende Exception) porque los métodos de negocio
 * la declaran explícitamente con "throws".
 */
public class AstreaException extends Exception {

    public AstreaException(String mensaje) {
        super(mensaje);
    }

    public AstreaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
