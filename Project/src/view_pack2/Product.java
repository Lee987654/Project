package view_pack2;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Product {
	private SimpleStringProperty prod_Id;
	private SimpleStringProperty kind_1;
	private SimpleStringProperty kind_2;
	private SimpleStringProperty price;
	private SimpleStringProperty prod_Size;
	private SimpleStringProperty prod_Count;
	
	public Product(String prod_Id, String kind_1, String kind_2, String price, String prod_Size, String prod_Count) {
		this.prod_Id = new SimpleStringProperty(prod_Id);
		this.kind_1 = new SimpleStringProperty(kind_1);
		this.kind_2 = new SimpleStringProperty(kind_2);
		this.price = new SimpleStringProperty(price);
		this.prod_Size = new SimpleStringProperty(prod_Size);
		this.prod_Count = new SimpleStringProperty(prod_Count);
		
	}
	
	public String getProd_Id() {
		return this.prod_Id.get();
	}
	
	public void setProd_Id(String prod_Id) {
		this.prod_Id.set(prod_Id);
	}
	public SimpleStringProperty prod_IdProperty() {
		return this.prod_Id;
	}
	
	
	public String getKind_1() {
		return this.kind_1.get();
	}
	
	public void setKind_1(String kind_1) {
		this.kind_1.set(kind_1);
	}
	public SimpleStringProperty kind_1Property() {
		return this.kind_1;
	}
	
	
	public String getKind_2() {
		return this.kind_2.get();
	}
	
	public void setKind_2(String kind_2) {
		this.kind_2.set(kind_2);
	}
	public SimpleStringProperty kind_2Property() {
		return this.kind_2;
	}
	
	public String getPrice() {
		return this.price.get();
	}
	
	public void setPrice(String price) {
		this.price.set(price);
	}
	public SimpleStringProperty priceProperty() {
		return this.price;
	}
	
	
	public String getProd_Size() {
		return this.prod_Size.get();
	}
	
	public void setProd_Size(String prod_Size) {
		this.prod_Size.set(prod_Size);
	}
	public SimpleStringProperty prod_SizeProperty() {
		return this.prod_Size;
	}
	
	public String getProd_Count() {
		return this.prod_Count.get();
	}
	
	public void setProd_Count(String prod_Count) {
		this.prod_Count.set(prod_Count);
	}
	public SimpleStringProperty prod_CountProperty() {
		return this.prod_Count;
	}
}
