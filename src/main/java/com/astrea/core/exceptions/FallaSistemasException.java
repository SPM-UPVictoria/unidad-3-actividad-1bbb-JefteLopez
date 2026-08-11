package com.astrea.core.exceptions;

/**
 * Se lanza cuando un hiperviaje "crítico" (factor > 9.0) falla
 * por la probabilidad aleatoria del 30%.
 */
public class FallaSistemasException extends AstreaException {

    public FallaSistemasException(String mensaje) {
        super(mensaje);
    }
}
