package test;

import controller.GestioneTurniCaritas;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
//Ricardo Buraioli
public class TestModificaTurno {
	
	private GestioneTurniCaritas gestioneTurni;
	
	public TestModificaTurno() {
		gestioneTurni = new GestioneTurniCaritas();
	}
	
	@Test
	public void checkModificaTurno() {
		assertEquals(true, gestioneTurni.modificaTurno(11,"test", 17));
		
	}
}
