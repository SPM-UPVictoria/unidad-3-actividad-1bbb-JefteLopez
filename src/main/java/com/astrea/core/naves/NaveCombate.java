package com.astrea.core.naves;

import com.astrea.core.base.NaveEspacial;
import com.astrea.core.exceptions.AstreaException;
import com.astrea.core.exceptions.EscudoCriticoException;

public class NaveCombate extends NaveEspacial {

    private static final double CONSUMO_VIAJE = 2.0;
    private static final double CONSUMO_ATAQUE = 15.0;
    private static final double FACTOR_ESCUDO_INICIAL = 0.4;

    private final double potenciaArma;
    private double integridadEscudo;

    public NaveCombate(String matricula, String modelo, double combustible,
                        double capacidadCombustible, double potenciaArma) throws AstreaException {
        super(matricula, modelo, combustible, capacidadCombustible);
        this.potenciaArma = potenciaArma;
        this.integridadEscudo = capacidadCombustible * FACTOR_ESCUDO_INICIAL;
    }

    public double getPotenciaArma() {
        return potenciaArma;
    }

    public double getIntegridadEscudo() {
        return integridadEscudo;
    }

    @Override
    public void viajar(double distancia) throws AstreaException {
        consumirCombustible(CONSUMO_VIAJE * distancia);
    }

    public void recibirImpacto(double dano) throws EscudoCriticoException {
        if (integridadEscudo - dano < 0) {
            throw new EscudoCriticoException(
                    "El impacto de " + dano + " deja el escudo por debajo de cero");
        }
        integridadEscudo -= dano;
    }

    public void atacar(NaveCombate objetivo) throws AstreaException {
        consumirCombustible(CONSUMO_ATAQUE);
        objetivo.recibirImpacto(this.potenciaArma);
    }
}
