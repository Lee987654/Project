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
		"����", "����"
		
		));
	listView.getSelectionModel().selectedIndexProperty().addListener(
			new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable,
				Number oldValue, Number newValue) {
			tableView oldValue, Number newValue) {
			tableView.scrollTo(newValue.intValue()); ����� �ε����� �� ����
	} ����� �ε��� ��ġ�� ��ũ�ѽ�Ŵ
	});
					
	ObservableList phoneList = FXCollections.observableArrayList(
			new Clothes("����, "���� ����.PNG"),
			new Clothes("����, "���� �ݹ���.PNG"),
			new Clothes("����, "�ܿ� ��Ʈ.PNG"),	
			new Clothes("����, "�����.PNG")"
						
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
				System.out.println("ListView ��: "  + item);
				
				clothes clothes = tableView.getSelectionModel().getSelectedItem();
				
				System.out.println("TableView ��: "+clothes.getSmartclothes());
				System.out.println("TableView �̹���: " + clothes.getImage());
			
			}
						
				
				public void handleBtnCancelAction(ActionEvent e) {
					Platform.exit();
				
				}
			
			}			
