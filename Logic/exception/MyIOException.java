package exception;

import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MyIOException extends IOException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
 public MyIOException() {
	 super("Error IOException");
 }
	
 
 
 public static void openPageFault(String pagina) {
	 
	 	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Errore");
    	alert.setHeaderText("La pagina "+ pagina +  " richiesta non è stata trovata, riprovare più tardi");
    	alert.showAndWait();
	 
 }

}
