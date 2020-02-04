<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id='YI_APP_File_explorer' scope='session' class='yi.etf.studiranje.datoteke.control.FileListEngine'/>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Датотеке</title>
		<link type='text/css' rel='stylesheet' href='YIStylesheet/font.css'/>
		<link type='text/css' rel='stylesheet' href='YIStylesheet/linx.css'/>
		<script type="text/javascript" src='YIJavascript/datoteke.ajax.js'></script>
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
		<div style='color:#FFB000'><div align='center'><b>ДИРЕКТОРИЈУМИ И ДАТОТЕКЕ</b></div></div>
		<div style='background-color:#FFB000'>&nbsp;</div>
		<div id='viewfield'>
			<div style='font-family:yi_courier_new'>
				<br>
				<div id='folder'>
					<jsp:include page="${'/FileListServlet'}">
						<jsp:param value="FOLDERS_ALL" name="operation"/>
					</jsp:include>
				</div>
				<br>
			</div>
			<div style='background-color:#FFB000'>&nbsp;</div>
		</div>
	</body>
</html>