<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="template/header.jsp" />

<h1>
	${ad.title}
	<c:if test="${loggedInUser.id!=ad.placerId}">
		<a class="btn btn-info" href="/Skeleton/bookmark?id=${ad.id}"><span class="glyphicon glyphicon-star"></span> Bookmark Ad</a>
		<a class="btn btn-info" href="/Skeleton/sendEnquiry?id=${ad.id}"><span class="glyphicon glyphicon-envelope"></span> Send Enquiry</a>
	</c:if>
	<c:if test="${loggedInUser.id==ad.placerId}">
		<a class="btn btn-info" href="/Skeleton/editAd?id=${ad.id}">Edit</a>
		<a class="btn btn-danger" href="/Skeleton/deleteAd?id=${ad.id}">Delete</a>
	</c:if>
</h1>
    
    
    <link rel="stylesheet" href="http://cdn.leafletjs.com/leaflet-0.7.3/leaflet.css" />
	<link rel="stylesheet" href="/Skeleton/css/Control.Geocoder.css" />
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script> <!-- 33 KB -->

<!-- fotorama.css & fotorama.js. -->
<link  href="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.2/fotorama.css" rel="stylesheet"> <!-- 3 KB -->
<script src="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.2/fotorama.js"></script> <!-- 16 KB -->
	
	<script src="http://cdn.leafletjs.com/leaflet-0.7.3/leaflet.js"></script>
	<script src="/Skeleton/js/Control.Geocoder.js"></script>
	<style type="text/css">
		div#map{
			width:100%;
			height:450px;
		}
	</style>
	  
	
	
    <div class="main">
    	

	<div class="row">

	 			<div class="fotorama">
					<c:forEach items="${ad.pictures}" var="pic">
						 <img style="float:left;" class="gallery" src="/Skeleton/img/ad/${pic.fileName}"/>
						
						<br />
					</c:forEach>
				</div>
			<div class="col-md-2"> 		
				<table class="smalltable">
		
		    		<tr>
		    			<td>Street&nbsp;&nbsp;
		    			<td>${ad.street}
		    		</tr>
		    		<tr>
		    			<td>House Nr.&nbsp;&nbsp;
		    			<td>${ad.houseNr}
		    		</tr>
		    		<tr>
		    			<td>City&nbsp;&nbsp;
		    			<td>${ad.city}
		    		</tr>
		    		<tr>
		    			<td>ZIP&nbsp;&nbsp;
		    			<td>${ad.zip}
		    		</tr>
		    		<tr>
		    			<td>Rent&nbsp;&nbsp;
		    			<td>${ad.rent}
		    		</tr>
		    		<tr>
		    			<td>Additional Cost&nbsp;&nbsp;
		    			<td>${ad.addCost}
		    		</tr>
		    		<tr>
		    			<td>Move In Date&nbsp;&nbsp;
		    			<td>${ad.dateIn}
		    		</tr>
		    		<tr>
		    			<td>Move Out Date&nbsp;&nbsp;
		    			<td>${ad.dateOut}
		    		</tr>
		    		<tr>
		    			<td>Room Size&nbsp;&nbsp;
		    			<td>${ad.roomSize}
		    		</tr>
		    		<tr>
		    			<td>Description&nbsp;&nbsp;
		    			<td>${ad.description}
		    		</tr>
		    		<tr>
		    			<td>About Us&nbsp;&nbsp;
		    			<td>${ad.us}
		    		</tr>
		    		<tr>
		    			<td>Ideal Roomie&nbsp;&nbsp;
		    			<td>${ad.you}
		    		</tr>
		    		<tr>
		  
	    	</table>
			</div>
	</div>




    	
    </div>	
    
 <div class="well well-sm"> <div id="map"></div></div>
	<script>
	
		// create a map in the "map" div, set the view to a given place and zoom
			var map = L.map('map').setView(["${ad.lat}]", "${ad.lng}]"], 15);
		


		//map.locate({setView: true, maxZoom: 16});
		// add an OpenStreetMap tile layer
		L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
		    attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
		}).addTo(map);

		// add a marker in the given location, attach some popup content to it and open the popup
		L.marker(["${ad.lat}]","${ad.lng}]"]).addTo(map)
		    .bindPopup("${shortDescription}")
		    .openPopup();
		
		// center marker on click
		map.on('popupopen', function(e) {
		    var px = map.project(e.popup._latlng);
		    px.y -= e.popup._container.clientHeight/2
		    map.panTo(map.unproject(px),{animate: true});
		});
			
	</script>


	<c:if test="${page_error != null }">
        <div class="alert alert-error">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <h4>Error!</h4>
                ${page_error}
        </div>
    </c:if>


<c:import url="template/footer.jsp" />
