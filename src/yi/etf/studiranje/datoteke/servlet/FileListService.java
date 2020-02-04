package yi.etf.studiranje.datoteke.servlet;

import java.io.File;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import yi.etf.studiranje.datoteke.model.FileList;
import yi.etf.studiranje.datoteke.servlet.io.FilesContentRequest;
import yi.etf.studiranje.datoteke.servlet.io.FilesContentResponse;
import yi.etf.studiranje.datoteke.servlet.io.FilesFullTreeRequest;
import yi.etf.studiranje.datoteke.servlet.io.FilesFullTreeResponse;
import yi.etf.studiranje.datoteke.servlet.io.FilesInfoRequest;
import yi.etf.studiranje.datoteke.servlet.io.FilesInfoResponse;
import yi.etf.studiranje.datoteke.servlet.io.FolderColapseRequest;
import yi.etf.studiranje.datoteke.servlet.io.FolderColapseResponse;
import yi.etf.studiranje.datoteke.servlet.io.FolderExpandRequest;
import yi.etf.studiranje.datoteke.servlet.io.FolderExpandResponse;
import yi.etf.studiranje.datoteke.servlet.io.FoldersInfoRequest;
import yi.etf.studiranje.datoteke.servlet.io.FoldersInfoResponse;
import yi.etf.studiranje.datoteke.servlet.io.FoldersRootsRequest;
import yi.etf.studiranje.datoteke.servlet.io.FoldersRootsResponse;

/**
 * Сервиси који се вежу за листу датотека. 
 * @author Computer
 * @version 1.0
 */
public final class FileListService {
	private FileListService() {}
	
	public static FilesContentResponse service(FilesContentRequest request) {
		try {
			FilesContentResponse response = new FilesContentResponse(); 
			
			File file = request.getFile();
			int pageNo = request.getPageNo();
			int pageSize = request.getPageSize();   
			
			if(request.getOperations()==FileListOperations.FILES_CONTENT) 
				throw new UnsupportedOperationException();
			
			response.setFile(file); 
			response.setSuccess(true);
			response.setContentEngine(response.getContentEngine()); 
			response.setOperation(FileListOperations.FILES_CONTENT); 
			response.setMessage("Очитавање успјешно"); 
			response.setPageNo(pageNo);
			response.setPageSize(pageSize);  
			
			return response; 
		}catch(Exception ex) {
			throw new RuntimeException(ex); 
		}
	}
	
	public static FilesFullTreeResponse service(FilesFullTreeRequest request) {
		try {
			FilesFullTreeResponse response = new FilesFullTreeResponse(); 
			FileListOperations operation = request.getOperations(); 
			File file = request.getFile();
			
			
			if(operation != FileListOperations.FOLDERS_ALL)
				throw new UnsupportedOperationException(); 
			
			response.setFile(file); 
			response.setSuccess(false); 
			response.setMessage("Догодила се грешка при очитавању стабла."); 
			response.setOperation(operation); 
			

			if(file==null) {
				FileList engine = request.getEngine(); 
				response.setContent(engine);
			}else{
				FileList engine = request.getEngine(); 
				engine.expand(request.getFile());
				response.setContent(engine);
			}
			
			
			response.setSuccess(true); 
			response.setMessage("Успјешно реализовано очитавање стабла."); 
			
			return response; 
		}catch(Exception ex) {
			throw new RuntimeException(ex); 
		}
	}
	
	public static FilesInfoResponse service(FilesInfoRequest request) {
		try {
			FilesInfoResponse response = new FilesInfoResponse(); 
			FileListOperations operation = request.getOperation(); 
			
			if(operation != FileListOperations.FILES_INFO) {
				throw new RuntimeException(); 
			}
			
			response.setSuccess(false);
			response.setMessage("Очитавање информација и датотеци је неуспјешно."); 
			
			File file = request.getFile(); 
			FileList engine = request.getEngine();
			
			if(file==null) throw new NullPointerException();
			if(engine==null) throw new NullPointerException(); 
			
			response.setFile(file);
			response.setLevel(engine.level(file)); 
			response.setOperation(operation);
			response.setFileList(engine);
			response.setSuccess(true); 
			response.setMessage("Очитавање информација о датотеци је успјешно."); 
			
			return response; 
		}catch(Exception ex) {
			throw new RuntimeException(ex); 
		}
	}
	
	public static FolderColapseResponse service(FolderColapseRequest request) {
		try {
			FolderColapseResponse response = new FolderColapseResponse(); 
			FileListOperations operation = request.getOperations(); 
			
			if(operation != FileListOperations.FOLDERS_COLAPSE) {
				throw new RuntimeException("Затварање листе садржаја није успјешно."); 
			}
			
			
			FileList engine = request.getEngine(); 
			engine.collapse(request.getFile());
			
			response.setOperations(operation); 
					
			response.setSuccess(false); 
			response.setMessage("Затварање листе садржаја није успјешно.");
			
			response.setFile(request.getFile()); 
			response.setFileList(request.getEngine()); 
			
			response.setSuccess(true); 
			response.setMessage("Затварање листе садржаја успјешно.");
			
			return response; 
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public static FolderExpandResponse service(FolderExpandRequest request) {
		try {
			FolderExpandResponse response = new FolderExpandResponse(); 
			FileListOperations operation = request.getOperations(); 
			
			if(operation != FileListOperations.FOLDERS_EXPAND) {
				throw new UnsupportedOperationException(); 
			}
			
			FileList engine = request.getEngine(); 
			File file = request.getFile(); 
			
			engine.expand(request.getFile());
			
			response.setSuccess(false);
			response.setMessage("Експанзија фолдера са датотекама неуспјешна."); 
			response.setFile(file);
			response.setFileList(engine);
			response.setLevel(engine.level(file)); 
			response.setCountFiles(engine.countDirectFiles()); 
			response.setCountFolders(engine.countDirectFolders()); 
			response.setCountTotal(engine.countDirect());
			response.setSuccess(true);
			response.setMessage("Експанзија фолдера са датотекама успјешна."); 
						
			return response; 
		}catch(Exception ex) {
			throw new RuntimeException(ex); 
		}
	}
	
	public static FoldersRootsResponse service(FoldersRootsRequest request) {
		try {
			FoldersRootsResponse response = new FoldersRootsResponse(); 
			
			FileListOperations operation = request.getOperation(); 
			
			if(operation != FileListOperations.FOLDERS_ROOTS) {
				throw new UnsupportedOperationException(); 
			}
			
			response.setMessage("Очитавање коријенова неуспјешно.");
			response.setSuccess(false);
			
			response.setRootsInfo(request.getEngine()); 
			
			response.setMessage("Очитавање коријенова успјешно.");
			response.setSuccess(true); 
			
			return response; 
		}catch(Exception ex) {
			throw new RuntimeException(ex); 
		}
	}
	
	public static FoldersInfoResponse service(FoldersInfoRequest request) {
		try {
			FoldersInfoResponse response = new FoldersInfoResponse(); 
			
			FileListOperations operation = request.getOperation(); 
			if(operation != FileListOperations.FOLDERS_INFO) {
				throw new UnsupportedOperationException(); 
			}
			
			response.setOperations(FileListOperations.FOLDERS_INFO); 
			response.setFileList(request.getEngine()); 
			response.setFile(request.getFile());
			response.setSuccess(true); 
			response.setMessage("");
			
			return response; 
		}catch(Exception ex) {
			throw new RuntimeException(ex); 
		}
	}
	
	public static boolean isExpanded(HttpServletRequest request) {
		try {
			File file = new File(URLDecoder.decode(request.getParameter("file.path"),"UTF-8").substring(6));
			FileList engine = FileListIOUtil.util.getOrInitEngine(request); 
			if(engine==null) return false;
			return engine.count(file)!=0; 
		}catch(Exception ex) {
			return false; 
		}
	}
	
	public static boolean isColapsed(HttpServletRequest request) {
		return !isExpanded(request); 
	}
}
