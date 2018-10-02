/**
 * ------------------------------------------------------------------------------
 * @Project       : JustekApp
 * @Source        : LogViewController.java
 * @Description  : 
 * @Author        : YOUNGOK YOO
 * @Version       : v1.0
 * Copyright(c) 2018 JUSTEK All rights reserved
 *------------------------------------------------------------------------------
 *                  변         경         사         항                       
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                      DESCRIPTION                        
 * ----------  ------  --------------------------------------------------------- 
 * 2018. 6. 19.   유영옥    신규생성                                     
 *------------------------------------------------------------------------------
 */
package justek.ide.view;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import justek.ide.MainApp;
import justek.ide.dao.MemberDAO;
import justek.ide.model.cmdLog.LogDTO;
import justek.ide.model.manager.DialogManager;

public class LogViewController {


	static final String Tag = "LogViewController";
	/** 
	 * @FieldType Label
	 * @Desc  사용자가 선택한 날짜를 입력하는 Label  예) 2018/06/07~2018/06/22
	 */
	@FXML
	private Label fxDateLabel;

	/** 
	 * @FieldType TableColumn<LogDTO, String>
	 * @Desc  (fxDataColumn / fxValueColumn)
	 */
	@FXML 
	private TableColumn<LogDTO, String> fxDataColumn; 

	@FXML 
	private TableColumn<LogDTO, String> fxValueColumn; 

	/** 
	 * @FieldType TableView<LogInfo>
	 * @Desc  
	 */
	@FXML
	private TableView<LogDTO> fxTableView; 

	/** 
	 * @FieldType DatePicker
	 * @Desc  Log 정보를 불러오기 위한 시작날짜와 종료날짜를 선택하기 위한 DatePicker (fxDatePicker /fxEndDatePicker )
	 */
	@FXML 
	private DatePicker fxDatePicker; 
	@FXML
	private DatePicker fxEndDatePicker;


	/** 
	 * @FieldType String
	 * @Desc  fxDateLabel에 입력하기 위해 선택한 날짜를 String에 저장한다.(startDate / endDate)
	 */
	String startDate;  //시작날짜;
	String endDate;  //종료날짜;

	/** 
	 * @FieldType MainApp
	 */
	private MainApp mainApp;


	/**
	 * @author : YOO YOUNGOK 
	 * @method  initialize
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 19.
	 */
	@FXML
	private void initialize() {
		/* TableCoulmn Data Format Setting -> StringProperty */
		this.fxDataColumn.setCellValueFactory(cellData -> cellData.getValue().getExecuteTime());
		this.fxValueColumn.setCellValueFactory(cellData -> cellData.getValue().getCmd());
		this.setTableViewData();
		this.fxDatePicker.setValue(LocalDate.now());
		this.fxEndDatePicker.setValue(LocalDate.now());
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	} 

	/**
	 * DB로 부터 사용자가 입력한 로그 정보를 불러온다.
	 * @author : YOO YOUNGOK 
	 * @method  setTableViewData
	 * @return  void
	 * @exception SQLException
	 * @see
	 * @since 2018. 6. 19.
	 */
	private void setTableViewData() {
		
		//Column의 Text정렬 Style Set
		this.fxDataColumn.setStyle( "-fx-alignment: CENTER;");
		this.fxValueColumn.setStyle( "-fx-alignment: CENTER-LEFT;");
		
		//초기날짜는 현재 날짜로 label에 입력한다.
		String date = LocalDate.now().toString();
		date= date.replaceAll("-", "/");
		String text = date+"~"+date;
		this.startDate = date;
		this.endDate = date;
		this.fxDateLabel.setText("검색일자 : "+text);

		this.getLogData();
	}

	/**
	 * fxDatePicker에서 날짜를 선택하면 startDate에 저장한다.	
	 * @author : YOO YOUNGOK 
	 * @method  getSelectedData
	 * @return  void
	 * @exception ParseException
	 * @see
	 * @since 2018. 6. 19.
	 */
	public void getSelectedDate() {
		String pattern = "yyyy/MM/dd";
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
		String date = this.fxDatePicker.getValue().format(dateFormatter);
		LocalDate currentDate = LocalDate.now();
		//현재 날짜보다 입력한 날짜보다 작으면 ErrorMessage 로 보여준다.
		if(currentDate.isBefore(this.fxDatePicker.getValue())){
			DialogManager.getInstance().showServerErrorConfirmDialog("현재날짜보다 이전 날짜로 입력하시기 바랍니다.");
			this.fxDatePicker.setValue(currentDate);			
		}
		else {
			this.startDate = date;
			this.fxDateLabel.setText("검색일자 : "+this.startDate+"~"+this.endDate);
		}
	}

	/**
	 * fxEndDatePicker에서 날짜를 선택하면 endDate에 저장한다.
	 * @author : YOO YOUNGOK 
	 * @method  getEndSelectedData
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 19.
	 */
	public void getEndSelectedDate() {
		String pattern = "yyyy/MM/dd";
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
		String date = this.fxEndDatePicker.getValue().format(dateFormatter);
		LocalDate currentDate = LocalDate.now();
		//현재 날짜보다 입력한 날짜보다 작으면 ErrorMessage 로 보여준다.		
		if(currentDate.isBefore(this.fxEndDatePicker.getValue())){
			DialogManager.getInstance().showServerErrorConfirmDialog("현재날짜보다 이전 날짜로 입력하시기 바랍니다.");
			this.fxEndDatePicker.setValue(currentDate);
		}
		else {
			this.endDate = date;
			this.fxDateLabel.setText("검색일자 : "+this.startDate+"~"+this.endDate);
		}
	}

	/**
	 * Log정보를 불러오기 위한 날짜를 모두 선택한 후 DB에 요청하기 위한 버튼 이벤트
	 * fxDatePicker에서 선택한 시작날짜와 fxEndDatePicker에서 선택한 종료날짜를 비교하여
	 * 시작날짜가 종료날짜보다 큰 경우 ErrorMessage를 보여준다.
	 * 
	 * @author : YOO YOUNGOK 
	 * @method  onClickLogButton
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 19.
	 */
	public void onClickLogButton() {
		//시작날짜와 종료날짜를 비교한다.
		if(this.fxEndDatePicker.getValue().isBefore(this.fxDatePicker.getValue())) {
			DialogManager.getInstance().showServerErrorConfirmDialog("시작날짜가 종료 날짜보다 작아야합니다. \n검색 날짜를 다시한번 확인하시기 바랍니다.");
		}
		//날짜 선택에 문제가 없으면 DB에 요청하는 MemberDAO객체의 selectLog를 호출한다.
		else {
			this.getLogData();
		}
	}
	
	/**
	 * DB에 요청하는 MemberDAO객체의 selectLog를 호출하여
	 * 불러온 Log정보가 null이 아니면 TableView에 나타낸다.
	 * 
	 * @author : YOO YOUNGOK 
	 * @method  getLogData
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 19.
	 */
	private void getLogData() {
		ObservableList<LogDTO> data = null;
		try {
			data = MemberDAO.getInstance().selectLog("admin01", startDate, endDate);
			if(data!=null) {
				this.fxTableView.setItems(data);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
