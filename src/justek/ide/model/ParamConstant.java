package justek.ide.model;

public class ParamConstant {

	/*************************
	 * 
	 * SDO 파라미터 COMMAND
	 * 
	 ************************ */

	public static enum SDOVALUE
	{
		PROPOTION_GAIN("Proportional gain"),
		INTEGRAL_GAIN("Integral gain"),
		GAIN_DIVIDE("Gain divide"),
		SCALE("Scale"),
		FEEDBACK_DERIVATIVE_GAIN("Velocity Feedback Derivative Gain"),
		VELOCITY_FF_GAIN("Velocity FF Gain"),
		ACCELERATION_FF_GAIN("Acceleration FF Gain");

		private String value;

		SDOVALUE(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
		
		public String getSDOSystemValue() {
			String commandString = "";
			switch (this) {
			case PROPOTION_GAIN:
				commandString = GETSDOVAULE.PROPOTION_GAIN.getValue();
				break;
			case INTEGRAL_GAIN:
				commandString = GETSDOVAULE.INTEGRAL_GAIN.getValue();
				break;
			case GAIN_DIVIDE:
				commandString = GETSDOVAULE.GAIN_DIVIDE.getValue();
				break;
			case SCALE:
				commandString = GETSDOVAULE.SCALE.getValue();
				break;
			case FEEDBACK_DERIVATIVE_GAIN:
				commandString =  GETSDOVAULE.FEEDBACK_DERIVATIVE_GAIN.getValue();
				break;
			case VELOCITY_FF_GAIN:
				commandString =  GETSDOVAULE.VELOCITY_FF_GAIN.getValue();
				break;
			case ACCELERATION_FF_GAIN:
				commandString = GETSDOVAULE.ACCELERATION_FF_GAIN.getValue();
				break;
			}
			return commandString;
		}
	}

	public static enum GETSDOVAULE
	{
		PROPOTION_GAIN("ethercat upload -p driver -t int32 0x2400 1"),
		INTEGRAL_GAIN("ethercat upload -p driver -t int32 0x2400 2"),
		GAIN_DIVIDE("ethercat upload -p driver -t int32 0x2400 3"),
		SCALE("ethercat upload -p driver -t int16  0x2400 4"),
		FEEDBACK_DERIVATIVE_GAIN("ethercat upload -p driver -t int32 0x2400 5"),
		VELOCITY_FF_GAIN("ethercat upload -p driver -t int32 0x2400 6"),
		ACCELERATION_FF_GAIN("ethercat upload -p driver -t int32 0x2400 7");

		private String value;

		GETSDOVAULE(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}
	
	/*************************
	 * 
	 * SDO 파라미터 COMMAND
	 * 
	 ************************ */
	
	public static enum HALLVALUE
	{
		PROPOTION_GAIN("Proportional gain"),
		INTEGRAL_GAIN("Integral gain"),
		GAIN_DIVIDE("Gain divide"),
		SCALE("Scale"),
		FEEDBACK_DERIVATIVE_GAIN("Velocity Feedback Derivative Gain"),
		VELOCITY_FF_GAIN("Velocity FF Gain"),
		ACCELERATION_FF_GAIN("Acceleration FF Gain");

		private String value;

		HALLVALUE(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
		
		public String getSDOSystemValue() {
			String commandString = "";
			switch (this) {
			case PROPOTION_GAIN:
				commandString = GETSDOVAULE.PROPOTION_GAIN.getValue();
				break;
			case INTEGRAL_GAIN:
				commandString = GETSDOVAULE.INTEGRAL_GAIN.getValue();
				break;
			case GAIN_DIVIDE:
				commandString = GETSDOVAULE.GAIN_DIVIDE.getValue();
				break;
			case SCALE:
				commandString = GETSDOVAULE.SCALE.getValue();
				break;
			case FEEDBACK_DERIVATIVE_GAIN:
				commandString =  GETSDOVAULE.FEEDBACK_DERIVATIVE_GAIN.getValue();
				break;
			case VELOCITY_FF_GAIN:
				commandString =  GETSDOVAULE.VELOCITY_FF_GAIN.getValue();
				break;
			case ACCELERATION_FF_GAIN:
				commandString = GETSDOVAULE.ACCELERATION_FF_GAIN.getValue();
				break;
			}
			return commandString;
		}

	}


	public final static String AMP_PEAK_CURRENT =  "Amplifier peak current";
	public final static String SDO_ERROR_REGISTER = "Error register";
	public final static String SDO_DEVICE_NAME = "Device name";
	public final static String SDO_HARDWARE_VERSION =  "Hardware version";
	public final static String SDO_SOFTWARE_VERSION = "Software version";
	public final static String SDO_IDENTITY =  "Identity";
	public final static String SDO_ERROR_SETTINGS =  "Error Settings";
	public final static String SDO_RXPDO = "csp/csv RxPDo";
	public final static String SDO_RXPDG =  "csp RxPDg";
	public final static String SDO_RXPDN= "csv RxPDn";
	public final static String SDO_TXPD = "csp/csv TxPD ";
	public final static String SDO_CSP_TXPDN = "csp TxPDn";
	public final static String SDO_CSV_TXPDN = "csv TxPDn";
	public final static String SDO_MANAGER_TYPE = "Sync manager type";
	public final static String SDO_RXPDO_ASSIGN =  "RxPDO assign";
	public final static String SDO_TXPDO_ASSGIN =  "TxPDO assign";
	public final static String SDO_OUTPUT_PARAMETER =  "SM output parameter";
	public final static String SDO_INPUT_PARAMETER = "SM input parameter";
	public final static String SDO_STATUS_REGISTER= "Manufacturer Status Register";
	public final static String SDO_AMP_DATA  = "Amplifier Data";
	public final static String SDO_MOTOR_DATA  = "Motor Data";
	public final static String SDO_MOTOR_ENCODER =  "Motor Encoder Wrap Value";
	public final static String SDO_MOTOR_AUX_ENCODER = "Motor Aux Encoder Wrap Value";
	public final static String SDO_PHASING_MODE = "Motor Phasing Mode";
	public final static String SDO_MOTOR_PHASING_POLARITY =  "Motor Phasing Polarity";
	public final static String SDO_MOTOR_INJECTION_CURRENT =  "Motor current Injection Current";
	public final static String SDO_MOTOR_CURRENT_INJECTION_TIME = "Motor Current Injection Time";
	public final static String SDO_DRIVE_DATA_DUMP = "Drive Data Dumps";
}
