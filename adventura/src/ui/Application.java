package ui;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logika.Hra;
import logika.IHra;

public class Application extends javafx.application.Application {

	public static void main(String[] args) {
		if (args.length == 0) {
			launch(args);
		} else {
			if (args[0].equals("-text")) {
				IHra hra = new Hra();
				TextoveRozhrani ui = new TextoveRozhrani(hra);
				ui.hraj();
			} else {
				System.out.println("Neplatný parameter.");
			}
		}

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass()
				.getResource("Home.fxml"));
		Parent root = loader.load();
		
		Controller c = loader.getController();
		
				
		primaryStage.setTitle("Adventúra");
		primaryStage.setScene(new Scene (root));
		primaryStage.show();		
	}
}
