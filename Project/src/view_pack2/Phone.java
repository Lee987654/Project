package view_pack2;

import javafx.beans.property.SimpleStringProperty;

public class Phone {
	private SimpleStringProperty smartPhone;//문자?���? ?��?��?�� ?��?��?��?�� property
	private SimpleStringProperty image;
	
	Phone(String smartPhone, String image){
		this.smartPhone = new SimpleStringProperty(smartPhone);
		this.image = new SimpleStringProperty(image);
	}

	public void setSmartPhone(String smartPhone) {
		this.smartPhone.set(smartPhone);
	}
	public String getSmartPhone() {
		return this.smartPhone.get();
	}
	public SimpleStringProperty smartPhoneProperty() {
		return this.smartPhone;
	}

	public void setImage(String image) {
		this.image.set(image);
	}
	public SimpleStringProperty imageProperty() {
		return this.image;
	}
	public String getImage() {
		return this.image.get();
	}
	

}
