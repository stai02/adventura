package ui;

import logika.Hra;
import logika.IHra;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;



public class HomeController extends GridPane {
	
	/*javafx.scene.image.Image(InputStream is);*/

	IHra hra = new Hra();
	/*HerniPlan plan = new HerniPlan();*/
	
	@FXML private TextField vstupniText;
	@FXML private TextArea textovePole;
	@FXML private TextArea postavy;
	@FXML private TextArea vychody;

	@FXML private MenuItem novahra;
	@FXML private MenuItem koniec;
	@FXML private MenuItem napoveda;
    @FXML private ImageView mapa;

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
		/*novahra.setOnAction(this::novaHra);*/
		/*koniec.setO
		koniec.setOnAction(e -> {vychody.setText("KONIEC");});
		napoveda.setOnAction(this::ukazNapovedu);*/
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

	}

}
