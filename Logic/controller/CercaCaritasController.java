package controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import bean.BachecaBoundary;
import bean.DonationBoundary;
import bean.PartecipaEventoBoundary;
import bean.PrenotaTurnoBoundary;
import bean.PromuoviEventoBoundary;
import dao.CercaCaritasDao;
import dao.CoordinateDao;
import dao.UserDao;
import entity.CoordinateMap;
import entity.MarkerID;
import exception.MyIOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class CercaCaritasController {
	private static final Logger logger = LoggerFactory.getLogger(CercaCaritasController.class);

	private CercaCaritasDao cercaCaritasD;
	private CoordinateDao coord;
	private UserDao v;

	
	
	public CercaCaritasController() {
		coord = new CoordinateDao();
		cercaCaritasD = new CercaCaritasDao();
		v = new UserDao();
	}

	
	public void initMap2(int id, String lati, String longi) {
		coord.setCoordinate(id,lati,longi);
	}
	
	public void promuoviEvento(int idCar, int idShop) {
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();

			Parent rootNode = fxmlLoader.load(getClass().getResourceAsStream("/boundary/PromuoviEvento.fxml"));

			Stage stage = new Stage();
			stage.setTitle("Promuovi Evento");

			stage.setScene(new Scene(rootNode, 600, 400));
			stage.setResizable(false);

			PromuoviEventoBoundary promEvento = fxmlLoader.getController();

			promEvento.loadFormBoundary(idCar, idShop);

			stage.show();

		}  catch (Exception e) {
			logger.error(e.getMessage());
			MyIOException.openPageFault("Promuovi Evento");
		}
	

	}


	public void vediNecessita(int idCar, int idUt) {

		try {
			FXMLLoader fxmlLoader = new FXMLLoader();

			Parent rootNode = fxmlLoader.load(getClass().getResourceAsStream("/boundary/Bacheca.fxml"));

			Stage stage = new Stage();
			stage.setTitle("Bacheca");

			stage.setScene(new Scene(rootNode, 700, 450));
			stage.setResizable(false);

			BachecaBoundary bacheca = fxmlLoader.getController();

			bacheca.loadFormBoundary(idCar, idUt);

			stage.show();

		}  catch (Exception e) {
			logger.error(e.getMessage());
			MyIOException.openPageFault("Bacheca");
		}

	}

	public void apriDonazione(int idCar, int idUt) {
		try {

			FXMLLoader fxmlLoader = new FXMLLoader();
			Parent rootNode = fxmlLoader.load(getClass().getResourceAsStream("/boundary/Donation.fxml"));

			DonationBoundary donationBoundary = fxmlLoader.getController();

			Stage stage = new Stage();
			stage.setTitle("Donazione");

			donationBoundary.initBoundary(idCar, idUt);

			stage.setScene(new Scene(rootNode, 800, 500));
			stage.setResizable(false);
			stage.show();

		} catch (Exception e) {
			logger.error(e.getMessage());
			MyIOException.openPageFault("Donation");
		}

	}

	public void prenotaTurno(int idCar, int idUt) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			Parent rootNode = fxmlLoader.load(getClass().getResourceAsStream("/boundary/Prenota_turno_volontariato.fxml"));
			PrenotaTurnoBoundary prenotaController = fxmlLoader.getController();

			Stage stage = new Stage();
			stage.setTitle("Prenota Turno");
			prenotaController.setData(idCar, idUt);
			stage.setScene(new Scene(rootNode, 630, 400));
			stage.setResizable(false);
			stage.show();

		} catch (Exception e) {
			logger.error(e.getMessage());
			MyIOException.openPageFault("Prenota Turno Volontario");
		}

	}

	public void partecipaEvento(int idEvent, int idUt) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			Parent rootNode = fxmlLoader.load(getClass().getResourceAsStream("/boundary/Partecipa_evento.fxml"));

			PartecipaEventoBoundary partecipaEvent = fxmlLoader.getController();

			Stage stage = new Stage();
			stage.setTitle("Prenota Turno");
			partecipaEvent.setData(idEvent, idUt);
			stage.setScene(new Scene(rootNode, 600, 400));
			stage.setResizable(false);
			stage.show();
		} catch (Exception e) {
			logger.error(e.getMessage());
			MyIOException.openPageFault("Partecipa Evento");
		}

	}
	
	
	

	public List<MarkerID> initMarkersCaritas(){
		return cercaCaritasD.getCaritasMarkers();
	}
	
	public List<MarkerID> initMarkersNegozio(){
		return cercaCaritasD.assegnaMarkerNegozio();
	}
	
	public List<MarkerID> initMarkersDonazione(int idCaritas){
		return cercaCaritasD.assegnaMarkerDonazione(idCaritas);

	}
	public List<MarkerID> initMarkersEvento(int idCar){
		return cercaCaritasD.assegnaMarkerEvento(idCar);

	}

	public List<MarkerID> initMarkersEvento() {
	
		return cercaCaritasD.assegnaMarkerEvento();
	}


	
	public List<CoordinateMap> initMarkerCar(){
		return cercaCaritasD.getCoordinateCaritas();
	}
	
	public List<CoordinateMap> initMarkerEvent(){
		
		return cercaCaritasD.getCoordinateEvento();
	}
	
	public List<CoordinateMap> initMarkerEvent(int idUser){
		
		return cercaCaritasD.getCoordinateEvento(idUser);
	}
	
	public List<CoordinateMap> initMarkerDonation(int idUser){
		return cercaCaritasD.getCoordinateDonazione(idUser);
	}
	
	public String trovaRuolo(int idUser) {
		return v.trovaTipoUtente(idUser);
	}
	
	public void initUser(int idUser, Object cercaBean){
	
		String ruoloUser = v.trovaTipoUtente(idUser);
		if(cercaBean.getClass() == bean.CercaCaritas.class) {
			((bean.CercaCaritas) cercaBean).initMarkers(idUser, ruoloUser);
		}
		else if (cercaBean.getClass() == beanweb.CercaCaritas.class) {
				((beanweb.CercaCaritas) cercaBean).setUser(idUser, ruoloUser);
		}
	}


}
