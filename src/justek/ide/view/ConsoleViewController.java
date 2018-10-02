/**
 * ------------------------------------------------------------------------------
 * @Project       : JustekApp
 * @Source        : ConsoleViewController.java
 * @Description  : ������  Telnet����� �ϱ� ���� �ܼ� ȭ���� �����Ѵ�. 
 * @Author        : YOUNGOK YOO
 * @Version       : v1.0
 * Copyright(c) 2018 JUSTEK All rights reserved
 *------------------------------------------------------------------------------
 *                  ��         ��         ��         ��                       
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                      DESCRIPTION                        
 * ----------  ------  --------------------------------------------------------- 
 * 2018.04.05   ������    �űԻ���                                     
 *------------------------------------------------------------------------------
 * 2018.06.19   ������     ������ ��ɾ� ���� �� 
 *                              Timer�� �����Ͽ� 2���� response�� ���� 
 *                              ���� while�� �����ϵ��� ����                          
 *------------------------------------------------------------------------------
 */

package justek.ide.view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Calendar;
import com.supinan.util.timer.SupinanTimer;
import com.supinan.util.timer.Timer;
import com.supinan.util.timer.TimerStopType;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import justek.ide.dao.MemberDAO;
import justek.ide.model.CommandConst;
import justek.ide.model.manager.DialogManager;
import justek.ide.model.manager.NetworkServerManager;

public class ConsoleViewController {

	static final String Tag = "ConsoleViewController";

	
	/** 
	 * @FieldType TextArea
	 * @Desc  > prompt  TextArea
	 */
	@FXML
	private TextArea promptTextArea;
	
	/** 
	 * @FieldType TextArea
	 * @Desc  ����� ���� ��ɾ �Է¹ޱ� ���� TextArea
	 */
	@FXML
	private TextArea consoleTextArea;
	/** 
	 * @FieldType TextArea
	 * @Desc  �����κ��� ���� ErrorMessage�� �Է��� TextArea
	 */
	@FXML
	private TextArea errorTextArea;

    @FXML
    private TextField fxInputTextField;

    @FXML
    private TextArea fxResultTextArea;
    
	/** 
	 * @FieldType TabPane
	 * @Desc consoleTextArea�� errorTextArea�� Tab�� ������ TabPane
	 */
	@FXML
	private TabPane consolePane;

	/** 
	 * @FieldType Tab
	 * @Desc  errorTextArea�� ������ Tab -> �����κ��� ErrorMessage�� ���� ��� Ȱ��ȭ �ȴ�.
	 */
	@FXML
	private Tab errorTab;
	
	/** 
	 * @FieldType Tab
	 * @Desc  errorTextArea�� ������ Tab -> �����κ��� ErrorMessage�� ���� ��� Ȱ��ȭ �ȴ�.
	 */
	@FXML
	private Tab fxTelnetTab;
	

	/** 
	 * @FieldType Socket
	 * @Desc ������ ����� Socket Object
	 */
	Socket socketClient;

	/** 
	 * @FieldType String
	 * @Desc �����κ��� ���� Response�� String���� ����. 
	 */
	String serverMsg;

	/** 
	 * @FieldType SupinanTimer
	 * @Desc   Ÿ�̸Ӹ� �����ϴ� Object
	 */
	SupinanTimer timer;
	/** 
	 * @FieldType int 
	 * @Desc  Ÿ�̸� ����� SupinanTimer�� ���� �����Ǵ� TimerID
	 */
	long timerId;

	
	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
	}

	int line_count=0; //TextArea count

	/**
	 * consoleTextArea�� ����ڰ� �Է��� ������ ������ ������ ����Ͽ� runCommnad�� �Է��� ���� �����Ѵ�.
	 * @author : YOO YOUNGOK 
	 * @method  keyEvent
	 * @param event
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 04. 05
	 */
	@FXML
	public void keyEvent(KeyEvent event) {
		
		if (event.getCode()== KeyCode.ENTER) {
			if(event.getSource().equals(this.fxInputTextField)) {
				String textData = this.fxInputTextField.getText();
				this.fxResultTextArea.appendText("\n Justek> "+textData);
				this.fxInputTextField.clear();
				this.runCommand(textData);
			}
			return;
		}
		
		
		if (event.getCode()== KeyCode.ENTER) {
			if(event.getSource().equals(this.consoleTextArea)) {
				this.promptTextArea.appendText("\n>");
				String[] s = this.consoleTextArea.getText().split("\n");
				String selString ="";
				
				if(s.length==-1) return;

				if(s.length>line_count) { 
					selString = s[s.length-1];
					if(selString.equals("") || selString.equals(">") || selString.contains("Response")) {
						return;
					}
				}

				line_count = s.length;

				String[] s2 = selString.split("\n");
				if(s2.length==-1) return;
				if(selString.equals("")) {
					return;
				}

				System.out.println("Input Test=="+selString);	             
				this.runCommand(selString);
			}
			//use this to do whatever you want to. Open Link etc.
		}
	}

	/**
	 * ����ڰ� �Է��� ��ɾ ������ �����ϴ� �Լ�.
	 * @author : YOO YOUNGOK 
	 * @method  runCommand
	 * @param runString String ->  ����ڰ� �Է��� ��ɾ�.
	 * @return  void
	 * @exception IOExcption / SQLException
	 * @see
	 * @since 2018. 04. 05.
	 * 		  2018. 06. 19  -> 2���Ŀ� response�� ���°�� socket����� �����ϵ��� ����
	 */
	public void runCommand(String runString) {
		System.out.println("Client: " + " runCommand :: Connection Established == " +runString);
		
		String[] parsingValue = runString.split(" ");
		String plcName = parsingValue[parsingValue.length-1];
		
		if(runString.contains("enable")) {
			//�ش� PLC�� ����Ǵ��� ���� Ȯ���� �Ѵ�..
			if(NetworkServerManager.getInstance().checkPlcFile(plcName+".plc")) {
				DialogManager.getInstance().showServerErrorConfirmDialog("���� �ش� PLC�� �������Դϴ�.");
				return;
			}
		}
		
		
		//MemberDAO�� insertLog()�� ȣ���Ͽ� DB�� �����Ѵ�.
		try {
			MemberDAO.getInstance().insertLog(runString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			socketClient = new Socket(CommandConst.address, 12345);
			String sendMsg = runString+"\r\n";
			BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			writer.write(sendMsg);
			writer.flush();

			/* �����κ��� response�� ���� ��쿡 socket�� �����ϱ� ���� SupinanTimer�� null�ΰ�� SupinanTimer ��ü�� �����Ѵ�.. */
			if(this.timer==null) {
				timer = new SupinanTimer();
			}

			//SupinanTimer�� ������ timer���̵� timerId�� �����Ѵ�.
			this.timerId = timer.addTimer(new TimerCall(500));

			/* plc ������ �����ϴ� ���� response�� �����Ƿ� Server Message�� ���� �ʴ´�. 2���Ŀ� Timer�� �ڵ����� socket�� �ݴ´�.*/
			if(!runString.contains("plc")) {
				
				serverMsg = reader.readLine();
				if(serverMsg!=null) {
					this.consolePane.getSelectionModel().select(fxTelnetTab);
					System.out.println(Tag+" Eco Response == "+serverMsg);
					if(this.socketClient!=null && !this.socketClient.isClosed()) {
						serverMsg = reader.readLine();
						this.fxResultTextArea.appendText("\n Justek> "+serverMsg);
					}

//					/* �����κ��� Response�� Error�� ���� Error Message Tab(errorTab)�� Ȱ��ȭ�Ͽ� Error Message�� �����ش�.  */
//					if(serverMsg.contains("ERR")) {
//						this.consolePane.getSelectionModel().select(this.errorTab);
//						this.errorTextArea.appendText("Justek> "+runString+"\n");
//						this.errorTextArea.appendText("Justek> "+serverMsg+"\n");
//					}
				}
			}
			
		} catch (Exception e) {
			if(e.getMessage().equals("Socket closed")) return;
			e.printStackTrace();
		}

	}

	/* ################### ############# Inner Class ############### ################### */	
	/**
	 * �����κ��� Response�� ���°�� while���� �����ϱ� ���� Timer Class
	 * @author YOUNGOK YOO
	 * @since 2018. 6. 19.
	 */
	public class TimerCall extends Timer{

		int sec=0;
		/**
		 * repeatPeriod �� �ð��� ���� ������ execute() �޼ҵ带 ȣ���ϵ��� �����Ѵ�
		 * @param repeatPeriod(�ݺ��ֱ�,ms)
		 * �� : repeatPeriod = 1000 �� ��� 1��
		 */
		public TimerCall(long repeatPeriod) {
			//timer�� 1�ʸ��� �����ϰ� timer�� 5���Ŀ� ������ �����ϵ��� �����Ѵ�. 
			super(repeatPeriod,5);
		}

		/**
		 * repeatPeriod���� ȣ��Ǵ� �޼ҵ�
		 */
		@Override
		public void execute() {
			Calendar cal = Calendar.getInstance();
			System.out.println(Tag+" :: TimerCall ȣ��! " + cal.get(Calendar.HOUR_OF_DAY) + "�� " +
					cal.get(Calendar.MINUTE) + "�� " + cal.get(Calendar.SECOND) + "��");
			sec++;	//1�ʸ��� sec���� ������Ų��.
			//2���Ŀ��� timer ������ �����Ѵ�.
			if(sec==2) {
				timer.removeTimer(timerId);		
			}
		}

		/** 
		 * Ÿ�̸Ӱ� ����Ǿ��� ��� ȣ��ȴ�
		 * �ش� Ÿ�̸� ����ü�� �����ð����� ���� �ݺ��̱� ������ 
		 * SupinanTimer �� closeTimer() �� ȣ������ �ʴ��� �ش� �޼ҵ��
		 * ȣ������ �ʴ´�		 * 
		 * @see com.supinan.util.timer.Timer#stopTimer(com.supinan.util.timer.TimerStopType)
		 */
		@Override
		public void stopTimer(TimerStopType type) {
			try {
				socketClient.close();
				socketClient=null;
				System.out.println(Tag + " :: socketClient �� �ݴ´�.:");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Tag + " :: TimerCall ����:" + type.name());
		}
	}	
	/* ################### ############# Inner Class End ############### ################### */	
}