package yi.etf.studiranje.datoteke.servlet.io;

import java.io.File;
import java.io.Serializable;

import yi.etf.studiranje.datoteke.model.FileList;
import yi.etf.studiranje.datoteke.servlet.FileListOperations;

/**
 * Одговор на захтијев за сажимање фолдера. 
 * @author Computer
 * @version 1.0
 */
public class FolderColapseResponse implements Serializable{
	private static final long serialVersionUID = 2969761168359174241L;
	
	private FileListOperations operations; 	
	private boolean success; 
	private String message;
	private File file; 
	
	public boolean isSuccess() {
		return success;
	}
	public FolderColapseResponse setSuccess(boolean success) {
		this.success = success;
		return this; 
	}
	public String getMessage() {
		return message;
	}
	public FolderColapseResponse setMessage(String message) {
		this.message = message;
		return this; 
	}
	public FileListOperations getOperations() {
		return operations;
	}
	public FolderColapseResponse setOperations(FileListOperations operations) {
		this.operations = operations;
		return this; 
	}
	public File getFile() {
		return file;
	}
	public FolderColapseResponse setFile(File file) {
		this.file = file;
		return this; 
	} 
	
	private int level;

	public int getLevel() {
		return level;
	}
	public FolderColapseResponse setLevel(int level) {
		this.level = level;
		return this; 
	} 
	
	private FileList fileList; 
	public FileList getFileList() {
		return fileList; 
	}
	public FolderColapseResponse setFileList(FileList fileList) {
		this.fileList = fileList; 
		return this; 
	}
}
