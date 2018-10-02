/**
 * ------------------------------------------------------------------------------
 * @Project       : JustekApp
 * @Source        : ConsoleViewController.java
 * @Description  : 서버와  Telnet통신을 하기 위한 콘솔 화면을 제공한다. 
 * @Author        : YOUNGOK YOO
 * @Version       : v1.0
 * Copyright(c) 2018 JUSTEK All rights reserved
 *------------------------------------------------------------------------------
 *                  변         경         사         항                       
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                      DESCRIPTION                        
 * ----------  ------  --------------------------------------------------------- 
 * 2018.04.05   유영옥    신규생성                                     
 *------------------------------------------------------------------------------
 * 2018.06.19   유영옥     서버에 명령어 전송 후 
 *                              Timer를 실행하여 2초후 response가 없는 
 *                              경우는 while를 중지하도록 수정                          
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
	 * @Desc  통신을 위한 명령어를 입력받기 위한 TextArea
	 */
	@FXML
	private TextArea consoleTextArea;
	/** 
	 * @FieldType TextArea
	 * @Desc  서버로부터 받은 ErrorMessage를 입력할 TextArea
	 */
	@FXML
	private TextArea errorTextArea;

    @FXML
    private TextField fxInputTextField;

    @FXML
    private TextArea fxResultTextArea;
    
	/** 
	 * @FieldType TabPane
	 * @Desc consoleTextArea와 errorTextArea의 Tab를 소유한 TabPane
	 */
	@FXML
	private TabPane consolePane;

	/** 
	 * @FieldType Tab
	 * @Desc  errorTextArea를 소유한 Tab -> 서버로부터 ErrorMessage를 받은 경우 활성화 된다.
	 */
	@FXML
	private Tab errorTab;
	
	/** 
	 * @FieldType Tab
	 * @Desc  errorTextArea를 소유한 Tab -> 서버로부터 ErrorMessage를 받은 경우 활성화 된다.
	 */
	@FXML
	private Tab fxTelnetTab;
	

	/** 
	 * @FieldType Socket
	 * @Desc 서버와 통신할 Socket Object
	 */
	Socket socketClient;

	/** 
	 * @FieldType String
	 * @Desc 서버로부터 받은 Response를 String으로 저장. 
	 */
	String serverMsg;

	/** 
	 * @FieldType SupinanTimer
	 * @Desc   타이머를 생성하는 Object
	 */
	SupinanTimer timer;
	/** 
	 * @FieldType int 
	 * @Desc  타이머 실행시 SupinanTimer에 의해 생성되는 TimerID
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
	 * consoleTextArea에 사용자가 입력한 라인의 마지막 라인을 계산하여 runCommnad에 입력한 값을 전달한다.
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
	 * 사용자가 입력한 명령어를 서버로 전달하는 함수.
	 * @author : YOO YOUNGOK 
	 * @method  runCommand
	 * @param runString String ->  사용자가 입력한 명령어.
	 * @return  void
	 * @exception IOExcption / SQLException
	 * @see
	 * @since 2018. 04. 05.
	 * 		  2018. 06. 19  -> 2초후에 response가 없는경우 socket통신을 종료하도록 수정
	 */
	public void runCommand(String runString) {
		System.out.println("Client: " + " runCommand :: Connection Established == " +runString);
		
		String[] parsingValue = runString.split(" ");
		String plcName = parsingValue[parsingValue.length-1];
		
		if(runString.contains("enable")) {
			//해당 PLC가 실행되는지 먼저 확인을 한다..
			if(NetworkServerManager.getInstance().checkPlcFile(plcName+".plc")) {
				DialogManager.getInstance().showServerErrorConfirmDialog("현재 해당 PLC가 실행중입니다.");
				return;
			}
		}
		
		
		//MemberDAO의 insertLog()를 호출하여 DB에 저장한다.
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

			/* 서버로부터 response가 없는 경우에 socket를 종료하기 위한 SupinanTimer가 null인경우 SupinanTimer 객체를 생성한다.. */
			if(this.timer==null) {
				timer = new SupinanTimer();
			}

			//SupinanTimer가 생성한 timer아이디를 timerId로 저장한다.
			this.timerId = timer.addTimer(new TimerCall(500));

			/* plc 파일을 실행하는 경우는 response가 없으므로 Server Message를 받지 않는다. 2초후에 Timer가 자동으로 socket를 닫는다.*/
			if(!runString.contains("plc")) {
				
				serverMsg = reader.readLine();
				if(serverMsg!=null) {
					this.consolePane.getSelectionModel().select(fxTelnetTab);
					System.out.println(Tag+" Eco Response == "+serverMsg);
					if(this.socketClient!=null && !this.socketClient.isClosed()) {
						serverMsg = reader.readLine();
						this.fxResultTextArea.appendText("\n Justek> "+serverMsg);
					}

//					/* 서버로부터 Response가 Error인 경우는 Error Message Tab(errorTab)를 활성화하여 Error Message를 보여준다.  */
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
	 * 서버로부터 Response가 없는경우 while문을 제어하기 위한 Timer Class
	 * @author YOUNGOK YOO
	 * @since 2018. 6. 19.
	 */
	public class TimerCall extends Timer{

		int sec=0;
		/**
		 * repeatPeriod 의 시간이 지날 때마다 execute() 메소드를 호출하도록 설정한다
		 * @param repeatPeriod(반복주기,ms)
		 * 예 : repeatPeriod = 1000 일 경우 1초
		 */
		public TimerCall(long repeatPeriod) {
			//timer를 1초마다 실행하고 timer는 5초후에 완전을 종료하도록 생성한다. 
			super(repeatPeriod,5);
		}

		/**
		 * repeatPeriod마다 호출되는 메소드
		 */
		@Override
		public void execute() {
			Calendar cal = Calendar.getInstance();
			System.out.println(Tag+" :: TimerCall 호출! " + cal.get(Calendar.HOUR_OF_DAY) + "시 " +
					cal.get(Calendar.MINUTE) + "분 " + cal.get(Calendar.SECOND) + "초");
			sec++;	//1초마다 sec값을 증가시킨다.
			//2초후에는 timer 실행을 종료한다.
			if(sec==2) {
				timer.removeTimer(timerId);		
			}
		}

		/** 
		 * 타이머가 종료되었을 경우 호출된다
		 * 해당 타이머 구현체는 일정시간마다 무한 반복이기 때문에 
		 * SupinanTimer 의 closeTimer() 를 호출하지 않는한 해당 메소드는
		 * 호출하지 않는다		 * 
		 * @see com.supinan.util.timer.Timer#stopTimer(com.supinan.util.timer.TimerStopType)
		 */
		@Override
		public void stopTimer(TimerStopType type) {
			try {
				socketClient.close();
				socketClient=null;
				System.out.println(Tag + " :: socketClient 을 닫는다.:");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Tag + " :: TimerCall 종료:" + type.name());
		}
	}	
	/* ################### ############# Inner Class End ############### ################### */	
}