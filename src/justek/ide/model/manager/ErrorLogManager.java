/**
 * ------------------------------------------------------------------------------
 * @Project       : JustekApp
 * @Source        : ErrorLogManager.java
 * @Description   : 
 * @Author        : YOUNGOK YOO /  확인자 : 
 * @Version       : v1.0
 * Copyright(c) 2018 JUSTEK All rights reserved
 *------------------------------------------------------------------------------
 *                  변         경         사         항                       
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                      DESCRIPTION                        
 * ----------  ------  --------------------------------------------------------- 
 * 2018. 7. 4.   유영옥    신규생성                                     
 *------------------------------------------------------------------------------
 */
package justek.ide.model.manager;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class ErrorLogManager {
	
	static final String Tag = "ErrorLogManager";

	private static ErrorLogManager instance;
	
	/**
	 * @author : YOO YOUNGOK 
	 * @method  getInstance
	 * @return  ErrorLogManager
	 * @exception 
	 * @see
	 * @since 2018. 7. 4.
	 */
	public static ErrorLogManager getInstance () {
		if(instance==null) {
			instance = new ErrorLogManager();
		}
		
		return instance;
	}

	

	/**
	 * @author : YOO YOUNGOK 
	 * @method  addErrorLog
	 * @param controller
	 * @param method
	 * @param error -> Error가 발생한 Exception class
	 * @return  void
	 * @exception 
	 * @see
	 * @since 2018. 7. 5.
	 */
	public void addErrorLog(String controller,String method,Exception error) {
        try{
            
        	GregorianCalendar today = new GregorianCalendar();
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        	String strTime = format.format(today.getTime());
        	
            // 파일 객체 생성
        	String fileName = System.getProperty("user.dir")+File.separator+"error";
        	
        	System.out.println(Tag+"::"+fileName);  
            File file = new File(fileName) ;
             
            if(!file.exists()) {
            	if(file.mkdirs()) {
            		System.out.println("Error 디렉토리 생성 성공!");
            	}
            	else {
            		return;
            	}
            }
            
            File file2 = new File(fileName+File.separator+strTime+".txt");
            if(!file2.exists()) {
            	if(file2.createNewFile()) {
            		System.out.println("File 생성 성공!");
            	}
            	else {
            		return;
            	}
            }
   
            
            // true 지정시 파일의 기존 내용에 이어서 작성
            FileWriter fw = new FileWriter(file2, true) ;
            format = new SimpleDateFormat("yyyy년 MM월 dd일 aa hh시 mm분 ss초");
            fw.write(format.format(today.getTime())+"\n");
            fw.flush();
            
            fw.write(controller);
            fw.flush();
            
            fw.write(" :: "+method+"\n");
            fw.flush();
            
            // 파일안에 문자열 쓰기
            fw.write(error.toString()+"\n");
            fw.flush();
            // 객체 닫기
            fw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
	}
	

}
