package yi.etf.studiranje.datoteke.servlet.io;

import java.io.Serializable;

import yi.etf.studiranje.datoteke.model.FileList;
import yi.etf.studiranje.datoteke.servlet.FileListOperations;

/**
 * Захтијев за листу коријена. 
 * @author Computer
 * @version 1.0
 */
public class FoldersRootsRequest implements Serializable{
	private static final long serialVersionUID = -145454228739625155L;
	private FileListOperations operation; 
	public FileListOperations getOperation() {
		return operation;
	}
	public FoldersRootsRequest setOperation(FileListOperations operation) {
		this.operation = operation;
		return this; 
	}
	
	private FileList engine; 
	public FileList getEngine() {
		return engine; 
	}
	public FoldersRootsRequest setEngine(FileList engine) {
		this.engine = engine; 
		return this; 
	} 
}
