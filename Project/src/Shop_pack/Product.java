package Shop_pack;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Product {
	private SimpleStringProperty prod_Id;
	private SimpleStringProperty kind_1;
	private SimpleStringProperty kind_2;
	private SimpleIntegerProperty price;
	private SimpleStringProperty prod_Size;
	
	public Product(String prod_Id, String kind_1, String kind_2, int price, String prod_Size) {
<<<<<<< HEAD
		this.prod_Id.set(prod_Id);
		this.kind_1.set(kind_1);
		this.kind_1.set(kind_2);
		this.price.set(price);
		this.prod_Size.set(prod_Size);
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


	public String getkind_1() {
		return this.kind_1.get();
	}

	public void setkind_1(String kind_1) {
		this.kind_1.set(kind_1);
	}
	public SimpleStringProperty kind_1Property() {
		return this.kind_1;
	
	
	
=======
		this.prod_Id = new SimpleStringProperty(prod_Id);
		this.kind_1 = new SimpleStringProperty(kind_1);
		this.kind_2 = new SimpleStringProperty(kind_2);
		this.price = new SimpleIntegerProperty(price);
		this.prod_Size = new SimpleStringProperty(prod_Size);
		
		
>>>>>>> branch 'master' of https://github.com/Lee987654/Project.git
	}
	
<<<<<<< HEAD
	
	public String getkind_2() {
		return this.kind_2.get();
	}

	public void setkind_2(String kind_2) {
		this.kind_2.set(kind_2);
	}
	public SimpleStringProperty kind_2Property() {
		return this.kind_2;
=======
	public String getProd_Id() {
		return this.prod_Id.get();
>>>>>>> branch 'master' of https://github.com/Lee987654/Project.git
	}
	
<<<<<<< HEAD
	public int getprice() {
=======
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
		return this.prod_Id.get();
	}
	
	public void setKind_2(String kind_2) {
		this.kind_2.set(kind_2);
	}
	public SimpleStringProperty kind_2Property() {
		return this.kind_2;
	}
	
	public int getPrice() {
>>>>>>> branch 'master' of https://github.com/Lee987654/Project.git
		return this.price.get();
	}
<<<<<<< HEAD

	public void setprice(int price) {
=======
	
	public void setPrice(int price) {
>>>>>>> branch 'master' of https://github.com/Lee987654/Project.git
		this.price.set(price);
	}
	
	public SimpleIntegerProperty priceProperty() {
		return this.price;
	}
	
<<<<<<< HEAD
	public String getprod_Size() {
		return this.prod_Size.get();
	}

	public void setprod_Size(String prod_Size) {
		this.prod_Size.set(prod_Size);
	}
	public SimpleStringProperty prod_SizeProperty() {
		return this.prod_Size;
	
	}
	
=======
	
	public String getProd_Size() {
		return this.prod_Size.get();
	}
	
	public void setProd_Size(String prod_Size) {
		this.prod_Size.set(prod_Size);
	}
	public SimpleStringProperty prod_SizeProperty() {
		return this.prod_Size;
	}
>>>>>>> branch 'master' of https://github.com/Lee987654/Project.git
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public int getPrice() {
//		return this.price.get();
//	}
//	public void setPrice(int price) {
//		this.price.set(price);
//	}
//	public SimpleIntegerProperty priceProperty() {
//		return this.price;
//	}
//}
