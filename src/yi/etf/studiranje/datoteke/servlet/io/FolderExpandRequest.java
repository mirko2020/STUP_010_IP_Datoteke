package yi.etf.studiranje.datoteke.servlet.io;

import java.io.File;
import java.io.Serializable;

import yi.etf.studiranje.datoteke.model.FileList;
import yi.etf.studiranje.datoteke.servlet.FileListOperations;

/**
 * Захтијев за експанзију саджаја фолдера у стаблу.
 * @author Computer
 * @version 1.0
 */
public class FolderExpandRequest implements Serializable {
	private static final long serialVersionUID = -4980990992268286503L;

	private FileListOperations operations;
	private FileList engine; 
	private File file;

	public FileListOperations getOperations() {
		return operations;
	}

	public  FolderExpandRequest setOperations(FileListOperations operations) {
		this.operations = operations;
		return this; 
	}

	public File getFile() {
		return file;
	}

	public  FolderExpandRequest setFile(File file) {
		this.file = file;
		return this; 
	}

	public FileList getEngine() {
		return engine;
	}

	public FolderExpandRequest setEngine(FileList engine) {
		this.engine = engine;
		return this;
	}
}
