package yi.etf.studiranje.datoteke.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

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
 * Сервлет који се користи за веб сервисе везане за стабла 
 * датотека и директоријуме.
 * @author Computer
 * @version 1.0
 */
@WebServlet("/FileWebService")
public class FileWebService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FileWebService() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		try {
			FileList list = FileListIOUtil.util.getOrInitEngine(request);
			FileListEngine engine = (FileListEngine) request.getSession().getAttribute("YI_APP_File_explorer");   
			FileListOperations operation = FileListOperations.valueOf(request.getParameter("operation"));
			switch(operation) {
				case FILES_CONTENT:
					FilesContentRequest req1 = FileListIOUtil.util.getFilesContentsRequest(request).setFileList(list); 
					FilesContentResponse resp1 = FileListService.service(req1);
					response.getWriter().println(FileListIOUtil.util.jsonize(resp1));
					break;
				case FILES_INFO: 
					FilesInfoRequest req2 = FileListIOUtil.util.getFilesInfoRequest(request).setEngine(list);
					FilesInfoResponse resp2 = FileListService.service(req2);
					response.getWriter().println(FileListIOUtil.util.jsonize(resp2));
					break; 
				case FOLDERS_ALL: 
					FilesFullTreeRequest req3 = FileListIOUtil.util.getFilesFullTreeRequest(request).setEngine(list);  
					FilesFullTreeResponse resp3 = FileListService.service(req3); 
					response.getWriter().println(FileListIOUtil.util.jsonize(resp3));
					break; 
				case FOLDERS_COLAPSE:
					FolderColapseRequest req4 = FileListIOUtil.util.getFolderColapseRequest(request).setEngine(list); 
					FolderColapseResponse resp4 = FileListService.service(req4);
					response.getWriter().println(FileListIOUtil.util.jsonize(resp4));
					break; 
				case FOLDERS_EXPAND: 
					FolderExpandRequest req5 = FileListIOUtil.util.getFolderExpandRequest(request).setEngine(list); 
					FolderExpandResponse resp5 = FileListService.service(req5);
					response.getWriter().println(FileListIOUtil.util.jsonize(resp5));
					break; 
				case FOLDERS_INFO: 
					FoldersInfoRequest req6 = FileListIOUtil.util.getFoldersInfoRequest(request).setEngine(list);
					boolean testExpand = engine.getRootList().isExpanded(req6.getFile()); 
					if(!testExpand) engine.getRootList().expand(req6.getFile()); 
					FoldersInfoResponse resp6 = FileListService.service(req6); 
					response.getWriter().println(FileListIOUtil.util.jsonize(resp6));
					if(!testExpand) engine.getRootList().collapse(req6.getFile()); 
					break; 
				case FOLDERS_ROOTS:
					FoldersRootsRequest req7 = FileListIOUtil.util.getFoldersRootsRequest(request).setEngine(list); 
					FoldersRootsResponse resp7 = FileListService.service(req7);
					response.getWriter().println(FileListIOUtil.util.jsonize(resp7));
					break; 
				case FOLDERS_EXPAND_COLAPSE_CHECK: 
					JsonObject root = new JsonObject(); 
					root.addProperty("success", "true");
					root.addProperty("test.expand", FileListService.isExpanded(request));
					response.getWriter().println(root.toString());
					break; 
			}
		}catch(Exception ex) {
			JsonObject object = new JsonObject(); 
			object.addProperty("success", false);
			object.addProperty("message", "Извршавање операције са датотекама и директоријумима није успјело.");
			response.getWriter().println(object.toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
