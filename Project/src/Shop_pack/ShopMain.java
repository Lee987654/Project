package Shop_pack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ShopMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Shop.fxml.fxml"));
		Scene scene = new Scene(root);

		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}

//회원 테이블  -> id, password, 이름
//구매내역 테이블 -> id, 상품, size, 개수, 총구매액
//상품 테이블 -> 상품 이름, 종류, 가격, 사이즈

