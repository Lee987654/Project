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
//tableview?—?Š” ? œëª©ê³¼ ì¢…ë£Œ?¼?ë§? ?…¸ì¶?.
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
		//boardList.add(new Board("test", "1234", "ê³µê°œ", "2020/05/05","?‚´?š©"));
		//title
		TableColumn<Board, String> tcTitle = new TableColumn<Board, String>();//?—¬ê¸°ë??„° ?•´?„ ?•„?š”?•´.
		tcTitle.setCellValueFactory(new Callback<CellDataFeatures<Board, String>, ObservableValue<String>>(){
			//ì²«ë²ˆì§¸ëŠ” ?¸?’‹, ?‘ë²ˆì§¸?Š” ë¦¬í„´ ???…
			@Override
			public ObservableValue<String> call(CellDataFeatures<Board, String> param) {
				return param.getValue().titleProperty();
			}
			
		});
		tcTitle.setText("? œëª?");
		
		//exitDate
		TableColumn<Board, String> tcExitDate = new TableColumn<>();
		tcExitDate.setCellValueFactory(new PropertyValueFactory<Board, String>("exitDate"));
		tcExitDate.setText("ì¢…ë£Œ?¼?");
		
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
				
				//?´? „, ?‹¤?Œë²„íŠ¼ ì¶”ê??•´?„œ ?‹¤?Œ ë²„íŠ¼ ?ˆ„ë¥´ë©´ ?‹¤?Œê°?, ?´? „?ˆ„ë¥´ë©´ ?´? „ê°?.
				//? œëª? ?„£?œ¼ë©? ? œëª©ê¸°ì¤??œ¼ë¡? ê°™ì? ? œëª©ì˜ ?‚´?š©?„ ?ˆ˜? • ë²„íŠ¼(?ˆ˜? •?ˆ„ë¥´ë©´ ?ˆ˜? •ê°??Š¥? ?ˆ˜? •?•œ?’¤ ?ˆ˜? •ë²„íŠ¼ ?ˆ„ë¥´ë©´ ?ˆ˜? • ? ?š©?) ì¶”ê??•´?„œ ì¶œë ¥?œ ?‚´?š©?„ ?‚´ê°? ?…? ¥?•´?„œ ?ˆ˜? •?•˜?Š” ê²?.
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
			ResultSet rs = pstmt.executeQuery();//resultset?? ë¬´ìŠ¨ ê¸°ëŠ¥?´ì§??
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
	

