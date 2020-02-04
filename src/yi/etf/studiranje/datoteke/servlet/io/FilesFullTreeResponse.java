package yi.etf.studiranje.datoteke.servlet.io;

import java.io.File;
import java.io.Serializable;

import yi.etf.studiranje.datoteke.model.FileList;
import yi.etf.studiranje.datoteke.servlet.FileListOperations;

/**
 * Преузимање цијелокупног отвореног стабла за директоријуме. 
 * @author Computer
 * @version 1.0
 */
public class FilesFullTreeResponse implements Serializable{
	private static final long serialVersionUID = 7602904012957206048L;
	private FileListOperations operation;  
	private boolean success; 
	private String message;
	private File file; 
	private FileList content; 
	
	public FileListOperations getOperation() {
		return operation;
	}
	public FilesFullTreeResponse setOperation(FileListOperations operation) {
		this.operation = operation;
		return this; 
	}
	public boolean isSuccess() {
		return success;
	}
	public FilesFullTreeResponse setSuccess(boolean success) {
		this.success = success;
		return this; 
	}
	public String getMessage() {
		return message;
	}
	public FilesFullTreeResponse setMessage(String message) {
		this.message = message;
		return this; 
	}
	public File getFile() {
		return file;
	}
	public FilesFullTreeResponse setFile(File file) {
		this.file = file;
		return this; 
	}
	public FileList getContent() {
		return content;
	}
	public FilesFullTreeResponse setContent(FileList content) {
		this.content = content;
		return this; 
	}
}
