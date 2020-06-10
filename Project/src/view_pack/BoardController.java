package view_pack;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
//tableview?? ? λͺ©κ³Ό μ’λ£?Ό?λ§? ?ΈμΆ?.
import javafx.util.Callback;
public class BoardController implements Initializable {
	Connection conn;
	
	@FXML TableView<Board> tableView;
	@FXML TextField txtTitle;
	@FXML ComboBox comboPublic;
	@FXML TextField dateExit;
	@FXML TextArea txtContent;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(url, "hr","hr");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ObservableList<Board> boardList = getBoardList();//FXCollections.observableArrayList();
		//boardList.add(new Board("test", "1234", "κ³΅κ°", "2020/05/05","?΄?©"));
		//title
		TableColumn<Board, String> tcTitle = new TableColumn<Board, String>();//?¬κΈ°λ??° ?΄? ???΄.
		tcTitle.setCellValueFactory(new Callback<CellDataFeatures<Board, String>, ObservableValue<String>>(){
			//μ²«λ²μ§Έλ ?Έ?, ?λ²μ§Έ? λ¦¬ν΄ ???
			@Override
			public ObservableValue<String> call(CellDataFeatures<Board, String> param) {
				return param.getValue().titleProperty();
			}
			
		});
		tcTitle.setText("? λͺ?");
		
		//exitDate
		TableColumn<Board, String> tcExitDate = new TableColumn<>();
		tcExitDate.setCellValueFactory(new PropertyValueFactory<Board, String>("exitDate"));
		tcExitDate.setText("μ’λ£?Ό?");
		
		tableView.getColumns().add(tcTitle);
		tableView.getColumns().add(tcExitDate);
		
		
		tableView.setItems(boardList);
		
		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Board>() {

			@Override
			public void changed(ObservableValue<? extends Board> observable, Board oldVal, Board newVal) {
				txtTitle.setText(newVal.getTitle());
				comboPublic.setValue(newVal.getPublicity());//setCellFactory(new PropertyValueFactory(newVal.getPublicity()));
				dateExit.setText(newVal.getExitDate());
				txtContent.setText(newVal.getContent());
				
				//?΄? , ?€?λ²νΌ μΆκ??΄? ?€? λ²νΌ ?λ₯΄λ©΄ ?€?κ°?, ?΄? ?λ₯΄λ©΄ ?΄? κ°?.
				//? λͺ? ?£?Όλ©? ? λͺ©κΈ°μ€??Όλ‘? κ°μ? ? λͺ©μ ?΄?©? ??  λ²νΌ(?? ?λ₯΄λ©΄ ?? κ°??₯? ?? ??€ ?? λ²νΌ ?λ₯΄λ©΄ ??  ? ?©?) μΆκ??΄? μΆλ ₯? ?΄?©? ?΄κ°? ?? ₯?΄? ?? ?? κ²?.
			}
			
		});
		}
		
		
		//TableColumn<Board, ?> tcTitle = tableView.getColumns()
		//Bindings.bindBidirectional(tableView.accessibleTextProperty(), txtTitle.textProperty());
		
	public ObservableList<Board> getBoardList(){
		ObservableList<Board> list = FXCollections.observableArrayList();
		String sql = "select title, publicity, exit_date, content from board";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();//resultset?? λ¬΄μ¨ κΈ°λ₯?΄μ§??
			while(rs.next()) {
				Board board = new Board(rs.getString("title"),null, rs.getString("publicity"), rs.getString("exit_date"), rs.getString("content"));
			list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}//end of getBoardList();
	}//end of class
	

