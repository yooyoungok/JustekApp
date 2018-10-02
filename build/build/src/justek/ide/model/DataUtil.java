package justek.ide.model;

public class DataUtil {
	
	private static DataUtil dataUtil;
	// 생성자
	private DataUtil () {
		System.out.println( "call DataSet constructor." );
	}
	// 조회 method
	public static DataUtil getInstance () {
		if(dataUtil == null) {
			dataUtil = new DataUtil();
		}
		return dataUtil;
	}
}
