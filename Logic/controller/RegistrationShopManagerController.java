package controller;


import java.io.IOException;

import dao.ShopRepository;
import entity.ShopUser;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public  class RegistrationShopManagerController {



	public int  registraNegozioPressed(String tipo, String nome, String pass, String via, String tel,String mail, String citta){

		ShopUser shop = new ShopUser(nome, pass,tipo, via,  tel, mail, citta);
		ShopRepository crep = new ShopRepository();
		int id = crep.insertShop(shop);
		shop.setId(id);
		return id;
	}

	public void register (String tipo, String nomeNegozio, String passwordNeg, String viaNeg, String telNeg, String mailNeg,String cittaResNeg, Window registraNegozio ) throws IOException {
		registraNegozioPressed(tipo, nomeNegozio, passwordNeg,
				viaNeg, telNeg, mailNeg,
				cittaResNeg);

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/boundary/Login_boundary.fxml"));
		Parent root = loader.load();
		Stage home = (Stage) registraNegozio;
		home.setScene(new Scene(root));
		home.show();
	}
	

}