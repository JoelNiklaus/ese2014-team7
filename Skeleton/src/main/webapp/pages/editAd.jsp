<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<c:import url="template/header.jsp" />


	<link rel="stylesheet" href="/Skeleton/lib/leaflet-0.7.3/leaflet.css" />
	<link rel="stylesheet" href="/Skeleton/css/Control.Geocoder.css" />
	<link rel="stylesheet" href="/Skeleton/lib/leaflet-0.7.3/MarkerCluster.css" />
	<link rel="stylesheet" href="/Skeleton/lib/leaflet-0.7.3/MarkerCluster.Default.css" />
	<link rel="stylesheet" href="/Skeleton/lib/leaflet-0.7.3/Control.Geocoder.css" />
	<script src="/Skeleton/lib/leaflet-0.7.3/leaflet.js"></script>
	<script src="/Skeleton/lib/leaflet-0.7.3/leaflet.markercluster.js"></script>
	<script src="/Skeleton/lib/leaflet-0.7.3/Control.Geocoder.js"></script>
	<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
	
	<style type="text/css">
		div#map{
			width:100%;
			height:250px;
		}
	</style>
	
	<c:if test="${not empty success}">
		<div class="alert alert-success" role="alert">
			${success}
		</div>
	</c:if>
	
	<c:if test="${not empty error}">
		<div class="alert alert-danger" role="alert">
			${error}
		</div>
	</c:if>

<h1>Edit Ad</h1>

<form:form method="post" modelAttribute="adForm" action="submitEditAd?adId=${ad.id}" id="adForm" cssClass="form-horizontal" autocomplete="off" enctype="multipart/form-data">
	<fieldset>

		<legend>General Information</legend>

		<label class="control-label" for="field-title">Title</label>
		<form:input class="form-control" path="Title" tabindex="1" maxlength="50" value="${ad.title}"/>
		<form:errors path="title" cssClass="help-inline" element="span"/>
		<br>

		<legend>Address</legend>
		
		<label class="control-label" for="field-street">Street</label>
		<form:input class="form-control" id="streetInput" path="street" onblur="geocode()" tabindex="2" maxlength="50" value="${ad.street}"/>
		<form:errors path="street" cssClass="help-inline" element="span"/>
		
		<label class="control-label" for="field-houseNr">House Nr.</label>
		<form:input class="form-control" id="houseNrInput" path="houseNr" onblur="geocode()" tabindex="3" maxlength="50" value="${ad.houseNr}"/>
		<form:errors path="houseNr" cssClass="help-inline" element="span"/>
		
		<label class="control-label" for="field-zip">ZIP</label>
		<form:input class="form-control" id="zipInput" path="zip" onblur="geocode()" tabindex="4" maxlength="4" value="${ad.zip}"/>
		<form:errors path="zip" cssClass="help-inline" element="span"/>

		
		<label class="control-label" for="field-city">City</label>
		<form:input class="form-control" id="cityInput" path="city" onblur="geocode()" tabindex="5" maxlength="50" value="${ad.city}"/>
		<form:errors path="city" cssClass="help-inline" element="span"/>
		<br>
		
		<!-- Lat -->
		<form:input class="form-control" type="hidden" id="latInput" path="lat" maxlength="50" value="${ad.lat}"/>
		<!-- Lon -->
		<form:input class="form-control" type="hidden" id="lngInput" path="lng" maxlength="50" value="${ad.lng}"/>
		<br>
	
		<div class="well well-sm"><div id="map"></div></div>		

		<legend>Costs</legend>

		<label class="control-label" for="field-rent">Rent</label>
		<form:input class="form-control" path="rent" tabindex="6" maxlength="4" value="${ad.rent}"/>
		<form:errors path="rent" cssClass="help-inline" element="span"/>
		
		<label class="control-label" for="field-addCost">Additional Cost</label>
		<form:input class="form-control" path="addCost" tabindex="7" maxlength="3" value="${ad.addCost}"/>
		<form:errors path="addCost" cssClass="help-inline" element="span"/>
		<br>
		
		<legend>Availability</legend>

		<link rel="stylesheet" href="/Skeleton/lib/datepicker-1.3.0/css/datepicker.css" />
		<link rel="stylesheet" href="/Skeleton/lib/datepicker-1.3.0/css/datepicker3.css" />
		<script src="/Skeleton/lib/datepicker-1.3.0/js/bootstrap-datepicker.js"></script>


		<label class="control-label" for="field-dateIn">Move In Date</label>
		<form:input class="datepicker form-control" path="dateIn" tabindex="8" maxlength="10" value="${ad.dateIn}"/>
		<form:errors path="dateIn" cssClass="help-inline" element="span"/>
		
		<label class="control-label" for="field-dateOut">Move Out Date</label>
		<form:input class="datepicker form-control" path="dateOut" tabindex="9" maxlength="10" value="${ad.dateOut}"/>
		<form:errors path="dateOut" cssClass="help-inline" element="span"/>
		<br>


		<script type="text/javascript">
			$('.datepicker').datepicker({
			    startDate: '-0d',
			    todayHighlight: true,
			    weekStart: 1,
			    calendarWeeks: true,
			    autoclose: true,
			    format: 'dd.mm.yyyy'
			})
		</script>
		
		<legend>Additional Information</legend>

		<label class="control-label" for="field-roomSize">Room Size</label>
		<form:input class="form-control" path="roomSize" tabindex="10" maxlength="3" value="${ad.roomSize}"/>
		<form:errors path="roomSize" cssClass="help-inline" element="span"/>
		
		<label class="control-label" for="field-description">Description</label>
		<form:input class="form-control" path="description" tabindex="11" maxlength="255" value="${ad.description}"/>
		<form:errors path="description" cssClass="help-inline" element="span"/>

		<label class="control-label" for="field-us">We are...</label>
		<form:input class="form-control" path="us" tabindex="12" maxlength="255" value="${ad.us}"/>
		<form:errors path="us" cssClass="help-inline" element="span"/>
		
		<label class="control-label" for="field-you">Ideal Roomie</label>
		<form:input class="form-control" path="you" tabindex="13" maxlength="255" value="${ad.you}"/>
		<form:errors path="you" cssClass="help-inline" element="span"/>


		<div class="form-actions">
			<button type="submit" class="btn btn-primary">Save Changes</button>
			<button type="button" class="btn">Cancel</button>
		</div>
	</fieldset>
</form:form>



	<c:if test="${page_error != null }">
		<div class="alert alert-error">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<h4>Error!</h4>
			${page_error}
		</div>
	</c:if>

	<script type="text/javascript">
	
		// create a map in the "map" div, set the view to a given place and zoom
		var lat = document.getElementById('latInput').value;
		var lng = document.getElementById('lngInput').value;
		var map = L.map('map', {center: [46.9467726,7.4442328], zoom: 8});
		var marker= new L.Marker([46.9467726,7.4442328], {draggable: true});
		
		if((lat!="") && (lng!="")){
			map.setView([lat,lng],13);
			marker= new L.Marker([lat,lng], {draggable: true});
		} 

	    marker.on('dragend', function(e){
	
			document.getElementById('latInput').value=e.target._latlng.lat; 
			document.getElementById('lngInput').value=e.target._latlng.lng; 

			marker.setLatLng([e.target._latlng.lat,e.target._latlng.lng],{draggable: true});
			
    	});
		L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
			attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
		}).addTo(map);
		map.addLayer(marker);
		
		map.on('click', onClick); 
		
		function onClick(e) {
			document.getElementById('latInput').value=e.latlng.lat; 
			document.getElementById('lngInput').value=e.latlng.lng; 

			marker.setLatLng([e.latlng.lat,e.latlng.lng],{draggable: true});
		}
		

		
		function geocode() {
			var city = document.getElementById('cityInput').value;
			var houseNr = document.getElementById('houseNrInput').value;
			var street = document.getElementById('streetInput').value;
			var url = " http://nominatim.openstreetmap.org/search?q="+city+",+"+street+"+"+houseNr+"&format=json&polygon=0&addressdetails=1";
			// requires jquery for ajax
			$.getJSON(url, function(data) {
				if (data.length > 0) {
					document.getElementById('latInput').value=parseFloat(data[0].lat); 
					document.getElementById('lngInput').value=parseFloat(data[0].lon); 

					marker.setLatLng([parseFloat(data[0].lat),parseFloat(data[0].lon)],{draggable: true});
					map.setView([parseFloat(data[0].lat),parseFloat(data[0].lon)],13);
				} 
			});
		} 	
		
	</script>

<c:import url="template/footer.jsp" />