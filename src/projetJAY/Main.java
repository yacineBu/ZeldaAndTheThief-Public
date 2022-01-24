package projetJAY;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import projetJAY.controleur.ControleurKeyPressed;
import projetJAY.controleur.ControleurKeyReleased;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = FXMLLoader.load(getClass().getResource("vue/vue1.fxml"));
			Scene scene = new Scene(root,480,480);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			root.requestFocus();
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Zelda and the thief");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
