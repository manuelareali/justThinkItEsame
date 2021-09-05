package bean;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.CreaNecessitaController;
import exception.MyException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.stage.Window;

public class CreaNecessitaBoundary {
	
	private Logger logger = LoggerFactory.getLogger(CreaNecessitaBoundary.class.getName());
	private String[] tipo = { "Vestiti", "Cibo" };
	private String[] urg = { "Alta", "Normale", "Bassa" };
	CreaNecessitaController creaNec = new CreaNecessitaController();
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
		creaNec.apriBachecaPersonale(this.idCaritas, stage);
	}

}
