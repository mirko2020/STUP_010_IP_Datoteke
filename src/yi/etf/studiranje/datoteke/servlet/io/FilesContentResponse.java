package yi.etf.studiranje.datoteke.servlet.io;

import java.io.File;
import java.io.Serializable;

import yi.etf.studiranje.datoteke.control.FileContentEngine;
import yi.etf.studiranje.datoteke.servlet.FileListOperations;

/**
 * Одговр на захтијев са садржајем датотеке. 
 * @author Computer
 * @version 1.0
 */
public class FilesContentResponse implements Serializable{
	private static final long serialVersionUID = -815761238415584224L;
	private byte[] content; 
	private File file; 
	private FileListOperations operation; 
	private FileContentEngine contentEngine; 
	private int pageNo; 
	private int pageSize; 
	private boolean success; 
	private String message; 
	
	public byte[] getContent() {
		return content; 
	}
	
	public FilesContentResponse setContent(byte[] content) {
		this.content = content; 
		return this; 
	}
	
	public File getFile() {
		return file; 
	}
	
	public FilesContentResponse setFile(File file) {
		this.file = file; 
		return this; 
	}
	
	public FileListOperations getOperation() {
		return operation; 
	}
	
	public FilesContentResponse setOperation(FileListOperations operation) {
		this.operation = operation; 
		return this; 
	}
	
	public int getPageNo() {
		return pageNo; 
	}
	
	public FilesContentResponse setPageNo(int pageNo) {
		this.pageNo = pageNo; 
		return this; 
	}
	
	public int getPageSize() {
		return pageSize; 
	}
	
	public FilesContentResponse setPageSize(int pageSize) {
		this.pageSize = pageSize; 
		return this; 
	}
	
	public boolean isSuccess() {
		return success; 
	}
	
	public FilesContentResponse setSuccess(boolean success) {
		this.success = success; 
		return this; 
	}
	
	public String getMessage() {
		return this.message; 
	}
	
	public FilesContentResponse setMessage(String message) {
		this.message = message; 
		return this; 
	}

	public FileContentEngine getContentEngine() {
		return contentEngine;
	}

	public FilesContentResponse setContentEngine(FileContentEngine contentEngine) {
		this.contentEngine = contentEngine;
		return this; 
	}
}
