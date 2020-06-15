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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
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
	Button btnSel, btnLogin, btnSignUp;
	@FXML
	TextField txtNum;
	@FXML
	VBox vboxList;
	
	Connection conn;
	Member loginMember = new Member("", "", "");//이걸 초기값을 null이 아닌걸로 설정해서 if문 써줬으면 됐을걸? ㅋㅋㅋㅋㅋㅋㅋ 븅신 ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ
	ObservableList<Member> memList = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		// txtNum.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, "hr", "hr");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		btnLogin.setOnAction((e)-> handleBtnLoginAction(e));
		btnSignUp.setOnAction((e) -> handleBtnSignUpAction(e));

		// listView.setItems(FXCollections.observableArrayList());
		// ObservableList<String> list = FXCollections.observableArrayList("���� ��",
		// "���� ����", "�ܿ� ��", "�ܿ� ����");
		// listView.setItems(list);

//	listView.getSelectionModel().selectedIndexProperty();
		// addListener(new ChangeListener<Number>() {

		// @Override
		// public void changed(ObservableValue<? extends Number> arg0, Number oldVal,
		// Number newVal) {
		// tableView.getSelectionModel().select(newVal.intValue());
		// tableView.scrollTo(newVal.intValue());
		// }

		// });

//		tableView.setItems(FXCollections.observableArrayList(
//				
//				new Phone("Ƽ����", "Clothes01.png"),
//				new Phone("����", "Clothes02.png"),
//				new Phone("�����", "Clothes03.png"),
//				new Phone("�ٶ�����", "Clothes04.png"),
//				new Phone("��Ʈ", "Clothes05.png"),
//				new Phone("�е�", "Clothes06.png"),
//				new Phone("û����", "Clothes07.png"),
//				new Phone("�����", "Clothes08.png"),
//				new Phone("������", "Clothes09.png"),
//				new Phone("����", "Clothes10.png"),
//				new Phone("��Ʈ", "Clothes11.png"),
//				new Phone("�Ź�", "Clothes12.png")
//				));
//		TableColumn<Phone, ?> tcSmartPhone = tableView.getColumns().get(0);
//		tcSmartPhone.setCellValueFactory(new PropertyValueFactory("smartPhone"));
//		
//		//tableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("smartPhone"));
//		
//		TableColumn<Phone, ?> tcImage = tableView.getColumns().get(1);
//		tcImage.setCellValueFactory(new PropertyValueFactory("image"));
//		
//		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Phone>() {
//
//			@Override
//			public void changed(ObservableValue<? extends Phone> observable, Phone oldVal, Phone newVal) {
//				imageView.setImage(new Image("/view_pack/images/" + newVal.getImage()));
//				
//			}
//			
//		});

		imageView.setImage(new Image("/view_pack/images/Clothes01.png"));

		txtNum.textProperty().addListener(new ChangeListener<String>() {// 갯수 입력칸에 숫자이외의 것은 못 넣게 함.
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					txtNum.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

		btnSel.setOnAction(new EventHandler<ActionEvent>() {//선택버튼. 구매할 상품분류를 다 정해서 리스트에 입력.

			@Override
			public void handle(ActionEvent arg0) {
				if (comboPublic.getValue() == null) {
					System.out.println("분류[1]을 선택하세요.");
				} else if (comboPublic2.getValue() == null) {
					System.out.println("분류[2]를 선택하세요.");
				} else if (comboSize.getValue() == null) {
					System.out.println("Size를 선택하세요.");
				} else if ((txtNum.getText().equals(null)) || (txtNum.getText().equals(""))) {
					System.out.println("갯수를 선택하세요.");
				} else {
					Product selectedProduct = new Product("prid_id", comboPublic.getValue(), comboPublic2.getValue(),
							txtNum.getText(), comboSize.getValue());
					ObservableList<Product> prodList = FXCollections.observableArrayList();
					prodList.add(selectedProduct);
					
				}
			}
			
		});

		comboPublic.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldVal, String newVal) {
				String sql = "select PROD_ID, KIND_1, KIND_2, PRICE, PROD_SIZE FROM PRODUCT WHERE KIND_1 IN (?)";

//				for(int i=0; i<prodList.size(); i++) {
//					prodList.get(i).setProd_Id(null);
//					prodList.get(i).setKind_1(null);
//					prodList.get(i).setKind_2(null);
//					prodList.get(i).setKind_1(null);
//				}
				ObservableList<Product> prodList = FXCollections.observableArrayList();
				ObservableList<String> kind2List = FXCollections.observableArrayList();
				try {
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, newVal);
					ResultSet rs = pstmt.executeQuery();

					int i = 0;
					while (rs.next()) {

						Product product = new Product(rs.getString("PROD_ID"), rs.getString("KIND_1"),
								rs.getString("KIND_2"), rs.getString("PRICE"), rs.getString("PROD_SIZE"));
						prodList.add(product);
						kind2List.add(rs.getString("KIND_2"));// 리스트 값 확인용
						System.out.println(prodList.get(i).getProd_Id());// prodList 값 생성 확인용
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
							}
						}
					}

				});
			}
		});// end of comboPublic.addListener

	}// end of initialize
		// 분류2가 선택된 상황에서 분류 1을 다시 선택하면 오류 발생.
		// product에는 사이즈가 null값으로 지정되어있다. 수량까지 선택한 후 확인 버튼을 누르면 해당 product에 size를 지정하고
		// 테이블 란에 리스트를 쭉 띄워준 뒤 가격*수량으로 합계가격을 도출해준다., 사이즈와 수량, 분류가 입력되지 않으면 팝업을 띄운다.
		// 구매확정 버튼을 누르면 로그인 멤버가 null이 아니라면 테이블로 들어갈 수 있게 만든다. else->로그인이 필요합니다 팝업 띄우기.

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
					loginStage.close();
					btnLogin.setText(loginMember.getName() + "님 환영합니다.");
				}
			});

			Button btnCancel = (Button) parent.lookup("#btnCancel");
			btnCancel.setOnAction((e)-> loginStage.close());

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
			btnCancel.setOnAction((e)->signUpStage.close());
			
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
		if(loginMember.getId().equals("")){
			System.out.println("로그인하세요.");
		}else {
			System.out.println("구매확정되었습니다.");
		}
	}

	public void handleBtnCancelAction() {

	}
}
