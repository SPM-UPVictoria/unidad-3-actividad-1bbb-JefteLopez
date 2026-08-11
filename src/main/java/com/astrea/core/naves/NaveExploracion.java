package com.astrea.core.naves;

import com.astrea.core.base.NaveEspacial;
import com.astrea.core.exceptions.AstreaException;
import com.astrea.core.exceptions.FallaSistemasException;

public class NaveExploracion extends NaveEspacial {

    private static final double CONSUMO_VIAJE = 0.8;
    private static final double CONSUMO_HIPERVIAJE = 50.0;
    private static final double FACTOR_LIMITE_SEGURO = 9.0;
    private static final double PROBABILIDAD_FALLA = 0.3;

    private boolean hiperviajeListo;

    public NaveExploracion(String matricula, String modelo, double combustible,
                            double capacidadCombustible) throws AstreaException {
        super(matricula, modelo, combustible, capacidadCombustible);
        this.hiperviajeListo = false;
    }

    public boolean isHiperviajeListo() {
        return hiperviajeListo;
    }

    @Override
    public void viajar(double distancia) throws AstreaException {
        consumirCombustible(CONSUMO_VIAJE * distancia);
    }

    public void activarHiperviaje(double factor) throws AstreaException {
        consumirCombustible(CONSUMO_HIPERVIAJE);

        if (factor > FACTOR_LIMITE_SEGURO) {
            if (Math.random() < PROBABILIDAD_FALLA) {
                hiperviajeListo = false;
                throw new FallaSistemasException(
                        "Falla de sistemas durante hiperviaje crítico (factor " + factor + ")");
            }
        }
        hiperviajeListo = true;
    }
}
