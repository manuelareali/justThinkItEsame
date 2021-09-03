package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import controller.CreaTurnoController;
//Manuela Reali
public class TestTurno {
	private CreaTurnoController creaT;
	
	public TestTurno() {
		creaT = new CreaTurnoController();
	}
	
	@Test
	public void testCreaTurno() {
		assertEquals(false, creaT.creaTurno(17, "Lunedi", "10:00", "15:00", 3, "Giggi è malato"));
	}
	
	
}
