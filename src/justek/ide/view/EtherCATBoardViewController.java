/**
 * ------------------------------------------------------------------------------
 * @Project       :  JustekApp
 * @Source        : EtherCATBoardViewController.java
 * @Description  : EtherCAT의 포트의 램프 상태를 각 램프의 정보를 서버로 부터 받아 ON/OFF상태를 지정한 색상으로 표시 하고 
 *                     사용자가 직접 스위치를 ON/OFF를 할 수 있는 기능을 제공한다.
 * @Author        : YOUNGOK YOO / 확인자 : 
 * @Version       : v1.0
 * Copyright(c) 2018 JUSTEK All rights reserved
 *------------------------------------------------------------------------------
 *                  변         경         사         항                       
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                      DESCRIPTION                        
 * ----------  ------  --------------------------------------------------------- 
 * 2018.06.14  유영옥    신규생성                                     
 *------------------------------------------------------------------------------
 */

package justek.ide.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import justek.ide.model.CommandConst;
import justek.ide.model.manager.DialogManager;
import justek.ide.model.manager.NetworkServerManager;
import justek.ide.utils.StringUtil;

public class EtherCATBoardViewController implements EventHandler<ActionEvent> {

	static final String Tag = "EtherCATBoardViewController";
	
	ObservableList<char[]> lampStatusArray = FXCollections.observableArrayList();

	/** 
	 * @FieldType Label
	 * @Desc  Port의 Name을 표시하는 Label component fxNameLabel/ fxNameLabel1
	 */
	@FXML
	private Label fxNameLabel;
	@FXML
	private Label fxNameLabel1;
	@FXML
	private Label fxNameLabel2;
	@FXML
	private Label fxNameLabel3;

	/** 
	 * @FieldType Circle
	 * @Desc PortName이 fxNameLabel에 해당하는 Switch램프의 ON/OFF 색상 표시 Circle component  ->  fxPort01 ~ fxPort08
	 */
	@FXML
	private Circle fxPort01;
	@FXML
	private Circle fxPort02;
	@FXML
	private Circle fxPort04;
	@FXML
	private Circle fxPort03;
	@FXML
	private Circle fxPort06;	
	@FXML
	private Circle fxPort05;
	@FXML
	private Circle fxPort08;
	@FXML
	private Circle fxPort07;

	/** 
	 * @FieldType Circle
	 * @Desc PortName이 fxNameLabel1에 해당하는 Switch램프의 ON/OFF 색상 표시 Circle component  ->  fxPort01 ~ fxPort08
	 */
	@FXML
	private Circle fxPort011;
	@FXML
	private Circle fxPort071;
	@FXML
	private Circle fxPort061;
	@FXML
	private Circle fxPort041;
	@FXML
	private Circle fxPort021;
	@FXML
	private Circle fxPort081;
	@FXML
	private Circle fxPort051;
	@FXML
	private Circle fxPort031;
	
	/** 
	 * @FieldType Circle
	 * @Desc PortName이 fxNameLabel3에 해당하는 Switch램프의 ON/OFF 색상 표시 Circle component  ->  fxPort01 ~ fxPort08
	 */
	@FXML
	private Circle fxPort013;
	@FXML
	private Circle fxPort073;
	@FXML
	private Circle fxPort063;
	@FXML
	private Circle fxPort043;
	@FXML
	private Circle fxPort023;
	@FXML
	private Circle fxPort083;
	@FXML
	private Circle fxPort053;
	@FXML
	private Circle fxPort033;
	
	/** 
	 * @FieldType Circle
	 * @Desc PortName이 fxNameLabel2에 해당하는 Switch램프의 ON/OFF 색상 표시 Circle component  ->  fxPort01 ~ fxPort08
	 */
	@FXML
	private Circle fxPort012;
	@FXML
	private Circle fxPort072;
	@FXML
	private Circle fxPort062;
	@FXML
	private Circle fxPort042;
	@FXML
	private Circle fxPort022;
	@FXML
	private Circle fxPort082;
	@FXML
	private Circle fxPort052;
	@FXML
	private Circle fxPort032;
	
	/** 
	 * @FieldType RadioButton
	 * @Desc PortName이 fxNameLabel1에 해당하는 Switch램프의 ON/OFF Set명령을 수행하기위한 RadioButton 
	 *          ON : RadioButton의 상태는 selected / OFF : unSelected (fxRadioButton011 ~ fxRadioButton081)
	 */
	@FXML
	private RadioButton fxRadioButton031;
	@FXML
	private RadioButton fxRadioButton051;
	@FXML
	private RadioButton fxRadioButton011;
	@FXML
	private RadioButton fxRadioButton071;
	@FXML
	private RadioButton fxRadioButton041;
	@FXML
	private RadioButton fxRadioButton021;
	@FXML
	private RadioButton fxRadioButton081;
	@FXML
	private RadioButton fxRadioButton061;

	/** 
	 * @FieldType RadioButton
	 * @Desc PortName이 fxNameLabel에 해당하는 Switch램프의 ON/OFF Set명령을 수행하기위한 RadioButton 
	 *          ON : RadioButton의 상태는 selected / OFF : unSelected (fxRadioButton011 ~ fxRadioButton081)
	 */
	@FXML
	private RadioButton fxRadioButton01;
	@FXML
	private RadioButton fxRadioButton02;
	@FXML
	private RadioButton fxRadioButton03;
	@FXML
	private RadioButton fxRadioButton06;
	@FXML
	private RadioButton fxRadioButton07;
	@FXML
	private RadioButton fxRadioButton04;
	@FXML
	private RadioButton fxRadioButton05;
	@FXML
	private RadioButton fxRadioButton08;

	/** 
	 * @FieldType RadioButton
	 * @Desc PortName이 fxNameLabel3에 해당하는 Switch램프의 ON/OFF Set명령을 수행하기위한 RadioButton 
	 *          ON : RadioButton의 상태는 selected / OFF : unSelected (fxRadioButton011 ~ fxRadioButton081)
	 */
	@FXML
	private RadioButton fxRadioButton013;
	@FXML
	private RadioButton fxRadioButton023;
	@FXML
	private RadioButton fxRadioButton033;
	@FXML
	private RadioButton fxRadioButton063;
	@FXML
	private RadioButton fxRadioButton073;
	@FXML
	private RadioButton fxRadioButton043;
	@FXML
	private RadioButton fxRadioButton053;
	@FXML
	private RadioButton fxRadioButton083;
	
	/** 
	 * @FieldType RadioButton
	 * @Desc PortName이 fxNameLabel2에 해당하는 Switch램프의 ON/OFF Set명령을 수행하기위한 RadioButton 
	 *          ON : RadioButton의 상태는 selected / OFF : unSelected (fxRadioButton011 ~ fxRadioButton081)
	 */
	@FXML
	private RadioButton fxRadioButton012;
	@FXML
	private RadioButton fxRadioButton022;
	@FXML
	private RadioButton fxRadioButton032;
	@FXML
	private RadioButton fxRadioButton062;
	@FXML
	private RadioButton fxRadioButton072;
	@FXML
	private RadioButton fxRadioButton042;
	@FXML
	private RadioButton fxRadioButton052;
	@FXML
	private RadioButton fxRadioButton082;
	
	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 * @since 2018.06.14
	 */
	@FXML
	private void initialize() {
		
		//인터넷이 연결되어 있지 않거나, 소켓을 열려있지 않으면 실행하지 않도록 처리
		if(!NetworkServerManager.checkNetworkConnection() || !NetworkServerManager.checkSocket()) return;
		
		NetworkServerManager.reSetSwitchID(CommandConst.SET_PORT_1);
		NetworkServerManager.reSetSwitchID(CommandConst.SET_PORT_2);
		NetworkServerManager.reSetSwitchID(CommandConst.SET_PORT_3);
		NetworkServerManager.reSetSwitchID(CommandConst.SET_PORT_4);

		this.getPortInformation(CommandConst.PORT_1);
		this.getPortInformation(CommandConst.PORT_2);
		this.getPortInformation(CommandConst.PORT_3);
		this.getPortInformation(CommandConst.PORT_4);
	}

	/**
	 * EtherCAT 카드의 각 포트의 램프ON/OFF상태를 램프로 표현하기 위한 명령어을 입력하여 정보를 받아온다.
	 * 이진수로 변경한 값이 0이면 OFF / 1 이면ON 
	 * 예) 00000001 -> 8번째만 ON상태	 * 
	 * @author : YOO YOUNGOK 
	 * @method  getPortInformation
	 * @param portName
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 14.
	 */
	private void getPortInformation(String portName) {
		String result = NetworkServerManager.getPortInfo(portName);
		System.out.println(Tag+"getPortInformation :: result ::" + result);
		ObservableList<Color> colorList = FXCollections.observableArrayList();  // Color List
		
		
		if(result!=null && !result.contains("ERROR")) {
			result = StringUtil.getNumnberFommat(result);		//서버로 부터 받은 값에 소수점형태로 받으므로 숫자 포맷을 변경
			int value = Integer.valueOf(result);	// 서버로 부터 받은 값을 이진수로 변경하기 위하여 int로 변경한다.
			
			//result가 255이면 switch가 모두 ON
			char[] array=null;
			if(value==255) {
				for(int i=0;i<8;i++) {
					colorList.add(Color.YELLOWGREEN);		//ON 상태이므로 YELLOWGREEN값을 모두 입력한다.
					array = new char[] {'1','1','1','1','1','1','1','1'};
				}

				// 저장된 color 배열과 상태값을 @param값으로 보내나 모든 램프가 동일한 ON상태이므로 null을 보낸다.
				if(portName.equals(CommandConst.PORT_1)) {
					this.setH1Info(colorList,array);
				}
				else if(portName.equals(CommandConst.PORT_2)) {
					this.setH2Info(colorList,array);
				}
				else if(portName.equals(CommandConst.PORT_3)) {
					this.setH3Info(colorList,array);
				}
				else if(portName.equals(CommandConst.PORT_4)) {
					this.setH4Info(colorList,array);
				}
			}
			//value가 0이면 switch 가 모두 OFF
			else if(value==0) {
				//for문을 통해 switch의 상태값을 확인한다.
				for(int i=0;i<8;i++) {
					colorList.add(Color.GRAY);		//OFF 상태이므로 GRAY값을 모두 입력한다.
					array = new char[] {'0','0','0','0','0','0','0','0'};
				}
				// 저장된 color 배열과 상태값이 @param값이나 모든 램프가 동일한 OFF상태이므로 상태값은 null을 보낸다.
				if(portName.equals(CommandConst.PORT_1)) {
					this.setH1Info(colorList,array);
				}
				else if(portName.equals(CommandConst.PORT_2)) {
					this.setH2Info(colorList,array);
				}
				else if(portName.equals(CommandConst.PORT_3)) {
					this.setH3Info(colorList,array);
				}
				else if(portName.equals(CommandConst.PORT_4)) {
					this.setH4Info(colorList,array);
				}
			}
			//result 값이 255와 0이 아닌 경우는 이진수로 변경하여 ON/OFF를 확인한다.
			else {
				//서버로 부터 받은 값아 Integer로 변경한 십진수를 이진수String으로 변경한다.
				result = Integer.toBinaryString(value);
				System.out.println(Tag+"getPortInformation :: Value ::" + result);
				//변경한 이진수 스트링을 char[]로 저장한다.
				
				char[] array_0 = result.toCharArray(); 
				/* 이진수로 변경시 8개 자리수를 비교해야 하므로 8개보다 작은경우 
				   나머지 앞자리를 모두 0으로 채워주기위한 for문을 실행한다.
				 */
				char[] array_1 = new char[8-array_0.length]; 
				if(array_0.length!=8) {
					for(int i=0;i<array_1.length;i++) {
						array_1[i]='0';
					}
				}
				
				//0으로 채워진 값과 이진수로 변경한 결과값을 merge를 위해 StringBuffer 클래스의 append를 이용한다.
				StringBuffer sb = new StringBuffer(String.valueOf(array_1)); 
				sb.append(String.valueOf(array_0));  // result+array
				result = sb.toString();
				
				System.out.println(Tag+"getPortInformation :: byte Array ::" + result);
				
				//switch들의 상태값을 switch의 ON/OFF 표시를 위한 함수를 호출한다.
				this.setPortColorList(portName, result.toCharArray());
			}
		}
	}

	/**
	 * 각 포트의  switch의 ON/OFF값을 확인하여 화면에 표시할 Color값을 저장한다. 
	 * switch ON : YELLOWGREEN color / switch OFF : GRAY color	 * 
	 * @author : YOO YOUNGOK 
	 * @method  setPortColorList
	 * @param portName String -> 포트명
	 * @param values char[]->  포트의 8개 lamp의 상태 (0 or 1)  
	 * @return  void
	 * @exception 
	*  @see
	 * @since 2018. 6. 14.
	 */
	private  void setPortColorList(String portName,char[] values) {
		
		ObservableList<Color> colorList = FXCollections.observableArrayList();		//상태에 따른 8개의 switch ColorArray
		
		//switch의 상태값을 for문을 이용하여 화면에 나타낼 Color를 결정한다.
		for(int i=0;i<values.length;i++) {
			char value = values[i];
			if(value =='0') {
				colorList.add(Color.GRAY);  // switch OFF
			}
			else if(value == '1') {
				colorList.add(Color.YELLOWGREEN); // switch ON
			}
		}

		System.out.println(Tag+"setPortColorList :: colorList size ::" + colorList.size());
		
		//포토이름에 따라의 switch ON/OFF 상태 화면에 표시할 함수를 호출한다.
		if(portName.equals(CommandConst.PORT_1)) {
			this.setH1Info(colorList,values);
		}
		else if(portName.equals(CommandConst.PORT_2)) {
			this.setH2Info(colorList,values);
		}
		else if(portName.equals(CommandConst.PORT_3)) {
			this.setH3Info(colorList,values);
		}
		else if(portName.equals(CommandConst.PORT_4)) {
			this.setH4Info(colorList,values);
		}

	}
	
	/**
	 * 첫번째 포트의 8개 switch의 ON/OFF상태를 Color와 RadioButton의 selected로 표시한다.
	 * 예) RadioButton -> switch ON : selected / OFF : unselected	 * 
	 * @author : YOO YOUNGOK 
	 * @method  setH1Info
	 * @param color  ObservableList<Color> ->  color array
	 * @param chars  char[] -> character array (0 / 1)
	 * @return  void
	 * @exception 
	*  @see
	 * @since 2018. 6. 19.
	 */
	private void setH1Info(ObservableList<Color> color,char[] chars) {
		
		//color배열로부터 각각의 해당하는 위치의 color를 표시한다.
		this.fxPort01.setFill(color.get(0));
		this.fxPort02.setFill(color.get(1));
		this.fxPort03.setFill(color.get(2));
		this.fxPort04.setFill(color.get(3));
		this.fxPort05.setFill(color.get(4));
		this.fxPort06.setFill(color.get(5));
		this.fxPort07.setFill(color.get(6));
		this.fxPort08.setFill(color.get(7));

		/*switch의 상태를 저장한 chars 배열로부터 각각의 해당하는 상태를 RadioButton에 표시한다.
		* 0(OFF) : selected-> false / 1(ON) : selected-> true*/
		if(chars!=null) {
			if(chars[0]=='1') this.fxRadioButton01.setSelected(true);
			else this.fxRadioButton01.setSelected(false);

			if(chars[1]=='1') this.fxRadioButton02.setSelected(true);
			else this.fxRadioButton02.setSelected(false);

			if(chars[2]=='1') this.fxRadioButton03.setSelected(true);
			else this.fxRadioButton03.setSelected(false);

			if(chars[3]=='1') this.fxRadioButton04.setSelected(true);
			else this.fxRadioButton04.setSelected(false);

			if(chars[4]=='1') this.fxRadioButton05.setSelected(true);
			else this.fxRadioButton05.setSelected(false);

			if(chars[5]=='1') this.fxRadioButton06.setSelected(true);
			else this.fxRadioButton06.setSelected(false);

			if(chars[6]=='1') this.fxRadioButton07.setSelected(true);
			else this.fxRadioButton07.setSelected(false);

			if(chars[7]=='1') this.fxRadioButton08.setSelected(true);
			else this.fxRadioButton08.setSelected(false);
		}
		
		//포트의 lamp의 상태값을 lampStatusArray에 저장한다.
		this.lampStatusArray.add(0, chars);
	}
	
	/**
	 * 두번째 포트의 8개 switch의 ON/OFF상태를 Color와 RadioButton의 selected로 표시한다.
	 * 예) RadioButton -> switch ON : selected / OFF : unselected	 * 
	 * @author : YOO YOUNGOK 
	 * @method  setH1Info
	 * @param color  ObservableList<Color> ->  color array
	 * @param chars  char[] -> character array (0 / 1)
	 * @return  void
	 * @exception 
	*  @see
	 * @since 2018. 6. 19.
	 */
	private void setH2Info(ObservableList<Color> color,char[] chars) {
		
		//color배열로부터 각각의 해당하는 위치의 color를 표시한다.
		this.fxPort011.setFill(color.get(0));
		this.fxPort021.setFill(color.get(1));
		this.fxPort031.setFill(color.get(2));
		this.fxPort041.setFill(color.get(3));
		this.fxPort051.setFill(color.get(4));
		this.fxPort061.setFill(color.get(5));
		this.fxPort071.setFill(color.get(6));
		this.fxPort081.setFill(color.get(7));

		/*switch의 상태를 저장한 chars 배열로부터 각각의 해당하는 상태를 RadioButton에 표시한다.
		* 0(OFF) : selected-> false / 1(ON) : selected-> true*/
		if(chars!=null) {
			if(chars[0]=='1') this.fxRadioButton011.setSelected(true);
			else this.fxRadioButton011.setSelected(false);

			if(chars[1]=='1') this.fxRadioButton021.setSelected(true);
			else this.fxRadioButton021.setSelected(false);

			if(chars[2]=='1') this.fxRadioButton031.setSelected(true);
			else this.fxRadioButton031.setSelected(false);

			if(chars[3]=='1') this.fxRadioButton041.setSelected(true);
			else this.fxRadioButton041.setSelected(false);

			if(chars[4]=='1') this.fxRadioButton051.setSelected(true);
			else this.fxRadioButton051.setSelected(false);

			if(chars[5]=='1') this.fxRadioButton061.setSelected(true);
			else this.fxRadioButton061.setSelected(false);

			if(chars[6]=='1') this.fxRadioButton071.setSelected(true);
			else this.fxRadioButton071.setSelected(false);

			if(chars[7]=='1') this.fxRadioButton081.setSelected(true);
			else this.fxRadioButton081.setSelected(false);
		}
		
		//포트의 lamp의 상태값을 lampStatusArray에 저장한다.
		this.lampStatusArray.add(1, chars);
	}
	
	/**
	 * 네번째 포트의 8개 switch의 ON/OFF상태를 Color와 RadioButton의 selected로 표시한다.
	 * 예) RadioButton -> switch ON : selected / OFF : unselected	 * 
	 * @author : YOO YOUNGOK 
	 * @method  setH1Info
	 * @param color  ObservableList<Color> ->  color array
	 * @param chars  char[] -> character array (0 / 1)
	 * @return  void
	 * @exception 
	*  @see
	 * @since 2018. 6. 14.
	 */
	
	private void setH4Info(ObservableList<Color> color,char[] chars) {
		
		//color배열로부터 각각의 해당하는 위치의 color를 표시한다.
		this.fxPort013.setFill(color.get(0));
		this.fxPort023.setFill(color.get(1));
		this.fxPort033.setFill(color.get(2));
		this.fxPort043.setFill(color.get(3));
		this.fxPort053.setFill(color.get(4));
		this.fxPort063.setFill(color.get(5));
		this.fxPort073.setFill(color.get(6));
		this.fxPort083.setFill(color.get(7));

		/*switch의 상태를 저장한 chars 배열로부터 각각의 해당하는 상태를 RadioButton에 표시한다.
		* 0(OFF) : selected-> false / 1(ON) : selected-> true*/
		if(chars!=null) {
			if(chars[0]=='1') this.fxRadioButton013.setSelected(true);
			else this.fxRadioButton013.setSelected(false);

			if(chars[1]=='1') this.fxRadioButton023.setSelected(true);
			else this.fxRadioButton023.setSelected(false);

			if(chars[2]=='1') this.fxRadioButton033.setSelected(true);
			else this.fxRadioButton033.setSelected(false);

			if(chars[3]=='1') this.fxRadioButton043.setSelected(true);
			else this.fxRadioButton043.setSelected(false);

			if(chars[4]=='1') this.fxRadioButton053.setSelected(true);
			else this.fxRadioButton053.setSelected(false);

			if(chars[5]=='1') this.fxRadioButton063.setSelected(true);
			else this.fxRadioButton06.setSelected(false);

			if(chars[6]=='1') this.fxRadioButton073.setSelected(true);
			else this.fxRadioButton073.setSelected(false);

			if(chars[7]=='1') this.fxRadioButton083.setSelected(true);
			else this.fxRadioButton083.setSelected(false);
		}
		
		//포트의 lamp의 상태값을 lampStatusArray에 저장한다.
		this.lampStatusArray.add(3, chars);
	}

	/**
	 * 세번째 포트의 8개 switch의 ON/OFF상태를 Color와 RadioButton의 selected로 표시한다.
	 * 예) RadioButton -> switch ON : selected / OFF : unselected	 * 
	 * @author : YOO YOUNGOK 
	 * @method  setH1Info
	 * @param color  ObservableList<Color> ->  color array
	 * @param chars  char[] -> character array (0 / 1)
	 * @return  void
	 * @exception 
	*  @see
	 * @since 2018. 6. 14.
	 */
	private void setH3Info(ObservableList<Color> color,char[] chars) {
		//color배열로부터 각각의 해당하는 위치의 color를 표시한다.
		this.fxPort012.setFill(color.get(0));
		this.fxPort022.setFill(color.get(1));
		this.fxPort032.setFill(color.get(2));
		this.fxPort042.setFill(color.get(3));
		this.fxPort052.setFill(color.get(4));
		this.fxPort062.setFill(color.get(5));
		this.fxPort072.setFill(color.get(6));
		this.fxPort082.setFill(color.get(7));

		/*switch의 상태를 저장한 chars 배열로부터 각각의 해당하는 상태를 RadioButton에 표시한다.
		* 0(OFF) : selected-> false / 1(ON) : selected-> true*/
		if(chars!=null) {
			if(chars[0]=='1') this.fxRadioButton012.setSelected(true);
			else this.fxRadioButton012.setSelected(false);

			if(chars[1]=='1') this.fxRadioButton022.setSelected(true);
			else this.fxRadioButton022.setSelected(false);

			if(chars[2]=='1') this.fxRadioButton032.setSelected(true);
			else this.fxRadioButton032.setSelected(false);

			if(chars[3]=='1') this.fxRadioButton042.setSelected(true);
			else this.fxRadioButton042.setSelected(false);

			if(chars[4]=='1') this.fxRadioButton052.setSelected(true);
			else this.fxRadioButton052.setSelected(false);

			if(chars[5]=='1') this.fxRadioButton062.setSelected(true);
			else this.fxRadioButton062.setSelected(false);

			if(chars[6]=='1') this.fxRadioButton072.setSelected(true);
			else this.fxRadioButton072.setSelected(false);

			if(chars[7]=='1') this.fxRadioButton082.setSelected(true);
			else this.fxRadioButton082.setSelected(false);
		}
		
		//포트의 lamp의 상태값을 lampStatusArray에 저장한다.
		this.lampStatusArray.add(2, chars);
	}

	
	/** (non-Javadoc)
	 * @see javafx.event.EventHandler#handle(javafx.event.Event)
	 */
	@Override
	public void handle(ActionEvent event) {
		if(event.getSource().equals(fxRadioButton01) ||
				event.getSource().equals(fxRadioButton02) ||
				event.getSource().equals(fxRadioButton03) ||
				event.getSource().equals(fxRadioButton04) ||
				event.getSource().equals(fxRadioButton05) ||
				event.getSource().equals(fxRadioButton06) ||
				event.getSource().equals(fxRadioButton07) ||
				event.getSource().equals(fxRadioButton08)) {
			
			DialogManager.getInstance().showServerErrorConfirmDialog("선택한 포트는 램프 ON/OFF기능이 설정되어 있지 않습니다.\n설정여부를 확인하시기 바랍니다.");
			this.getPortInformation(CommandConst.PORT_1);
		}
		else if(event.getSource().equals(fxRadioButton011) ||
				event.getSource().equals(fxRadioButton021) ||
				event.getSource().equals(fxRadioButton031) ||
				event.getSource().equals(fxRadioButton041) ||
				event.getSource().equals(fxRadioButton051) ||
				event.getSource().equals(fxRadioButton061) ||
				event.getSource().equals(fxRadioButton071) ||
				event.getSource().equals(fxRadioButton081)) {
			
			DialogManager.getInstance().showServerErrorConfirmDialog("선택한 포트는 램프 ON/OFF기능이 설정되어 있지 않습니다.\n설정여부를 확인하시기 바랍니다.");
			this.getPortInformation(CommandConst.PORT_2);
		}
		else if(event.getSource().equals(fxRadioButton012) ||
				event.getSource().equals(fxRadioButton022) ||
				event.getSource().equals(fxRadioButton032) ||
				event.getSource().equals(fxRadioButton042) ||
				event.getSource().equals(fxRadioButton052) ||
				event.getSource().equals(fxRadioButton062) ||
				event.getSource().equals(fxRadioButton072) ||
				event.getSource().equals(fxRadioButton082)) {
			
			String value = this.getLampStatus(CommandConst.PORT_3);
			this.setSwitch(CommandConst.PORT_3,value);
			
		}
		else if(event.getSource().equals(fxRadioButton013) ||
				event.getSource().equals(fxRadioButton023) ||
				event.getSource().equals(fxRadioButton033) ||
				event.getSource().equals(fxRadioButton043) ||
				event.getSource().equals(fxRadioButton053) ||
				event.getSource().equals(fxRadioButton063) ||
				event.getSource().equals(fxRadioButton073) ||
				event.getSource().equals(fxRadioButton083)) {
			
			String value = this.getLampStatus(CommandConst.PORT_4);
			this.setSwitch(CommandConst.PORT_4,value);
		}
	}
	
	/**
	 * @author : YOO YOUNGOK 
	 * @method  setSwitch
	 * @param portName
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	private void setSwitch(String portName,String value) {
		boolean result = NetworkServerManager.setSwitch(portName, value);
		//램프의 설정이 성공하면 portName에 해당하는 포트의 램프상태를 다시 요청한다.
		if(result) {
			this.getPortInformation(portName);
		}
	}
	
	

	/**
	 * @author : YOO YOUNGOK 
	 * @method  getLampStatus
	 * @param portName
	 * @return  String
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	private String getLampStatus(String portName) {
		String result = null;
		char[] array = null;
		
		if(portName.equals(CommandConst.PORT_4)) {
			array = this.lampStatusArray.get(3);
			
			if(this.fxRadioButton013.isSelected())  array[0] ='1';
			else array[0] ='0';
			
			if(this.fxRadioButton023.isSelected())  array[1] ='1';
			else array[1] ='0';
			
			if(this.fxRadioButton033.isSelected())  array[2] ='1';
			else array[2] ='0';
			
			if(this.fxRadioButton043.isSelected())  array[3] ='1';
			else array[3] ='0';
			
			if(this.fxRadioButton053.isSelected())  array[4] ='1';
			else array[4] ='0';
			
			if(this.fxRadioButton063.isSelected())  array[5] ='1';
			else array[5] ='0';
			
			if(this.fxRadioButton073.isSelected())  array[6] ='1';
			else array[6] ='0';
			
			if(this.fxRadioButton083.isSelected())  array[7] ='1';
			else array[7] ='0';
		}

		else if(portName.equals(CommandConst.PORT_3)) {
			array = this.lampStatusArray.get(2);
			
			if(this.fxRadioButton012.isSelected())  array[0] ='1';
			else array[0] ='0';
			
			if(this.fxRadioButton022.isSelected())  array[1] ='1';
			else array[1] ='0';
			
			if(this.fxRadioButton032.isSelected())  array[2] ='1';
			else array[2] ='0';
			
			if(this.fxRadioButton042.isSelected())  array[3] ='1';
			else array[3] ='0';
			
			if(this.fxRadioButton052.isSelected())  array[4] ='1';
			else array[4] ='0';
			
			if(this.fxRadioButton062.isSelected())  array[5] ='1';
			else array[5] ='0';
			
			if(this.fxRadioButton072.isSelected())  array[6] ='1';
			else array[6] ='0';
			
			if(this.fxRadioButton082.isSelected())  array[7] ='1';
			else array[7] ='0';
		}
		
		result = StringUtil.byteToDec(new String(array));
		System.out.println(Tag +" :: "+portName + " getLampStatus == "+result);
		return result;
	}
	public void testAction() {
		this.getPortInformation(CommandConst.PORT_3);
	}
}
