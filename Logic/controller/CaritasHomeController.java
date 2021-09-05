package controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import bean.BachecaPersonaleBoundary;
import bean.GestisciDonazioniBoundary;
import bean.GestisciEventiCaritasBoundary;
import bean.GestisciOffertaCaritas;
import bean.GestisciTurniBoundary;
import dao.CaritasRepository;
import dao.UserDao;
import entity.CaritasUser;
import exception.MyIOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * Sample Skeleton for "CaritasHomePage.fxml" Controller Class You can copy and
 * paste this code into your favorite IDE
 **/

public class CaritasHomeController {
	private static Logger logger = LoggerFactory.getLogger(CaritasHomeController.class.getName());

	private CaritasUser currentUser;
	
	public void deleteAccount(int id) {
		UserDao userd = new UserDao();
		userd.delete(id);
	}

	public CaritasUser getCurrentUser() {
		return this.currentUser;
	}

	
	public void apiriGestDonazioni(int idCar, Window offerteProposte) {
		try {

			 GestisciDonazioniBoundary gestDon;

			FXMLLoader fxmlLoader = new FXMLLoader();
			Parent rootNode = fxmlLoader.load(getClass().getResourceAsStream("../boundary/Gestisci_Donazioni.fxml"));
			gestDon = fxmlLoader.getController();
			Stage stage = (Stage) offerteProposte.getScene().getWindow();
			stage.setTitle("Gestisci Eventi");
		
			gestDon.loadFormBoundary(idCar);
			stage.setScene(new Scene(rootNode, 800, 500));
			stage.setResizable(false);
			stage.show();

		} catch (Exception e) {
			logger.error(e.getMessage());
			MyIOException.openPageFault("Gestisci Donazioni");
		}
	}
	public void apriGestTurni(int idCar, Window offerteProposte) {
		 GestisciTurniBoundary gestTurn = new GestisciTurniBoundary();
		 try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/boundary/gestisci_turni_caritas.fxml"));
				Parent root = loader.load();

				Stage home = (Stage) offerteProposte.getScene().getWindow();

				gestTurn = loader.getController();

				gestTurn.loadFormBoundary(idCar);

				home.setScene(new Scene(root, 883, 550));
				home.show();

			}catch (Exception e) {
				logger.error(e.getMessage());
				MyIOException.openPageFault("Bacheca Personale");
			}
	}
	public void apriGestEventi(int idCar, Window offerteProposte) {
		 try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				Parent rootNode = fxmlLoader
						.load(getClass().getResourceAsStream("../boundary/Gestisci_eventi_caritas.fxml"));
				GestisciEventiCaritasBoundary gestisciB = fxmlLoader.getController();
				Stage stage = (Stage) offerteProposte.getScene().getWindow();
				stage.setTitle("Gestisci Eventi");
				
				gestisciB.loadShop(idCar);
				stage.setScene(new Scene(rootNode, 800, 500));
				stage.setResizable(false);
				stage.show();

			} catch (Exception e) {
				logger.error(e.getMessage());
				MyIOException.openPageFault("Gestisci Eventi Caritas");
			}
	}
	public void apriBacheca(int idCar, Window offerteProposte) {
		 BachecaPersonaleBoundary bacheca = new BachecaPersonaleBoundary();
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/boundary/Bacheca_Personale.fxml"));
				Parent root = loader.load();

				Stage home = (Stage) offerteProposte.getScene().getWindow();
				home.setScene(new Scene(root, 775, 500));
				home.show();

				bacheca = loader.getController();
			
				bacheca.loadFormBoundary(idCar);

			}catch (Exception e) {
				logger.error(e.getMessage());
				MyIOException.openPageFault("Gestisci Turni Caritas");
			}
	}
	
	public void apriOfferte(int idCar, Window offerteProposte) {
		try {
			GestisciOffertaCaritas gestOff;

			FXMLLoader fxmlLoader = new FXMLLoader();
			Parent rootNode = fxmlLoader.load(getClass().getResourceAsStream("/boundary/GestisciOfferte.fxml"));

			gestOff = fxmlLoader.getController();
			Stage stage = (Stage) offerteProposte;
			stage.setTitle("Gestisci Offerte agli Eventi Proposti dalle Caritas");
			gestOff.loadFormBoundary(idCar);
			stage.setScene(new Scene(rootNode, 830, 530));
			stage.setResizable(false);
			stage.show();

		} catch (Exception e) {
			logger.error(e.getMessage());
			MyIOException.openPageFault("Gestisci Offerte Caritas");
		}
    	
	}

	public void setCurrentUser(CaritasUser currentUser) {
		this.currentUser = currentUser;
	}

	public void initDataCaritas(int idUser, Object caritasHomeBoundary) {
		CaritasRepository c = new CaritasRepository();
		currentUser = c.getCaritasByID(idUser);
		if(caritasHomeBoundary.getClass() == bean.CaritasHomeBoundary.class) {
			((bean.CaritasHomeBoundary) caritasHomeBoundary).initDataC(currentUser.getId(), currentUser.getNome());
		}
		else if (caritasHomeBoundary.getClass() == beanweb.CaritasHomeBoundary.class) {
			((beanweb.CaritasHomeBoundary) caritasHomeBoundary).initDataC(currentUser.getId(), currentUser.getNome(), currentUser.getEmail());
		}
	}

}
