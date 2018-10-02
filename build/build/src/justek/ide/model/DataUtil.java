package justek.ide.model;

public class DataUtil {
	
	private static DataUtil dataUtil;
	// ������
	private DataUtil () {
		System.out.println( "call DataSet constructor." );
	}
	// ��ȸ method
	public static DataUtil getInstance () {
		if(dataUtil == null) {
			dataUtil = new DataUtil();
		}
		return dataUtil;
	}
}
