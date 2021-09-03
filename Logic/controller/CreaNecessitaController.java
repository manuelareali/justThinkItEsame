package controller;

import dao.BachecaDao;
import entity.Necessita;

public class CreaNecessitaController {
	private BachecaDao creaBachecaDao;
	private static CreaNecessitaController instance = null;
	private int idCaritas;
	
	public static CreaNecessitaController getInstace() {
		if(instance == null) {
			instance = new CreaNecessitaController ();
		}
		return instance;
		}
	
		
	
	
	
	public CreaNecessitaController() {
		creaBachecaDao = new BachecaDao();
		
	}
	
	
	public boolean creaNecessita(String tipo, String urg, String desc) {
		
		Necessita necessita = new Necessita(tipo, desc, urg);
		return creaBachecaDao.creaNecessita(necessita, idCaritas);
		
	}
	
	
	
	public void inizializza(int codCar) {
		this.idCaritas = codCar;
	}
	
}
