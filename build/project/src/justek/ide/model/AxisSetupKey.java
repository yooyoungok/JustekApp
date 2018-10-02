package justek.ide.model;

public class AxisSetupKey {

    public final static String DriverNumber="DriverNumber";
    
	//AxisSetup 0
	public final static String AxisControConfiguration="AxisControlConfiguration";
	public final static String AxisIdentity="AxisIdentity";
	public final static String ElectroMechanicalConfiguration="ElectroMechanicalConfiguration";
	public final static String Numerator="Numerator";
	public final static String Denominator="Denominator";
	public final static String Transmission="Transmission";
	public final static String SingleFeedback="SingleFeedback";
	public final static String LoopFeedbackConfiguration="LoopFeedbackConfiguration";
	public final static String ModeOperation="ModeOperation";
	
	//AxisSetup 1
	public final static String MotorType="MotorType";
	public final static String PeakCurrent="PeakCurrent";
	public final static String ContinuousStallCurrent="ContinuousStallCurrent";
	public final static String MaximumMotorSpeed="MaximumMotorSpeed";
	public final static String PolePairsperRevolution="PolePairsperRevolution";
	
	//AxisSetup 3
	public final static String PositionDisplayUnits="PositionDisplayUnits";
	public final static String VelocityDisplayUnits="VelocityDisplayUnits";
	public final static String PosionUnitFactor="PosionUnitFactor";
	public final static String VelocityDisplayFactor="VelocityDisplayFactor";
	public final static String UnitCalculation="OnTheMotor";
 
	//AxisSetup 4
	public final static String ContinuousDriver="ContinuousDriver";
	public final static String ContinuousMotor="ContinuousMotor";	
	public final static String MaxVoltage="MaxVoltage";
	public final static String MaxCurrent="MaxCurrent";
	public final static String PWMLimit="PWMLimit";
	public final static String PeakMotor="PeakMotor";
	public final static String PeakDriver="PeakDriver";
	public final static String PeakDuration="PeakDuration";
	public final static String IntegralLimit="IntegralLimit";
    
	//AxisSetup 7(SettingWindow)
	public final static String VelocityWindow="VelocityWindow";
	public final static String PositionTimeWindow="PositionTimeWindow";
	public final static String VeloticyTimeWindow="VeloticyTimeWindow";
	public final static String PositionWindow="PositionWindow";

	//AxisSetup 6(Protections)
	public final static String VelocityLimit="VelocityLimit";
	public final static String OverVoltage="OverVoltage";
	public final static String YawPositionError="YawPositionError";
	public final static String PositionError="PositionError";
	public final static String MotorStuck="MotorStuck";
	public final static String VelocityError="VelocityError";
	public final static String CurrentLimit="CurrentLimit";
	public final static String MaxTracking="MaxTracking";
	public final static String UnderVoltage="UnderVoltage";
   
	
	//AxisSetup 5(Motor Limits and Modulo)
	public final static String LowModulo="LowModulo";
	public final static String StopDeceleration ="StopDeceleration";
	public final static String LowPosition="LowPosition";
	public final static String MaxVelocity="MaxVelocity";
	public final static String HighPosition="HighPosition";
	public final static String HighModulo="HighModulo";
	public final static String AdvanceCheckBox02="AdvanceCheckBox02";
	public final static String AdvanceCheckBox01="AdvanceCheckBox01";
	public final static String ModuloOptions="ModuloOptions";
	
	//AxisSetup 12(Commutation)
	public final static String Velocity="Velocity";
	public final static String Deplace="Deplace";
	public final static String CurrentLevel="CurrentLevel";
	public final static String VelolcityFeedBack="VelolcityFeedBack";
	public final static String PosicionFeedBack="PosicionFeedBack";
	public final static String CommutFeedBack="CommutFeedBack";
	public final static String ActiveCurrent="ActiveCurrent";
	public final static String VerticalValue="VerticalValue";
	public final static String AutoMethodValue="AutoMethodValue";
}
