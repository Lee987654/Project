package Shop_pack;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;


public class ShopController implements Initializable {
	Connection conn;
	
	@FXML ComboBox comboProd, comboWeather, comboSize;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		String url = "jdbc:oracle:thin:@localhost:xe";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(url, "hr", "hr");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}//end of initialize
	
}//end of ShopController
	
	
