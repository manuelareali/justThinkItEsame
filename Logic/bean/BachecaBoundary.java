package bean;


import java.util.List;
import controller.BachecaController;
import entity.Necessita;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;


public class BachecaBoundary {
	private int idCar;
	private int idUte;
	BachecaController bachecaController = new BachecaController();

	@FXML
	private Text nomeCaritas;

	@FXML
	private TextArea cibo;

	@FXML
	private TextArea varie;

	@FXML
	private TextArea vestiti;
	
    @FXML
    private TableView<Necessita> tab;
	
    @FXML
    private TableColumn<Necessita, String> tipologia;

    @FXML
    private TableColumn<Necessita, String> descrizione;

    @FXML
    private TableColumn<Necessita, String> urgenza;

	@FXML
	void creaDonazione(ActionEvent event) {

		bachecaController.apriPaginaDonazione(idCar, idUte);
	}

	@FXML
	void creaEmail(ActionEvent event) {

		bachecaController.apriPaginaEmail(idCar, idUte);

	}

	public void loadFormBoundary(int idCar, int idUte) {
		this.idCar = idCar;
		this.idUte = idUte;
	
		List<Necessita> necessita = bachecaController.loadForm(idCar);

		ObservableList<Necessita> data = FXCollections.observableArrayList(necessita);
		descrizione.setCellValueFactory(new PropertyValueFactory<>("Descrizione"));
		tipologia.setCellValueFactory(new PropertyValueFactory<>("Tipologia"));
		urgenza.setCellValueFactory(new PropertyValueFactory<>("Urgenza"));

		tab.setItems(data);

		}

	

}
