package com.astrea.core.naves;

import com.astrea.core.base.NaveEspacial;
import com.astrea.core.exceptions.AstreaException;

public class NaveCarga extends NaveEspacial {

    private static final double CONSUMO_LIGERO = 1.5;
    private static final double CONSUMO_PESADO = 3.0;

    private final double capacidadCarga;
    private double cargaActual;

    public NaveCarga(String matricula, String modelo, double combustible,
                      double capacidadCombustible, double capacidadCarga) throws AstreaException {
        super(matricula, modelo, combustible, capacidadCombustible);
        if (capacidadCarga < 0) {
            throw new AstreaException("La capacidad de carga no puede ser negativa");
        }
        this.capacidadCarga = capacidadCarga;
        this.cargaActual = 0.0;
    }

    public double getCapacidadCarga() {
        return capacidadCarga;
    }

    public double getCargaActual() {
        return cargaActual;
    }

    public void cargar(double cantidad) throws AstreaException {
        if (cantidad < 0) {
            throw new AstreaException("La cantidad de carga no puede ser negativa");
        }
        if (cargaActual + cantidad > capacidadCarga) {
            throw new AstreaException("La carga excede la capacidad de carga de la nave");
        }
        cargaActual += cantidad;
    }

    @Override
    public void viajar(double distancia) throws AstreaException {
        double tasa = (cargaActual > capacidadCarga * 0.5) ? CONSUMO_PESADO : CONSUMO_LIGERO;
        consumirCombustible(tasa * distancia);
    }
}
