package yi.etf.studiranje.datoteke.servlet.io;

import java.io.File;
import java.io.Serializable;

import yi.etf.studiranje.datoteke.model.FileList;
import yi.etf.studiranje.datoteke.servlet.FileListOperations;

/**
 * Захтијев за садржај датотека. 
 * @author Computer
 * @version 1.0
 */
public class FilesContentRequest implements Serializable{
	private static final long serialVersionUID = -6864815511161068840L;
	private File file; 
	private FileListOperations operations; 
	private int pageSize; 
	private int pageNo; 
	private FileList fileList; 
	
	public File getFile() {
		return file; 
	}
	
	public FilesContentRequest setFile(File file) {
		this.file = file; 
		return this; 
	}
	
	public int getPageSize() {
		return pageSize; 
	}
	
	public int getPageNo() {
		return pageNo; 
	}
	
	public FilesContentRequest setPageSize(int pageSize) {
		this.pageSize = pageSize; 
		return this; 
	}
	
	public FilesContentRequest setPageNo(int pageNo) {
		this.pageNo = pageNo; 
		return this; 
	}
	
	public FileListOperations getOperations() {
		return operations;
	}
	
	public FilesContentRequest setOperations(FileListOperations operations) {
		this.operations = operations; 
		return this; 
	}

	public FileList getFileList() {
		return fileList;
	}

	public FilesContentRequest setFileList(FileList fileList) {
		this.fileList = fileList;
		return this; 
	}
}
