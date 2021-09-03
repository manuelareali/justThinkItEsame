package bean;

import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.scene.input.MouseEvent;
import controller.GestisciEventiController;
import controller.ShopHomeController;
import entity.EventTab;
import exception.MyException;
import exception.MyIOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class GestisciEventiPropCaritas {

	private EventTab event;
	private int idShop;
	private GestisciEventiController gestisciEventiC;
	
	Logger logger = LoggerFactory.getLogger(GestisciEventiPropCaritas.class.getName());
    @FXML
    private TableView<EventTab> table;

    @FXML
    private TableColumn<EventTab, String> nomeEvento;

    @FXML
    private TableColumn<EventTab, String> nomeCaritas;

    @FXML
    private TableColumn<EventTab, String> noteEvento;
    
    @FXML
    private TableColumn<EventTab, String> statoEvento;

    @FXML
    private TableColumn<EventTab, String> tipoEvento;

    @FXML
    private Button eliminaE;

    @FXML
    private Button back;

    @FXML
    private Button proposteNegozi;

  
 
    
    public GestisciEventiPropCaritas() {
    	gestisciEventiC = new GestisciEventiController();

    }
    
    @FXML
    void backButtonPressed(ActionEvent event) {
    	
    	TransizionePagine pagine = new TransizionePagine();
    	pagine.visualizzaShopHomePage( back.getScene().getWindow(), idShop);

    }

    @FXML
    void proposteNegozi(ActionEvent event) {
	
				try {
					check();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/boundary/ProposteOfferte.fxml"));
					Parent root = loader.load();

					Stage stage = (Stage) proposteNegozi.getScene().getWindow();
					ProponiOffertaCaritas proponiOfferta = loader.getController();
					proponiOfferta.load(idShop, this.event.getId());
					stage.setTitle("Proponi Offerta alla Caritas");
					stage.setScene(new Scene(root, 600, 450));
					stage.setResizable(false);

					stage.show();

				} catch (Exception e) {
					logger.error(e.getMessage());
					MyIOException.openPageFault("Proposte Offerte");
				}
			}
		
    
    
 
    
	public boolean check() throws MyException{
		if(this.event == null) {
			throw new MyException("Devi selezionare una riga della taballa",MyException.NEGOZIO_ERROR);

		}
		return true;
	}


    @FXML
    void prendiEvento(MouseEvent event) {
    	this.event = table.getSelectionModel().getSelectedItem();
    }
    
    public void loadShop(int idShop) {
		this.idShop = idShop;
		List<EventTab> listEv = gestisciEventiC.caricaEventiPropCaritas();
		ObservableList<EventTab> data = FXCollections.observableArrayList(listEv);
		nomeEvento.setCellValueFactory(new PropertyValueFactory<>("NomeEvento"));
		nomeCaritas.setCellValueFactory(new PropertyValueFactory<>("NomeCaritas"));
		noteEvento.setCellValueFactory(new PropertyValueFactory<>("NoteEvento"));
		tipoEvento.setCellValueFactory(new PropertyValueFactory<>("TipoEvento"));
		
		table.setItems(data);

	}


}
