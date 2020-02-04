package yi.etf.studiranje.datoteke.servlet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
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
import yi.etf.studiranje.datoteke.util.ZipEngine;

/**
 * Сервлет који се користи за преузимање директоријума као 
 * запакованих датотека. Једноставан сервлет без контроле процеса. 
 * Сматра се да директоријуми неће имати обиман садржај, укључив и коријене.
 * Наравно и датотеке да неће бити велике.  
 * @author Computer
 * @version 1.0
 */
@WebServlet("/DownloadFolderServlet")
public class DownloadFolderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DownloadFolderServlet() {
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
			if(!file.isDirectory()) throw new RuntimeException(); 		
			
			ZipEngine ze = new ZipEngine(file);
			ze.zip(); 
		
			ByteArrayOutputStream baos = (ByteArrayOutputStream) ze.getOutput(); 
			response.setContentType("application/octet-stream");
			response.setContentLengthLong(baos.toByteArray().length);
			response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''"+URLEncoder.encode(file.getName(),"UTF-8")+".zip");
			
			try(ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray())){ 
				int size = bais.available(); 
				for(int i=0; i<size; i++){
					response.getOutputStream().write(bais.read());
				}
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
			response.sendError(404, "Догодила се грешка при преузимању или директоријум не постоји.");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
