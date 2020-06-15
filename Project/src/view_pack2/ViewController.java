package view_pack2;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Shop_pack.Member;
import Shop_pack.Product;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class ViewController implements Initializable {
	@FXML
	ListView<String> listView;
	@FXML
	TableView<Product> tableView;
	@FXML
	ImageView imageView;
	@FXML
	ComboBox<String> comboPublic, comboPublic2, comboSize;
	@FXML
	Button btnLogin, btnSignUp, btnSel, btnBuy;
	@FXML
	TextField txtNum;

	Connection conn;
	Member loginMember = new Member("", "", "");
	ObservableList<Member> memList = FXCollections.observableArrayList();
	ObservableList<Product> selectedList = FXCollections.observableArrayList();
	ObservableList<Product> prodList = FXCollections.observableArrayList();
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
		btnLogin.setOnAction((e) -> handleBtnLoginAction(e));
		btnSignUp.setOnAction((e) -> handleBtnSignUpAction(e));

		imageView.setImage(new Image("/view_pack/images/Clothes01.png"));

		txtNum.textProperty().addListener(new ChangeListener<String>() {// 갯수 입력칸에 숫자이외의 것은 못 넣게 함.
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					txtNum.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

		comboPublic.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {//분류[1] 선택->db에서 테이블 쿼리->분류[2] 입력

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldVal, String newVal) {
				String sql = "select PROD_ID, KIND_1, KIND_2, PRICE, PROD_SIZE FROM PRODUCT WHERE KIND_1 IN (?)";
				prodList.clear();
				ObservableList<String> kind2List = FXCollections.observableArrayList();
				try {
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, newVal);
					ResultSet rs = pstmt.executeQuery();

					int i = 0;
					while (rs.next()) {

						Product product = new Product(rs.getString("PROD_ID"), rs.getString("KIND_1"),
								rs.getString("KIND_2"), rs.getString("PRICE"), rs.getString("PROD_SIZE"), null);
						prodList.add(product);
						kind2List.add(rs.getString("KIND_2"));//리스트 값 확인용
						System.out.println(prodList.get(i).getProd_Id());
						i++;
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				comboPublic2.setItems(kind2List);
				comboPublic2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

					@Override
					public void changed(ObservableValue<? extends String> arg0, String oldVal, String newVal) {
						for (int i = 0; i < prodList.size(); i++) {

							if (newVal.equals(prodList.get(i).getKind_2())) {
								imageView.setImage(
										new Image("/view_pack/images/" + prodList.get(i).getProd_Id() + ".PNG"));
								//이미지를 입력한 후에 분류[1]을 변경하면 진행은 되지만 오류가 발생함.
							}
						}
					}

				});
			}
		});
		
		btnSel.setOnAction(new EventHandler<ActionEvent>() {//선택버튼. 구매할 상품분류를 다 정해서 리스트에 입력.

			@Override
			public void handle(ActionEvent arg0) {
				handleBtnselectAction(arg0);
//				if (comboPublic.getValue() == null) {
//					System.out.println("분류[1]을 선택하세요.");
//				} else if (comboPublic2.getValue() == null) {
//					System.out.println("분류[2]를 선택하세요.");
//				} else if (comboSize.getValue() == null) {
//					System.out.println("Size를 선택하세요.");
//				} else if ((txtNum.getText().equals(null)) || (txtNum.getText().equals(""))) {
//					System.out.println("갯수를 선택하세요.");
//				} else {
//					Product selectedProduct = new Product("prod_id", comboPublic.getValue(), comboPublic2.getValue(),
//							txtNum.getText(), comboSize.getValue());
//					selectedList.add(selectedProduct);
//				}
			}
			
		});
		
		btnBuy.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				handleBtnRegAction(arg0);
//				if (loginMember.getId().equals("")) {
//					System.out.println("로그인이 필요합니다.");
//				} else if (selectedList.size() == 0) {
//					System.out.println("상품을 선택하세요.");
//				} else {
//					System.out.println("구매가 완료되었습니다.");//구매 테이블 생성, db연결해서 상품정보, 갯수를 구매 테이블에 입력.
//					String sql = "insert into MEMBER_BUY(ID, PROD_ID, PROD_SIZE, PROD_NUM, TOTAL_BUY, )"
//							+ "VALUES(?, ?, ?, ?, ?)";
//					try {
//						PreparedStatement pstmt = conn.prepareStatement(sql);
//						pstmt.setString(1, "");//내용 넣기
//						pstmt.setString(2, "");
//						pstmt.setString(3, "");
//						pstmt.setString(4, "");
//						pstmt.setString(5, "");
//					} catch (SQLException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
			}

		});
	}//end of initialize
		//comboPublic 에 change action걸고 바뀔때마다 선택된 값에 맞는 이미지를 보여준다.->db에서 가져온 데이터에서 id값을 빼서 이미지뷰에 id값+이미지.
		//product에는 사이즈가 null값으로 지정되어있다. 수량까지 선택한 후 확인 버튼을 누르면 해당 product에 size를 지정하고 테이블 란에 리스트를 쭉 띄워준 뒤 가격*수량으로 합계가격을 도출해준다., 사이즈와 수량, 분류가 입력되지 않으면 팝업을 띄운다.
		//구매확정 버튼을 누르면 로그인 멤버가 null이 아니라면 테이블로 들어갈 수 있게 만든다. else->로그인이 필요합니다 팝업 띄우기.

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

			TextField txtID = (TextField) parent.lookup("#txtID");
			PasswordField txtPassword = (PasswordField) parent.lookup("#txtPassword");
			Button inBtnLogin = (Button) parent.lookup("#btnLogin");

			inBtnLogin.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {

					String sql = "select ID, PASSWORD, NAME from MEMBER";

					try {
						PreparedStatement pstmt = conn.prepareStatement(sql);
						ResultSet rs = pstmt.executeQuery();//지저분함. db에서 추출하려하지말고 리스트에 다 넣은 다음에 리스트에서 맞는거 출력해야겠다.
						while (rs.next()) {

							Member member = new Member(rs.getString("ID"), rs.getString("PASSWORD"),
									rs.getString("Name"));
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
					loginStage.close();
					btnLogin.setText(loginMember.getName() + "님 환영합니다.");
				}
			});

			Button btnCancel = (Button) parent.lookup("#btnCancel");
			btnCancel.setOnAction((e) -> loginStage.close());

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

			Button btnCancel = (Button) parent.lookup("#btnCancel");
			btnCancel.setOnAction((e) -> signUpStage.close());

			Scene scene = new Scene(parent);
			signUpStage.setScene(scene);
			signUpStage.show();
			signUpStage.setResizable(false);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//end of handleBtnSignUp..

	public void handleBtnRegAction(ActionEvent e) {
		if (loginMember.getId().equals("")) {
			messagePopup("로그인하세요.");
		} else {
			
			for(int i=0; i<selectedList.size(); i++) {
			String sql = "insert into MEMBER_BUY(ID, PROD_ID, PROD_SIZE, PROD_NUM, TOTAL_BUY)"
			+ "VALUES(?, ?, ?, ?, ?)";
			
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, memList.get(0).getId());//내용 넣기
				pstmt.setString(2, selectedList.get(i).getProd_Id());
				pstmt.setString(3, selectedList.get(i).getProd_Size());
				pstmt.setString(4, selectedList.get(i).getProd_Count());
				pstmt.setInt(5, Integer.parseInt(selectedList.get(i).getProd_Count())*Integer.parseInt(selectedList.get(i).getPrice()));
				pstmt.executeUpdate();
				
				selectedList.clear();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
			messagePopup("구매가 완료되었습니다.");
		}
	}

	public void handleBtnselectAction(ActionEvent e) {
//		if (comboPublic.getValue() == null) {
//		System.out.println("분류[1]을 선택하세요.");
//	} else if (comboPublic2.getValue() == null) {
//		System.out.println("분류[2]를 선택하세요.");
//	} else if (comboSize.getValue() == null) {
//		System.out.println("Size를 선택하세요.");
//	} else if ((txtNum.getText().equals(null)) || (txtNum.getText().equals(""))) {
//		System.out.println("갯수를 선택하세요.");
//	} else {
//		Product selectedProduct = new Product("prod_id", comboPublic.getValue(), comboPublic2.getValue(),
//				txtNum.getText(), comboSize.getValue());
//		selectedList.add(selectedProduct);
//	}
//
//		
		if (comboPublic.getValue() == null || comboPublic.getValue().equals("")) {
			messagePopup("[1]분류 선택하세요.");
		} else if (comboPublic2.getValue() == null || comboPublic2.getValue().equals("")) {
			messagePopup("[2]분류 선택하세요.");
		} else if (comboSize.getValue() == null|| comboSize.getValue().equals("")) {
			messagePopup("SIZE 선택하세요.");
		} else if (txtNum.getText().equals(null)|| txtNum.getText().equals("")) {
			messagePopup("수량 선택하세요.");
		} else {
			for(int i=0; i<prodList.size(); i++) {
				if(prodList.get(i).getKind_2().equals(comboPublic2.getValue())) {
					Product selectedProduct = new Product(prodList.get(i).getProd_Id(), prodList.get(i).getKind_1(), prodList.get(i).getKind_2(),
							prodList.get(i).getPrice(), comboSize.getValue(), txtNum.getText());
					selectedProduct.setProd_Count(txtNum.getText());
					selectedList.add(selectedProduct);
				}
			}			
			tableView.setItems(selectedList);
			tableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("kind_2"));
			tableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory("prod_Size"));
			tableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory("price"));
			tableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory("prod_Count"));
						
		}
//		TableColumn<Phone, ?> tcSmartPhone = tableView.getColumns().get(0);
//		tcSmartPhone.setCellValueFactory(new PropertyValueFactory("smartPhone"));
//		
//		//tableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("smartPhone"));
		
	}
public void messagePopup(String message) {
		
		
		// 컨테이너(HBox) 생성.
		HBox hbox = new HBox();
		hbox.setStyle("-fx-background-color: black; -fx-background-radius: 20;");
		hbox.setAlignment(Pos.CENTER);
		// 컨트롤(ImageView)
//		ImageView imageView = new ImageView();
//		imageView.setImage(new Image("icons/dialog-info.png"));
//		imageView.setFitHeight(30);;
//		imageView.setFitWidth(30);
		// 컨트롤(Label)
		Label label = new Label();
		HBox.setMargin(label, new Insets(0,5,0,5));
		label.setText(message);
		label.setStyle("-fx-text-fill: white; ");
		// 컨테이너에 컨트롤 담기.
		//hbox.getChildren().add(imageView);
		hbox.getChildren().add(label);
		// 팝업 생성. 컨테이너 담아서 팝업 호출.
		Popup popup = new Popup();
		popup.getContent().add(hbox);
		popup.setAutoHide(true);
		popup.show(btnSel.getScene().getWindow());
	
	
	}
}


