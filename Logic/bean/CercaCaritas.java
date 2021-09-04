package bean;

import com.sothawo.mapjfx.*;
import com.sothawo.mapjfx.event.MapLabelEvent;
import com.sothawo.mapjfx.event.MapViewEvent;
import com.sothawo.mapjfx.event.MarkerEvent;

import controller.CaritasHomeController;
import controller.CercaCaritasController;
import controller.ShopHomeController;
import controller.UserHomeController;
import entity.MarkerID;
import exception.MyIOException;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CercaCaritas {

	
	
	public enum MarkerType {
		CARITASMARKER, EVENTO, DONAZIONE, MAP
	}
	
	
	private static final String EVE = "buttonEvento";
	private static final String PRO = "buttonPromuoviEvento";
	private static final String ALL = "buttonAllLocations";
	private static final String DON = "buttonDonazione";
	private static final String TURN = "buttonTurnoVolontariato";
	private static final String BAC = "buttonBacheca";
	private int idCaritas;
	private int idEvento;
	private String ruolo;
	private int idUser;
	private static final String VOLUNTEER = "Volontario";
	private static final String SHOP = "Negozio";
	private static final String CARITAS = "Caritas";
	
	/** logger for the class. */
	private static final Logger logger = LoggerFactory.getLogger(CercaCaritas.class);

	private static final Coordinate RomaCentro = new Coordinate(41.900412, 12.494619);
	


	

	/** default zoom value. */
	private static final int ZOOMDEFAULT = 14;

	/** the markers. */

	private CercaCaritasController cercaController;

	private List<MarkerID> markerCaritas;
	private List<MarkerID> markerEventi;
	private List<MarkerID> markerDonazioni;



	private Marker markerClick;

	/** the labels. */

	

	// a circle around the castle

	@FXML
	/** button to set the map's zoom. */
	private Button buttonZoom;

	/** the MapView containing the map */
	@FXML
	private MapView mapView;

	/**
	 * the box containing the top controls, must be enabled when mapView is
	 * initialized
	 */
	@FXML
	private HBox topControls;

	/** Slider to change the zoom value */
	@FXML
	private Slider sliderZoom;

	/** Accordion for all the different options */
	@FXML
	private Accordion leftControls;

	/** section containing the location button */
	@FXML
	private TitledPane optionsLocations;

	/** button to set the map's center */
	@FXML
	private Button buttonDonazione;

	/** button to set the map's center */
	@FXML
	private Button buttonTurnoVolontariato;

	/** button to set the map's center */
	@FXML
	private Button buttonEvento;

	/** button to set the map's center */
	@FXML
	private Button buttonBacheca;

	/** button to set the map's extent. */
	@FXML
	private Button buttonAllLocations;

	@FXML
	private Button buttonBack;
	
	@FXML
	private Button buttonPromuoviEvento;

	/** for editing the animation duration */
	@FXML
	private TextField animationDuration;

	/** Label to display the current center */
	@FXML
	private Label labelCenter;

	/** Label to display the current extent */
	@FXML
	private Label labelExtent;

	/** Label to display the current zoom */
	@FXML
	private Label labelZoom;

	/** label to display the last event. */
	@FXML
	private Label labelEvent;

	/** RadioButton for MapStyle OSM */
	@FXML
	private RadioButton radioMsOSM;

	
	/** RadioButton for MapStyle WMS. */
	@FXML
	private RadioButton radioMsWMS;

	/** RadioButton for MapStyle XYZ */
	@FXML
	private RadioButton radioMsXYZ;

	/** ToggleGroup for the MapStyle radios */
	@FXML
	private ToggleGroup mapTypeGroup;

	/** Check button for harbour marker */
	@FXML
	private CheckBox checkEventoMarker;

	/** Check button for castle marker */
	@FXML
	private CheckBox checkCaritasMarker;

	/** Check button for harbour marker */
	@FXML
	private CheckBox checkDonazioneMarker;

	/** Check button for click marker */
	@FXML
	private CheckBox checkClickMarker;



	/** params for the WMS server. */
	private WMSParam wmsParam = new WMSParam().setUrl("http://ows.terrestris.de/osm/service?").addParam("layers",
			"OSM-WMS");

	private XYZParam xyzParams = new XYZParam()
			.withUrl("https://server.arcgisonline.com/ArcGIS/rest/services/World_Topo_Map/MapServer/tile/{z}/{y}/{x})")
			.withAttributions(
					"'Tiles &copy; <a href=\"https://services.arcgisonline.com/ArcGIS/rest/services/World_Topo_Map/MapServer\">ArcGIS</a>'");

	private ObservableList<Node> listaBottoni;
	private ObservableList<Node> listaBottoniOld;
	
	
	String	s = "errore IoException";
	private void indietro() {
		TransizionePagine switchPage = new TransizionePagine();
	
	if (ruolo.equals(VOLUNTEER)) {
		switchPage.visualizzaUserHomePage(buttonBack.getScene().getWindow(), this.idUser);
	}
	
	
	else if(ruolo.equalsIgnoreCase(SHOP)) {
		switchPage.visualizzaShopHomePage(buttonBack.getScene().getWindow(), this.idUser);
	}
	
	else if(ruolo.equalsIgnoreCase(CARITAS)) {
		switchPage.backToMenuCaritas(idUser, buttonBack.getScene().getWindow());
	}

	}

	
	
	
	private void promuoviEvento(int idCar, int idShop) {
		
		cercaController.promuoviEvento(idCar, idShop);
	

	}


	private void vediNecessita(int idCar, int idUt) {
		cercaController.vediNecessita(idCar, idUt);
	}

	private void apriDonazione(int idCar, int idUt) {
		cercaController.apriDonazione(idCar, idUt);

	}

	private void prenotaTurno(int idCar, int idUt) {
		cercaController.prenotaTurno(idCar, idUt);
	}

	private void partecipaEvento(int idEvent, int idUt) {
		cercaController.partecipaEvento(idEvent, idUt);

	}

	// molto da cambiare

	public CercaCaritas() throws NumberFormatException, SQLException {	
		cercaController = new CercaCaritasController();
	}

	public void initMarkers(int idUser, String ruolo) {
		this.ruolo = ruolo;
		this.idUser = idUser;
		
		if (this.ruolo.contentEquals(CARITAS)) {
		markerEventi =	cercaController.initMarkersEvento(this.idUser);
		for (MarkerID markerE : markerEventi) {
			markerE.getMarker().setVisible(false);
		}
		markerDonazioni =	cercaController.initMarkersDonazione(this.idUser);
		for (MarkerID markerD : markerDonazioni) {
			markerD.getMarker().setVisible(false);
		}
		}else if(this.ruolo.contentEquals(VOLUNTEER)) {
			markerEventi=cercaController.initMarkersEvento();
			for (MarkerID markerE : markerEventi) {
				markerE.getMarker().setVisible(false);
			}
		}

		markerClick = Marker.createProvided(Marker.Provided.ORANGE).setVisible(true);

		markerCaritas = cercaController.initMarkersCaritas();
		for (MarkerID markerC : markerCaritas) {
			markerC.getMarker().setVisible(true);
		}
		
	}


	public void initMapAndControls(Projection projection) {

	
		logger.trace("begin initialize");

//	     
		mapView.setCustomMapviewCssURL(getClass().getResource("/custom_mapview.css"));

		leftControls.setExpandedPane(optionsLocations);

	
		setControlsDisable(false);

		VBox obj = ((VBox) optionsLocations.getContent());
		listaBottoni = obj.getChildren();
		listaBottoniOld = FXCollections.observableArrayList();

		// wire up the location buttons
		buttonBack.setOnAction(event -> indietro());
		buttonBacheca.setOnAction(event -> vediNecessita(idCaritas, idUser));
		buttonPromuoviEvento.setOnAction(event -> promuoviEvento(idCaritas, idUser));
		buttonTurnoVolontariato.setOnAction(event -> prenotaTurno(idCaritas, idUser));
		buttonEvento.setOnAction(event -> partecipaEvento(idEvento, idUser));
		buttonDonazione.setOnAction(event -> apriDonazione(idCaritas, idUser));
		buttonAllLocations.setOnAction(event -> {
			CercaCaritasController cercaCaritas = new CercaCaritasController();
			cercaCaritas.initMap2(idUser, markerClick.getPosition().getLatitude().toString(), markerClick.getPosition().getLongitude().toString());
			

		});
		logger.trace("location buttons done");

		buttonDonazione.setVisible(false);
		buttonTurnoVolontariato.setVisible(false);
		buttonBacheca.setVisible(false);
		buttonEvento.setVisible(false);
		buttonAllLocations.setVisible(false);
		buttonPromuoviEvento.setVisible(false);
		// wire the zoom button and connect the slider to the map's zoom
		buttonZoom.setOnAction(event -> mapView.setZoom(ZOOMDEFAULT));
		sliderZoom.valueProperty().bindBidirectional(mapView.zoomProperty());

		labelCenter.textProperty().bind(Bindings.format("center: %s", mapView.centerProperty()));
		labelZoom.textProperty().bind(Bindings.format("zoom: %.0f", mapView.zoomProperty()));
		logger.trace("options and labels done");

		// watch the MapView's initialized property to finish initialization
		mapView.initializedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				afterMapIsInitialized();
			}
		});

		// observe the map type radiobuttons
		mapTypeGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
			logger.debug("map type toggled to {}", newValue.toString());
			MapType mapType = MapType.OSM;
			if (newValue == radioMsOSM) {
				mapType = MapType.OSM;
		
			} else if (newValue == radioMsWMS) {
				mapView.setWMSParam(wmsParam);
				mapType = MapType.WMS;
			} else if (newValue == radioMsXYZ) {
				mapView.setXYZParam(xyzParams);
				mapType = MapType.XYZ;
			}
			mapView.setMapType(mapType);
		});
		mapTypeGroup.selectToggle(radioMsOSM);

		setupEventHandlers();
		switch (this.ruolo) {
		case VOLUNTEER:
			setMarkerVolontario();
			break;
			
		case SHOP:
			setMarkerNegozio();
			break;
			
		case CARITAS:
			setMarkerCaritas();
			break;
			
		default:
			break;
		}



		logger.trace("marker checks done");

		
		// finally initialize the map view
		logger.trace("start map initialization");
		mapView.initialize(Configuration.builder().projection(projection).showZoomControls(false).build());
		logger.debug("initialization finished");

	}

	
	private void setMarkerCaritas() {

		for (MarkerID markerCaritastemp : markerCaritas) {
			checkCaritasMarker.setGraphic(new ImageView(
					new Image(markerCaritastemp.getMarker().getImageURL().toExternalForm(), 16.0, 16.0, true, true)));
			checkCaritasMarker.selectedProperty().bindBidirectional(markerCaritastemp.getMarker().visibleProperty());
		}
		
		
		for (MarkerID markerDonazione : markerDonazioni) {
			checkDonazioneMarker.setGraphic(new ImageView(
					new Image(markerDonazione.getMarker().getImageURL().toExternalForm(), 16.0, 16.0, true, true)));
			checkDonazioneMarker.selectedProperty()
					.bindBidirectional(markerDonazione.getMarker().visibleProperty());

		}
		for (MarkerID markerEvento : markerEventi) {
			checkEventoMarker.setGraphic(new ImageView(
					new Image(markerEvento.getMarker().getImageURL().toExternalForm(), 16.0, 16.0, true, true)));
			checkEventoMarker.selectedProperty().bindBidirectional(markerEvento.getMarker().visibleProperty());
		}
	


		checkClickMarker.setGraphic(
				new ImageView(new Image(markerClick.getImageURL().toExternalForm(), 16.0, 16.0, true, true)));
		checkClickMarker.selectedProperty().bindBidirectional(markerClick.visibleProperty());
		
	}
	
	
	private void setMarkerNegozio() {
		for (MarkerID markerCaritas2 : markerCaritas) {
			checkCaritasMarker.setGraphic(new ImageView(
					new Image(markerCaritas2.getMarker().getImageURL().toExternalForm(), 16.0, 16.0, true, true)));
			checkCaritasMarker.selectedProperty().bindBidirectional(markerCaritas2.getMarker().visibleProperty());
		}

		checkClickMarker.setGraphic(
				new ImageView(new Image(markerClick.getImageURL().toExternalForm(), 16.0, 16.0, true, true)));
		checkClickMarker.selectedProperty().bindBidirectional(markerClick.visibleProperty());

	}
	
	
	private void setMarkerVolontario() {
		for (MarkerID markerEvento : markerEventi) {
			checkEventoMarker.setGraphic(new ImageView(
					new Image(markerEvento.getMarker().getImageURL().toExternalForm(), 16.0, 16.0, true, true)));
			checkEventoMarker.selectedProperty().bindBidirectional(markerEvento.getMarker().visibleProperty());
		}

		for (MarkerID markerCaritas2 : markerCaritas) {
			checkCaritasMarker.setGraphic(new ImageView(
					new Image(markerCaritas2.getMarker().getImageURL().toExternalForm(), 16.0, 16.0, true, true)));
			checkCaritasMarker.selectedProperty().bindBidirectional(markerCaritas2.getMarker().visibleProperty());
		}
	

		checkClickMarker.setGraphic(
				new ImageView(new Image(markerClick.getImageURL().toExternalForm(), 16.0, 16.0, true, true)));
		checkClickMarker.selectedProperty().bindBidirectional(markerClick.visibleProperty());
	}

	/**
	 * initializes the event handlers.
	 */
	
	private void setVisibilityButton() {

		for (Node node : listaBottoni) {
			node.setVisible(false);
			buttonBack.setVisible(true);
		}
	}
	
	private void setupEventHandlers() {

		markerClick.setPosition(RomaCentro);

		mapView.addEventHandler(MapViewEvent.MAP_CLICKED, event -> {
			event.consume();
			final Coordinate newPosition = event.getCoordinate().normalize();
			labelEvent.setText("Event: map clicked at: " + newPosition);

			
			switchPosition(newPosition);
			setVisibilityButton();

		});

		// add an event handler for MapViewEvent#MAP_EXTENT and set the extent in the
		// map
		mapView.addEventHandler(MapViewEvent.MAP_EXTENT, event -> {
			event.consume();
			mapView.setExtent(event.getExtent());
		});

		// add an event handler for extent changes and display them in the status label
		mapView.addEventHandler(MapViewEvent.MAP_BOUNDING_EXTENT, event -> {
			event.consume();
			labelExtent.setText(event.getExtent().toString());
		});

		

		mapView.addEventHandler(MarkerEvent.MARKER_CLICKED, event -> {

			event.consume();
			Marker marker = event.getMarker();		
			for (MarkerID markerC : markerCaritas) {
				if (marker.getId().equals(markerC.getMarker().getId())) {
					logger.debug("Hai cliccato sulla caritas.");
					updateButtonsBox(MarkerType.CARITASMARKER);
					this.idCaritas = markerC.getId();
				}
			}
			if (markerEventi != null) {
			for (MarkerID markerE : markerEventi) {
				if (marker.getId().equals(markerE.getMarker().getId())) {
					logger.debug("Hai cliccato un evento");
					updateButtonsBox(MarkerType.EVENTO);
					this.idEvento = markerE.getId();
				}
			}}
			checkPositionButton(marker);
			labelEvent.setText("Event: marker clicked: " + marker.getId());
		});
		mapView.addEventHandler(MarkerEvent.MARKER_RIGHTCLICKED, event -> {
			event.consume();
			labelEvent.setText("Event: marker right clicked: " + event.getMarker().getId());

		});
		mapView.addEventHandler(MapLabelEvent.MAPLABEL_CLICKED, event -> {
			event.consume();
			labelEvent.setText("Event: label clicked: " + event.getMapLabel().getText());
		});
		mapView.addEventHandler(MapLabelEvent.MAPLABEL_RIGHTCLICKED, event -> {
			event.consume();
			labelEvent.setText("Event: label right clicked: " + event.getMapLabel().getText());
		});

		logger.trace("map handlers initialized");
	}
	
	
	
	
	private void switchPosition(Coordinate newPosition) {
		if (markerClick.getVisible()) {

			markerClick.setPosition(newPosition);
			// adding can only be done after coordinate is set
			mapView.addMarker(markerClick);

		}
		
	}




	public void checkPositionButton( Marker marker) {
	
	
		if (marker.equals(markerClick)) {
			updateButtonsBox(MarkerType.MAP);
		}
	}
	
	

	public void updateButtonsBox(MarkerType markerType) {
		for (Node nod : listaBottoniOld) {
			if (!listaBottoni.contains(nod)) {
				listaBottoni.add(nod);
			}
		}

		ObservableList<Node> lista = listaBottoni;
		List<Node> listaBottoniDaRimuovere = new ArrayList<>();
		searchButtonsToRemoveByUser(this.ruolo, markerType, lista, listaBottoniDaRimuovere);
		removeButtons(lista, listaBottoniDaRimuovere);
		showButtons(lista, listaBottoniDaRimuovere);
		listaBottoniOld.addAll(listaBottoniDaRimuovere);
	}

	public void showButtons(ObservableList<Node> lista, List<Node> listaBottoniDaRimuovere) {
		// Mostra tutti i bottoni in ordine
		for (Node node : lista) {
			Button btn = (Button) node;
			if (!listaBottoniDaRimuovere.contains(btn))
				btn.setVisible(true);
		}
	}

	public void removeButtons(ObservableList<Node> lista, List<Node> listaBottoniDaRimuovere) {
		for (Node node : listaBottoniDaRimuovere) {
			lista.remove(node);
		}
	}

	
	
	public void removeButtonEventi(String ruolo,ObservableList<Node> lista, List<Node> listaBottoniDaRimuovere) {
		for (Node node : lista) {
			Button btn = (Button) node;
			switch (ruolo) {
			case VOLUNTEER:
				switch (btn.getId()) {
				case PRO:
				case BAC:
				case TURN:
				case ALL:
				case DON:
					listaBottoniDaRimuovere.add(btn);
					break;
				default:
					break;
				}
				break;
			case SHOP:
				switch (btn.getId()) {
				case EVE:
				case PRO:
				case TURN:
				case ALL:
				case BAC:
				case DON:
					listaBottoniDaRimuovere.add(btn);
					break;
				default:
					break;
				}
				break;
				
				
			case CARITAS:
				switch (btn.getId()) {
				case EVE:
				case PRO:
				case TURN:
				case ALL:
				case BAC:
				case DON:
					listaBottoniDaRimuovere.add(btn);
					break;
				default:
					break;
				}
				break;
				default:
					break;

			}
			
		}
	}
	public void removeButtonCaritas(String ruolo,ObservableList<Node> lista, List<Node> listaBottoniDaRimuovere) {
		for (Node node : lista) {
			Button btn = (Button) node;
			switch (ruolo) {
			case VOLUNTEER:
				switch (btn.getId()) {
				case EVE:
				case PRO:
				case ALL:

					listaBottoniDaRimuovere.add(btn);
					break;
				default:
					break;
				}
			break;

	

		case SHOP:
				switch (btn.getId()) {
				case EVE:
				case TURN:
				case ALL:

					listaBottoniDaRimuovere.add(btn);
					break;
				default:
					break;
				}
			
			break;
			
		case CARITAS:
				switch(btn.getId()) {
					case EVE:
					case TURN:
					case ALL:
					case DON:
					case BAC:	
						listaBottoniDaRimuovere.add(btn);
						break;
					default:
						break;
				}
				break;
				
			default:
				break;
			}
		}
	}
	
	
	public void searchButtonsToRemoveByUser(String ruolo, MarkerType type, ObservableList<Node> lista,
			List<Node> listaBottoniDaRimuovere) {
	
		
		
		if (type.equals(MarkerType.CARITASMARKER)) {
		removeButtonCaritas( ruolo,lista,listaBottoniDaRimuovere);
		}
		if (type.equals(MarkerType.EVENTO)) {
		removeButtonEventi(ruolo,lista,listaBottoniDaRimuovere);
		}
		if (type.equals(MarkerType.MAP)) {
			for (Node node : lista) {
				Button btn = (Button) node;
				switch (btn.getId()) {
				case PRO:
				case BAC:
				case TURN:
				case EVE:
				case DON:
					listaBottoniDaRimuovere.add(btn);
					break;
					default:
						break;
				}
			}
		}

	}
	
	
	private void setControlsDisable(boolean flag) {
		topControls.setDisable(flag);
		leftControls.setDisable(flag);
	}


	private void afterMapIsInitialized() {
		logger.trace("map intialized");
		logger.debug("setting center and enabling controls...");
		// start at the harbour with default zoom
		mapView.setZoom(ZOOMDEFAULT);
		mapView.setCenter(RomaCentro);
		// add the markers to the map - they are still invisible

		if (this.ruolo.contentEquals(CARITAS) || this.ruolo.contentEquals(VOLUNTEER)) {
		for (int i = 0; i < markerEventi.size(); i++) {
			mapView.addMarker(markerEventi.get(i).getMarker());
		}}
	
		if(this.ruolo.contentEquals(CARITAS)) {
		for (int i = 0; i < markerDonazioni.size(); i++) {
			mapView.addMarker(markerDonazioni.get(i).getMarker());
		}}
		for (int i = 0; i < markerCaritas.size(); i++) {
			mapView.addMarker(markerCaritas.get(i).getMarker());
		}
		setControlsDisable(false);
	}



	public void setUser(int id, String ruolo) {
		this.idUser = id;
		this.ruolo = ruolo;
		

	}
}
