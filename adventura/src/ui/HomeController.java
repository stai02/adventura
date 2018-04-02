package ui;

import logika.Hra;
import logika.IHra;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


public class HomeController extends GridPane implements Observer {
	
	IHra hra = new Hra();
	
	@FXML private TextField vstupniText;
	@FXML private TextArea textovePole;
	@FXML private TextArea position;
	@FXML private ListView<String> seznamMistnosti;
	@FXML private ListView<String> seznamVeci;
	@FXML private HBox batoh;
	@FXML private MenuItem novahra;
	@FXML private MenuItem koniec;
	@FXML private MenuItem napoveda;
    @FXML private ImageView mapa;
    @FXML private ImageView batoh1;
    @FXML private ImageView batoh2;
    @FXML private ImageView batoh3;
    @FXML private ImageView batoh4;
    @FXML private Rectangle ikona;
    @FXML private Rectangle wc;
    @FXML private Rectangle zborovna;
    @FXML private Rectangle bufet;
    @FXML private Menu menu;

	/*Image image = new Image(getResourceAsStream(getResourceAsStream("@../../resources/mapa.PNG"));
	Button button = new Button("",new ImageView(image));*/
    
	@FXML public void odeslaniPrikazu() throws MalformedURLException {
		String prikaz = vstupniText.getText();
		if (prikaz.equals("nápoveda")) {
			zobrazNapovedu();
		} else if (prikaz.equals("koniec")) {
			ukoncenieHry();
		} else {
		String odpoved =
				hra.zpracujPrikaz(prikaz);
		textovePole.setText(odpoved);
		}
		vstupniText.setText("");
		if(hra.konecHry()) {
			vstupniText.setDisable(true);
		}
	}
		
		
	@FXML 
	public void ovladacUdalosti() {
		String odpoved =
				hra.zpracujPrikaz("koniec");
		textovePole.setText(odpoved);		
	}
	
	@FXML 
	public void ukoncenieHry() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Potvrdiť ukončenie hry");
		alert.setHeaderText("Ukončiť hru");
		alert.setContentText("Naozaj chcete ukončiť hru ?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			String odpoved =
					hra.zpracujPrikaz("koniec");
			textovePole.setText(odpoved);
			vstupniText.setDisable(true);
			System.exit(0);			
		} else {
			alert.close();
		}
	}
	
	@FXML 
	public void zobrazNapovedu() throws MalformedURLException {
		File myFile = new File ("C:\\Users\\Ivanka\\git\\adventura\\src\\ui\\napoveda.html");
		URL helpURL = myFile.toURI().toURL();
		WebView webView = new WebView();
		WebEngine engine = webView.getEngine();
		engine.load(helpURL.toString());
		Scene scene = new Scene(webView);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}	
	
	
	@FXML 
	public void novaHra() {
		hra.zpracujPrikaz("koniec");
		IHra novaHra = new Hra();
		seznamMistnosti.getItems().clear();
		seznamVeci.getItems().clear();
		inicializuj(novaHra);		
	}
		
	
	
	public void inicializuj(IHra hra) {
		this.hra = hra;
		vstupniText.setDisable(false);
		position.setEditable(false);
		position.setText(hra.getHerniPlan().getAktualniProstor().getNazov());
		bufet.setVisible(true);
		wc.setVisible(true);
		zborovna.setVisible(true);
		ikona.setTranslateX(260);
		ikona.setTranslateY(250);
		Tooltip tooltip = new Tooltip("Aktuálna pozícia");
		Tooltip.install(ikona, tooltip);
	    textovePole.setText(hra.vratUvitani());
		seznamMistnosti.getItems().addAll(hra.getHerniPlan().getAktualniProstor().popisVychodu());
		seznamVeci.getItems().add(hra.getHerniPlan().getAktualniProstor().popisVeciVMiestnosti());
		hra.getHerniPlan().addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		position.setText(hra.getHerniPlan().getAktualniProstor().getNazov());
		batoh.getChildren().clear();
		if (hra.getHerniPlan().getBatoh().jeVecVBatohu("paradajka")) {
			if (!batoh.getChildren().contains(batoh2)) {
				ImageView batoh2 = new ImageView();
				batoh2.setFitHeight(100);
				batoh2.setFitWidth(100);
				batoh.getChildren().add(batoh2);
				Image image = new Image(getClass().getResource("paradajka.PNG").toExternalForm());
				batoh2.setImage(image);
				Tooltip para = new Tooltip("Paradajka");
				Tooltip.install(batoh2, para);
			}
		}
		if (hra.getHerniPlan().getBatoh().jeVecVBatohu("cesnak")) {
			if (!batoh.getChildren().contains(batoh3)) {
				ImageView batoh3 = new ImageView();
				batoh3.setFitHeight(100);
				batoh3.setFitWidth(100);
				batoh.getChildren().add(batoh3);
				Image image = new Image(getClass().getResource("cesnak.PNG").toExternalForm());
				batoh3.setImage(image);
				Tooltip cesn = new Tooltip("Cesnak");
				Tooltip.install(batoh3, cesn);
			}
		}
		if (hra.getHerniPlan().getBatoh().jeVecVBatohu("medicinbal")) {
			if (!batoh.getChildren().contains(batoh1)) {
				ImageView batoh1 = new ImageView();
				batoh1.setFitHeight(100);
				batoh1.setFitWidth(100);
				batoh.getChildren().add(batoh1);
				Image image = new Image(getClass().getResource("medicinbal.PNG").toExternalForm());
				batoh1.setImage(image);
				Tooltip medi = new Tooltip("Medicinbal");
				Tooltip.install(batoh1, medi);
			}
		}
		if (hra.getHerniPlan().getBatoh().jeVecVBatohu("kluc")) {
			if (!batoh.getChildren().contains(batoh4)) {
				ImageView batoh4 = new ImageView();
				batoh4.setFitHeight(100);
				batoh4.setFitWidth(100);
				batoh.getChildren().add(batoh4);
				Image image = new Image(getClass().getResource("kluc.PNG").toExternalForm());
				batoh4.setImage(image);
				Tooltip kluc = new Tooltip("Kľúč");
				Tooltip.install(batoh4, kluc);
			}
		}
		seznamMistnosti.getItems().clear();
		seznamVeci.getItems().clear();
		seznamMistnosti.getItems().addAll(hra.getHerniPlan().getAktualniProstor().popisVychodu());
		seznamVeci.getItems().add(hra.getHerniPlan().getAktualniProstor().popisVeciVMiestnosti());
		if (hra.getHerniPlan().getAktualniProstor().getNazov().equals("prvé poschodie")) {
			ikona.setTranslateX(220);
			ikona.setTranslateY(170);
		}
		if (hra.getHerniPlan().getAktualniProstor().getNazov().equals("4.C")) {
			ikona.setTranslateX(260);
			ikona.setTranslateY(250);
		}
		if (hra.getHerniPlan().getAktualniProstor().getNazov().equals("toalety")) {
			ikona.setTranslateX(140);
			ikona.setTranslateY(240);
			bufet.setVisible(false);
		}
		if (hra.getHerniPlan().getAktualniProstor().getNazov().equals("zborovňa")) {
			ikona.setTranslateX(330);
			ikona.setTranslateY(200);
		}
		if (hra.getHerniPlan().getAktualniProstor().getNazov().equals("schody")) {
			ikona.setTranslateX(283);
			ikona.setTranslateY(100);
		}
		if (hra.getHerniPlan().getAktualniProstor().getNazov().equals("prízemie")) {
			ikona.setTranslateX(260);
			ikona.setTranslateY(10);
		}
		if (hra.getHerniPlan().getAktualniProstor().getNazov().equals("bufet")) {
			ikona.setTranslateX(95);
			ikona.setTranslateY(40);
			zborovna.setVisible(false);
		}
		if (hra.getHerniPlan().getAktualniProstor().getNazov().equals("skrinky")) {
			ikona.setTranslateX(315);
			ikona.setTranslateY(-35);
		}
		if (hra.getHerniPlan().getAktualniProstor().getNazov().equals("telocvičňa")) {
			ikona.setTranslateX(470);
			ikona.setTranslateY(-130);
			wc.setVisible(false);
		}
	}
}
