package bean;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.ProponiOfferta;
import exception.MyException;
import exception.Trigger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
public class ProponiOffertaCaritas {

 	Logger logger = LoggerFactory.getLogger(ProponiOffertaCaritas.class.getName());
	private int idShop;
	private int idEv;
	private   	ProponiOfferta proponiOfferta ;
    @FXML
    private TextField prezzo;
    @FXML
    private DatePicker dataEvento;

    @FXML
    private TextArea note;

    @FXML
    private Button conferma;
    
    @FXML
    private Button indietro;
    

   public ProponiOffertaCaritas() {
	   proponiOfferta = new ProponiOfferta();
   }

  @FXML
  void conferma(ActionEvent event) {
  	Trigger trigger = new Trigger();
  	try {
  		if(trigger.isNumeric(prezzo.getText()) && check()) {
	  		proponiOfferta.proponi(idShop, idEv, Float.parseFloat(prezzo.getText()),dataEvento.getValue().toString(), note.getText());	
			proponiOfferta.switchPage(conferma.getScene().getWindow(), idShop);
		}
  		}catch(MyException e) {
  			logger.error(e.getMessage());
  		}catch (NumberFormatException n) {
  			logger.error("Non sono presenti solo numeri in Prezzo Evento." + n.getMessage());
  		}
  	}
  
    public boolean check() throws MyException {
    	if(dataEvento.getValue() == null) {
    		throw new MyException("Alcuni campi sono vuoti",MyException.CAMPI_VUOTI);

    	}
		return true;
    }


    @FXML
    public void indietro(ActionEvent event) {
    	 proponiOfferta.switchPage(conferma.getScene().getWindow(), idShop);
    }
    
    

    

    
    public void load(int idShop, int idEvento) {
    	this.idShop = idShop;
    	this.idEv = idEvento;
    }
}
