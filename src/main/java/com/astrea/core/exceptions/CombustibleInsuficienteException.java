package com.astrea.core.exceptions;

/**
 * Se lanza cuando una nave no tiene combustible suficiente
 * para completar una operación (viajar, atacar, hiperviaje, etc.).
 * El combustible NO debe descontarse cuando se lanza esta excepción.
 */
public class CombustibleInsuficienteException extends AstreaException {

    public CombustibleInsuficienteException(String mensaje) {
        super(mensaje);
    }
}
