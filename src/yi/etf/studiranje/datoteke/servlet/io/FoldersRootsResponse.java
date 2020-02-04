package yi.etf.studiranje.datoteke.servlet.io;

import java.io.File;
import java.io.Serializable;

import yi.etf.studiranje.datoteke.model.FileList;
import yi.etf.studiranje.datoteke.servlet.FileListOperations;

/**
 * Одговор на захтијев за листу коријена. 
 * @author Computer
 * @version 1.0
 */
public class FoldersRootsResponse implements Serializable{
	private static final long serialVersionUID = -1546970222854070621L;
	private FileListOperations operations; 	
	private boolean success; 
	private String message;
	private File file; 
	
	public boolean isSuccess() {
		return success;
	}
	public FoldersRootsResponse setSuccess(boolean success) {
		this.success = success;
		return this; 
	}
	public String getMessage() {
		return message;
	}
	public FoldersRootsResponse setMessage(String message) {
		this.message = message;
		return this; 
	}
	public FileListOperations getOperations() {
		return operations;
	}
	public FoldersRootsResponse setOperations(FileListOperations operations) {
		this.operations = operations;
		return this; 
	}
	public File getFile() {
		return file;
	}
	public FoldersRootsResponse setFile(File file) {
		this.file = file;
		return this; 
	} 
	
	private FileList rootsInfo;

	public FileList getRootsInfo() {
		return rootsInfo;
	}
	public FoldersRootsResponse setRootsInfo(FileList rootsInfo) {
		this.rootsInfo = rootsInfo;
		return this; 
	} 
}
