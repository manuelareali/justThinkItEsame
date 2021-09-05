package bean;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.scene.input.MouseEvent;
import controller.BachecaPersonaleController;
import entity.Necessita;
import exception.MyException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class BachecaPersonaleBoundary {
	
	private BachecaPersonaleController bachecaController;
	private static Logger logger = LoggerFactory.getLogger(BachecaPersonaleBoundary.class.getName());
	private int caritas;
	private Necessita necc;
	BachecaPersonaleController bachecaPersC = new BachecaPersonaleController();

	
	@FXML
	private TableView<Necessita> bacheca;

	@FXML
	private TableColumn<Necessita, String> tipologia;

	@FXML
	private TableColumn<Necessita, String> descrizione;

	@FXML
	private TableColumn<Necessita, String> urgenza;

	@FXML
	private Button necessita;

	@FXML
	private Button elimina;

	@FXML
	private Button back;
	
	

	@FXML
	void backPressed(ActionEvent event) {
    bachecaPersC.back(caritas, back.getScene().getWindow());

	}

	@FXML
	void creaNecessita(ActionEvent event) {
		bachecaPersC.apriPaginaNecessita(caritas, necessita.getScene().getWindow());
		}
		
	

	@FXML
	void prendiNecessita(MouseEvent event) {
		this.necc = bacheca.getSelectionModel().getSelectedItem();
	}
	
	@FXML
	void eliminaNecessita(ActionEvent event) {
		try {
			if(check()) {
				bachecaController.eliminaAnnuncio(this.necc.getIdNece());
			}
		}catch(MyException e) {
			logger.error(e.getMessage());
		}
	}
	
	public boolean check() throws MyException{
		if(this.necc == null) {
			
			throw new MyException("Devi selezionare una riga della tabella",MyException.CARITAS_ERROR);
			
		}
		return true;
	}

	public void loadFormBoundary(int idCar) {
		this.caritas = idCar;

		bachecaController = new BachecaPersonaleController();
		List<Necessita> necessitaList = bachecaController.loadForm(idCar);
		ObservableList<Necessita> data = FXCollections.observableArrayList(necessitaList);

		descrizione.setCellValueFactory(new PropertyValueFactory<>("Descrizione"));

		tipologia.setCellValueFactory(new PropertyValueFactory<>("Tipologia"));

		urgenza.setCellValueFactory(new PropertyValueFactory<>("Urgenza"));

		bacheca.setItems(data);

	}



}
