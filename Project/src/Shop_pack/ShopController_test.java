package Shop_pack;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ShopController_test implements Initializable {
	@FXML
	TextField txtID;
	@FXML
	PasswordField txtPassword;
	@FXML
	Button btnLogin, btnSignUp;

	Connection conn;
	Member loginMember = new Member(null, null, null);
	ObservableList<Member> memList = FXCollections.observableArrayList();
	//LoginController loginController = new LoginController();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		String url = "jdbc:oracle:thin:@localhost:1521:xe";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, "hr", "hr");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		btnLogin.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				handleBtnLoginAction(arg0);
			}

		});

		btnSignUp.setOnAction((e) -> handleBtnSignUpAction(e));
	}//end of initialize

	public void handleBtnLoginAction(ActionEvent event) {

		Stage loginStage = new Stage(StageStyle.UTILITY);
		loginStage.initModality(Modality.WINDOW_MODAL);
		loginStage.initOwner(btnLogin.getScene().getWindow());

		try {

			Parent parent = FXMLLoader.load(getClass().getResource("ShopLogin.fxml"));
			Scene scene = new Scene(parent);
			loginStage.setScene(scene);
			loginStage.show();
			loginStage.setResizable(false);

			Button btnLogin = (Button) parent.lookup("#btnLogin");
			btnLogin.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					handleLoginAction(event);
					//loginMember.setId(loginController.loginMember.getId());
					//loginMember.setName(loginController.loginMember.getName());
					loginStage.close();
				}

			});

			Button btnCancel = (Button) parent.lookup("#btnCancel");
			btnCancel.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					loginStage.close();

				}

			});

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}//end of handleBtnLogin

	public void handleBtnSignUpAction(ActionEvent event) {

		Stage signUpStage = new Stage(StageStyle.UTILITY);
		signUpStage.initModality(Modality.WINDOW_MODAL);
		signUpStage.initOwner(btnLogin.getScene().getWindow());

		Parent parent;
		try {
			parent = FXMLLoader.load(getClass().getResource("ShopSignUp.fxml"));
			Scene scene = new Scene(parent);
			signUpStage.setScene(scene);
			signUpStage.show();
			signUpStage.setResizable(false);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}//end of handleBtnSignUp..

	public void handleLoginAction(ActionEvent event) {
		
		

		if (txtID.getText() == null || txtID.getText().contentEquals("")) {
			//messagePopup("ID를 입력하세요.");
		} else if (txtPassword.getText() == null || txtPassword.getText().contentEquals("")) {
			//messagePopup("Password를 입력하세요.");
		} else {
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
		}

	}//end of handleLoginAction.

}//end of ShopController2
