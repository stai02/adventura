package ui;

import logika.Hra;
import logika.IHra;


import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


public class Controller extends GridPane {
	
	IHra hra = new Hra();
	
	@FXML private TextField vstupniText;
	
	@FXML private TextArea textovePole;
	
	@FXML public void odeslaniPrikazu() {
		String prikaz = vstupniText.getText();
		String odpoved =
				hra.zpracujPrikaz(prikaz);
		textovePole.appendText(odpoved);
		vstupniText.setText("");
	}
	
	@FXML 
	public void ovladacUdalosti() {
		
	}
	public void inicializuj(IHra hra) {
		
	}

}
