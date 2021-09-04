package controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import bean.GestisciEventiPropCaritas;
import dao.OfferteNegoziCaritas;
import entity.Offerte;
import exception.MyIOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ProponiOfferta {

	private OfferteNegoziCaritas offerte;

	
	public ProponiOfferta() {
		offerte = new OfferteNegoziCaritas();
	}
	
    public void switchPage(Window stage, int idShop) {
    	Logger logger = LoggerFactory.getLogger(ProponiOfferta.class.getName());
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/boundary/EventiPropNeg.fxml"));
			Parent root = loader.load();

			Stage home = (Stage) stage;
			home.setScene(new Scene(root, 800, 500));
			home.show();

			GestisciEventiPropCaritas gest = loader.getController();
			gest.loadShop(idShop);

		}catch (Exception e) {
			logger.error(e.getMessage());
			MyIOException.openPageFault("Shop Event propose");
		}
	}
	
	public int proponi(int idNeg, int evento, float prezzo, String data, String note) {
		Offerte offerteEntity = new Offerte(idNeg, evento, prezzo, data, note);
		offerte.creaOfferta(offerteEntity);
		return 0;	
	}
	
	public boolean confermaEvento(int idOfferta) {
		return offerte.confermaOfferta(idOfferta);
	}
	
	public List<Offerte> caricaOfferte(int idCar){
		return this.offerte.cercaOfferteCaritas(idCar);
	}
}
