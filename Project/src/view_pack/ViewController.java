package view_pack;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
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
	}
	public void handleBtnRegAction() {
		
	}
	public void handleBtnCancelAction() {
		
	}
}
