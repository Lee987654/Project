package Shop_pack;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Popup;

public class LoginController implements Initializable {

	@FXML
	TextField txtID;
	@FXML
	PasswordField txtPassword;
	//@FXML
	//Button btnLogin;
	Connection conn;
	ObservableList<Member> memList = FXCollections.observableArrayList();
	Member loginMember;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		String url = "jdbc:oracle:thin:@localhost:1521:xe";
//
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn = DriverManager.getConnection(url, "hr", "hr");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}//end of initialize

	public void handleLoginAction(ActionEvent e) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, "hr", "hr");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (txtID.getText() == null || txtID.getText().contentEquals("")) {
			//messagePopup("ID를 입력하세요.");
		} else if (txtPassword.getText() == null || txtPassword.getText().contentEquals("")) {
			//messagePopup("Password를 입력하세요.");
		} else {
			//ObservableList<Member> list = FXCollections.observableArrayList();
		
		String sql = "select ID, PASSWORD, NAME from MEMBER";

			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();//지저분함. db에서 추출하려하지말고 리스트에 다 넣은 다음에 리스트에서 맞는거 출력해야겠다.
				while (rs.next()) {

					Member member = new Member(rs.getString("ID"), rs.getString("PASSWORD"), rs.getString("Name"));
					memList.add(member);//memList에 회원이력을 전부 삽입.
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			for (int i = 0; i < memList.size(); i++) {
				if (memList.get(i).getId().equals(txtID.getText())
						&& memList.get(i).getPassword().equals(txtPassword.getText())) {
					System.out.println(memList.get(i).getName() + " 님 환영합니다.");
					//shopController2.loginMember = new Member(memList.get(i).getId(),memList.get(i).getPassword(),memList.get(i).getName());
					loginMember.setId(memList.get(i).getId());
					loginMember.setName(memList.get(i).getName());
					break;
				//만약 회원리스트와 맞는 아이디/비번이라면 창 종료.
				}
			}
			System.out.println(loginMember.getId() + "님 확인용입니다.");
		}
	}//end of handleLoginAction

//	public void messagePopup(String message) {
//		HBox hbox = new HBox();
//		hbox.setStyle("-fx-background-color: black; -fx-background=radius: 20;");
//		hbox.setAlignment(Pos.CENTER);
//
//		//		ImageView imageView = new ImageView();
//		//		imageView.setImage(new Image("/icons/dialog-info.png"));
//		//		imageView.setFitHeight(30); imageView.setFitWidth(30);
//
//		Label label = new Label();
//		label.setText(message);
//		label.setStyle("-fx-text-fill: white; ");
//
//		//hbox.getChildren().add(imageView);
//		hbox.getChildren().add(label);
//		HBox.setMargin(label, new Insets(0, 5, 0, 5));
//
//		Popup popup = new Popup();
//		popup.getContent().add(hbox);
//		popup.setAutoHide(true);//?
//		popup.show(btnLogin.getScene().getWindow());
//
//	}

}//end of LoginController
