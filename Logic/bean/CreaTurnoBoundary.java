package bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.CreaTurnoController;
import exception.MyException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class CreaTurnoBoundary {
	private static Logger logger = LoggerFactory.getLogger(CreaTurnoBoundary.class.getName());
	CreaTurnoController creaTurn = new CreaTurnoController();


	@FXML
	private Button back;

	@FXML
	private Button creaTurno;

	@FXML
	private TextArea note;

    @FXML
    private ChoiceBox<String> orain;

    @FXML
    private ChoiceBox<String> oraFin;

	@FXML
	private ChoiceBox<String> giorni;

	@FXML
	private TextField numParte;

	private int caritas;

	@FXML
	void backPressed(ActionEvent event) {
		

		creaTurn.switchPage(creaTurno.getScene().getWindow(), this.caritas);

	}


	public boolean checker() throws MyException {
		// Controlla che non ci siano campi lasciati vuoti	
		if(giorni.getValue() == null || orain.getValue() == null  || oraFin.getValue() == null || numParte.getText() == null) {
			logger.error("Alcuni campi sono vuoti");
			throw new MyException("Devi selezionare una riga della taballa",MyException.CAMPI_VUOTI);

			
		}
		if(orain.getValue() == oraFin.getValue()) {
			logger.error("Devi inserire orari diversi");
			throw new MyException("Devi selezionare una riga della taballa",MyException.CARITAS_ERROR);

		}
		return true;
	}
	
	


	public boolean isNumeric(String str) { 
		  try {  
		    Integer.parseInt(str); 
		    return true;
		  } catch(NumberFormatException e){  
			  logger.error("Inserisci correttamente il numero di partecipanti");
		    return false;  
		  } 
		}
	
	@FXML
	void creaTurnoPressed(ActionEvent event) {
		try {
			if (checker() && isNumeric(numParte.getText())) {
					creaTurn.creaTurno(caritas, giorni.getValue().toString(), orain.getValue().toString(), oraFin.getValue().toString(),
							Integer.parseInt(numParte.getText()), note.getText());
						creaTurn.switchPage(creaTurno.getScene().getWindow(), caritas);
				}
			
		} catch (NumberFormatException e) {
			
			logger.error(e.getMessage());
		
		} catch (MyException e) {
		
			logger.error(e.getMessage());
			logger.error("Il codice di errore "+ e.getErrorNumber());
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	} 
	

	public void setCaritas(int caritas) {
		this.caritas = caritas;
	}
	


	@FXML
	void initialize() {
		String[] days = { "Lunedi", "Martedì", "Mercoledi", "Giovedi", "Venerdi", "Sabato", "Domenica" };

		for (int i = 0; i < 7; i++) {

			giorni.getItems().add(days[i]);

		}
		String[] oraIn = { "8:00", "11:00", "14:00","17:00","19:00"};
		
		for(int i= 0; i<30; i++) {
			orain.getItems().add(oraIn[i]);
		}
		
		String[] oraFine = {"11:00", "14:00","17:00","19:00","23:00"};
		
		for(int i= 0; i<30; i++) {
			oraFin.getItems().add(oraFine[i]);
		}
		

	}

}
