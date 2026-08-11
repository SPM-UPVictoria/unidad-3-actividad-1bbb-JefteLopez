package com.astrea.core;
import com.astrea.core.base.*;
import com.astrea.core.naves.*;
import com.astrea.core.exceptions.*;
import org.junit.Test;
import static org.junit.Assert.*;
public class NaveExploracionTest {
    @Test
    public void testViajeEstandar() throws AstreaException {
        NaveExploracion nave = new NaveExploracion("E-01", "Pionero", 100.0, 300.0);
        nave.viajar(10.0);
        assertEquals(92.0, nave.getCombustible(), 0.001);
    }
    @Test
    public void testHiperviajeSeguro() throws AstreaException {
        NaveExploracion nave = new NaveExploracion("E-02", "Pionero", 100.0, 300.0);
        nave.activarHiperviaje(8.0);
        assertEquals(50.0, nave.getCombustible(), 0.001);
        assertTrue(nave.isHiperviajeListo());
    }
    @Test
    public void testHiperviajeSinCombustible() throws AstreaException {
        NaveExploracion nave = new NaveExploracion("E-03", "Pionero", 40.0, 300.0);
        try {
            nave.activarHiperviaje(5.0);
            fail("Debió lanzar CombustibleInsuficienteException");
        } catch (CombustibleInsuficienteException e) {
            assertEquals(40.0, nave.getCombustible(), 0.001);
        }
    }
    @Test
    public void testHiperviajeCriticoFalla() throws AstreaException {
        NaveExploracion nave = new NaveExploracion("E-04", "Pionero", 300.0, 300.0);
        boolean falloDetectado = false;
        for (int i = 0; i < 50; i++) {
            try {
                nave.activarHiperviaje(10.0);
                nave.repostarCombustible(50.0);
            } catch (FallaSistemasException e) {
                falloDetectado = true;
                assertFalse(nave.isHiperviajeListo());
                break;
            }
        }
        assertTrue("En 50 intentos debió fallar al menos 1 vez con 30% de probabilidad", falloDetectado);
    }
}
