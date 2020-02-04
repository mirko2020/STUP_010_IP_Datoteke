package yi.etf.studiranje.datoteke.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yi.etf.studiranje.datoteke.control.FileListEngine;
import yi.etf.studiranje.datoteke.model.FileList;

/**
 * Сервлет који служи за преузимање датотека. 
 * Једноставан сервлет и нема контролу процеса. 
 * Намијењен за мање датотеке. Користи се дакле ХТТП. 
 * @author Computer
 * @version 1.0
 */
@WebServlet("/DownloadFileServlet")
public class DownloadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DownloadFileServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String path = request.getParameter("file");
		try {
			FileListEngine engine = (FileListEngine) request.getSession().getAttribute("YI_APP_File_explorer"); 
			FileList roots =  engine.getRootList(); 
			File file = new File(URLDecoder.decode(path,"UTF-8"));
			if(roots.getRoot(file)==null) throw new RuntimeException(); 
			if(!file.exists()) throw new RuntimeException(); 
			if(!file.isFile()) throw new RuntimeException();
			response.setContentType("application/octet-stream");
			response.setContentLengthLong(file.length());
			response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''"+URLEncoder.encode(file.getName(),"UTF-8"));
			try(FileInputStream fis = new FileInputStream(file)){
				while(fis.available()!=0) {
					response.getOutputStream().write(fis.read());
				}
			}
		}catch(Exception ex) {
			response.sendError(404, "Догодила се грешка при преузимању или датотека не постоји.");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response); 
	}

}
