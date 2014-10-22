<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="template/header.jsp" />


<h1>Map</h1>

	<link rel="stylesheet" href="http://cdn.leafletjs.com/leaflet-0.7.3/leaflet.css" />
	<link rel="stylesheet" href="/Skeleton/css/Control.Geocoder.css" />
	<script src="http://cdn.leafletjs.com/leaflet-0.7.3/leaflet.js"></script>
	<script src="/Skeleton/js/Control.Geocoder.js"></script>
	<style type="text/css">
		div#map{
			width:100%;
			height:450px;
		}
	</style>
		<div id="map"></div>
	<script>
	
		// create a map in the "map" div, set the view to a given place and zoom
			var map = L.map('map').setView(["${Coordinates.lat}]", "${Coordinates.lon}]"], 15);
		


		//map.locate({setView: true, maxZoom: 16});
		// add an OpenStreetMap tile layer
		L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
		    attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
		}).addTo(map);

		// add a marker in the given location, attach some popup content to it and open the popup
		L.marker(["${Coordinates.lat}]","${Coordinates.lon}]"]).addTo(map)
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
