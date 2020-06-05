package Shop_pack;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Product {
	private SimpleStringProperty name;
	private SimpleStringProperty weather;
	private SimpleStringProperty size;
	private SimpleIntegerProperty price;
	
	Product(String name, String weather, String size, int price){
		this.name = new SimpleStringProperty(name);
		this.weather = new SimpleStringProperty(weather);
		this.size = new SimpleStringProperty(size);
		this.price = new SimpleIntegerProperty(price);
	}
	
	public String getName() {
		return this.name.get();
	}
	public void setName(String name) {
		this.name.set(name);
	}
	public SimpleStringProperty nameProperty() {
		return this.name;
	}
	
	public String getWeather() {
		return this.weather.get();
	}
	public void setWeather(String weather) {
		this.weather.set(weather);
	}
	public SimpleStringProperty weatherProperty() {
		return this.weather;
	}
	
	public String getSize() {
		return this.size.get();
	}
	public void setSize(String size) {
		this.size.set(size);
	}
	public SimpleStringProperty sizeProperty() {
		return this.size;
	}
	
	public int getPrice() {
		return this.price.get();
	}
	public void setPrice(int price) {
		this.price.set(price);
	}
	public SimpleIntegerProperty priceProperty() {
		return this.price;
	}
}
