package com.astrea.core.base;

import com.astrea.core.exceptions.AstreaException;
import com.astrea.core.exceptions.CombustibleInsuficienteException;

/**
 * Clase base abstracta para todas las naves del sistema Astrea.
 */
public abstract class NaveEspacial {

    private final String matricula;
    private final String modelo;
    private double combustible;
    private final double capacidadCombustible;

    protected NaveEspacial(String matricula, String modelo, double combustible,
                            double capacidadCombustible) throws AstreaException {
        if (capacidadCombustible < 0) {
            throw new AstreaException("La capacidad de combustible no puede ser negativa");
        }
        if (combustible < 0) {
            throw new AstreaException("El combustible no puede ser negativo");
        }
        if (combustible > capacidadCombustible) {
            throw new AstreaException("El combustible inicial excede la capacidad del tanque");
        }

        this.matricula = matricula;
        this.modelo = modelo;
        this.combustible = combustible;
        this.capacidadCombustible = capacidadCombustible;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public double getCombustible() {
        return combustible;
    }

    public double getCapacidadCombustible() {
        return capacidadCombustible;
    }

    /**
     * Agrega combustible al tanque, validando límites.
     */
    public void repostarCombustible(double cantidad) throws AstreaException {
        if (cantidad < 0) {
            throw new AstreaException("La cantidad a repostar no puede ser negativa");
        }
        if (combustible + cantidad > capacidadCombustible) {
            throw new AstreaException("El repostaje excede la capacidad del tanque");
        }
        combustible += cantidad;
    }

    /**
     * Descuenta combustible. Si no alcanza, NO se descuenta nada
     * y se lanza CombustibleInsuficienteException.
     */
    protected void consumirCombustible(double cantidad) throws CombustibleInsuficienteException {
        if (cantidad > combustible) {
            throw new CombustibleInsuficienteException(
                    "Combustible insuficiente: se requieren " + cantidad
                            + " y solo hay " + combustible);
        }
        combustible -= cantidad;
    }

    /**
     * Cada tipo de nave define su propio consumo de combustible al viajar.
     */
    public abstract void viajar(double distancia) throws AstreaException;
}
