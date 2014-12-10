<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="template/header.jsp" />

<c:if test="${not empty success}">
	<div class="alert alert-success" role="alert">
		${success}
	</div>
</c:if>

<style>
  .cluster {
    background: #2d84c8;
    border-radius: 50%;
    text-align: center;
    color: white;
    font-weight: 700;
    border: 1px solid #2d84c8;
    font-family: monospace;
  }

  .cluster:before {
     content: ' ';
     position: absolute;
     border-radius: 50%;
     z-index: -1;
     top: 1px;
     left: 1px;
     right: 1px;
     bottom: 1px;
     border: 1px solid white;
  }

  .digits-1 {
    font-size: 14px;
    height: 28px;
    width: 28px;
    line-height: 28px;
    margin-top: -14px;
    margin-left: -14px;
  }

  .digits-2 {
    font-size: 16px;
    height: 34px;
    width: 34px;
    line-height: 35px;
    margin-top: -17px;
    margin-left: -17px;
  }

  .digits-2:before {
    border-width: 2px;
  }

  .digits-3 {
    font-size: 18px;
    height: 48px;
    width: 47px;
    line-height: 47px;
    border-width: 3px;
    margin-top: -24px;
    margin-left: -24px;
  }

  .digits-3:before {
    border-width: 3px;
  }

  .digits-4 {
    font-size: 18px;
    height: 58px;
    width: 58px;
    line-height: 57px;
    border-width: 4px;
    margin-top: -29px;
    margin-left: -29px;
  }

  .digits-4:before {
    border-width: 4px;
  }
</style>
<link rel="stylesheet" href="/Skeleton/lib/leaflet-0.7.3/leaflet.css" />
<link rel="stylesheet" href="/Skeleton/css/Control.Geocoder.css" />
<link rel="stylesheet" href="/Skeleton/lib/leaflet-0.7.3/MarkerCluster.css" />
<link rel="stylesheet" href="/Skeleton/lib/leaflet-0.7.3/MarkerCluster.Default.css" />
<link rel="stylesheet" href="/Skeleton/lib/leaflet-0.7.3/Control.Geocoder.css" />
<link href="/Skeleton/lib/noUiSlider/jquery.nouislider.css" rel="stylesheet">
<link href="/Skeleton/css/search.css" rel="stylesheet">
<link rel="stylesheet" href="/Skeleton/lib/datepicker-1.3.0/css/datepicker.css" />
<link rel="stylesheet" href="/Skeleton/lib/datepicker-1.3.0/css/datepicker3.css" />
<script src="/Skeleton/lib/datepicker-1.3.0/js/bootstrap-datepicker.js"></script>

<div class="panel" style="background-color: #fafafa; padding: 5pt; 	border: 1px solid; border-color: #f0f0f0;">
	
	<h2 class="form-heading">Search</h2>
	<c:if test="${not empty message}">
		<div class="alert alert-success" role="alert">
			${message}
		</div>
	</c:if>
	
	<br>
	<br>
	<form:form method="post" modelAttribute="searchForm" id="searchForm" cssClass="form-horizontal" autocomplete="off">
		<fieldset>
			<div class="form-group">
				<div class="col-md-2 col-lg-1" style="height:60px; " align="center">
					<label for="field-price">Rent</label>
				</div>
				<div class="col-md-3 col-lg-4" style="height:30px;">
					<div style="width:100%; padding-left:50px; padding-right:50px;">
						<div id="priceSlider"  ></div>
					</div>
				</div>
				<div class="col-md-1"></div>
				<div class="col-md-2" style="height:60px;" align="center">
					<label for="field-rooms">Room Size</label>
				</div>
				<div class="col-md-3 col-lg-4">
					<div style="width:100%; padding-left:50px; padding-right:50px;">
						<div id="roomSizeSlider"  ></div>
					</div>
				</div>
			</div>				
			<br>

			<div id="advancedSearchContainer">
			
				<div class="row">
					<div class="col-md-2">
						<label class="control-label" for="field-dateIn">Add. cost max</label>
					</div>
					<div class="col-md-3">
						<form:input class="form-control" path="addCostMax" id="field-dateIn" placeholder="1000" tabindex="5" maxlength="35"/>
					</div>						
					<div class="col-md-2 col-md-offset-1">
						<label class="control-label" for="field-city">City</label>
					</div>
						
					<div class="col-md-3">
						<form:input class="form-control" path="city" id="field-city" placeholder="City" tabindex="5" maxlength="35"/>
					</div>	
					
					<div class="col-md-2">
						<label class="control-label" for="field-city">Earliest Move In Date</label>
					</div>
					<div class="col-md-3">
						<form:input style="background-color:white" class="datepicker form-control" path="earliestMoveInDate" maxlength="10" placeholder="DD.MM.YYYY"/>
					</div>	
					<div class="col-md-2 col-md-offset-1">
						<label class="control-label" for="field-city">Latest Move In Date</label>
					</div>
					<div class="col-md-3">
						<form:input readonly="true" style="background-color:white" class="datepicker form-control" path="latestMoveInDate" maxlength="10" placeholder="DD.MM.YYYY or empty"/>
					</div>	
					
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
				</div>
			</div>
			
			<form:input type="hidden" path="priceMin" id="field-priceMin" maxlength="45"/>
			<form:input type="hidden" path="priceMax" id="field-priceMax" maxlength="45"/>
			<form:input type="hidden" path="roomSizeMin" id="field-roomSizeMin" maxlength="45"/>
			<form:input type="hidden" path="roomSizeMax" id="field-roomSizeMax" maxlength="45"/>
			
			
			<div class="pull-right">
				<button type="button" class="btn btn btn-default" id="advancedSearchBtn"><span class="glyphicon glyphicon-plus"></span> Advanced Search</button>
				<button type="submit" onclick="javascript: form.action='search';" value="searched" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> Search</button>
				<button type="submit" onclick="javascript: form.action='saveSearch';" id="save" class="btn btn-info"><span class="glyphicon glyphicon-floppy-save"></span> Save</button>
					
			</div>
		</fieldset>
	</form:form>
</div>	

<div role="tabpanel">
	<ul class="nav nav-tabs" role="tablist" id="myTab">
	  	<li role="presentation"><a href="#listTab"><span class="glyphicon glyphicon-list"></span> List</a></li>
	  	<li role="presentation"><a href="#mapTab"><span class="glyphicon glyphicon-globe"></span> Map</a></li>
	</ul>
	<br>	
	<div class="tab-content">
		<div role="tabpanel" class="tab-pane active" id="mapTab">
			<div class="well well-sm"><div id="map"></div></div>
		</div>
				
			<div role="tabpanel" class="tab-pane"  id="listTab">
				<div class="container">
				<div class="row">
				<c:forEach items="${searchResults}" var="ad">
				<div class="col-sm-4 col-md-3">
					<div class="panel panel-primary" onclick="javascript:location.href='ad?id=${ad.id}'">
						<div class="panel-heading" style="min-height: 70px; max-height: 70px"><h5>${ad.title}</h5></div>
						<div class="panel-body" style="min-height: 300px; max-height: 300px" >
							<a style="padding:1em;" >
							<c:if test="${not empty ad.pictures}">
								<c:forEach items="${ad.pictures}" varStatus="loopCount" var="pic">
									<c:if test="${loopCount.count eq 1}">
										<img width="150px;" class="gallery" src="/Skeleton/img/ad/${pic.fileName}" />
									</c:if>
								</c:forEach>
							</c:if>
							<c:if test="${empty ad.pictures}">
								<img width="150px" class="gallery" src="/Skeleton/img/Placeholder.png" />
							</c:if>
							
								
						  	</a>
						  	<p style="padding:1em;">${ad.description}</p>
						</div>
								
						<div class="panel-footer" style="min-height: 80px; max-height: 80px"><b>Area: </b>${ad.city},  <b>Price:</b> CHF ${ad.rent},  <b>Room Size:</b> ${ad.roomSize}m²,  <b>Posted: </b>${ad.postingDateFormatted}</div>
					</div>
				</div>
				</c:forEach>
				</div>
			</div>
		</div>
	</div>
</div>
	
<div id="tabid"></div>	
<script src="/Skeleton/lib/noUiSlider/jquery.nouislider.js"></script>
<script src="/Skeleton/lib/noUiSlider/jquery.liblink.js"></script>
<script src="/Skeleton/lib/noUiSlider/wNumb.js"></script>	
<script src="/Skeleton/js/bootstrap.min.js"></script>
<script src="/Skeleton/lib/leaflet-0.7.3/leaflet.js"></script>
<script src="/Skeleton/lib/leaflet-0.7.3/leaflet.markercluster.js"></script>
<script src="/Skeleton/lib/leaflet-0.7.3/Control.Geocoder.js"></script>
	
<script>
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
		$(this).html(
			'<strong>Max: </strong>' +
			'<span>' + value + '</span>'
		);
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
		$(this).html(
			'<strong>Max: </strong>' +
			'<span>' + value + '</span>'
		);			
	});
	$(document).ready(function(){		
		$("#priceSlider").val(["${searchAttributes.priceMin}","${searchAttributes.priceMax}"]);
		$("#roomSizeSlider").val(["${searchAttributes.roomSizeMin}","${searchAttributes.roomSizeMax}"]);
		$("#field-city").val("${searchAttributes.city}");
	
		$('#advancedSearchContainer').hide();
		$('#save').hide();		
	});		
</script>	

<script>
	// create a map in the "map" div, set the view to a given place and zoom
	var map = L.map('map', {center: [46.9467726,7.4442328], zoom: 8});
	L.Control.geocoder().addTo(map);
	L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
	    attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
	}).addTo(map);
	
	var markers = L.markerClusterGroup({ 
		iconCreateFunction: function(cluster) {

		      var count = cluster.getChildCount();

		      var digits = (count+'').length;


		      return new L.DivIcon({
		        html: count,
		        className:'cluster digits-'+digits,
		        iconSize: null
		      });
		},
		spiderfyOnMaxZoom: true, showCoverageOnHover: true, zoomToBoundsOnClick: true });
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
		L.marker([lat,lng]).addTo(map).bindPopup(shortDescriptoin);
	};

	// center marker on click
	map.on('popupopen', function(e) {
	    var px = map.project(e.popup._latlng);
	    px.y -= e.popup._container.clientHeight/2
	    map.panTo(map.unproject(px),{animate: true});
	});		
</script>

<script>
	$('#advancedSearchBtn').click(function(){
		$('#advancedSearchBtn').toggleClass('active');
		if ($('#advancedSearchBtn').hasClass('active')) {
			$('#advancedSearchContainer').show(200);
			$('#save').show(200);
			showAdvSearch = true;
		} else {
			$('#advancedSearchContainer').hide(200);
			$('#save').hide(200);
			showAdvSearch = false;
		}
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
</script>

<c:if test="${not empty searchResults}">
	<c:forEach var="ad" items="${searchResults}">
		<c:forEach items="${ad.pictures}" varStatus="loopCount" var="pic">
			<c:if test="${loopCount.count eq 1}">
				<script type="text/javascript">
					populate("${ad.lat}","${ad.lng}","	<a class='pull-left' ><img class='media-object' src='/Skeleton/img/ad/${pic.fileName}' height='80px'></a><b>${ad.title}</b> <br /> ${ad.street} ${ad.houseNr} <br /> ${ad.city} ${ad.zip} <br /> <a href='ad?id=${ad.id}'>open</a>");
				</script>
			</c:if>
		</c:forEach>
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
