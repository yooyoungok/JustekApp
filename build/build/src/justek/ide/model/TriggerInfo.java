package justek.ide.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TriggerInfo {

	public StringProperty  source;
	public StringProperty  level;
	public StringProperty  mode;
	public StringProperty  slope;
	public StringProperty  delay;
	public StringProperty  resolution;
	public StringProperty  recordTime;
	
	public void setSource(String source) {
		this.source = new SimpleStringProperty(source);
	}
	
	public String  getSourceWithTitle() {
		String value = "source="+this.source.get();
		return value;
	}
	
	public String  getSource() {
		return this.source.get();
	}
	
	public void setLevel(String level) {
		this.level = new SimpleStringProperty(level);
	}
	
	public String  getLevelWithTitle() {
		String value = "level="+this.level.get();
		return value;
	}
	
	public String  getLevel() {
		String value = this.level.get();
		return value;
	}
	
	public void setMode(String mode) {
		this.mode = new SimpleStringProperty(mode);
	}
	
	public String  getModeWithTitle() {
		String value = "mode="+this.mode.get();
		return value;
	}
	
	public String  getMode() {
		String value = this.mode.get();
		return value;
	}
	
	public void setSlope(String slope) {
		this.slope = new SimpleStringProperty(slope);
	}
	
	public String  getSlopeWithTitle() {
		String value = "slope="+this.slope.get();
		return value;
	}
	
	public String  getSlope() {
		String value = this.slope.get();
		return value;
	}
	
	public void setDelay(String delay) {
		this.delay = new SimpleStringProperty(delay);
	}
	
	public String  getDelayWithTitle() {
		String value = "delay="+this.delay.get();
		return value;
	}
	
	public String  getDelay() {
		String value = this.delay.get();
		return value;
	}
	
	public void setResolution(String resolution) {
		this.resolution = new SimpleStringProperty(resolution);
	}
	public String  getResolution() {
		String value = this.resolution.get();
		return value;
	}
	public String  getResolutionWithTitle() {
		String value = "Resolution="+this.resolution.get();
		return value;
	}
	
	public void setRecordTime(String time) {
		this.recordTime = new SimpleStringProperty(time);
	}
	
	public String  getRecordTime() {
		String value = this.recordTime.get();
		return value;
	}
	public String  getRecordTimeWithTitle() {
		String value = "RecordTime="+this.recordTime.get();
		return value;
	}

	@Override
	public String toString() {
		return "TriggerInfo [source=" + source + ", level=" + level + ", mode=" + mode + ", slope=" + slope + ", delay="
				+ delay + ", resolution=" + resolution + ", recordTime=" + recordTime + "]";
	}
	
	
}
