package controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import bean.GestisciEventiCaritasBoundary;
import dao.CaritasRepository;
import dao.EventoDao;
import dao.ShopRepository;
import entity.CaritasUser;
import entity.EventTab;
import entity.ShopUser;
import exception.MyIOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public class GestisciEventiCaritasController {

	private EventoDao eventDao;

	public List<EventTab> caricaEventi(int idCaritas) {

		return eventDao.cercaEventiCaritas(idCaritas);

	}
	
	
	public boolean confermaEvento(int idEvento) {
		return eventDao.confermaEvento(idEvento);
	}

	public GestisciEventiCaritasController() {
		eventDao = new EventoDao();
	}
	
	public void switchPage(Window stage, int idCar) {
		Logger logger = LoggerFactory.getLogger(GestisciEventiCaritasController.class.getName());
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/boundary/gestisci_eventi_caritas.fxml"));
			Parent root = loader.load();

			Stage home = (Stage) stage;
			home.setScene(new Scene(root, 800, 500));
			home.show();

			GestisciEventiCaritasBoundary gest = loader.getController();
			gest.loadShop(idCar);

		} catch (Exception e) {
			logger.error(e.getMessage());
			MyIOException.openPageFault("Gestisci Eventi Caritas");
		}
	}

	public boolean cancellaEvento(int idEvento) {
		return eventDao.cancellaEvento(idEvento);
	}

	public void creaEventoGeneral(String nomeEvento, String tipo,Float prezzo, String noteEvento , int idCar, int idNeg ) {
		CaritasRepository caritasdao = new CaritasRepository();
		ShopRepository shopDao = new ShopRepository();
		CaritasUser caritas = caritasdao.getCaritasByID(idCar);
		ShopUser shop = shopDao.getShopByID(idNeg);
		EventTab evento = new EventTab(caritas.getId(),tipo,shop.getId(),nomeEvento, caritas.getNome(), noteEvento, prezzo);
		this.eventDao.creaEvento(evento);
	}
}
