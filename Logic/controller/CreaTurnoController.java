package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import bean.GestisciTurniBoundary;
import dao.CreaTurnoDao;
import exception.MyIOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public class CreaTurnoController {
	private static Logger logger = LoggerFactory.getLogger(CreaTurnoController.class.getName());


	private CreaTurnoDao turnodao;
	
	public CreaTurnoController() {
		turnodao = new CreaTurnoDao();
	}
	
	public void switchPage(Window stage, int caritas) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/boundary/gestisci_turni_caritas.fxml"));
			Parent root = loader.load();

			Stage home = (Stage) stage;
			home.setScene(new Scene(root, 885, 550));
			home.show();

			GestisciTurniBoundary gest = loader.getController();
			gest.loadFormBoundary(caritas); 

		}  catch (Exception e) {
			logger.error(e.getMessage());
			MyIOException.openPageFault("Gestisci Turni Caritas");
		}
	}
	public boolean creaTurno(int codCar, String giorno, String oraIni, String oraFin, int numPart, String newNote) {
		return turnodao.creaTurno(codCar, giorno, newNote, oraIni, oraFin, numPart);
	}
}
