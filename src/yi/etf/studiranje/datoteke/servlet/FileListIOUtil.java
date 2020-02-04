package yi.etf.studiranje.datoteke.servlet;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import yi.etf.studiranje.datoteke.config.Config;
import yi.etf.studiranje.datoteke.control.FileListEngine;
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
 * Улаз објеката захтијева из ХТТПа и излаз објеката одговора у ЈСОН.
 * @author Computer
 * @version 1.0
 */
public final class FileListIOUtil {
	public static FileListIOUtil util = new FileListIOUtil(); 
	private FileListIOUtil() {}

	public FilesContentRequest getFilesContentsRequest(HttpServletRequest request) {
		try { 
			FilesContentRequest req = new FilesContentRequest();
			FileListOperations operation = FileListOperations.valueOf(request.getParameter("operation")); 
			if(operation != FileListOperations.FILES_CONTENT) throw new NullPointerException("Операција је неправилна или недефинисана."); 
			req.setOperations(operation); 
			File file = new File(URLDecoder.decode(request.getParameter("file.path"), "UTF-8").substring(6));
			int pageNo = Integer.parseInt(request.getParameter("page.no"));
			int pageSize = Integer.parseInt(request.getParameter("page.size"));
			req.setFile(file); 
			req.setPageNo(pageNo);
			req.setPageSize(pageSize); 
			return req; 
		}catch(Exception ex) {
			throw new RuntimeException("Неправилан захтијев.", ex); 
		}
	}
	
	public FilesFullTreeRequest getFilesFullTreeRequest(HttpServletRequest request) {
		try { 
			FilesFullTreeRequest req = new FilesFullTreeRequest();
			FileListOperations operation = FileListOperations.valueOf(request.getParameter("operation")); 
			if(operation != FileListOperations.FOLDERS_ALL) throw new NullPointerException("Операција је неправилна или недефинисана."); 
			req.setOperations(operation); 
			String path = request.getParameter("file.path");
			if(path!=null) req.setFile(new File(URLDecoder.decode(path,"UTF-8").substring(6)));
			return req; 
		}catch(Exception ex) {
			throw new RuntimeException("Неправилан захтијев.", ex); 
		}
	}
	
	public FilesInfoRequest getFilesInfoRequest(HttpServletRequest request) {
		try { 
			FilesInfoRequest req = new FilesInfoRequest();
			FileListOperations operation = FileListOperations.valueOf(request.getParameter("operation")); 
			if(operation != FileListOperations.FILES_INFO) throw new NullPointerException("Операција је неправилна или недефинисана."); 
			req.setOperation(operation); 
			File file = new File(URLDecoder.decode(request.getParameter("file.path"),"UTF-8").substring(6)); 
			req.setFile(file);
			return req; 
		}catch(Exception ex) {
			throw new RuntimeException("Неправилан захтијев.", ex); 
		}
	}
	
	public FolderColapseRequest getFolderColapseRequest(HttpServletRequest request) {
		try { 
			FolderColapseRequest req = new FolderColapseRequest();
			FileListOperations operation = FileListOperations.valueOf(request.getParameter("operation")); 
			if(operation != FileListOperations.FOLDERS_COLAPSE) throw new NullPointerException("Операција је неправилна или недефинисана."); 
			req.setOperations(operation);
			File file = new File(URLDecoder.decode(request.getParameter("file.path"),"UTF-8").substring(6)); 
			req.setFile(file);
			return req; 
		}catch(Exception ex) {
			throw new RuntimeException("Неправилан захтијев.", ex); 
		}
	}
	
	public FolderExpandRequest getFolderExpandRequest(HttpServletRequest request) {
		try { 
			FolderExpandRequest req = new FolderExpandRequest();
			FileListOperations operation = FileListOperations.valueOf(request.getParameter("operation")); 
			if(operation != FileListOperations.FOLDERS_EXPAND) throw new NullPointerException("Операција је неправилна или недефинисана."); 
			req.setOperations(operation); 
			File file = new File(URLDecoder.decode(request.getParameter("file.path"), "UTF-8").substring(6)); 
			req.setFile(file);
			return req; 
		}catch(Exception ex) {
			throw new RuntimeException("Неправилан захтијев.", ex); 
		}
	}
	
	public FoldersInfoRequest getFoldersInfoRequest(HttpServletRequest request) {
		try { 
			FoldersInfoRequest req = new FoldersInfoRequest();
			FileListOperations operation = FileListOperations.valueOf(request.getParameter("operation")); 
			if(operation != FileListOperations.FOLDERS_INFO) throw new NullPointerException("Операција је неправилна или недефинисана."); 
			req.setOperation(operation); 
			File file = new File(URLDecoder.decode(request.getParameter("file.path"),"UTF-8").substring(6)); 
			req.setFile(file);
			return req; 
		}catch(Exception ex) {
			throw new RuntimeException("Неправилан захтијев.", ex); 
		}
	}
	
	public FoldersRootsRequest getFoldersRootsRequest(HttpServletRequest request) {
		try { 
			FoldersRootsRequest req = new FoldersRootsRequest();
			FileListOperations operation = FileListOperations.valueOf(request.getParameter("operation")); 
			if(operation != FileListOperations.FOLDERS_ROOTS) throw new NullPointerException("Операција је неправилна или недефинисана."); 
			req.setOperation(operation); 
			return req; 
		}catch(Exception ex) {
			throw new RuntimeException("Неправилан захтијев.", ex); 
		}
	}
	
	public String jsonize(FilesContentResponse response) {
		JsonObject object = new JsonObject(); 
		object.addProperty("operation", response.getOperation().toString());
		object.addProperty("file.path", response.getFile().getPath());
		try{object.addProperty("file.path.enc", URLEncoder.encode(response.getFile().getPath(),"UTF-8"));}catch(Exception ex) {}
		object.addProperty("file.content", response.getContentEngine().readBasicRecord(response.getPageSize(), response.getPageNo()));
		object.addProperty("page.no", response.getPageNo()); 
		object.addProperty("page.size", response.getPageSize());
		object.addProperty("success", response.isSuccess());
		object.addProperty("message", response.getMessage());
		return object.toString();
	}
	
	public String jsonize(FilesFullTreeResponse response) {
		JsonObject object = new JsonObject(); 
		object.addProperty("operation", response.getOperation().toString());
		if(response.getFile()!=null) object.addProperty("file.path", response.getFile().getPath());
		if(response.getFile()!=null) object.addProperty("file.path.enc", response.getFile().getPath());
		
		if(response.getFile()!=null) {
			JsonArray array = new JsonArray();
			for(var mEntry: response.getContent().getAllFolders(response.getFile()).entrySet()) {
				JsonObject node = new JsonObject();
				node.addProperty("node.path", mEntry.getKey());
				try{node.addProperty("node.path.enc", URLEncoder.encode(mEntry.getKey(),"UTF-8"));}catch(Exception ex) {}
				node.addProperty("node.level", response.getContent().level(mEntry.getValue()));
				node.addProperty("node.mode" , (response.getContent().isExpanded(mEntry.getValue())?"expanded":"colapsed"));
				node.addProperty("node.filemode", (mEntry.getValue().isFile()?"FILE":(mEntry.getValue().isDirectory()?"DIR":"NONE"))); 
				array.add(node);
			}
			for(var mEntry: response.getContent().getAllFiles(response.getFile()).entrySet()) {
				JsonObject node = new JsonObject();
				node.addProperty("node.path", mEntry.getKey());
				try{node.addProperty("node.path.enc", URLEncoder.encode(mEntry.getKey(),"UTF-8"));}catch(Exception ex) {}
				node.addProperty("node.level", response.getContent().level(mEntry.getValue()));
				node.addProperty("node.mode" , (response.getContent().isExpanded(mEntry.getValue())?"expanded":"colapsed"));
				node.addProperty("node.filemode", (mEntry.getValue().isFile()?"FILE":(mEntry.getValue().isDirectory()?"DIR":"NONE"))); 
				array.add(node);
			}
			object.add("file.nodes", array);
		} 
		object.addProperty("success", response.isSuccess());
		object.addProperty("message", response.getMessage());
		return object.toString();
	}
	
	public String jsonize(FilesInfoResponse response) {
		JsonObject object = new JsonObject(); 
		object.addProperty("operation", response.getOperation().toString());
		object.addProperty("file.path", response.getFile().getPath());
		try{object.addProperty("file.path.enc", URLEncoder.encode(response.getFile().getPath(),"UTF-8"));}catch(Exception ex) {}
		object.addProperty("file.level", response.getLevel()); 
		object.addProperty("file.mode", (response.getFileList().isExpanded(response.getFile())?"expanded":"colapsed"));
		object.addProperty("file.filemode", (response.getFile().isFile()?"FILE":(response.getFile().isDirectory()?"DIR":"NONE"))); 
		object.addProperty("count.files", response.getFileList().countDirectFiles(response.getFile()));
		object.addProperty("count.directories", response.getFileList().countDirectFolders(response.getFile()));
		object.addProperty("count.total", response.getFileList().countDirect(response.getFile()));
		object.addProperty("file.size", response.getFile().length());
		object.addProperty("success", response.isSuccess());
		object.addProperty("message", response.getMessage());
		return object.toString();
	}
	
	public String jsonize(FoldersInfoResponse response) {
		JsonObject object = new JsonObject(); 
		object.addProperty("operation", response.getOperations().toString());
		object.addProperty("file.path", response.getFile().getPath());
		try{object.addProperty("file.path.enc", URLEncoder.encode(response.getFile().getPath(),"UTF-8"));}catch(Exception ex) {}
		object.addProperty("file.level", response.getLevel()); 
		object.addProperty("file.mode", (response.getFileList().isExpanded(response.getFile())?"expanded":"colapsed"));
		object.addProperty("file.filemode", (response.getFile().isFile()?"FILE":(response.getFile().isDirectory()?"DIR":"NONE"))); 
		object.addProperty("count.files", response.getFileList().countDirectFiles(response.getFile()));
		object.addProperty("count.directories", response.getFileList().countDirectFolders(response.getFile()));
		object.addProperty("count.total", response.getFileList().countDirect(response.getFile()));
		object.addProperty("file.size", response.getFile().length());
		object.addProperty("success", response.isSuccess());
		object.addProperty("message", response.getMessage());
		return object.toString();
	}
	
	public String jsonize(FolderColapseResponse response) {
		JsonObject object = new JsonObject(); 
		object.addProperty("operation", response.getOperations().toString());
		object.addProperty("file.path", response.getFile().getPath());
		try{object.addProperty("file.path.enc", URLEncoder.encode(response.getFile().getPath(),"UTF-8"));}catch(Exception ex) {}
		object.addProperty("file.level", response.getLevel()); 
		object.addProperty("file.mode", (response.getFileList().isExpanded(response.getFile())?"expanded":"colapsed"));
		object.addProperty("file.filemode", (response.getFile().isFile()?"FILE":(response.getFile().isDirectory()?"DIR":"NONE"))); 
		object.addProperty("success", response.isSuccess());
		object.addProperty("message", response.getMessage());
		return object.toString();
	}
	
	public String jsonize(FolderExpandResponse response) {
		JsonObject object = new JsonObject(); 
		object.addProperty("operation", response.getOperations().toString());
		object.addProperty("file.path", response.getFile().getPath());
		try{object.addProperty("file.path.enc", URLEncoder.encode(response.getFile().getPath(),"UTF-8"));}catch(Exception ex) {}
		object.addProperty("file.level", response.getLevel()); 
		object.addProperty("file.mode", (response.getFileList().isExpanded(response.getFile())?"expanded":"colapsed"));
		object.addProperty("file.filemode", (response.getFile().isFile()?"FILE":(response.getFile().isDirectory()?"DIR":"NONE"))); 
		object.addProperty("success", response.isSuccess());
		object.addProperty("message", response.getMessage());
		return object.toString();
	}
	
	public String jsonize(FoldersRootsResponse response) {
		JsonObject object = new JsonObject(); 
		object.addProperty("operation", response.getOperations().toString()); 
		JsonArray array = new JsonArray();
		for(var rootInfo: response.getRootsInfo().getAllFiles().entrySet()) {
			JsonObject node = new JsonObject(); 
			node.addProperty("root.path", rootInfo.getValue().getPath());
			try{node.addProperty("root.path.enc", URLEncoder.encode(rootInfo.getValue().getPath(),"UTF-8"));}catch(Exception ex) {}
			node.addProperty("root.level", response.getRootsInfo().level(rootInfo.getValue()));
			node.addProperty("root.mode" , (response.getRootsInfo().isExpanded(rootInfo.getValue())?"expanded":"colapsed"));
			node.addProperty("root.filemode", (rootInfo.getValue().isFile()?"FILE":(rootInfo.getValue().isDirectory()?"DIR":"NONE"))); 
		}
		object.add("file.roots", array);
		object.addProperty("success", response.isSuccess());
		object.addProperty("message", response.getMessage());
		response.getRootsInfo();
		return object.toString();
	}
	
	
	public FileList getOrInitEngine(HttpServletRequest request) {
		Config config = Config.config;
		FileListEngine list = (FileListEngine) request.getSession().getAttribute("YI_APP_File_explorer");   
		if(list==null)
		try {
			File file = new File(config.getFilelist()); 
			FileListEngine engine = new FileListEngine();  
			if(file.exists()) engine.load(); 
			else engine.addRoot("web.app", new File(request.getSession().getId()));
 			request.getSession().setAttribute("YI_APP_File_explorer", engine); 
 			return engine.getRootList();
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
		return list.getRootList(); 
	}
}
