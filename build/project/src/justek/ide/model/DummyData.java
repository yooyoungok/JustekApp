package justek.ide.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DummyData {

	ObservableList<SDOInfo> ampData = FXCollections.observableArrayList();
	ObservableList<SDOInfo>	motorData = FXCollections.observableArrayList();
	ObservableList<SDOInfo> currentData = FXCollections.observableArrayList();
	ObservableList<SDOInfo> posData = FXCollections.observableArrayList();
	ObservableList<SDOInfo> tableData = FXCollections.observableArrayList();
	
	private static DummyData instance;

	public static DummyData getInstance () {
		if(instance==null) {
			instance = new DummyData();
		}
		return instance;
	}
	
	
	public ObservableList<SDOInfo> dummySDOInfo(String driverNumber) {
		File file = new File("amp_"+driverNumber+".txt");
		BufferedReader br;
		FileReader fr = null; 
		String line =null;
		
		if(!file.exists())
			return null;
		
		try{
			fr = new FileReader(file); 
			br = new BufferedReader(fr);

			int i=1;
			while((line = br.readLine()) != null){

				SDOInfo info = this.inputAmpInfo(line,i);
				if(info!=null) {
					System.out.println(info.toString());
					ampData.add(info);
				}
				i++;
			}
			
		}catch(IOException e) {
			this.ampData = null;
			e.printStackTrace(); 
		}
		
		return ampData;
	}
	
	public ObservableList<SDOInfo> dummyCurrentSDOInfo(String driverNumber) {
		File file = new File("cur_"+driverNumber+".txt");
		BufferedReader br;
		FileReader fr = null; 
		String line =null;
		
		if(!file.exists())
			return null;
		
		try{
			fr = new FileReader(file); 
			br = new BufferedReader(fr);

			int i=1;
			while((line = br.readLine()) != null){
				SDOInfo info = this.inputAmpInfo(line,i);
				if(info!=null) {
					System.out.println(info.toString());
					currentData.add(info);
				}
				i++;
			}
			
		}catch(IOException e) {
			this.currentData = null;
			e.printStackTrace(); 
		}
		
		return currentData;
	}
	
	public ObservableList<SDOInfo> getTableSDOInfo(String motorName) {
		
		this.tableData.clear();
		
		File file = new File("./home/motor/"+motorName+".txt");
		
		BufferedReader br;
		FileReader fr = null; 
		String line =null;
		
		if(!file.exists())
			return null;
		
		try{
			fr = new FileReader(file); 
			br = new BufferedReader(fr);

			int i =1;
			while((line = br.readLine()) != null){
				SDOInfo info = this.inputAmpInfo(line,i);
				if(info!=null) {
					System.out.println(info.toString());
					this.tableData.add(info);
				}
				i++;
			}
			
		}catch(IOException e) {
			this.tableData = null;
			e.printStackTrace(); 
		}
		
		return tableData;
	}
	
	public ObservableList<SDOInfo> dummyMotorSDOInfo(String driverNumber) {
		File file = new File("motor_"+driverNumber+".txt");
		BufferedReader br;
		FileReader fr = null; 
		String line =null;
		
		if(!file.exists())
			return null;
		
		try{
			fr = new FileReader(file); 
			br = new BufferedReader(fr);

			int i=1;
			while((line = br.readLine()) != null){

				SDOInfo info = this.inputAmpInfo(line,i);
				if(info!=null) {
					System.out.println(info.toString());
					motorData.add(info);
				}
				i++;
			}
			
		}catch(IOException e) {
			this.motorData = null;
			e.printStackTrace(); 
		}
		
		return motorData;
	}
	
	public ObservableList<SDOInfo> dummyPosSDOInfo(String driverNumber) {
		File file = new File("pos_"+driverNumber+".txt");
		BufferedReader br;
		FileReader fr = null; 
		String line =null;
		
		if(!file.exists())
			return null;
		
		try{
			fr = new FileReader(file); 
			br = new BufferedReader(fr);

			int i=1;
			while((line = br.readLine()) != null){

				SDOInfo info = this.inputAmpInfo(line,i);
				if(info!=null) {
					System.out.println(info.toString());
					motorData.add(info);
				}
				i++;
			}
			
		}catch(IOException e) {
			this.motorData = null;
			e.printStackTrace(); 
		}
		
		return motorData;
	}
	
	public SDOInfo inputAmpInfo(String readData, int subIndex) {
		SDOInfo result = new SDOInfo();
		try {
		String[] parsingList = null;
		if(readData!=null) {
			parsingList = readData.split(",");
		}
		
		result.setIndex(parsingList[0]);
		result.setName(parsingList[1]);
		result.setValue(parsingList[2]);
		result.setPermission(parsingList[3]);
		result.setDefaultValue(parsingList[4]);
		result.setDescription(parsingList[5]);
		
		System.out.println(readData);
		
		}catch(Exception e) {
			result = null;
		}
		
		return result;
	}
	
	public DriverInfo defaultDriverInfo() {
		DriverInfo info = new DriverInfo();
		info.setName(new SimpleStringProperty(""));
		info.setSingnal(new SimpleStringProperty(""));
		info.setValue(new SimpleStringProperty(""));
		return info;
	}
	
	public ObservableList<DriverInfo> defaultDriverInfoList() {
		ObservableList<DriverInfo> list =  FXCollections.observableArrayList();
		for(int i=0;i<100;i++) {
			if(i==0) {
				DriverInfo info = this.defaultDriverInfo();
				info.setDriverNo(new SimpleStringProperty("1") );
				info.setName(new SimpleStringProperty(CommandConst.driverList.get(0)));
				list.add(info);
			}
			else {
				list.add(this.defaultDriverInfo());
			}
		}
		return list;
	}
}
