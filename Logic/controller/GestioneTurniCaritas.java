package controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import bean.CreaTurnoBoundary;
import dao.CreaTurnoDao;
import entity.TurnoTab;
import exception.MyIOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public class GestioneTurniCaritas {
	
	private CreaTurnoDao turniDao;
	Logger logger = LoggerFactory.getLogger(GestioneTurniCaritas.class.getName());

	public GestioneTurniCaritas() {
		this.turniDao = new CreaTurnoDao();
	}

	public void apriCreaTurno(int caritas, Window creaTurn) {
		CreaTurnoBoundary caritasTurniBoundary = new CreaTurnoBoundary();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/boundary/CreaTurno.fxml"));
			Parent root = loader.load();

			caritasTurniBoundary = loader.getController();
			caritasTurniBoundary.setCaritas(caritas);
			Stage home = (Stage) creaTurn;
			home.setScene(new Scene(root, 780, 500));

			home.show();
		} catch (Exception e) {
			logger.error(e.getMessage());
			MyIOException.openPageFault("Gestisci Turni Caritas");
		}
	}
	
	
	public boolean modificaTurno(int idTurn, String newNote, int codCar) {
		return this.turniDao.modificaTurno(idTurn, newNote, codCar);
		
	}
	
	
	public boolean cancellaTurno(int idTurn) {
		return this.turniDao.cancellaTurno(idTurn);
	
	}

	public List<TurnoTab> caricaTurni(int idCaritas) {
		return this.turniDao.visualizzaTurni(idCaritas);
	}

}
