<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import='java.net.URLDecoder' %>
<%@ page import='java.io.File' %>

<jsp:useBean id='YI_APP_File_explorer' scope='session' class='yi.etf.studiranje.datoteke.control.FileListEngine'/>

<%
	if(request.getParameter("file")==null) {
		response.sendError(404, "Тражена страница не постоји, или није доступна под датим параметрима.");
		return; 
	}

	File targetFile = new File(URLDecoder.decode(request.getParameter("file"),"UTF-8"));
	request.setAttribute("targetFile", targetFile); 
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Датотеке</title>
		<link type='text/css' rel='stylesheet' href='../YIStylesheet/font.css'/>
		<link type='text/css' rel='stylesheet' href='../YIStylesheet/linx.css'/>
		<style>
			@media only screen and (max-width: 900px) {
			  #viewfield{
			  	 display:none;
			  }
			}
		</style>
	</head>
	<body>
		<div style='background-color:#FFB000'>&nbsp;</div>
		<div style='color:#FFB000'><div align='center'><b>ДИРЕКТОРИЈУМИ И ДАТОТЕКЕ - ДАТОТЕКА</b></div></div>
		<div style='background-color:#FFB000'>&nbsp;</div>
		<div id='viewfield'>
			<br>
			<c:if test='${param["file"] ne null}'>
				<br>
				<div id='file' style='font-family:yi_courier_new' align='center'><c:out value='${param["file"]}'></c:out></div>
				<script> document.getElementById('file').innerHTML=unescape(decodeURI(document.getElementById('file').innerHTML))</script>
				<hr>
				<br>
				<table>
					<thead align='left'>
						<tr>
							<th>Ставка &nbsp;&nbsp;&nbsp;</th>
							<th>вриједност</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>Ниво &nbsp;&nbsp;&nbsp;</td>
							<td><c:out value='${sessionScope["YI_APP_File_explorer"].rootList.level(targetFile)}'></c:out></td>
						</tr>
						<tr>
							<td>Величина у бајтовима &nbsp;&nbsp;&nbsp;</td>
							<td><c:out value='${targetFile.length()}'></c:out></td>
						</tr>
					</tbody>
				</table>
				<br>
				<a href='../DownloadFileServlet?file=${param["file"]}' target='_blank'>Преузимање</a> 
				<script>
					document.write('<a href=\'../YIView/file_view.jsp?file='+encodeURI('${param["file"]}')+'\' target=\'_blank\'>преглед</a>'); 
				</script>
				<br>
			</c:if>
			<c:if test='${param["file"] eq null}'>
				<div>Не постоје информације о циљном директоријуму или датотеци.</div>
			</c:if>
			<br>
			<div style='background-color:#FFB000'>&nbsp;</div>
		</div>
	</body>
</html>