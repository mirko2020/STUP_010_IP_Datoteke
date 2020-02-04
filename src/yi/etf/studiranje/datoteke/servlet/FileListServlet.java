package yi.etf.studiranje.datoteke.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yi.etf.studiranje.datoteke.control.FileListEngine;
import yi.etf.studiranje.datoteke.model.FileList;
import yi.etf.studiranje.datoteke.servlet.io.FilesFullTreeRequest;
import yi.etf.studiranje.datoteke.servlet.io.FilesFullTreeResponse;

/**
 * Сервлет који враћа ХТМЛ листу стабла датотека. 
 * Користи се за директно угнијеждење у ХТМЛ. 
 * @author Computer
 * @version 1.0
 */
@WebServlet("/FileListServlet")
public class FileListServlet extends HttpServlet {
	private static final long serialVersionUID = -258778111134382553L; 
	
    public FileListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html"); 
		
		try {
			FileList list = FileListIOUtil.util.getOrInitEngine(request);
			FileListEngine listEngine = (FileListEngine) request.getSession().getAttribute("YI_APP_File_explorer");   
			FileListOperations operation = FileListOperations.valueOf(request.getParameter("operation").trim());
			
			listEngine.load(); 
			list = FileListIOUtil.util.getOrInitEngine(request);
			
			if(operation!=FileListOperations.FOLDERS_ALL) {
				return; 
			}
			
			FilesFullTreeRequest req3 = FileListIOUtil.util.getFilesFullTreeRequest(request).setEngine(list);
			req3.setEngine(list); 
			
			FilesFullTreeResponse resp3 = FileListService.service(req3); 
			
			if(resp3==null)
				return; 
			
			response.getWriter().println("<dl id='folder_tree'>");
			response.getWriter().println("<dt>Листа отворених директоријума и датотека</dt>");
			
			
			for(var mEntry: resp3.getContent().getOpened().entrySet()) {
				resp3.getContent().expand(mEntry.getValue()); 
				String blanks = ""; 
				for(int i=0; i<resp3.getContent().level(mEntry.getValue()); i++) {
					blanks += "&nbsp;&nbsp;&nbsp;"; 
				}
				if(mEntry.getValue().isDirectory())
					response.getWriter().println("<dd id='file://"+URLEncoder.encode(mEntry.getValue().toString(),"UTF-8")+"' onclick='expand(\"file://"+URLEncoder.encode(mEntry.getValue().toString(),"UTF-8")+"\")'>"+blanks+"[DIR]["+req3.getEngine().countDirectFiles(mEntry.getValue())+"+"+req3.getEngine().countDirectFolders(mEntry.getValue())+"]");
				if(mEntry.getValue().isFile())
					response.getWriter().println("<dd id='file://"+URLEncoder.encode(mEntry.getValue().toString(),"UTF-8")+"' onclick='expand(\"file://"+URLEncoder.encode(mEntry.getValue().toString(),"UTF-8")+"\")'>"+blanks+"[FILE]["+mEntry.getValue().length()+"]");
				response.getWriter().println(mEntry.getValue().getName().replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">","&gt;"));
				response.getWriter().println("&nbsp;");
				if(mEntry.getValue().isDirectory()) {
					response.getWriter().println("<script>document.write('<a href=\"YIWebPages/folder_preview.jsp?file='+encodeURI('"+URLEncoder.encode(mEntry.getValue().toString(),"UTF-8")+"')+'\" onclick=\"event.stopPropagation()\" target=\"_blank\">ПРЕГЛЕД</a>');</script>");
				}
				if(mEntry.getValue().isFile()) {
					response.getWriter().println("<script>document.write('<a href=\"YIWebPages/file_preview.jsp?file='+encodeURI('"+URLEncoder.encode(mEntry.getValue().toString(),"UTF-8")+"')+'\" onclick=\"event.stopPropagation()\" target=\"_blank\">ПРЕГЛЕД</a>');</script>");
				}
				response.getWriter().println("</dd>");
				resp3.getContent().collapse(mEntry.getValue()); 
			}
			
			response.getWriter().println("</dl>");
		}catch(Exception ex) {
			return; 
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
