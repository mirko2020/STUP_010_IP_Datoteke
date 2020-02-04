package yi.etf.studiranje.datoteke.servlet.io;

import java.io.File;
import java.io.Serializable;

import yi.etf.studiranje.datoteke.model.FileList;
import yi.etf.studiranje.datoteke.servlet.FileListOperations;

/**
 * Одговор при захтијеву за информације. 
 * @author Computer
 * @version 1.0
 */
public class FilesInfoResponse implements Serializable {
	private static final long serialVersionUID = 7199348493557586681L;
	private FileListOperations operation;  
	private boolean success; 
	private String message;
	private File file;
	private FileList fileList; 
	
	public FileListOperations getOperation() {
		return operation;
	}
	public FilesInfoResponse setOperation(FileListOperations operation) {
		this.operation = operation;
		return this; 
	}
	public boolean isSuccess() {
		return success;
	}
	public FilesInfoResponse setSuccess(boolean success) {
		this.success = success;
		return this; 
	}
	public String getMessage() {
		return message;
	}
	public FilesInfoResponse setMessage(String message) {
		this.message = message;
		return this;
	}
	public File getFile() {
		return file;
	}
	public FilesInfoResponse setFile(File file) {
		this.file = file;
		return this;
	} 
	
	private int level;

	public int getLevel() {
		return level;
	}
	public FilesInfoResponse setLevel(int level) {
		this.level = level;
		return this; 
	} 
	
	public FileList getFileList() {
		return fileList;
	}
	public void setFileList(FileList fileList) {
		this.fileList = fileList;
	}
}
