package controller;

import java.io.IOException;
import java.sql.SQLException;

import dao.CaritasRepository;
import entity.CaritasUser;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public class RegistrazioneCaritasController  {
	
	int tipo;



	public int completaButtonPressed( String nomeCaritas,String tipo, String passwordCaritas, String via, 
		
		String telefono, String email, String cittadiResidenza) throws SQLException {
	
		CaritasUser newUser = new CaritasUser(nomeCaritas,tipo, passwordCaritas, via, telefono, email, cittadiResidenza);
	
			CaritasRepository crep = new CaritasRepository();
			if (crep.insertCaritas(newUser)==0) {
				return 1;
			}
			
			return 0;
		

	}
	
	public void register(String nomeCaritas, String tipo, String passwordCaritas, String via, String telefono, String email, String cittadiResidenza, Window completaButton) throws SQLException, IOException {
		completaButtonPressed(nomeCaritas, tipo, passwordCaritas, via,telefono, email, cittadiResidenza);

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/boundary/Login_boundary.fxml"));
		Parent root = loader.load();
		Stage home = (Stage) completaButton;
		home.setScene(new Scene(root));
		home.show();
	}
	
	



}
