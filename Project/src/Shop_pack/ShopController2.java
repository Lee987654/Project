package Shop_pack;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

public class ShopController2 implements Initializable {
	@FXML private ListView<String> listView;
	@FXML private TableView<clothes> tableView;
	@FXML private ImageView imageView;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listView.setItems(FXCollectionsobservableArrayList(
		"상의", "하의"
		
		));
	listView.getSelectionModel().selectedIndexProperty().addListener(
			new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable,
				Number oldValue, Number newValue) {
			tableView oldValue, Number newValue) {
			tableView.scrollTo(newValue.intValue()); 변경된 인덱스의 행 선택
	} 변경된 인덱스 위치로 스크롤시킴
	});
					
	ObservableList phoneList = FXCollections.observableArrayList(
			new Clothes("상의, "여름 셔츠.PNG"),
			new Clothes("상의, "여름 반바지.PNG"),
			new Clothes("하의, "겨울 코트.PNG"),	
			new Clothes("하의, "긴바지.PNG")"
						
		);
			
			TableColumn tcClothes = tableView.getColumns().get(0);
			tcClothes.setCellValueFactory(new PropertyValueFactory("Clothes"));
			tcClothes.setStyle("-fx-alignment: CENTER;");
			
			TableColumn tcImage = tableView.getColumns().get(1);
			tcImage.setCellValueFactory(new PropertyValueFactory("image"));
			tcImage.setStyle("-fx-alignment: CENTER;");
			
			tableView.setItems(ClothesList);
			
			tableView.getSelectionModel().selectedItemProperty().addListener(
					new ChangeListener<Phone>() {
						@Override
						public void changed(ObservableValu<? extends Phone> observable,
								Phone oldValue, Phone newValue) {
							if(newValue!=null) {
								imageView.setImage(new Image(
										getClass().getResource("images/" + newValue.getImage()). 
										toString()));
							}
						}
					}
				);
			}
			
			public void handleBtnOkAction(ActionEvent e) {
				String item = listView.getSelectionModel().getSelectedItem();
				System.out.println("ListView 옷: "  + item);
				
				clothes clothes = tableView.getSelectionModel().getSelectedItem();
				
				System.out.println("TableView 옷: "+clothes.getSmartclothes());
				System.out.println("TableView 이미지: " + clothes.getImage());
			
			}
						
				
				public void handleBtnCancelAction(ActionEvent e) {
					Platform.exit();
				
				}
			
			}			
