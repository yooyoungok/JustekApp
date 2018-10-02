/**
 * ------------------------------------------------------------------------------
 * @Project       : JustekApp
 * @Source        : LogViewController.java
 * @Description  : 
 * @Author        : YOUNGOK YOO
 * @Version       : v1.0
 * Copyright(c) 2018 JUSTEK All rights reserved
 *------------------------------------------------------------------------------
 *                  ��         ��         ��         ��                       
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                      DESCRIPTION                        
 * ----------  ------  --------------------------------------------------------- 
 * 2018. 6. 19.   ������    �űԻ���                                     
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
	 * @Desc  ����ڰ� ������ ��¥�� �Է��ϴ� Label  ��) 2018/06/07~2018/06/22
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
	 * @Desc  Log ������ �ҷ����� ���� ���۳�¥�� ���ᳯ¥�� �����ϱ� ���� DatePicker (fxDatePicker /fxEndDatePicker )
	 */
	@FXML 
	private DatePicker fxDatePicker; 
	@FXML
	private DatePicker fxEndDatePicker;


	/** 
	 * @FieldType String
	 * @Desc  fxDateLabel�� �Է��ϱ� ���� ������ ��¥�� String�� �����Ѵ�.(startDate / endDate)
	 */
	String startDate;  //���۳�¥;
	String endDate;  //���ᳯ¥;

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
	 * DB�� ���� ����ڰ� �Է��� �α� ������ �ҷ��´�.
	 * @author : YOO YOUNGOK 
	 * @method  setTableViewData
	 * @return  void
	 * @exception SQLException
	 * @see
	 * @since 2018. 6. 19.
	 */
	private void setTableViewData() {
		
		//Column�� Text���� Style Set
		this.fxDataColumn.setStyle( "-fx-alignment: CENTER;");
		this.fxValueColumn.setStyle( "-fx-alignment: CENTER-LEFT;");
		
		//�ʱ⳯¥�� ���� ��¥�� label�� �Է��Ѵ�.
		String date = LocalDate.now().toString();
		date= date.replaceAll("-", "/");
		String text = date+"~"+date;
		this.startDate = date;
		this.endDate = date;
		this.fxDateLabel.setText("�˻����� : "+text);

		this.getLogData();
	}

	/**
	 * fxDatePicker���� ��¥�� �����ϸ� startDate�� �����Ѵ�.	
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
		//���� ��¥���� �Է��� ��¥���� ������ ErrorMessage �� �����ش�.
		if(currentDate.isBefore(this.fxDatePicker.getValue())){
			DialogManager.getInstance().showServerErrorConfirmDialog("���糯¥���� ���� ��¥�� �Է��Ͻñ� �ٶ��ϴ�.");
			this.fxDatePicker.setValue(currentDate);			
		}
		else {
			this.startDate = date;
			this.fxDateLabel.setText("�˻����� : "+this.startDate+"~"+this.endDate);
		}
	}

	/**
	 * fxEndDatePicker���� ��¥�� �����ϸ� endDate�� �����Ѵ�.
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
		//���� ��¥���� �Է��� ��¥���� ������ ErrorMessage �� �����ش�.		
		if(currentDate.isBefore(this.fxEndDatePicker.getValue())){
			DialogManager.getInstance().showServerErrorConfirmDialog("���糯¥���� ���� ��¥�� �Է��Ͻñ� �ٶ��ϴ�.");
			this.fxEndDatePicker.setValue(currentDate);
		}
		else {
			this.endDate = date;
			this.fxDateLabel.setText("�˻����� : "+this.startDate+"~"+this.endDate);
		}
	}

	/**
	 * Log������ �ҷ����� ���� ��¥�� ��� ������ �� DB�� ��û�ϱ� ���� ��ư �̺�Ʈ
	 * fxDatePicker���� ������ ���۳�¥�� fxEndDatePicker���� ������ ���ᳯ¥�� ���Ͽ�
	 * ���۳�¥�� ���ᳯ¥���� ū ��� ErrorMessage�� �����ش�.
	 * 
	 * @author : YOO YOUNGOK 
	 * @method  onClickLogButton
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 19.
	 */
	public void onClickLogButton() {
		//���۳�¥�� ���ᳯ¥�� ���Ѵ�.
		if(this.fxEndDatePicker.getValue().isBefore(this.fxDatePicker.getValue())) {
			DialogManager.getInstance().showServerErrorConfirmDialog("���۳�¥�� ���� ��¥���� �۾ƾ��մϴ�. \n�˻� ��¥�� �ٽ��ѹ� Ȯ���Ͻñ� �ٶ��ϴ�.");
		}
		//��¥ ���ÿ� ������ ������ DB�� ��û�ϴ� MemberDAO��ü�� selectLog�� ȣ���Ѵ�.
		else {
			this.getLogData();
		}
	}
	
	/**
	 * DB�� ��û�ϴ� MemberDAO��ü�� selectLog�� ȣ���Ͽ�
	 * �ҷ��� Log������ null�� �ƴϸ� TableView�� ��Ÿ����.
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
