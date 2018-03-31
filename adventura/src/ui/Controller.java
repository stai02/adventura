package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import logika.Hra;
import logika.IHra;

public class Controller extends VBox {
	
	@FXML public Button promenna;
	
	@FXML private TextField vstupniText;
	
	@FXML private TextArea textovePole;
	
	@FXML public void odeslaniPrikazu() {
		String prikaz = vstupniText.getText();
		String odpoved = 
				hra.zpracujPrikaz(prikaz);
		textovePole.appendText(odpoved);
	}
	
	@FXML
	public void ovladacUdalosti() {
		
	}
}
