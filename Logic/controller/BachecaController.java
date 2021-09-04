package controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import bean.DonationBoundary;
import bean.EmailBoundary;
import dao.BachecaDao;
import entity.BachecaEntity;
import entity.Necessita;
import exception.MyIOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class BachecaController  {
	private static Logger logger = LoggerFactory.getLogger(BachecaController.class.getName());

	
	public List<Necessita> loadForm(int idCar) {
		BachecaDao bacheca = new BachecaDao();
		BachecaEntity bachecaEntity = new BachecaEntity();
		List<Necessita>	necessita = bacheca.visualizzaNecessita(idCar);
		bachecaEntity.setNecessita(necessita);
		return bachecaEntity.getNecessita();
			
		
		}
	
	
	public void apriPaginaEmail(int idCar, int idUte) {
		EmailBoundary emailBoundary;

		try {

			FXMLLoader fxmlLoader = new FXMLLoader();

			Parent rootNode = fxmlLoader.load(getClass().getResourceAsStream("/boundary/Email.fxml"));

			emailBoundary = fxmlLoader.getController();

			Stage stage = new Stage();
			stage.setTitle("Email");

			stage.setScene(new Scene(rootNode, 800, 500));
			stage.setResizable(false);

			emailBoundary.loadEmail(idCar, idUte);

			stage.show();

		} catch (Exception e) {
			logger.error(e.getMessage());
			MyIOException.openPageFault("Email");
		}
	}
	
	
	public void apriPaginaDonazione(int idCar, int idUte) {
		try {

			DonationBoundary donationBoundary;

			FXMLLoader fxmlLoader = new FXMLLoader();
			Parent rootNode = fxmlLoader.load(getClass().getResourceAsStream("/boundary/Donation.fxml"));

			donationBoundary = fxmlLoader.getController();

			Stage stage = new Stage();
			stage.setTitle("Donazione");
			donationBoundary.initBoundary(idCar, idUte);
			stage.setScene(new Scene(rootNode, 800, 500));
			stage.setResizable(false);
			stage.show();

		} catch (Exception e) {
			logger.error(e.getMessage());
			MyIOException.openPageFault("Donazione");
		}
	}
	


	
}
