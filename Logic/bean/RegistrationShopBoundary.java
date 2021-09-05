package bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.RegistrationShopManagerController;
import exception.MyException;
import exception.MyIOException;
import exception.Trigger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class RegistrationShopBoundary {
	private RegistrationShopManagerController regNeg;

	private String tipo;
	Logger logger = LoggerFactory.getLogger(RegistrationShopBoundary.class.getName());

	@FXML
	private TextField cittaResNeg;

	@FXML
	private TextField viaNeg;

	@FXML
	private TextField civicoNeg;

	@FXML
	private TextField telNeg;

	@FXML
	private TextField nomeNegozio;

	@FXML
	private TextField mailNeg;

	@FXML
	private CheckBox typeCiboNeg;

	@FXML
	private CheckBox typeVestNeg;

	@FXML
	private Button registraNegozio;

	@FXML
	private Button backButtonNeg;

	@FXML
	private PasswordField passwordNeg;

	@FXML
	private PasswordField confermaPassNeg;

	@FXML
	private Text passwordMatch;

	public RegistrationShopBoundary() {
		this.regNeg = new RegistrationShopManagerController();
	}

	@FXML
	void backButtonNegPressed(ActionEvent event) {
		TransizionePagine pageSwitch;
		pageSwitch = new TransizionePagine();
		pageSwitch.visualizzaPagina("/boundary/RegistrazioneMenu.fxml", backButtonNeg.getScene().getWindow());

	}

	@FXML
	void registraNegozioPressed(ActionEvent event) {
		Trigger trigger = new Trigger();

		try {
			checker();
			trigger.isNumeric(telNeg.getText());
			trigger.isNumeric(civicoNeg.getText());
			regNeg.register(tipo, nomeNegozio.getText(), passwordNeg.getText(),
					viaNeg.getText() + " " + civicoNeg.getText(), telNeg.getText(), mailNeg.getText(),
					cittaResNeg.getText(), registraNegozio.getScene().getWindow());
		} catch (NumberFormatException e) {
			logger.error("Non sono presenti solo numeri in Telefono o N civico" + e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			MyIOException.openPageFault("Login");
		}
	}

	public boolean checker() throws MyException {
		MyException e = new MyException("Alcuni campi sono vuoti.",MyException.CAMPI_VUOTI);
		// Controlla che non ci siano campi lasciati vuoti

		if (cittaResNeg.getText().isEmpty() || viaNeg.getText().isEmpty() || civicoNeg.getText().isEmpty()
				|| telNeg.getText().isEmpty() || nomeNegozio.getText().isEmpty() || mailNeg.getText().isEmpty()) {

			throw e;

		}
		if (typeCiboNeg.isSelected()) {
			tipo = "Cibo";
		} else if (typeVestNeg.isSelected()) {
			tipo = "Vestiti";
		} else {
			throw e;
		}

		// Valida che i campi password e conferma password siano uguali

		if (passwordNeg.getText().equals(confermaPassNeg.getText()) && !passwordNeg.getText().equals("")) {
			passwordMatch.setVisible(false);
			return true;
		} else {
			passwordMatch.setText("Le password non corrispondono");
			passwordMatch.setVisible(true);
			return false;
		}
	}

}
