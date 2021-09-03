package bean;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.CreaNecessitaController;
import exception.MyException;
import exception.MyIOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.Window;

public class CreaNecessitaBoundary {
	
	private Logger logger = LoggerFactory.getLogger(CreaNecessitaBoundary.class.getName());
	private String[] tipo = { "Vestiti", "Cibo" };
	private String[] urg = { "Alta", "Normale", "Bassa" };

	private TextArea[] text;



	@FXML
	private TextArea descrizione;

	@FXML
	private ChoiceBox<String> tipologia;

	@FXML
	private ChoiceBox<String> urgenza;

	@FXML
	private Button creaAnnuncio;

	@FXML
	private Button back;

	private int idCaritas;

	@FXML
	void backPressed(ActionEvent event) {
		this.switchPage(back.getScene().getWindow());

	}


	@FXML
	void creaAnnuncioPressed(ActionEvent event) {
		CreaNecessitaController creaNec = new CreaNecessitaController();
		creaNec.inizializza(idCaritas);
		try{
			checker();
		
			boolean i = creaNec.creaNecessita(tipologia.getValue().toString(), urgenza.getValue().toString(),
					descrizione.getText());
			if (i) {
				this.switchPage(creaAnnuncio.getScene().getWindow());
			} else {
				logger.trace("errore nella creazione dell'annuncio");
			}
		
		}catch(MyException e){
			e.getMessage();
			e.printStackTrace();		
		}
	}
	
	
	public boolean checker() throws MyException {
		if ( this.text[0].getText() == null || this.tipologia.getValue() == null || this.urgenza.getValue() == null) {
			throw new MyException("Devi selezionare una riga della taballa",MyException.CARITAS_ERROR);

		}
		return true;
		
	}

	@FXML
	void initialize() {
		tipologia.getItems().addAll(tipo);
		urgenza.getItems().addAll(urg);
		text = new TextArea[] { this.descrizione };

	}

	public void setCaritas2(int caritas2) {
		this.idCaritas = caritas2;
	}

	public void switchPage(Window stage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/boundary/Bacheca_Personale.fxml"));
			Parent root = loader.load();

			Stage home = (Stage) stage;
			home.setScene(new Scene(root, 775, 500));
			home.show();

			BachecaPersonaleBoundary bacheca = loader.getController();
			bacheca.loadFormBoundary(idCaritas);

		} catch (Exception e) {
			logger.error(e.getMessage());
			MyIOException.openPageFault("Bacheca Personale");
		}
	}

}
