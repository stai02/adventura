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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


public class HomeController extends GridPane implements Observer {
	
	IHra hra = new Hra();
	
	@FXML private TextField vstupniText;
	@FXML private TextArea textovePole;
	@FXML private ListView<String> seznamMistnosti;
	@FXML private ListView<String> seznamPostav;
	@FXML private ListView<String> seznamVeci;

	@FXML private MenuItem novahra;
	@FXML private MenuItem koniec;
	@FXML private MenuItem napoveda;
    @FXML private ImageView mapa;
    @FXML private Rectangle ikona;
    @FXML private Rectangle wc;
    @FXML private Rectangle zborovna;
    @FXML private Rectangle bufet;

    

    @FXML private Button button;

    
	@FXML private ImageView batoh1;
    @FXML private ImageView batoh2;
    @FXML private ImageView batoh3;
    @FXML private Menu menu;

	/*Image image = new Image(getResourceAsStream(getResourceAsStream("@../../resources/mapa.PNG"));
	Button button = new Button("",new ImageView(image));*/
    
	@FXML public void odeslaniPrikazu() {
		String prikaz = vstupniText.getText();
		String odpoved =
				hra.zpracujPrikaz(prikaz);
		textovePole.setText(odpoved);
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
		String odpoved =
				hra.zpracujPrikaz("koniec");
		textovePole.setText(odpoved);
		vstupniText.setDisable(true);
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Potvrdiť ukončenie hry");
		alert.setHeaderText("Ukončiť hru");
		alert.setContentText("Naozaj chcete ukončiť hru ?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			System.exit(0);
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
		seznamPostav.getItems().clear();
		seznamVeci.getItems().clear();
		inicializuj(novaHra);		
	}
		
	
	
	public void inicializuj(IHra hra) {
		this.hra = hra;
		vstupniText.setDisable(false);
		bufet.setVisible(true);
		wc.setVisible(true);
		zborovna.setVisible(true);
		ikona.setTranslateX(260);
		ikona.setTranslateY(250);
	    textovePole.setText(hra.vratUvitani());
		seznamMistnosti.getItems().addAll(hra.getHerniPlan().getAktualniProstor().popisVychodu());
		seznamPostav.getItems().addAll(hra.getHerniPlan().popisPostavVMiestnosti());
		seznamVeci.getItems().add(hra.getHerniPlan().getAktualniProstor().popisVeciVMiestnosti());
		hra.getHerniPlan().addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		seznamMistnosti.getItems().clear();
		seznamVeci.getItems().clear();
		seznamMistnosti.getItems().addAll(hra.getHerniPlan().getAktualniProstor().popisVychodu());
		seznamPostav.getItems().addAll(hra.getHerniPlan().popisPostavVMiestnosti());
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
