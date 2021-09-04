package controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bean.GestisciEventiBoundary;
import bean.GestisciEventiPropCaritas;
import dao.EventoDao;
import entity.EventTab;
import exception.MyIOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public class GestisciEventiController {
	
	Logger logger = LoggerFactory.getLogger(GestisciEventiController.class.getName());

	private EventoDao eventDao;

	
	
	public GestisciEventiController() {
	
		eventDao = new EventoDao();
	}
	
	public List<EventTab> caricaEventiPropCaritas() {
		return this.eventDao.cercaEventiPropCaritas();

	}
	
	
	public List<EventTab> caricaEventi(int idShop){
		return eventDao.cercaEventi(idShop);	
	}
	
	public boolean confermaEvento(int idEvento) {
		return eventDao.confermaEvento(idEvento);
	}
	
	public boolean cancellaEvento(int evento){
		return eventDao.cancellaEvento(evento);
	}
	
	public void gestisciProposte(Window gestEvent, int idShop){
    	try {

			FXMLLoader fxmlLoader = new FXMLLoader();
			Parent rootNode = fxmlLoader.load(getClass().getResourceAsStream("/boundary/EventiPropNeg.fxml"));

			GestisciEventiPropCaritas gestisciProp = fxmlLoader.getController();
			Stage stage = (Stage) gestEvent;
			stage.setTitle("Gestisci Eventi Proposti dalle Caritas");
			gestisciProp.loadShop(idShop);
			stage.setScene(new Scene(rootNode, 800, 500));
			stage.setResizable(false);
			stage.show();

		}catch (Exception e) {
			logger.error(e.getMessage());
			MyIOException.openPageFault("Shope event propose");
		}
	}
		
	
	public void gestEventi(Window gestEvent, int idShop) {
		try {

			FXMLLoader fxmlLoader = new FXMLLoader();
			Parent rootNode = fxmlLoader.load(getClass().getResourceAsStream("/boundary/Eventi2.fxml"));

			GestisciEventiBoundary gestisciBoundary = fxmlLoader.getController();
			Stage stage = (Stage) gestEvent;
			stage.setTitle("Gestisci Eventi");
			gestisciBoundary.loadShop(idShop);
			stage.setScene(new Scene(rootNode, 800, 500));
			stage.setResizable(false);
			stage.show();

		} catch (Exception e) {
			logger.error(e.getMessage());
			MyIOException.openPageFault("shop Event");
		}
	}
	
}
