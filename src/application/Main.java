package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class Main extends Application {
	
	public static Stage stage;
	
	
	@Override
	public void start(Stage primaryStage) {
		System.out.println("Test");
		try {
			// give access to the other controllers to this primary stage!
			stage = primaryStage;
					
			// Connect to the FXML (contains our layout) and load it in
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation( Main.class.getResource("view/CoverViewer.fxml") );
			BorderPane layout = (BorderPane) loader.load();
						
			// Put the layout onto the scene
			Scene scene = new Scene( layout );
						
			// Set the scene on the stage
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
