package yi.etf.studiranje.datoteke.servlet.io;

import java.io.File;
import java.io.Serializable;

import yi.etf.studiranje.datoteke.model.FileList;
import yi.etf.studiranje.datoteke.servlet.FileListOperations;

/**
 * Информације о фајлу или фолдеру - захтијев. 
 * @author Computer
 * @version 1.0
 */
public class FoldersInfoRequest implements Serializable{
	private static final long serialVersionUID = -8209319553215093770L;
	private FileList engine; 
	private FileListOperations operation; 
	private File file;
	
	public FileListOperations getOperation() {
		return operation;
	}
	public FoldersInfoRequest setOperation(FileListOperations operation) {
		this.operation = operation;
		return this; 
	}
	public File getFile() {
		return file;
	}
	public FoldersInfoRequest  setFile(File file) {
		this.file = file;
		return this; 
	} 
	
	public FileList getEngine() {
		return engine; 
	}
	public FoldersInfoRequest setEngine(FileList engine) {
		this.engine = engine;
		return this; 
	}
}
