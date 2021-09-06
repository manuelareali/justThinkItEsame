package bean;

import java.util.Optional;
import controller.GestisciEventiController;
import controller.ShopHomeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;



public class ShopHomeBoundary {
	

	

	private int idShop;
	

	
    @FXML
    private Button gestEvent;

    @FXML
    private Button deleteAccountButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Text nomeCognome;


    @FXML
    private Button searchCaritasButton;

    @FXML
    private Button gestisciProposteCaritas;

    private GestisciEventiController gest;
    
    public ShopHomeBoundary() {
    	gest= new GestisciEventiController();
    }


    @FXML
    public void cercaCaritas(ActionEvent event) {
    	TransizionePagine switchPage = new TransizionePagine();
    	switchPage.apriMappa(this.idShop, searchCaritasButton.getScene().getWindow());

    }

    

    @FXML
    public void deleteAccountButtonPressed(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Logout");
    	alert.setHeaderText("Se cancelli il tuo account verrano cancellati anche le tue attività in corso e potresti ricevere delle sanzioni");
    	alert.setContentText("Sei sicuro di voler cancellare il tuo account?");
    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    		ShopHomeController controller = new ShopHomeController();
    		controller.deleteAccount(idShop);
    		TransizionePagine pageSwitch = new TransizionePagine();
    		pageSwitch.backToLogin(deleteAccountButton.getScene().getWindow());
    	
    	} 	
    }
    

    @FXML
    public void gestisciProposteCaritas() {
    	gest.gestisciProposte(gestisciProposteCaritas.getScene().getWindow(), idShop);
    }

    
	@FXML
	public void gestisciEventi(ActionEvent event) {
		gest.gestEventi(gestEvent.getScene().getWindow(), idShop);
	}
 
  

    @FXML
    public void logoutButtonPressed(ActionEvent event) {
    	
     	
    	TransizionePagine pageSwitch = new TransizionePagine();
    	pageSwitch.logout(logoutButton.getScene().getWindow());
    	
    	
	}

	public void initData(int id, String nome) {
		this.idShop = id;
    	nomeCognome.setText(nome);

	}





}
