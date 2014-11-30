
	/**
	 * Search Sliders 
	 *
	 */
		$("#priceSlider").noUiSlider({
			start: [0, 3000],
			behaviour: 'drag-tap',
			connect: true,
			step: 10,
			range: ({
				'min': 0,
				'max': 3000
			}),
			/*format: wNumb({
				decimals: 0,
				postfix: ' CHF',
			})*/
		});
		$("#priceSlider").Link('lower').to($("#field-priceMin"), null, wNumb({
					decimals: 0,
				}));


		$("#priceSlider").Link('lower').to('-inline-<div class="priceSliderTooltip" ></div>', function ( value ) {
			
			$(this).html(
				'<strong>Min: </strong>' +
				'<span>' + value + '</span>'
			);
		});
	
		$("#priceSlider").Link('lower').to('-inline-<div class="priceSliderTooltip" ></div>', function ( value ) {
			
			$(this).html(
				'<strong>Min: </strong>' +
				'<span>' + value + '</span>'
			);
		});
		$("#priceSlider").Link('upper').to($("#field-priceMax"), null, wNumb({
			decimals: 0,
		}));
		$("#priceSlider").Link('upper').to('-inline-<div class="priceSliderTooltip" ></div>', function ( value ) {
		

			
			if(value==3000)
			{
				$(this).html(
						'<strong>Max: </strong>' +
						'<span>' + "Unlimited" + '</span>'
					);
				
			} else {
				$(this).html(
						'<strong>Max: </strong>' +
						'<span>' + value + '</span>'
					);
			}

		});
		$("#roomSizeSlider").noUiSlider({
			start: [0, 300],
			behaviour: 'drag-tap',
			connect: true,
			step: 1,
			range: ({
				'min': 0,
				'max': 300
			}),
			/*format: wNumb({
				decimals: 0,
				postfix: ' CHF',
			})*/
		});
		
		$("#roomSizeSlider").Link('lower').to($("#field-roomSizeMin"), null, wNumb({
			decimals: 0,
		}));
		$("#roomSizeSlider").Link('lower').to('-inline-<div class="roomSizeSliderTooltip" ></div>', function ( value ) {
		
			$(this).html(
				'<strong>Min: </strong>' +
				'<span>' + value + '</span>'
			);
		});
		$("#roomSizeSlider").Link('upper').to($("#field-roomSizeMax"), null, wNumb({
			decimals: 0,
		}));
		$("#roomSizeSlider").Link('upper').to('-inline-<div class="roomSizeSliderTooltip" ></div>', function ( value ) {
		
			if(value==300)
			{
				$(this).html(
						'<strong>Max: </strong>' +
						'<span>' + "Unlimited" + '</span>'
					);
				
			} else {
				$(this).html(
						'<strong>Max: </strong>' +
						'<span>' + value + '</span>'
					);
			}
			
		});
		
		$(document).ready(function(){			
			$("#priceSlider").val(["${searchAttributes.priceMin}","${searchAttributes.priceMax}"]);
			$("#roomSizeSlider").val(["${searchAttributes.roomSizeMin}","${searchAttributes.roomSizeMax}"]);
			$("#field-city").val("${searchAttributes.city}");
		});
		


	
		// create a map in the "map" div, set the view to a given place and zoom
		var map = L.map('map', {center: [46.9467726,7.4442328], zoom: 8});
		L.Control.geocoder().addTo(map);
		L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
		    attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
		}).addTo(map);
	
		var markers = L.markerClusterGroup({ spiderfyOnMaxZoom: true, showCoverageOnHover: true, zoomToBoundsOnClick: true });

		function populate(lat, lng, shortDescriptoin) {

				var m = L.marker([lat,lng]);
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
		
		function setMarker(lat, lng, shortDescriptoin) {
			L.marker([lat,lng]).addTo(map)
			    .bindPopup(shortDescriptoin);
			    //.openPopup();
		};

		// center marker on click
		map.on('popupopen', function(e) {
		    var px = map.project(e.popup._latlng);
		    px.y -= e.popup._container.clientHeight/2
		    map.panTo(map.unproject(px),{animate: true});

		});
			


	$('#myTab a[href="#mapTab"]').click(function (e) {
		  e.preventDefault()
		  $(this).tab('show')
		});
	$('#myTab a[href="#listTab"]').click(function (e) {
		  e.preventDefault()
		  $(this).tab('show')
		});
	
	$( document ).ready(function() {
		$('#myTab a[href="#listTab"]').tab('show');
		});
