package controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import bean.CaritasHomeBoundary;
import bean.CreaNecessitaBoundary;
import dao.BachecaDao;
import entity.BachecaEntity;
import entity.Necessita;
import exception.MyIOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;




public class BachecaPersonaleController {
	
	private BachecaDao bacheca;
	private static Logger logger = LoggerFactory.getLogger(BachecaPersonaleController.class.getName());

	
	
	public boolean eliminaAnnuncio(int nece) {
		return	bacheca.eliminaNecessita(nece); 
	}
	
	public void apriPaginaNecessita(int caritas, Window necessita) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/boundary/Crea_Necessita.fxml"));
			Parent root = loader.load();
			CreaNecessitaBoundary creaNec = loader.getController();
			creaNec.setCaritas2(caritas);
			Stage home = (Stage) necessita;
			home.setScene(new Scene(root, 600, 400));

			home.show();
		} catch (Exception e) {
			logger.error(e.getMessage());
			MyIOException.openPageFault("Crea Necessita");
		}
	}
	
	public void back(int caritas, Window back) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/boundary/CaritasHomePage.fxml"));
			Parent root = loader.load();
			CaritasHomeBoundary homeC = loader.getController();
		
			CaritasHomeController caritasHomeC = new CaritasHomeController();
			caritasHomeC.initDataCaritas(caritas, homeC);

			Stage home = (Stage) back;
			home.setScene(new Scene(root, 800, 600));

			home.show();
		} catch (Exception e) {
			logger.error(e.getMessage());
			MyIOException.openPageFault("Caritas Home Page");
		}
	}
	
	
	public List<Necessita> loadForm(int idCar) {
		
		bacheca = new BachecaDao();
		BachecaEntity bachecaE = new BachecaEntity();
		List<Necessita> necessita = bacheca.visualizzaNecessita(idCar);
		bachecaE.setNecessita(necessita);
	
		return bachecaE.getNecessita();
	}
	
}
