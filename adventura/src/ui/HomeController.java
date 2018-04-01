package ui;

import logika.Hra;
import logika.IHra;
import java.util.Observable;
import java.util.Observer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;



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

	}
	
	
	
/*	private void ukazNapovedu(ActionEvent event) {
		String helpURL = hra.vratEpilog();
		WebView webView = new WebView();
		WebEngine engine = webView.getEngine();
		engine.load(helpURL);
		
		Scene scene = new Scene(webView);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
		
		
		
	}*/
	
	
	public void inicializuj(IHra hra) {
		this.hra = hra;
	    textovePole.setText(hra.vratUvitani());
		seznamMistnosti.getItems().addAll(hra.getHerniPlan().getAktualniProstor().popisVychodu());
		seznamPostav.getItems().addAll(hra.getHerniPlan().popisPostavVMiestnosti());
		seznamVeci.getItems().add(hra.getHerniPlan().getAktualniProstor().popisVeciVMiestnosti());
		hra.getHerniPlan().addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		seznamMistnosti.getItems().clear();
		seznamPostav.getItems().clear();
		seznamVeci.getItems().clear();
		seznamMistnosti.getItems().addAll(hra.getHerniPlan().getAktualniProstor().popisVychodu());
		seznamPostav.getItems().addAll(hra.getHerniPlan().popisPostavVMiestnosti());
		seznamVeci.getItems().add(hra.getHerniPlan().getAktualniProstor().popisVeciVMiestnosti());
	}
}
