package yi.etf.studiranje.datoteke.servlet.io;

import java.io.File;
import java.io.Serializable;

import yi.etf.studiranje.datoteke.model.FileList;
import yi.etf.studiranje.datoteke.servlet.FileListOperations;

/**
 * Захтијев за информације о датотекама. 
 * @author Computer
 * @version 1.0
 */
public class FilesInfoRequest implements Serializable{
	private static final long serialVersionUID = 7602904012957206048L;
	private FileListOperations operation;  
	private File file;
	private FileList engine; 
	
	public FileListOperations getOperation() {
		return operation;
	}
	public FilesInfoRequest setOperation(FileListOperations operation) {
		this.operation = operation;
		return this; 
	}
	public File getFile() {
		return file;
	}
	public FilesInfoRequest setFile(File file) {
		this.file = file;
		return this;
	}
	
	public FileList getEngine() {
		return engine;
	}
	public FilesInfoRequest setEngine(FileList engine) {
		this.engine = engine;
		return this; 
	} 
}
