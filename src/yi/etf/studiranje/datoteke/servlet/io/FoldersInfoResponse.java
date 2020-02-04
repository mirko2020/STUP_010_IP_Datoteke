package yi.etf.studiranje.datoteke.servlet.io;

import java.io.File;
import java.io.Serializable;

import yi.etf.studiranje.datoteke.model.FileList;
import yi.etf.studiranje.datoteke.servlet.FileListOperations;

/**
 * Информације о датотеци или директоријуму - одговор. 
 * @author Computer
 * @version 1.0
 */
public class FoldersInfoResponse implements Serializable{
	private static final long serialVersionUID = 213274842731952551L;
	private FileListOperations operations; 	
	private boolean success; 
	private String message;
	private File file; 
	
	
	public boolean isSuccess() {
		return success;
	}
	public FoldersInfoResponse setSuccess(boolean success) {
		this.success = success;
		return this; 
	}
	public String getMessage() {
		return message;
	}
	public FoldersInfoResponse setMessage(String message) {
		this.message = message;
		return this; 
	}
	public FileListOperations getOperations() {
		return operations;
	}
	public FoldersInfoResponse setOperations(FileListOperations operations) {
		this.operations = operations;
		return this; 
	}
	public File getFile() {
		return file;
	}
	public FoldersInfoResponse setFile(File file) {
		this.file = file;
		return this; 
	} 
	
	private int countTotal; 
	private int countFiles; 
	private int countFolders;
	private int fileSize; 
	private boolean isFile; 
	private int level; 
	
	public int getCountTotal() {
		return countTotal;
	}
	public FoldersInfoResponse setCountTotal(int countTotal) {
		this.countTotal = countTotal;
		return this; 
	}
	public int getCountFiles() {
		return countFiles;
	}
	public FoldersInfoResponse setCountFiles(int countFiles) {
		this.countFiles = countFiles;
		return this; 
	}
	public int getCountFolders() {
		return countFolders;
	}
	public FoldersInfoResponse setCountFolders(int countFolders) {
		this.countFolders = countFolders;
		return this; 
	}
	public int getFileSize() {
		return fileSize;
	}
	public FoldersInfoResponse setFileSize(int fileSize) {
		this.fileSize = fileSize;
		return this; 
	}
	public boolean isFile() {
		return isFile;
	}
	public FoldersInfoResponse setFile(boolean isFile) {
		this.isFile = isFile;
		return this; 
	}
	public int getLevel() {
		return level;
	}
	public FoldersInfoResponse setLevel(int level) {
		this.level = level;
		return this; 
	}
	
	private FileList fileList;
	
	public FileList getFileList() {
		return fileList;
	}
	public FoldersInfoResponse setFileList(FileList fileList) {
		this.fileList = fileList;
		return this; 
	}
}
