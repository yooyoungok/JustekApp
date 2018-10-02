/**
 * ------------------------------------------------------------------------------
 * @Project       : JustekApp
 * @Source        : FTPManager.java
 * @Description   : 
 * @Author        : YOUNGOK YOO /  Ȯ���� : 
 * @Version       : v1.0
 * Copyright(c) 2018 JUSTEK All rights reserved
 *------------------------------------------------------------------------------
 *                  ��         ��         ��         ��                       
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                      DESCRIPTION                        
 * ----------  ------  --------------------------------------------------------- 
 * 2018. 7. 5.   ������    �űԻ���                                     
 *------------------------------------------------------------------------------
 */
package justek.ide.model.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.ArrayList;
//import org.apache.commons.net.ftp.FTP;
//import org.apache.commons.net.ftp.FTPClient;
//import org.apache.commons.net.ftp.FTPReply;
import com.oroinc.net.ftp.*;

import justek.ide.model.CommandConst;

public class FTPManager {

	static final String Tag = "FTPManager";

	private static final String sUpDir = "/test/";
	private static final String sDownDir = "C:/";

	FTPClient ftpClient ; 

	private static FTPManager instance;

	public static FTPManager getInstance () {
		if(instance==null) {
			instance = new FTPManager();
		}
		return instance;
	}

	//	/**
	//	 * 
	//	 * @param ip         ftp����ip
	//	 * @param port       ftp����port
	//	 * @param id         �����id
	//	 * @param password   �����password 
	//	 * @param folder     ftp����������������
	//	 * @param files      ���ε�list
	//	 * @return
	//	 */
	//	public boolean sendFtpServer(String ip, int port, String id, String password,
	//
	//			String folder,String localPath, ArrayList<String> files) {
	//		boolean isSuccess = false;
	//		FTPClient ftp = null;
	//		int reply;
	//		try {
	//			ftp = new FTPClient();
	//
	//			ftp.setControlEncoding("UTF-8");
	//			ftp.connect(ip, port);
	//			System.out.println("Connected to " + ip + " on "+ftp.getRemotePort());
	//
	//			// After connection attempt, you should check the reply code to verify
	//			// success.
	//			reply = ftp.getReplyCode();
	//			if (!FTPReply.isPositiveCompletion(reply)) {
	//				ftp.disconnect();
	//				System.err.println("FTP server refused connection.");
	//				System.exit(1);
	//			}
	//
	//			if(!ftp.login(id, password)) {
	//				ftp.logout();
	//				throw new Exception("ftp ������ �α������� ���߽��ϴ�.");
	//			}
	//
	//			ftp.setFileType(FTP.BINARY_FILE_TYPE);
	//			ftp.enterLocalPassiveMode();
	//
	//			System.out.println(ftp.printWorkingDirectory());
	//			try{
	//				ftp.makeDirectory(folder);
	//			}catch(Exception e){
	//				e.printStackTrace();
	//			}
	//			ftp.changeWorkingDirectory(folder);
	//			System.out.println(ftp.printWorkingDirectory());
	//
	//
	//			for(int i = 0; i < files.size(); i++) {
	//				String sourceFile = localPath + files.get(i);        
	//				File uploadFile = new File(sourceFile);
	//				FileInputStream fis = null;
	//				try {
	//					fis = new FileInputStream(uploadFile);
	//					System.out.println(sourceFile + " : ���۽��� = >");
	//					isSuccess = ftp.storeFile(files.get(i), fis);
	//					System.out.println(sourceFile + " : ���۰�� = >" + isSuccess);
	//				} catch(IOException e) {
	//					e.printStackTrace();
	//					isSuccess = false;
	//				} finally {
	//					if (fis != null) {
	//						try {fis.close(); } catch(IOException e) {}
	//					}
	//				}//end try
	//			}//end for
	//
	//			ftp.logout();
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//		} finally {
	//			if (ftp != null && ftp.isConnected()) {
	//				try { ftp.disconnect(); } catch (IOException e) {}
	//			}
	//		}
	//		return isSuccess;
	//	}

	public void connect() {
		if(this.ftpClient==null) {
			ftpClient = new FTPClient();
		}

		try {
			
			ftpClient.connect(CommandConst.address, 21);
			int reply = ftpClient.getReplyCode();
			if(!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
			}
			
			boolean isLogin = ftpClient.login("jscs1", "dure258");
			//�α��� �����̸�...
			if(isLogin) {
				
			}
			
		} catch (SocketException e) {
			ErrorLogManager.getInstance().addErrorLog(Tag, "connect", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			
			if(ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					ErrorLogManager.getInstance().addErrorLog(Tag, "connect :: IOException", e);
				}
			}
			ErrorLogManager.getInstance().addErrorLog(Tag, "connect", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	// ������ ���� �޴´�
	private boolean get(String source, String target, String name) {

		boolean flag = false;

		//	  OutputStream output = null;
		//	  try {
		//	   // �޴� ���� ���� �� ��ġ�� �� �̸����� ���� �����ȴ�
		//	    File local = new File(sDownDir, name);
		//	    output = new FileOutputStream(local);
		//	  }catch (FileNotFoundException fnfe) {
		//	   
		//	    return flag;
		//	  }
		//	  
		//	  File file = new File(source);
		//	  try {
		//	    if (ftpClient.retrieveFile(source, output)) {
		//	     flag = true;
		//	    }
		//	  }catch (IOException ioe) {
		//
		//	  }
		return flag;
	}
}


