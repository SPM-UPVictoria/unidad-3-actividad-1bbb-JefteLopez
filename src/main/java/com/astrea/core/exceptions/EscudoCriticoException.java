package com.astrea.core.exceptions;

/**
 * Se lanza cuando un impacto recibido dejaría la integridad
 * del escudo por debajo de cero (daño mayor a la integridad actual).
 */
public class EscudoCriticoException extends AstreaException {

    public EscudoCriticoException(String mensaje) {
        super(mensaje);
    }
}
