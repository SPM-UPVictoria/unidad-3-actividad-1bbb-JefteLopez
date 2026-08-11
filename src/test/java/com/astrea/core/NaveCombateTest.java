package com.astrea.core;
import com.astrea.core.base.*;
import com.astrea.core.naves.*;
import com.astrea.core.exceptions.*;
import org.junit.Test;
import static org.junit.Assert.*;
public class NaveCombateTest {
    @Test
    public void testViajeEstandar() throws AstreaException {
        NaveCombate nave = new NaveCombate("W-01", "Vanguardia", 100.0, 500.0, 40.0);
        nave.viajar(10.0);
        assertEquals(80.0, nave.getCombustible(), 0.001);
    }
    @Test
    public void testRecibirImpactoLeve() throws AstreaException {
        NaveCombate nave = new NaveCombate("W-02", "Vanguardia", 100.0, 500.0, 40.0);
        nave.recibirImpacto(50.0);
        assertEquals(150.0, nave.getIntegridadEscudo(), 0.001);
    }
    @Test(expected = EscudoCriticoException.class)
    public void testRecibirImpactoCritico() throws AstreaException {
        NaveCombate nave = new NaveCombate("W-03", "Vanguardia", 100.0, 500.0, 40.0);
        nave.recibirImpacto(250.0);
    }
    @Test
    public void testAtacarObjetivo() throws AstreaException {
        NaveCombate atacante = new NaveCombate("W-04", "Agresor", 100.0, 500.0, 60.0);
        NaveCombate objetivo = new NaveCombate("W-05", "Defensor", 100.0, 500.0, 40.0);
        atacante.atacar(objetivo);
        assertEquals(85.0, atacante.getCombustible(), 0.001);
        assertEquals(140.0, objetivo.getIntegridadEscudo(), 0.001);
    }
    @Test(expected = CombustibleInsuficienteException.class)
    public void testAtacarSinCombustible() throws AstreaException {
        NaveCombate atacante = new NaveCombate("W-06", "Agresor", 10.0, 500.0, 60.0);
        NaveCombate objetivo = new NaveCombate("W-07", "Defensor", 100.0, 500.0, 40.0);
        atacante.atacar(objetivo);
    }
}
