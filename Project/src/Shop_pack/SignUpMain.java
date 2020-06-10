package Shop_pack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SignUpMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("ShopSignUp.fxml"));
		Scene scene = new  Scene(parent);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
	public static void main(String[] args) {
		launch(args);
	}
}
