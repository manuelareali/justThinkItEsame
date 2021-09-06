package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import controller.CreaNecessitaController;

//Manuela Reali
public class TestNecessita {
	CreaNecessitaController creaNece = new CreaNecessitaController();
	

	
	@Test
	public void testNece() {
		this.creaNece.inizializza(17);
		assertEquals(true, this.creaNece.creaNecessita("Cibo", "Alta", "Voglio il latte"));
	
	}
	

}
