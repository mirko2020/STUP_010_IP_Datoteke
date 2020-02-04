"UTF-8"

/**
 * Функционалности у раду са стаблом директоријума. 
 */

function expand(file){
	if(!test_expand_ajax_execute(file)){
		expand_ajax_execute(file);
		get_ajax_execute(file);
	}else{
		colapse_ajax_execute(file);
	}
}

function test_expand_ajax_execute(file){	
	var xmlhttp = new XMLHttpRequest();
    
    var origin = location.origin; 
    var path = location.pathname.split('/')[1]; 
    xmlhttp.open("POST", origin+"/"+path+"/FileWebService", false);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send("operation=FOLDERS_EXPAND_COLAPSE_CHECK&file.path="+encodeURI(file));
    
    var result = JSON.parse(xmlhttp.responseText);
    if(result.success){ 
    	return result['test.expand']; 	
    }
    return false; 
}

function expand_ajax_execute(file){	
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == XMLHttpRequest.DONE) { 
			if (xmlhttp.status == 200) {
				return; 
		    }
	    };
	}
    
    var origin = location.origin; 
    var path = location.pathname.split('/')[1]; 
    xmlhttp.open("POST", origin+"/"+path+"/FileWebService", true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send("operation=FOLDERS_EXPAND&file.path="+encodeURI(file));
}

function colapse_ajax_execute(file){
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == XMLHttpRequest.DONE) { 
			if (xmlhttp.status == 200) {
				var nodes = document.getElementById('folder_tree').querySelectorAll("dd");  
				for (var node of nodes) {	
					try{
					    var currentNode = document.getElementById(node.id);
					    if(currentNode.id == file){
							continue; 
						} 
						if(currentNode.id.trim().startsWith(file)){
							document.getElementById('folder_tree').removeChild(currentNode); 
						}
					}catch(err){
						continue; 
					}
				}
				return; 
		    }
	    };
	}
    
    var origin = location.origin; 
    var path = location.pathname.split('/')[1]; 
    xmlhttp.open("POST", origin+"/"+path+"/FileWebService", true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send("operation=FOLDERS_COLAPSE&file.path="+encodeURI(file));
}

function get_file_info(file){
	file = "file:/"+file; 
	var xmlhttp = new XMLHttpRequest();
    
    var origin = location.origin; 
    var path = location.pathname.split('/')[1]; 
    xmlhttp.open("POST", origin+"/"+path+"/FileWebService", false);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send("operation=FILES_INFO&file.path="+encodeURI(file));
 
    return  JSON.parse(xmlhttp.responseText);
}

function get_folder_info(file){
	file = "file:/"+file; 
	var xmlhttp = new XMLHttpRequest();
    
    var origin = location.origin; 
    var path = location.pathname.split('/')[1]; 
    xmlhttp.open("POST", origin+"/"+path+"/FileWebService", false);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send("operation=FOLDERS_INFO&file.path="+encodeURI(file));
    return  JSON.parse(xmlhttp.responseText);
}

function get_ajax_execute(file){
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == XMLHttpRequest.DONE) { 
			if (xmlhttp.status == 200) {
			   var result = JSON.parse(xmlhttp.responseText);
			   if(result.success){ 
				   var nodes = result["file.nodes"];
				   var neoHTML = ''; 
				   for(var i=0; i<nodes.length; i++){
					   var newNode = document.createElement("dd"); 
					   var nodePathParts = nodes[i]["node.path"].split(/[/\\]/g);
					   newNode.id = 'file://'+nodes[i]["node.path.enc"];
					   if(document.getElementById(newNode.id)!=null){
						   var el = document.getElementById(newNode.id);
						   el.parentNode.removeChild(el);
					   }
					   var blank = ""; 
					   for(var j=1; j<nodes[i]["node.level"]; j++)
						   blank+="&nbsp;&nbsp;&nbsp;";
					   var filemode = nodes[i]["node.filemode"]; 
					   
					   var headerNumber = ''; 
					   var link = ''; 
					   if(filemode=='FILE'){
						   var jsonRes = get_file_info(nodes[i]["node.path.enc"]);
						   headerNumber = jsonRes["file.size"];
						   link = '<a href="YIWebPages/file_preview.jsp?file='+encodeURI(nodes[i]["node.path.enc"])+'" onclick="event.stopPropagation()" target="_blank">ПРЕГЛЕД</a>'; 
					   }else if(filemode=='DIR'){
						   var jsonRes = get_folder_info(nodes[i]["node.path.enc"]);
						   headerNumber = jsonRes["count.files"]+"+"+jsonRes["count.directories"]+"="+jsonRes["count.total"];
						   newNode.setAttribute("onclick", "expand('file://"+nodes[i]["node.path.enc"]+"')"); 
						   link = '<a href="YIWebPages/folder_preview.jsp?file='+encodeURI(nodes[i]["node.path.enc"])+'" onclick="event.stopPropagation()" target="_blank">ПРЕГЛЕД</a>';
					   }
					   
					   var header = "["+filemode+"]["+headerNumber+"] ";
					   newNode.innerHTML = blank+header+nodePathParts[nodePathParts.length-1]+"&nbsp;&nbsp;&nbsp;"+link; 
					   
					   neoHTML +=  newNode.outerHTML; 
					   
					    /* document.getElementById(file).insertAdjacentHTML("afterend",newNode.outerHTML); */
					   /* document.getElementById(file).parentElement.innerHTML += newNode.outerHTML;     */ 
				   } 
				   
				   document.getElementById(file).insertAdjacentHTML("afterend",neoHTML);
			   }
			   return; 
		    }
	    };
	}
	
	var origin = location.origin; 
    var path = location.pathname.split('/')[1]; 
    xmlhttp.open("POST", origin+"/"+path+"/FileWebService", true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send("operation=FOLDERS_ALL&file.path="+encodeURI(file));
}