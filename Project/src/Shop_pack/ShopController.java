package Shop_pack

import java.net.URL;
import java.sql.Connection;
<<<<<<< HEAD
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
=======
import java.sql.DriverManager;
>>>>>>> branch 'master' of https://github.com/Lee987654/Project.git
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
<<<<<<< HEAD
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
	
=======
import javafx.scene.control.ComboBox;


>>>>>>> branch 'master' of https://github.com/Lee987654/Project.git
public class ShopController implements Initializable {
	Connection conn;//import java.sql
	
	
	
	@FXML private ImageView Image;
	@FXML private ComboBox<String> option1;
	@FXML private ComboBox<String> option2;
	@FXML private ComboBox<String> option3;
	@FXML private TextField txtTitle;
	
	@FXML ComboBox comboProd, comboWeather, comboSize;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		String url = "jdbc:oracle:thin:@localhost:1521";
		try {
			conn=DriverManager.getConnection(url, "hr", "hr");
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			
			e.printStackTrace();
<<<<<<< HEAD
			
 public void handleBtnRegAction(ActionEvent e) {
//	if (txtTitle.getText() == null || txtTitle.getText().contentEquals("")) {
	//				messagePopup("제목입력하세요.");
	} else if (option1.getValue() == null || option1.getValue().equals("")) {
					messagePopup("옵션 선택해주세요.");
	} else if (option2.getValue() == null || option2.getValue().equals("")) {
					messagePopup("옵션 선택해주세요.");
	} else if (option3.getValue() == null || option3.getValue().equals("")) {
					messagePopup("옵션 선택해주세요.");
	} else if (quantity.getValue() == null || quantity.getValue().equals("")) {
					messagePopup("수량 선택해주세요.");
	
	
	// DB 입력...Connection, PreparedStatement, executeUpdate();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy/MM/dd");
	String sql = "insert into board(title,password,publicity,exit_date,content)"
	+ "values(?,?,?,?,?)";
					
	try {
	PreparedStatement pstmt =conn.prepareStatement(sql);
//	pstmt.setString(1, txtTitle.getText());
//	pstmt.setString(2, txtPassword.getText());
	pstmt.setString(1, option1.getValue());
	pstmt.setString(2, option2.getValue());
	pstmt.setString(3, option3.getValue());
	pstmt.setString(4, quantity.format(formatter));
	
						
	int r = pstmt.executeUpdate();
	System.out.println(r + " 건 입력됨.");
	} catch (SQLException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
	}
		// 각 필드 초기화.
//		txtTitle.setText(null);
//		txtPassword.setText(null);
	    option1.setValue(null);
	    option2.setValue(null);
	    option3.setValue(null);
	   
	    txtContent.setText(null);
			} // end of if..
			
			
			} // end of handleBtnRegAction()
			
			
			public void messagePopup(String message) 
			
			{
				
				// 컨테이너(HBox) 생성.
				HBox hbox = new HBox();
				hbox.setStyle("-fx-background-color: black; -fx-background-radius: 20;");
				hbox.setAlignment(Pos.CENTER);
				// 컨트롤(ImageView)
				ImageView imageView = new ImageView();
				imageView.setImage(new Image("icons/dialog-info.png"));
				imageView.setFitHeight(30);;
				imageView.setFitWidth(30);
				// 컨트롤(Label)
				Label label = new Label();
				HBox.setMargin(label, new Insets(0,5,0,5));
				label.setText(message);
				label.setStyle("-fx-text-fill: white; ");
				// 컨테이너에 컨트롤 담기.
				hbox.getChildren().add(imageView);
				hbox.getChildren().add(label);
				// 팝업 생성. 컨테이너 담아서 팝업 호출.
				Popup popup = new Popup();
				popup.getContent().add(hbox);
				popup.setAutoHide(true);
				popup.show(btnReg.getScene().getWindow());
			
			
			}

			


		}
}// end of class		
		
=======
		}
		
		
	}//end of initialize
	
}//end of ShopController
	
	
>>>>>>> branch 'master' of https://github.com/Lee987654/Project.git
