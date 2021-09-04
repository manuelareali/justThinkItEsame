package controller;

import entity.PartecipaEvento;
import javafx.stage.Stage;
import javafx.stage.Window;
import dao.EventoDao;

public class PartecipaEventoController {

	private PartecipaEvento partecipaz;


	
	public void apriPartecipaEvento(int idEvento, int idUtente , Float importo, Window partecipa) {
		setDataController(idEvento, idUtente);
		partecipaEvento(importo);
		Stage st = (Stage) partecipa.getScene().getWindow();
		st.close();
	}
	
	public boolean partecipaEvento(float importo) {
		this.partecipaz.setImport(importo);
		EventoDao partecipazione = new EventoDao();
		return partecipazione.creaPartecipazione(this.partecipaz);
	}

	
	public void setDataController(int idEv, int idVol) {
		
		this.partecipaz = new PartecipaEvento(idEv, idVol);

	}

}
