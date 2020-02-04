<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding='UTF-8' %>
<%@ page isErrorPage="true" %>
<%@ page import="java.io.ByteArrayOutputStream" %>
<%@ page import="java.io.OutputStreamWriter" %>
<%@ page import="java.io.PrintWriter" %>

<!DOCTYPE html>
<html>
	<head>
	    <title>Корисници</title>
	</head>	
	<body>
	<h1>Извјештај о грешци или статусу.</h1>
	
	<table>
		<tr>
			<td><b>Статусни код : </b></td>
			<td><%=response.getStatus()%></td>
		</tr>
		<tr>
			<td><b>Порука : </b></td>
			<td><%=request.getAttribute("javax.servlet.error.message")==null?"":
									(request.getAttribute("javax.servlet.error.message")
									.toString()
									.replaceAll("&", "&amp;")
	    							.replaceAll("<", "&lt;")
	    							.replaceAll(">", "&gt;"))%></td>
		</tr>
	</table>
	
	<div style='color:blue'>
		<br>
		<br>
		<code>
		<% 
			if(exception!=null){
		    	out.println(exception.getClass().getName().concat(": "+exception.getMessage())
		    			.replaceAll("&", "&amp;")
						.replaceAll("<", "&lt;")
						.replaceAll(">", "&gt;")); 	
		    }
		
		%>
		</code>
	</div>
	
	<div style="color: #F00;">
	    <code>
	    <%
	    	if(exception!=null){
	    		ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
	    		exception.printStackTrace(new PrintWriter(new OutputStreamWriter(baos,"UTF-8"),true));
	    		out.println(new String(baos.toByteArray(),"UTF-8")
	    						    .replaceAll("&", "&amp;")
	    							.replaceAll("<", "&lt;")
	    							.replaceAll(">", "&gt;"));  
	    	}
	    %>
	    </code>
	</div>
</body>
</html>