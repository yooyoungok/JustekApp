package justek.ide.utils;

import java.util.Calendar;

import com.supinan.util.timer.Timer;
import com.supinan.util.timer.TimerStopType;

public class RepeatTimer extends Timer {

	long time=0;
	public RepeatTimer(long repeatPeriod) {
		super(repeatPeriod);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		System.out.println("TimerCall ȣ��! " + cal.get(Calendar.HOUR_OF_DAY) + "�� " +
				cal.get(Calendar.MINUTE) + "�� " + cal.get(Calendar.SECOND) + "��");
		time++;
	}

	/** (non-Javadoc)
	 * @see com.supinan.util.timer.Timer#stopTimer(com.supinan.util.timer.TimerStopType)
	 */
	@Override
	public void stopTimer(TimerStopType arg0) {
		// TODO Auto-generated method stub

	}

}
