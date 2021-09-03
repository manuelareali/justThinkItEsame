package bean;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.DonationController;
import exception.MyException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DonationBoundary {
	private static Logger logger = LoggerFactory.getLogger(DonationBoundary.class.getName());

	
	
	@FXML
	private RadioButton vestiti;

	@FXML
	private RadioButton cibo;

	@FXML
	private Button donazione;

	@FXML
	private Button indietro;

	@FXML
	private TextField indirizzo;

	@FXML
	private TextArea descrizione;


	private DonationController controller;

	public DonationBoundary() {
		controller = new DonationController();
	}

	@FXML
	public void creaDonazione(ActionEvent event) {
		try {
			if (checker()) {
				controller.setIndirizzo(this.indirizzo.getText());
				controller.setDescrizione(this.descrizione.getText());
				controller.creaDonazione();
				Stage st = (Stage) donazione.getScene().getWindow();
				st.close();
			}
		} catch (MyException e) {
			logger.error(e.getMessage());
		}
	}


	public boolean checker() throws MyException{
		// Controlla che non ci siano campi lasciati vuoti
		if (cibo.isSelected()) {
			controller.setTipologia(2);
		}
		else if (vestiti.isSelected()) {
			controller.setTipologia(1);
		}
		
		if(indirizzo.getText().isEmpty()) {
			throw new MyException("Devi selezionare una riga della taballa",MyException.CAMPI_VUOTI);

		}
		
		if(!cibo.isSelected() && !vestiti.isSelected())  {
			throw new MyException("Devi selezionare una riga della taballa",MyException.CAMPI_VUOTI);

		}
		
		return true;
	}

	

	public void initBoundary(int idCar, int idUte) {
		controller.initController(idCar, idUte);
	}

}
