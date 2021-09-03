package beanweb;


import controller.DonationController;


public class DonationBoundary {
	
	private static DonationBoundary instance = null;
	private DonationController controller;
	private int utente;
	private int idCar;

	
	public static DonationBoundary getInstance() {
		if(instance == null) {
			instance = new DonationBoundary();
		}
		return instance;
		}
	
	
	
	private DonationBoundary() {
		controller = new DonationController();
	}
	
	
	
	public boolean creaDonazione(int tipo, String indirizzo,String descrizione){
		if ( indirizzo == null || indirizzo.equals("")) {
			return false;
		}
		else {
			controller.creaDonazione2( tipo , indirizzo, descrizione,this.utente,this.idCar);
			return true;
		}
	}
	



	

	public void initBoundary(int idCar, int idUte) {
		this.utente = idUte;
		this.idCar = idCar;
		controller.initController(idCar, idUte);
	}

}

