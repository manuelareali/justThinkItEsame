package controller;

import java.util.List;
import dao.CreaTurnoDao;
import entity.Orario;
import entity.Turno;
import entity.PartecipaTurno;
import javafx.event.ActionEvent;


public class PrenotaTurnoController {

	private int idUtente;
	private int idCaritas;
	private static PrenotaTurnoController instance = null;
	private CreaTurnoDao checkTurniPossibili;

	public PrenotaTurnoController() {
		 checkTurniPossibili = new CreaTurnoDao();
	}
	
	
	public static PrenotaTurnoController getInstance() {
		if (PrenotaTurnoController.instance == null)
			PrenotaTurnoController.instance = new PrenotaTurnoController();
		return instance;
	}


	public boolean prenotaTurno(String giorno, String oraIn, String oraFin, String cv) {
		boolean error;
		int idTurno;
		
		Turno turno = new Turno(giorno ,oraIn, oraFin);

		idTurno = checkTurniPossibili.trovaTurno(turno);

		PartecipaTurno partecipazione = new PartecipaTurno(idUtente, idTurno, idCaritas, cv);

		error = checkTurniPossibili.partecipazioneTurno(partecipazione);

		return error;

	}
	
	

	public void setDataController(int idCar, int idUte) {
		this.idCaritas = idCar;
		this.idUtente = idUte;
	}

	public String[] inizializzaGiorni() {
		checkTurniPossibili = new CreaTurnoDao();		
		return checkTurniPossibili.visualizzaGiorni();
	}

	
	public  List<Orario> initializzaOrari() {
		
		return  checkTurniPossibili.visualizzaOrario();

	}
}
