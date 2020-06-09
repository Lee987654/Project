package Shop_pack;

import javafx.beans.property.SimpleStringProperty;

public class Member {
	private SimpleStringProperty id;
	private SimpleStringProperty password;
	private SimpleStringProperty name;
	
	public Member(String id, String password, String name) {
		this.id = new SimpleStringProperty(id);
		this.password = new SimpleStringProperty(password);
		this.name = new SimpleStringProperty(name);
	}
	
	public String getId() {
		return this.id.get();
	}
	public void setId(String id) {
		this.id.set(id);
	}
	public SimpleStringProperty idProperty() {
		return this.id;
	}
	
	public String getPassword() {
		return this.password.get();
	}
	public void setPassword(String password) {
		this.password.set(password);
	}
	public SimpleStringProperty passwordProperty() {
		return this.password;
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
	
}
