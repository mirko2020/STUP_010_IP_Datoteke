<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import='yi.etf.studiranje.datoteke.bean.ListPaggingBean' %>
<%@ page import='java.net.URLDecoder' %>
<%@ page import='java.io.File' %>

<jsp:useBean id='YI_APP_File_explorer' scope='session' class='yi.etf.studiranje.datoteke.control.FileListEngine'/>
<jsp:useBean id='yi_datoteke_pregled_stranicenje' scope='session' class='yi.etf.studiranje.datoteke.bean.ListPaggingBean'></jsp:useBean>

<%
	if(request.getParameter("file")==null) {
		response.sendError(404, "Тражена страница не постоји, или није доступна под датим параметрима.");
		return; 
	}
	File targetFile = new File(URLDecoder.decode(request.getParameter("file"),"UTF-8"));
	request.setAttribute("targetFile", targetFile); 
	
	ListPaggingBean pagging = (ListPaggingBean) request.getSession().getAttribute("yi_datoteke_pregled_stranicenje");
	try{pagging.setPageNo(Long.parseLong(request.getParameter("page_no")));}catch(Exception ex){}
	try{pagging.setPageSize(Long.parseLong(request.getParameter("page_size")));}catch(Exception ex){}
	
	pagging.setCount(targetFile.length()); 
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
		<div style='color:#FFB000'><div align='center'><b>ДИРЕКТОРИЈУМИ И ДАТОТЕКЕ - ДАТОТЕКА - ПРЕГЛЕД</b></div></div>
		<div style='background-color:#FFB000'>&nbsp;</div>
		<div id='viewfield'>
			<c:if test='${param["file"] ne null}'>
				<br>
				<div id='file' style='font-family:yi_courier_new' align='center'><c:out value='${param["file"]}'></c:out></div>
				<script> document.getElementById('file').innerHTML=unescape(decodeURI(document.getElementById('file').innerHTML))</script>
				<hr>
				<br>
				<form method='POST' action='./file_view.jsp'>
					<table>
						<tr>
							<td>Величина странице : </td>
							<td><input type='text' id='page_size' name='page_size' value='${sessionScope["yi_datoteke_pregled_stranicenje"].pageSize}' style='width:25px'/></td>
						</tr>
						<tr>
							<td>Текућа страница : </td>
							<td><input type='text' id='page_no' name='page_no' value='${sessionScope["yi_datoteke_pregled_stranicenje"].pageNo}' style='width:25px'/></td>
						</tr>
						<tr>
							<td>Број страница :</td>
							<td><input type='text' readonly value='${sessionScope["yi_datoteke_pregled_stranicenje"].pageCount}' style='width:25px'/></td>
						</tr>
					</table>
					<br>
					<input type='hidden' value='${param["file"]}' name='file'/>
					<input type='submit' value='Освјежавање' name='refresh'/>
				</form>
				<br>
				<br>
				<div id='content' style='font-family:yi_courier_new'>
					<c:out value='${sessionScope["YI_APP_File_explorer"].getFileEngine(targetFile).readBasicRecord(sessionScope["yi_datoteke_pregled_stranicenje"].pageSize, sessionScope["yi_datoteke_pregled_stranicenje"].pageNo)}'></c:out>
				</div>
			</c:if>
			<c:if test='${param["file"] eq null}'>
				<div>Не постоје информације о циљном директоријуму или датотеци.</div>
			</c:if>
			<br>
			<div style='background-color:#FFB000'>&nbsp;</div>
		</div>
	</body>
</html>