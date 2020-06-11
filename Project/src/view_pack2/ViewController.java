package view_pack2;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Shop_pack.Product;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public class ViewController implements Initializable {
	@FXML ListView<String> listView;
	@FXML TableView<Phone> tableView;
	@FXML ImageView imageView;
	@FXML ComboBox<String> comboPublic2;
	@FXML ComboBox<String> comboPublic;
	
	Connection conn;
	
	
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
		
	//	listView.setItems(FXCollections.observableArrayList());
	//	ObservableList<String> list = FXCollections.observableArrayList("���� ��", "���� ����", "�ܿ� ��", "�ܿ� ����");
	//	listView.setItems(list);
		
//	listView.getSelectionModel().selectedIndexProperty();
	//addListener(new ChangeListener<Number>() {

		//	@Override
		//	public void changed(ObservableValue<? extends Number> arg0, Number oldVal, Number newVal) {
		//		tableView.getSelectionModel().select(newVal.intValue());
		//		tableView.scrollTo(newVal.intValue());
		//	}
			
	//	});
		
		tableView.setItems(FXCollections.observableArrayList(
				
				new Phone("Ƽ����", "Clothes01.png"),
				new Phone("����", "Clothes02.png"),
				new Phone("�����", "Clothes03.png"),
				new Phone("�ٶ�����", "Clothes04.png"),
				new Phone("��Ʈ", "Clothes05.png"),
				new Phone("�е�", "Clothes06.png"),
				new Phone("û����", "Clothes07.png"),
				new Phone("�����", "Clothes08.png"),
				new Phone("������", "Clothes09.png"),
				new Phone("����", "Clothes10.png"),
				new Phone("��Ʈ", "Clothes11.png"),
				new Phone("�Ź�", "Clothes12.png")
				));
		TableColumn<Phone, ?> tcSmartPhone = tableView.getColumns().get(0);
		tcSmartPhone.setCellValueFactory(new PropertyValueFactory("smartPhone"));
		
		//tableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("smartPhone"));
		
		TableColumn<Phone, ?> tcImage = tableView.getColumns().get(1);
		tcImage.setCellValueFactory(new PropertyValueFactory("image"));
		
		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Phone>() {

			@Override
			public void changed(ObservableValue<? extends Phone> observable, Phone oldVal, Phone newVal) {
				imageView.setImage(new Image("/view_pack/images/" + newVal.getImage()));
				
			}
			
		});
		
		imageView.setImage(new Image("/view_pack/images/Clothes01.png"));
	
		comboPublic.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldVal, String newVal) {
				String sql = "select PROD_ID, KIND_1, KIND_2, PRICE, PROD_SIZE FROM PRODUCT WHERE KIND_1 LIKE 'TOPS'";
				ObservableList<Product> prodList = FXCollections.observableArrayList();
				ObservableList<String> kind2List = FXCollections.observableArrayList();
				try {
					PreparedStatement pstmt = conn.prepareStatement(sql);
					//pstmt.setString(1, newVal);
					ResultSet rs = pstmt.executeQuery();
					
					int i=0;
					while(rs.next()) {
						
						Product product = new Product(rs.getString("PROD_ID"), rs.getString("KIND_1"),rs.getString("KIND_2"),rs.getInt("PRICE"),rs.getString("PROD_SIZE"));
						
						kind2List.add(rs.getString("KIND_2"));//리스트 값 확인용
						System.out.println(kind2List.get(i));
						i++;
					}
				
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					comboPublic2.setItems(kind2List);
			}
		});
	}//comboPublic 에 change action걸고 바뀔때마다 선택된 값에 맞는 이미지를 보여준다.->db에서 가져온 데이터에서 id값을 빼서 이미지뷰에 id값+이미지.
	//product에는 사이즈가 null값으로 지정되어있다. 수량까지 선택한 후 확인 버튼을 누르면 해당 product에 size를 지정하고 테이블 란에 리스트를 쭉 띄워준 뒤 가격*수량으로 합계가격을 도출해준다., 사이즈와 수량, 분류가 입력되지 않으면 팝업을 띄운다.
	//구매확정 버튼을 누르면 로그인 멤버가 null이 아니라면 테이블로 들어갈 수 있게 만든다. else->로그인이 필요합니다 팝업 띄우기.
	
	
	public void handleBtnRegAction(ActionEvent e) {
//		String sql = "select PROD_ID, KIND_1, KIND_2, PRICE, PROD_SIZE FROM PRODUCT WHERE KIND_1 = ?";
//		
//		try {
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, comboPublic.getValue());
//			ResultSet rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				
//				Product product = new Product(rs.getString("PROD_ID"), rs.getString("KIND_1"),rs.getString("KIND_2"),rs.getInt("PRICE"),rs.getString("PROD_SIZE"));
//				prodList.add(product);
//			}
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		for(int i=0; i<prodList.size(); i++) {
//			comboPublic2.setValue(prodList.get(i).getKind_2());
//		}
	
		
		
		
	}
	public void handleBtnCancelAction() {
		
	}
}
