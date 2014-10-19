<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="template/header.jsp" />


<h1>Map</h1>
	<style type="text/css">
	div#map_container{
		width:100%;
		height:450px;
	}
	</style>
	<script src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>

	 
	<script type="text/javascript">
		var geocoder;
		var map;

		function loadMap() {
			geocoder = new google.maps.Geocoder();

			var latlng = new google.maps.LatLng(46.9512905,7.4385747);
			var mapOptions = {
				zoom: 17,
				center: latlng,
				mapTypeId: google.maps.MapTypeId.ROADMAP
			};
			map = new google.maps.Map(document.getElementById("map_container"),mapOptions);
	 
			var address = "${Address}";
			var geocoder = geocoder.geocode( { 'address': address}, function(results, status) {
		  	    if (status == google.maps.GeocoderStatus.OK) {
					map.setCenter(results[0].geometry.location);
					var marker = new google.maps.Marker({
						map: map,
						position: results[0].geometry.location,
						title: "${Address}"
					});
				} else {
					alert('Geocode was not successful for the following reason: ' + status);
				}
			});
		}
	</script>
	 
	<body onload="loadMap()">
	<div id="map_container"></div>
	
	<c:if test="${page_error != null }">
        <div class="alert alert-error">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <h4>Error!</h4>
                ${page_error}
        </div>
    </c:if>


<c:import url="template/footer.jsp" />
