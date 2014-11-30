/**
 * Creates a drag and drop area
 */

Dropzone.autoDiscover = false;
var dropZone = new Dropzone("#file-dropzone", { 			
	init: function() {
		imageIds = document.getElementById("files").value;
		var replacer = new RegExp(" ", "g");
		var images = imageIds.replace(replacer,"").split(',');
		images.forEach(function(image){
			if(image!=""){
				$.post("/Skeleton/getImgUrl?id="+image,function( data ) {
					// Create the mock file:
					
					// get file Size
					var xhr = new XMLHttpRequest();
					var size = 0;
					xhr.open('HEAD', '/Skeleton/img/ad/'+data, false);
					xhr.onreadystatechange = function(){
					  if ( xhr.readyState == 4 ) {
					    if ( xhr.status == 200 ) {
					      size =  xhr.getResponseHeader('Content-Length');
					    } 
					  }
					};
					xhr.send(null);
					
					var mockFile = { name: data, size: size, status: 'success', accepted: true, serverId: image };
					mockFile.upload = {bytesSent: 12345};
					mockFile.kind = "image";
					// Call the default addedfile event handler
					dropZone.emit("addedfile", mockFile);
					// And optionally show the thumbnail of the file:
					dropZone.emit("thumbnail", mockFile, '/Skeleton/img/ad/'+data);
					dropZone.files.push( mockFile );
					dropZone.emit("success", mockFile, image);
				});
			}
		});
	},
	accept: function(file, done){
		var re = /(?:\.([^.]+))?$/;
		var ext = re.exec(file.name)[1];
		ext = ext.toUpperCase();
		if ( ext == "JPEG" || ext == "BMP" || ext == "GIF" ||  ext == "JPG" ||  ext == "PNG" ||  ext == "JPE") {
			done();
		} else { 
			done("Please select an Image file"); 
		}
	},
	url: "/Skeleton/upload?name=${loggedInUser.id}",
	addRemoveLinks: true
});
	
		
function refreshIds(files){
	var imageIds = "";
			
	files.forEach(function(file){
		if(imageIds==""){
			imageIds = file.serverId;
		} else {
			imageIds += ", " + file.serverId;
		}
	});
			
	document.getElementById("files").value = imageIds;
}
		
dropZone.on("success", function(file, response) {
	file.serverId = response; // If you just return the ID when storing the file
	refreshIds(dropZone.getAcceptedFiles());
});
		
dropZone.on("removedfile", function(file) {
	if (!file.serverId) { return; } // The file hasn't been uploaded
	$.post("/Skeleton/removePicture?id=" + file.serverId); // Send the file id along
	refreshIds(dropZone.getAcceptedFiles());
});