package justek.ide.model;

import javafx.event.EventHandler;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TreeItem;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class FileTreeItem extends TreeItem<String> {
	  
	Image icon = new Image (
			getClass().getResourceAsStream("/img/close_folder.png"));
	Image open_icon = new Image (
			getClass().getResourceAsStream("/img/open_folder.png"));
	Image file_icon = new Image (
			getClass().getResourceAsStream("/img/file.png"));
	
	Image plc_icon = new Image (
			getClass().getResourceAsStream("/img/plc_file.png"));
	  //this stores the full path to the file or directory
	  private String fullPath;
	  public String getFullPath(){return(this.fullPath);}
	  
	  private boolean isDirectory;
	  public boolean isDirectory(){return(this.isDirectory);}

	  public FileTreeItem(String name) {
		  super(name);
	  }
	  
	  public FileTreeItem(Path file){
		  super(file.toString());
		  this.fullPath=file.toString();
		  //test if this is a directory and set the icon
		  if(Files.isDirectory(file)){
			  this.isDirectory=true;
			  this.setGraphic(new ImageView(icon));
		  }else{
			  this.isDirectory=false;
			  if(file.toString().endsWith(".plc")) {
				  this.setGraphic(new ImageView(plc_icon));
			  }
			  else {
				  this.setGraphic(new ImageView(file_icon));
			  }
			 
			  //	      this.setGraphic(new ImageView(fileImage));
			  //if you want different icons for different file types this is where you'd do it
		  }

		  //set the value
		  if(!fullPath.endsWith(File.separator)){
			  //set the value (which is what is displayed in the tree)
			  String value=file.toString();
			  int indexOf=value.lastIndexOf(File.separator);
			  if(indexOf>0){
				  this.setValue(value.substring(indexOf+1));
			  }else{
				  this.setValue(value);
			  }
		  }

		  // Set tree modification related event handlers (branchExpandedEvent)
		  this.addEventHandler(TreeItem.branchExpandedEvent(),new EventHandler<TreeItem.TreeModificationEvent<String>>()
		  {
			  @Override
			  public void handle(TreeItem.TreeModificationEvent<String> event)
			  {
				  FileTreeItem source=(FileTreeItem)event.getSource();
				  if(source.isDirectory()&&source.isExpanded()){
					  
					  	          ImageView iv=(ImageView)source.getGraphic();
					  	          iv.setImage(open_icon);
				  }
				  
				  try{
					  if(source.getChildren().isEmpty()){
						  Path path=Paths.get(source.getFullPath());
						  BasicFileAttributes attribs=Files.readAttributes(path,BasicFileAttributes.class);
						  if(attribs.isDirectory()){
							  DirectoryStream<Path> dir=Files.newDirectoryStream(file);
							  for(Path file:dir){
								  FileTreeItem treeNode=new FileTreeItem(file);
								  source.getChildren().add(treeNode);
							  }
						  }
					  }else{
						  //if you want to implement rescanning a directory for changes this would be the place to do it
					  }
				  }catch(IOException x){
					  x.printStackTrace();
				  }
			  }
		  });


		  // Set tree modification related event handlers (branchCollapsedEvent)
		  this.addEventHandler(TreeItem.branchCollapsedEvent(),new EventHandler<TreeItem.TreeModificationEvent<String>>()
		  {
			
			  @Override
			  public void handle(TreeItem.TreeModificationEvent<String> event)
			  {
				  FileTreeItem source=(FileTreeItem)event.getSource();
			        if(source.isDirectory()&&!source.isExpanded()){
			          ImageView iv=(ImageView)source.getGraphic();
			          iv.setImage(icon);
			        }
			  }

		  });
		          
//		   this.addEventHandler(TreeItem.branchExpandedEvent(),new EventHandler<TreeItem.TreeModificationEvent>(){
//			      @Override
//			      public void handle(TreeModificationEvent e){
//			    	  FileTreeItem source=(FileTreeItem)e.getSource();
//			        if(source.isDirectory()&&source.isExpanded()){
////			          ImageView iv=(ImageView)source.getGraphic();
////			          iv.setImage(folderExpandImage);
//			        }
//			        try{
//			          if(source.getChildren().isEmpty()){
//			            Path path=Paths.get(source.getFullPath());
//			            BasicFileAttributes attribs=Files.readAttributes(path,BasicFileAttributes.class);
//			            if(attribs.isDirectory()){
//			              DirectoryStream<Path> dir=Files.newDirectoryStream(file);
//			              for(Path file:dir){
//			                FileTreeItem treeNode=new FileTreeItem(file);
//			                source.getChildren().add(treeNode);
//			              }
//			            }
//			          }else{
//			            //if you want to implement rescanning a directory for changes this would be the place to do it
//			          }
//			        }catch(IOException x){
//			          x.printStackTrace();
//			        }
//			      }
//			    });
//		   
//		    this.addEventHandler(TreeItem.branchCollapsedEvent(),new EventHandler(TreeItem.TreeModificationEven){
//		        @Override
//		        public void handle(Event e){
//		          FileTreeItem source=(FileTreeItem)e.getSource();
//		          if(source.isDirectory()&&!source.isExpanded()){
////		            ImageView iv=(ImageView)source.getGraphic();
////		            iv.setImage(folderCollapseImage);
//		          }
//		        }
//		      });
	  }	  
}