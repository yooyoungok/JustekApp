/**
 * ------------------------------------------------------------------------------
 * @Project       : JustekApp
 * @Source        : ErrorLogManager.java
 * @Description   : 
 * @Author        : YOUNGOK YOO /  Ȯ���� : 
 * @Version       : v1.0
 * Copyright(c) 2018 JUSTEK All rights reserved
 *------------------------------------------------------------------------------
 *                  ��         ��         ��         ��                       
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                      DESCRIPTION                        
 * ----------  ------  --------------------------------------------------------- 
 * 2018. 7. 4.   ������    �űԻ���                                     
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
	 * @param error -> Error�� �߻��� Exception class
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
        	
            // ���� ��ü ����
        	String fileName = System.getProperty("user.dir")+File.separator+"error";
        	
        	System.out.println(Tag+"::"+fileName);  
            File file = new File(fileName) ;
             
            if(!file.exists()) {
            	if(file.mkdirs()) {
            		System.out.println("Error ���丮 ���� ����!");
            	}
            	else {
            		return;
            	}
            }
            
            File file2 = new File(fileName+File.separator+strTime+".txt");
            if(!file2.exists()) {
            	if(file2.createNewFile()) {
            		System.out.println("File ���� ����!");
            	}
            	else {
            		return;
            	}
            }
   
            
            // true ������ ������ ���� ���뿡 �̾ �ۼ�
            FileWriter fw = new FileWriter(file2, true) ;
            format = new SimpleDateFormat("yyyy�� MM�� dd�� aa hh�� mm�� ss��");
            fw.write(format.format(today.getTime())+"\n");
            fw.flush();
            
            fw.write(controller);
            fw.flush();
            
            fw.write(" :: "+method+"\n");
            fw.flush();
            
            // ���Ͼȿ� ���ڿ� ����
            fw.write(error.toString()+"\n");
            fw.flush();
            // ��ü �ݱ�
            fw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
	}
	

}
