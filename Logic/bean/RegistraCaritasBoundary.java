package bean;

import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.RegistrazioneCaritasController;
import exception.MyException;
import exception.MyIOException;
import exception.Trigger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RegistraCaritasBoundary {

	private RegistrazioneCaritasController regController;
	Logger logger = LoggerFactory.getLogger(RegistraCaritasBoundary.class.getName());

	TransizionePagine pageSwitch;
	private String tipo;

	@FXML
	private TextField cittadiResidenza;

	@FXML
	private TextField via;

	@FXML
	private TextField civico;

	@FXML
	private TextField telefono;

	@FXML
	private Button backButton;

	@FXML
	private TextField nomeCaritas;

	@FXML
	private TextField email;

	@FXML
	private CheckBox type;

	@FXML
	private CheckBox type2;

	@FXML
	private Button completaButton;

	@FXML
	private PasswordField passwordCaritas;

	@FXML
	private Text passwordMatch;

	@FXML
	private PasswordField confermaPassCaritas;

	public RegistraCaritasBoundary() {
		regController = new RegistrazioneCaritasController();
	}

	@FXML
	void backButtonPressed(ActionEvent event) {

		pageSwitch = new TransizionePagine();
		pageSwitch.visualizzaPagina("/boundary/RegistrazioneMenu.fxml", backButton.getScene().getWindow());

	}

	@FXML
	public void completaButtonPressed(ActionEvent event) throws SQLException {
		Trigger trigger = new Trigger();

		try {
			checker();
			trigger.isNumeric(telefono.getText());
			regController.register(nomeCaritas.getText(), tipo, passwordCaritas.getText(), via.getText(),
					telefono.getText(), email.getText(), cittadiResidenza.getText(), completaButton.getScene().getWindow());

		} catch (NumberFormatException e) {
			logger.error("In Telefono ed in civico devono essere inseriti solo numeri" + e.getMessage());
		} catch (MyException e) {
			logger.error(e.getMessage());
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			MyIOException.openPageFault("Login");
		}
	}

	public boolean checker() throws MyException {
		MyException e = new MyException("Alcuni campi sono vuoti.",MyException.CAMPI_VUOTI);
		// Controlla che non ci siano campi lasciati vuoti

		if (cittadiResidenza.getText().isEmpty() || via.getText().isEmpty() || civico.getText().isEmpty()
				|| telefono.getText().isEmpty() || nomeCaritas.getText().isEmpty() || email.getText().isEmpty()) {
			

			throw e;
		}

		if (type.isSelected() && type2.isSelected()) {
			tipo = "Tutto";
		} else if (type2.isSelected()) {
			tipo = "Cibo";
		} else if (type.isSelected()) {
			tipo = "Vestiti";
		} else {
			throw e;// Almeno uno dei tipi deve essere selezionato
		}

		// Valida che i campi password e conferma password siano uguali

		if (passwordCaritas.getText().isEmpty()) {
			return false;
		}
		if (passwordCaritas.getText().equals(confermaPassCaritas.getText())) {
			passwordMatch.setVisible(false);
			return true;
		} else {
			passwordMatch.setText("Le password non corrispondono");
			passwordMatch.setVisible(true);
			return false;
		}

	}

}