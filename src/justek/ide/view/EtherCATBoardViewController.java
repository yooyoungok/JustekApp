/**
 * ------------------------------------------------------------------------------
 * @Project       :  JustekApp
 * @Source        : EtherCATBoardViewController.java
 * @Description  : EtherCAT�� ��Ʈ�� ���� ���¸� �� ������ ������ ������ ���� �޾� ON/OFF���¸� ������ �������� ǥ�� �ϰ� 
 *                     ����ڰ� ���� ����ġ�� ON/OFF�� �� �� �ִ� ����� �����Ѵ�.
 * @Author        : YOUNGOK YOO / Ȯ���� : 
 * @Version       : v1.0
 * Copyright(c) 2018 JUSTEK All rights reserved
 *------------------------------------------------------------------------------
 *                  ��         ��         ��         ��                       
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                      DESCRIPTION                        
 * ----------  ------  --------------------------------------------------------- 
 * 2018.06.14  ������    �űԻ���                                     
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
	 * @Desc  Port�� Name�� ǥ���ϴ� Label component fxNameLabel/ fxNameLabel1
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
	 * @Desc PortName�� fxNameLabel�� �ش��ϴ� Switch������ ON/OFF ���� ǥ�� Circle component  ->  fxPort01 ~ fxPort08
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
	 * @Desc PortName�� fxNameLabel1�� �ش��ϴ� Switch������ ON/OFF ���� ǥ�� Circle component  ->  fxPort01 ~ fxPort08
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
	 * @Desc PortName�� fxNameLabel3�� �ش��ϴ� Switch������ ON/OFF ���� ǥ�� Circle component  ->  fxPort01 ~ fxPort08
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
	 * @Desc PortName�� fxNameLabel2�� �ش��ϴ� Switch������ ON/OFF ���� ǥ�� Circle component  ->  fxPort01 ~ fxPort08
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
	 * @Desc PortName�� fxNameLabel1�� �ش��ϴ� Switch������ ON/OFF Set����� �����ϱ����� RadioButton 
	 *          ON : RadioButton�� ���´� selected / OFF : unSelected (fxRadioButton011 ~ fxRadioButton081)
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
	 * @Desc PortName�� fxNameLabel�� �ش��ϴ� Switch������ ON/OFF Set����� �����ϱ����� RadioButton 
	 *          ON : RadioButton�� ���´� selected / OFF : unSelected (fxRadioButton011 ~ fxRadioButton081)
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
	 * @Desc PortName�� fxNameLabel3�� �ش��ϴ� Switch������ ON/OFF Set����� �����ϱ����� RadioButton 
	 *          ON : RadioButton�� ���´� selected / OFF : unSelected (fxRadioButton011 ~ fxRadioButton081)
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
	 * @Desc PortName�� fxNameLabel2�� �ش��ϴ� Switch������ ON/OFF Set����� �����ϱ����� RadioButton 
	 *          ON : RadioButton�� ���´� selected / OFF : unSelected (fxRadioButton011 ~ fxRadioButton081)
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
		
		//���ͳ��� ����Ǿ� ���� �ʰų�, ������ �������� ������ �������� �ʵ��� ó��
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
	 * EtherCAT ī���� �� ��Ʈ�� ����ON/OFF���¸� ������ ǥ���ϱ� ���� ��ɾ��� �Է��Ͽ� ������ �޾ƿ´�.
	 * �������� ������ ���� 0�̸� OFF / 1 �̸�ON 
	 * ��) 00000001 -> 8��°�� ON����	 * 
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
			result = StringUtil.getNumnberFommat(result);		//������ ���� ���� ���� �Ҽ������·� �����Ƿ� ���� ������ ����
			int value = Integer.valueOf(result);	// ������ ���� ���� ���� �������� �����ϱ� ���Ͽ� int�� �����Ѵ�.
			
			//result�� 255�̸� switch�� ��� ON
			char[] array=null;
			if(value==255) {
				for(int i=0;i<8;i++) {
					colorList.add(Color.YELLOWGREEN);		//ON �����̹Ƿ� YELLOWGREEN���� ��� �Է��Ѵ�.
					array = new char[] {'1','1','1','1','1','1','1','1'};
				}

				// ����� color �迭�� ���°��� @param������ ������ ��� ������ ������ ON�����̹Ƿ� null�� ������.
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
			//value�� 0�̸� switch �� ��� OFF
			else if(value==0) {
				//for���� ���� switch�� ���°��� Ȯ���Ѵ�.
				for(int i=0;i<8;i++) {
					colorList.add(Color.GRAY);		//OFF �����̹Ƿ� GRAY���� ��� �Է��Ѵ�.
					array = new char[] {'0','0','0','0','0','0','0','0'};
				}
				// ����� color �迭�� ���°��� @param���̳� ��� ������ ������ OFF�����̹Ƿ� ���°��� null�� ������.
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
			//result ���� 255�� 0�� �ƴ� ���� �������� �����Ͽ� ON/OFF�� Ȯ���Ѵ�.
			else {
				//������ ���� ���� ���� Integer�� ������ �������� ������String���� �����Ѵ�.
				result = Integer.toBinaryString(value);
				System.out.println(Tag+"getPortInformation :: Value ::" + result);
				//������ ������ ��Ʈ���� char[]�� �����Ѵ�.
				
				char[] array_0 = result.toCharArray(); 
				/* �������� ����� 8�� �ڸ����� ���ؾ� �ϹǷ� 8������ ������� 
				   ������ ���ڸ��� ��� 0���� ä���ֱ����� for���� �����Ѵ�.
				 */
				char[] array_1 = new char[8-array_0.length]; 
				if(array_0.length!=8) {
					for(int i=0;i<array_1.length;i++) {
						array_1[i]='0';
					}
				}
				
				//0���� ä���� ���� �������� ������ ������� merge�� ���� StringBuffer Ŭ������ append�� �̿��Ѵ�.
				StringBuffer sb = new StringBuffer(String.valueOf(array_1)); 
				sb.append(String.valueOf(array_0));  // result+array
				result = sb.toString();
				
				System.out.println(Tag+"getPortInformation :: byte Array ::" + result);
				
				//switch���� ���°��� switch�� ON/OFF ǥ�ø� ���� �Լ��� ȣ���Ѵ�.
				this.setPortColorList(portName, result.toCharArray());
			}
		}
	}

	/**
	 * �� ��Ʈ��  switch�� ON/OFF���� Ȯ���Ͽ� ȭ�鿡 ǥ���� Color���� �����Ѵ�. 
	 * switch ON : YELLOWGREEN color / switch OFF : GRAY color	 * 
	 * @author : YOO YOUNGOK 
	 * @method  setPortColorList
	 * @param portName String -> ��Ʈ��
	 * @param values char[]->  ��Ʈ�� 8�� lamp�� ���� (0 or 1)  
	 * @return  void
	 * @exception 
	*  @see
	 * @since 2018. 6. 14.
	 */
	private  void setPortColorList(String portName,char[] values) {
		
		ObservableList<Color> colorList = FXCollections.observableArrayList();		//���¿� ���� 8���� switch ColorArray
		
		//switch�� ���°��� for���� �̿��Ͽ� ȭ�鿡 ��Ÿ�� Color�� �����Ѵ�.
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
		
		//�����̸��� ������ switch ON/OFF ���� ȭ�鿡 ǥ���� �Լ��� ȣ���Ѵ�.
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
	 * ù��° ��Ʈ�� 8�� switch�� ON/OFF���¸� Color�� RadioButton�� selected�� ǥ���Ѵ�.
	 * ��) RadioButton -> switch ON : selected / OFF : unselected	 * 
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
		
		//color�迭�κ��� ������ �ش��ϴ� ��ġ�� color�� ǥ���Ѵ�.
		this.fxPort01.setFill(color.get(0));
		this.fxPort02.setFill(color.get(1));
		this.fxPort03.setFill(color.get(2));
		this.fxPort04.setFill(color.get(3));
		this.fxPort05.setFill(color.get(4));
		this.fxPort06.setFill(color.get(5));
		this.fxPort07.setFill(color.get(6));
		this.fxPort08.setFill(color.get(7));

		/*switch�� ���¸� ������ chars �迭�κ��� ������ �ش��ϴ� ���¸� RadioButton�� ǥ���Ѵ�.
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
		
		//��Ʈ�� lamp�� ���°��� lampStatusArray�� �����Ѵ�.
		this.lampStatusArray.add(0, chars);
	}
	
	/**
	 * �ι�° ��Ʈ�� 8�� switch�� ON/OFF���¸� Color�� RadioButton�� selected�� ǥ���Ѵ�.
	 * ��) RadioButton -> switch ON : selected / OFF : unselected	 * 
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
		
		//color�迭�κ��� ������ �ش��ϴ� ��ġ�� color�� ǥ���Ѵ�.
		this.fxPort011.setFill(color.get(0));
		this.fxPort021.setFill(color.get(1));
		this.fxPort031.setFill(color.get(2));
		this.fxPort041.setFill(color.get(3));
		this.fxPort051.setFill(color.get(4));
		this.fxPort061.setFill(color.get(5));
		this.fxPort071.setFill(color.get(6));
		this.fxPort081.setFill(color.get(7));

		/*switch�� ���¸� ������ chars �迭�κ��� ������ �ش��ϴ� ���¸� RadioButton�� ǥ���Ѵ�.
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
		
		//��Ʈ�� lamp�� ���°��� lampStatusArray�� �����Ѵ�.
		this.lampStatusArray.add(1, chars);
	}
	
	/**
	 * �׹�° ��Ʈ�� 8�� switch�� ON/OFF���¸� Color�� RadioButton�� selected�� ǥ���Ѵ�.
	 * ��) RadioButton -> switch ON : selected / OFF : unselected	 * 
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
		
		//color�迭�κ��� ������ �ش��ϴ� ��ġ�� color�� ǥ���Ѵ�.
		this.fxPort013.setFill(color.get(0));
		this.fxPort023.setFill(color.get(1));
		this.fxPort033.setFill(color.get(2));
		this.fxPort043.setFill(color.get(3));
		this.fxPort053.setFill(color.get(4));
		this.fxPort063.setFill(color.get(5));
		this.fxPort073.setFill(color.get(6));
		this.fxPort083.setFill(color.get(7));

		/*switch�� ���¸� ������ chars �迭�κ��� ������ �ش��ϴ� ���¸� RadioButton�� ǥ���Ѵ�.
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
		
		//��Ʈ�� lamp�� ���°��� lampStatusArray�� �����Ѵ�.
		this.lampStatusArray.add(3, chars);
	}

	/**
	 * ����° ��Ʈ�� 8�� switch�� ON/OFF���¸� Color�� RadioButton�� selected�� ǥ���Ѵ�.
	 * ��) RadioButton -> switch ON : selected / OFF : unselected	 * 
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
		//color�迭�κ��� ������ �ش��ϴ� ��ġ�� color�� ǥ���Ѵ�.
		this.fxPort012.setFill(color.get(0));
		this.fxPort022.setFill(color.get(1));
		this.fxPort032.setFill(color.get(2));
		this.fxPort042.setFill(color.get(3));
		this.fxPort052.setFill(color.get(4));
		this.fxPort062.setFill(color.get(5));
		this.fxPort072.setFill(color.get(6));
		this.fxPort082.setFill(color.get(7));

		/*switch�� ���¸� ������ chars �迭�κ��� ������ �ش��ϴ� ���¸� RadioButton�� ǥ���Ѵ�.
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
		
		//��Ʈ�� lamp�� ���°��� lampStatusArray�� �����Ѵ�.
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
			
			DialogManager.getInstance().showServerErrorConfirmDialog("������ ��Ʈ�� ���� ON/OFF����� �����Ǿ� ���� �ʽ��ϴ�.\n�������θ� Ȯ���Ͻñ� �ٶ��ϴ�.");
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
			
			DialogManager.getInstance().showServerErrorConfirmDialog("������ ��Ʈ�� ���� ON/OFF����� �����Ǿ� ���� �ʽ��ϴ�.\n�������θ� Ȯ���Ͻñ� �ٶ��ϴ�.");
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
		//������ ������ �����ϸ� portName�� �ش��ϴ� ��Ʈ�� �������¸� �ٽ� ��û�Ѵ�.
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
