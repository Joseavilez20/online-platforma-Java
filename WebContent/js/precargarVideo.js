var selDiv = "";
	var storedFiles = [];
	
	$(document).ready(function() {
		$("#files").on("change", handleFileSelect);
		
		selDiv = $("#selectedFiles"); 
		//$("#form1").on("submit", handleForm);
		
		//$("body").on("click", ".selFile", removeFile);
		$("body").on("click", ".fa-trash", removeFile);
	});
		
	function handleFileSelect(e) {
		var files = e.target.files;
		var filesArr = Array.prototype.slice.call(files);
		filesArr.forEach(function(f) {			

//                        if(!f.type.match("video.*") ) {
//				return;
//			}
                        
			
			storedFiles.push(f);
			
			var reader = new FileReader();
			
                        reader.onload = function (e) {
                            if(f.type.match("video.*")) {
                                
				/*var html = "<div><img src=\"" + e.target.result + "\" data-file='"+f.name+"' class='selFile' title='Click to remove'>" + f.name + "<br clear=\"left\"/></div>";*/
				var html = "<div onevideo class='card col-sm-4' style='width: 22rem; '><video  height='340' controls>"+
				"<source  src=\"" + e.target.result + "\" type='video/mp4'>"+"</video>"+"<div class='card-body'>"+"<span class='card-title'>"+f.name+"</span><i class='fa fa-trash' style='color:Tomato'></i> </div></div>";

			}else  if(f.type.match("image.*")){
                           var html = "<div class='card col-sm-4' style='width: 22rem;'><img class='img-thumbnail  card-img-top'  src=\"" + e.target.result + "\" data-file='"+f.name+"'>" + "<div class='card-body'>"+"<span class='card-title'>"+f.name+"</span><i class='fa fa-trash ' style='color:Tomato'></i> </div></div>";
				
                        }else if(f.type.match("application/pdf")){ 
                            var html = "<div ><span>pdf</span>"+f.name+"</div>"; 
                        }
           
				selDiv.append(html);
				
			}
			reader.readAsDataURL(f); 
		});
		
	}
		
	/*function handleForm(e) {
		e.preventDefault();
		var data = new FormData();
		
		for(var i=0, len=storedFiles.length; i<len; i++) {
			data.append('files', storedFiles[i]);	
		}
		
		var xhr = new XMLHttpRequest();
		xhr.open('POST', 'ServletImg', true);
		
		xhr.onload = function(e) {
			if(this.status == 200) {
				console.log(e.currentTarget.responseText);	
				alert(e.currentTarget.responseText + ' items uploaded.');
			}
		}
		
		xhr.send(data);
	}
	*/	
	function removeFile(e) {
		//var file = $(this).data("card-title");
		var ele = $(e.target).parent().find(".card-title")[0]
        var file = ele.innerText;
		for(var i=0;i<storedFiles.length;i++) {
			if(storedFiles[i].name === file) {
				storedFiles.splice(i,1);
				break;
			}
		}
		
		$(this).parent().parent().remove();
		console.log(storedFiles);

	}