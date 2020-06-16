package view_pack2;

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

public class SignUpController implements Initializable {
	@FXML TextField txtID, txtName;
	@FXML PasswordField txtPassword;
	@FXML Button btnSignUp, btnCancel;
	ObservableList<Member> memList = FXCollections.observableArrayList();
	Connection conn;
	PreparedStatement pstmt;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(url, "hr", "hr");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//end of initialize

	public void handleBtnSignUpAction(ActionEvent e) {
		
		String lstSql = "select ID, PASSWORD, Name from member";
		try {
			pstmt = conn.prepareStatement(lstSql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Member member = new Member(rs.getString("ID"), rs.getString("PASSWORD"),
						rs.getString("Name"));
				memList.add(member);// memList에 회원이력을 전부 삽입.
			}
			
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if(txtID.getText()==null || txtID.getText().contentEquals("")) {
			messagePopup("ID를 입력하세요.");
		} else if (txtPassword.getText()==null || txtPassword.getText().contentEquals("")) {
			messagePopup("비밀번호를 입력하세요.");
		} else if (txtName.getText() == null || txtName.getText().contentEquals("")) {
			messagePopup("개인정보(이름)을 입력하세요.");
		}else{
			int count = 0;
			for(int i=0; i<memList.size(); i++) {
				if(txtID.getText().equals(memList.get(i).getId())) 
					count++;
			}
			if(count!=0) {
				messagePopup("중복된 아이디가 존재합니다.");
			}else {
				String sql = "insert into MEMBER(id, password, name)" + "values(?,?,?)";
				
				try {
					PreparedStatement pstmt;
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, txtID.getText());
					pstmt.setString(2, txtPassword.getText());
					pstmt.setString(3, txtName.getText());
					
					int r = pstmt.executeUpdate();
					System.out.println(r + " 건 입력됨.");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				txtID.setText(null);
				txtPassword.setText(null);
				txtName.setText(null);
				
			}
		}
	}//end of handleBtnSignUpAction()
	
	public void messagePopup(String message) {
		HBox hbox = new HBox();
		hbox.setStyle("-fx-background-color: black; -fx-background=radius: 20;");
		hbox.setAlignment(Pos.CENTER);
	
//		ImageView imageView = new ImageView();
//		imageView.setImage(new Image("/icons/dialog-info.png"));
//		imageView.setFitHeight(30); imageView.setFitWidth(30);
		
		Label label = new Label();
		label.setText(message);
		label.setStyle("-fx-text-fill: white; ");
		
		//hbox.getChildren().add(imageView);
		hbox.getChildren().add(label);
		HBox.setMargin(label, new Insets(0,5,0,5));
		
		Popup popup = new Popup();
		popup.getContent().add(hbox);
		popup.setAutoHide(true);//?
		popup.show(btnSignUp.getScene().getWindow());
		
	}
//	
//	public void handleBtnCancelAction(ActionEvent e) {
//		
//	}
}//end of SignUpController
