package bean;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.GestisciEventiCaritasController;
import exception.MyException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class PromuoviEventoGenerale {
	
	Logger logger = LoggerFactory.getLogger(PromuoviEventoGenerale.class.getName());


	private int idCar;
	private String tipo;
	private int idShop;
	private GestisciEventiCaritasController switchPage;

	
    @FXML
    private TextField nome;

    @FXML
    private TextField prezzo;

    @FXML
    private RadioButton idCibo;

    @FXML
    private RadioButton idVestiti;

    @FXML
    private RadioButton tutto;

    @FXML
    private TextArea note;

    @FXML
    private Button confermaEventoPressed;
   
    @FXML
    private Button indietro;
    

    public PromuoviEventoGenerale() {
    	 switchPage = new GestisciEventiCaritasController();
    }
 
  
    public void loadId(int idCar) {
    	this.idCar = idCar;
    }
    
    @FXML
    void indietro(ActionEvent event) {
    	switchPage.switchPage(indietro.getScene().getWindow(), idCar);
       }
    

    @FXML
	void confermaEventoPressed(ActionEvent event) {
		GestisciEventiCaritasController controller = new GestisciEventiCaritasController();
		float x = (float) 0.0;
			try {
					checker();
					controller.creaEventoGeneral(nome.getText(), tipo, x, note.getText(), this.idCar, this.idShop);
					switchPage.switchPage(confermaEventoPressed.getScene().getWindow(), idCar);

			} catch (MyException e) {
				logger.error(e.getMessage());
			}
	}
    
  

	public boolean checker() throws MyException {
		if (idCibo.isSelected()) {
			tipo = "Cibo";
		}
	
		else if (idVestiti.isSelected()) {
			tipo = "Vestiti";
			
		}else if(tutto.isSelected()) {
			tipo = "Tutto";
			
		}
		
		if(nome.getText().isEmpty() || !idCibo.isSelected() && !idVestiti.isSelected() && !tutto.isSelected()) {
			throw new MyException("Alcuni campi sono vuoti", MyException.CAMPI_VUOTI);

		}
		return true; 				
	}


}

