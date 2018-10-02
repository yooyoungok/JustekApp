package justek.ide.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

import justek.ide.model.CommandConst;

public class StringUtil {


	//binary -> hex,decimal
	public static String binaryToDec(String value) {
		String result;
		if(value.contains("0b")) {
			result =  value.replaceAll("0b", "");
		}
		else {
			return value;
		}
		Long v = Long.parseLong(result,2);
		return String.valueOf(v);
	}
	
	public static String byteToDec(String value) {
		String result=null;
		if(value.contains("0b")) {
			result =  value.replaceAll("0b", "");
		}
		else {
			result = value;
		}
		
		Long v = Long.parseLong(result,2);
		return String.valueOf(v);
	}

	public static String binaryToHex(String value) {

		String result = binaryToDec(value);

		result = getDecimalToHexValue(result);

		return result;
	}


	//getBinary
	public static String getToBiniary(String value) {
		String result = "";
		if(value.contains("0x")) {
			result = hexToBinary(value);
		}
		else {
			result = decToBinary(value);
		}

		return result;
	}

	//hex-> binary
	public static String hexToBinary(String value) {
		String result="";
		if(value.contains("0x")) {
			result =  value.replaceAll("0x", "").trim();
		}
		else {
			result = value.trim();
		}

		Long v = Long.parseLong(result, 16);
		String binaryString = Long.toBinaryString(v);
		String padded = String.format("%8s", binaryString.replace(' ', '0'));
		StringBuffer sb = new StringBuffer(binaryString);
		sb.insert(0, "0");
		sb.insert(1, "b");
		return sb.toString();
	}

	//decimal-> binary
	public static String decToBinary(String value) {
		String result="";
		if(value.contains("0x")) {
			return value;
		}
		else {
			result = value;
		}

		Long v = Long.parseLong(result);
		String binaryString = Long.toBinaryString(v);
		String padded = String.format("%8s", binaryString.replace(' ', '0'));
		StringBuffer sb = new StringBuffer(binaryString);
		sb.insert(0, "0");
		sb.insert(1, "b");
		return sb.toString();
	}

	//decimal->hex
	public static String getDecimalToHexValue(String value) {
		String result = "";

		if(value.contains("0x")) {
			return value;
		}

		long v = Long.parseLong(value);

		result = Long.toHexString(v).toUpperCase();
		StringBuffer sb = new StringBuffer(result);
		sb.insert(0, "0");
		sb.insert(1, "x");
		return sb.toString();
	}

	//hex->decimal
	public static String getHexToDecimalValue(String value) {
		String result = "";

		if(value.contains("0x")) {
			result =  value.replaceAll("0x", "");
		}
		else {
			return value;
		}

		Long v = Long.parseLong(result, 16);
		return String.valueOf(v);
	}

	public static String getNumnberFommat(String value) {

		if(value==null) {
			return "null";
		}

		if(value.contains("0x")) {
			return value;
		}

		try {
			String moneyString = new Double(value).toString(); 
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMaximumFractionDigits( CommandConst.MAXDIGITS ) ;
			double double_01 = Double.parseDouble(moneyString);
			return (nf.format(double_01));
		}
		catch(NumberFormatException e) {
			return value;
		}

		//소수점 이하 최대 자리를 지정한다.

		//        String format = "#,##0.0000"; 
		//        DecimalFormat df = new DecimalFormat(format); 
		//        DecimalFormatSymbols dfs = new DecimalFormatSymbols(); 
		//
		//        dfs.setGroupingSeparator(',');// 구분자를 ,로 
		//        df.setGroupingSize(3);//3자리 단위마다 구분자처리 한다. 
		//        df.setDecimalFormatSymbols(dfs); 

	}

	public static String getNumnberFommatWithMaxDigits(String dblMoneyString, int maxDigits) {

		if(dblMoneyString.contains("0x")) {
			return dblMoneyString;
		}

		try {
			String moneyString = new Double(dblMoneyString).toString(); 
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMaximumFractionDigits(maxDigits ) ;
			double double_01 = Double.parseDouble(moneyString);
			return (nf.format(double_01));
		}
		catch(NumberFormatException e) {
			return dblMoneyString;
		}
	}

	public static String getNumberFommat(double dataDouble) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(  CommandConst.MAXDIGITS  ) ;
		return (nf.format(dataDouble));
	}

	public static String getNumberFommatWithMaxDigits(double dataDouble, int maxDigits) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits( maxDigits ) ;
		return (nf.format(dataDouble));
	}

	//remove comma
	public static String removeCommna(String value) {

		if(value.isEmpty() || value == null) return null;	///값이 없으면 null을 리턴한다.

		String result = "";
		result = value.replaceAll(",", "");
		
		if(!isStringDouble(result)) return null;	//숫자가 아니면 null을 리턴한다.

		return result;
	}

	public static String addComma(String value) {

		if(value.isEmpty()) return "0";

//		if(isStringDouble(value)) return null;

		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(5) ;
		String result = "0";
		result = nf.format(Double.parseDouble(value));
		return result;
	}

	public static String removeDot(String readData) {
		String result = readData;
		if(readData!=null) {
			int dot = readData.indexOf('.');
			if(dot==-1) {
				result = readData;
			}else {
				result = readData.substring(0, dot);
			}
		}
		return result;
	}


	/**
	 * @author : YOO YOUNGOK 
	 * @method  isStringDouble
	 * @param value //숫자인지 확인할 String 값
	 * @return
	 * @return  boolean
	 * @exception 
	 * @see
	 * @since 2018. 6. 21.
	 */
	public static boolean isStringDouble(String value) {
		try {
			Double.parseDouble(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
