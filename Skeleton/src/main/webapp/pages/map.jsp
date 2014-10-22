<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="template/header.jsp" />


<h1>Map</h1>

	<link rel="stylesheet" href="/Skeleton/lib/leaflet-0.7.3/leaflet.css" />
	<link rel="stylesheet" href="/Skeleton/css/Control.Geocoder.css" />
	<link rel="stylesheet" href="/Skeleton/lib/leaflet-0.7.3/MarkerCluster.css" />
	<link rel="stylesheet" href="/Skeleton/lib/leaflet-0.7.3/MarkerCluster.Default.css" />
	<link rel="stylesheet" href="/Skeleton/lib/leaflet-0.7.3/Control.Geocoder.css" />
	<script src="/Skeleton/lib/leaflet-0.7.3/leaflet.js"></script>
	<script src="/Skeleton/lib/leaflet-0.7.3/leaflet.markercluster-src.js"></script>
	<script src="/Skeleton/lib/leaflet-0.7.3/Control.Geocoder.js"></script>
	
	<style type="text/css">
		div#map{
			width:100%;
			height:450px;
		}
	</style>
	<div id="map"></div>
	<script type="text/javascript">
	
		// create a map in the "map" div, set the view to a given place and zoom
		var map = L.map('map', {center: [46.9467726,7.4442328], zoom: 8});
		L.Control.geocoder().addTo(map);
		L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
		    attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
		}).addTo(map);
	
		var markers = L.markerClusterGroup({ spiderfyOnMaxZoom: true, showCoverageOnHover: true, zoomToBoundsOnClick: true });

		function populate(lat, lon, shortDescriptoin) {

				var m = L.marker([lat,lon]);
				m.bindPopup(shortDescriptoin);
				markers.addLayer(m);
	
			return;
		}

		var polygon;
		markers.on('clustermouseover', function (a) {
			if (polygon) {
				map.removeLayer(polygon);
			}
			polygon = L.polygon(a.layer.getConvexHull());
			map.addLayer(polygon);
		});

		markers.on('clustermouseout', function (a) {
			if (polygon) {
				map.removeLayer(polygon);
				polygon = null;
			}
		});

		map.on('zoomend', function () {
			if (polygon) {
				map.removeLayer(polygon);
				polygon = null;
			}
		});

		function addMarkerLayer(){
			map.addLayer(markers);
		}
		
		function setMarker(lat, lon, shortDescriptoin) {
			L.marker([lat,lon]).addTo(map)
			    .bindPopup(shortDescriptoin);
			    //.openPopup();
		};

		// center marker on click
		map.on('popupopen', function(e) {
		    var px = map.project(e.popup._latlng);
		    px.y -= e.popup._container.clientHeight/2
		    map.panTo(map.unproject(px),{animate: true});

		});
			
	</script>
	
	<c:if test="${not empty adList}">
		<c:forEach var="ad" items="${adList}">
			<script type="text/javascript">
				populate("${ad.lat}","${ad.lon}","<b>${ad.title}</b> <br /> ${ad.street} ${ad.houseNr} <br /> ${ad.city} ${ad.zip} <br /> <a href='ad?adId=${ad.ad_Id}'>open</a>");
			</script>
		</c:forEach>
		<script type="text/javascript">
			addMarkerLayer();
		</script>
	</c:if>


	<c:if test="${page_error != null }">
        <div class="alert alert-error">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <h4>Error!</h4>
                ${page_error}
        </div>
    </c:if>


<c:import url="template/footer.jsp" />
