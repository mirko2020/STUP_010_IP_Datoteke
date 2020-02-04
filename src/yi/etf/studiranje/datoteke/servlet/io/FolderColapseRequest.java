package yi.etf.studiranje.datoteke.servlet.io;

import java.io.File;
import java.io.Serializable;

import yi.etf.studiranje.datoteke.model.FileList;
import yi.etf.studiranje.datoteke.servlet.FileListOperations;

/**
 * Захтијев за сажимање директоријума у стаблу. 
 * @author Computer
 * @version 1.0
 */
public class FolderColapseRequest implements Serializable{
	private static final long serialVersionUID = -6511081392822739077L;
	private FileListOperations operations; 
	private FileList engine;
	private File file;
	
	public FileListOperations getOperations() {
		return operations;
	}
	public  FolderColapseRequest setOperations(FileListOperations operations) {
		this.operations = operations;
		return this; 
	}
	public File getFile() {
		return file;
	}
	public FolderColapseRequest setFile(File file) {
		this.file = file;
		return this; 
	}
	public FileList getEngine() {
		return engine;
	}
	public FolderColapseRequest setEngine(FileList engine) {
		this.engine = engine;
		return this; 
	} 
	
}
