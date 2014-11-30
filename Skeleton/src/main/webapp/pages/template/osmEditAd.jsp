
	<script src="/Skeleton/lib/leaflet-0.7.3/leaflet.js"></script>
	<script src="/Skeleton/lib/leaflet-0.7.3/leaflet.markercluster.js"></script>
	<script src="/Skeleton/lib/leaflet-0.7.3/Control.Geocoder.js"></script>
	<link rel="stylesheet" href="/Skeleton/lib/leaflet-0.7.3/leaflet.css" />
	<link rel="stylesheet" href="/Skeleton/css/Control.Geocoder.css" />
	<link rel="stylesheet" href="/Skeleton/lib/leaflet-0.7.3/MarkerCluster.css" />
	<link rel="stylesheet" href="/Skeleton/lib/leaflet-0.7.3/MarkerCluster.Default.css" />
	<link rel="stylesheet" href="/Skeleton/lib/leaflet-0.7.3/Control.Geocoder.css" />
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