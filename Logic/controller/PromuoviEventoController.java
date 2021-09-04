package controller;

import dao.CaritasRepository;
import dao.EventoDao;
import dao.ShopRepository;
import entity.CaritasUser;
import entity.EventTab;
import entity.ShopUser;
import javafx.stage.Stage;
import javafx.stage.Window;

public class PromuoviEventoController {
	
	private EventoDao eventDao;
	
	
	
	public PromuoviEventoController() {
		eventDao = new EventoDao();
		
	}
	
	public void promuoviEvento(String nome, String tipo, String note, Float prezzo, int idCar, int idShop, Window conferma) {
		creaEventoController(nome, tipo, note, prezzo, idCar, idShop);
		Stage st = (Stage) conferma;
		st.close();		
	}
	

	public void creaEventoController(String nomeEvento, String tipo, String noteEvento, float prezzoEvento, int idCar, int idUser) {
		
		
		CaritasRepository caritasdao = new CaritasRepository();
		ShopRepository shopDao = new ShopRepository();
		CaritasUser caritas = caritasdao.getCaritasByID(idCar);
		ShopUser shop = shopDao.getShopByID(idUser);
		EventTab evento = new EventTab(caritas.getId(),tipo,shop.getId(),nomeEvento, caritas.getNome(), noteEvento, prezzoEvento);
		this.eventDao.creaEvento(evento);
		
	}
	
}
