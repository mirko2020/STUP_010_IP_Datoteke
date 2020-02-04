package yi.etf.studiranje.datoteke.servlet.io;

import java.io.File;
import java.io.Serializable;

import yi.etf.studiranje.datoteke.model.FileList;
import yi.etf.studiranje.datoteke.servlet.FileListOperations;

/**
 * Одговор на захтијев за експанзију фолдера. 
 * @author Computer
 * @version 1.0
 */
public class FolderExpandResponse implements Serializable{
	private static final long serialVersionUID = 8498761430513329027L;
	
	private FileListOperations operations; 	
	private boolean success; 
	private String message;
	private File file; 
	
	public boolean isSuccess() {
		return success;
	}
	public FolderExpandResponse setSuccess(boolean success) {
		this.success = success;
		return this; 
	}
	public String getMessage() {
		return message;
	}
	public FolderExpandResponse setMessage(String message) {
		this.message = message;
		return this; 
	}
	public FileListOperations getOperations() {
		return operations;
	}
	public FolderExpandResponse setOperations(FileListOperations operations) {
		this.operations = operations;
		return this; 
	}
	public File getFile() {
		return file;
	}
	public FolderExpandResponse setFile(File file) {
		this.file = file;
		return this; 
	} 
	
	private int countTotal; 
	private int countFiles; 
	private int countFolders;
	private int level; 

	public int getCountTotal() {
		return countTotal;
	}
	public FolderExpandResponse setCountTotal(int countTotal) {
		this.countTotal = countTotal;
		return this; 
	}
	public int getCountFiles() {
		return countFiles;
	}
	public FolderExpandResponse setCountFiles(int countFiles) {
		this.countFiles = countFiles;
		return this; 
	}
	public int getCountFolders() {
		return countFolders;
	}
	public FolderExpandResponse setCountFolders(int countFolders) {
		this.countFolders = countFolders;
		return this; 
	}
	public int getLevel() {
		return level;
	}
	public FolderExpandResponse setLevel(int level) {
		this.level = level;
		return this; 
	}
	
	private FileList fileList;

	public FileList getFileList() {
		return fileList;
	}
	public FolderExpandResponse setFileList(FileList fileList) {
		this.fileList = fileList;
		return this; 
	} 
}
