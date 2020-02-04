package yi.etf.studiranje.datoteke.servlet.io;

import java.io.File;
import java.io.Serializable;

import yi.etf.studiranje.datoteke.model.FileList;
import yi.etf.studiranje.datoteke.servlet.FileListOperations;

/**
 * Захтијев за тренутно стабло директоријума. 
 * @author Computer
 * @version 1.0
 */
public class FilesFullTreeRequest implements Serializable{
	private static final long serialVersionUID = 6880327154498387520L;
	
	private File file; 
	private FileListOperations operations;
	
	public File getFile() {
		return file;
	}
	public FilesFullTreeRequest setFile(File file) {
		this.file = file;
		return this; 
	}
	public FileListOperations getOperations() {
		return operations;
	}
	public FilesFullTreeRequest setOperations(FileListOperations operations) {
		this.operations = operations;
		return this; 
	}
	
	private FileList engine;
	
	public FileList getEngine() {
		return engine; 
	}
	public FilesFullTreeRequest setEngine(FileList engine) {
		this.engine = engine; 
		return this;
	}
}
