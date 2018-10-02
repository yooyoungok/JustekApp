package justek.ide.model;


public class RealTimeInfo {
	
	public String DriverNo;
	public String StausWords;
	public String TargetPosition;
	public String ActualPosition;
	public String ActualVelocity;
	public String PosionError;
	
	public RealTimeInfo() { 	}

	@Override
	public String toString() {
		return "RealTimeInfo [DriverNo=" + DriverNo + ", StausWords=" + StausWords + ", TargetPosition="
				+ TargetPosition + ", ActualPosition=" + ActualPosition + ", ActualVelocity=" + ActualVelocity
				+ ", PosionError=" + PosionError + "]";
	}
	
}
