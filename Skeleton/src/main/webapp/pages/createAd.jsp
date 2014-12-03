<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<c:import url="template/header.jsp" />



	<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
	<link href="/Skeleton/css/dropzone.css" type="text/css" rel="stylesheet" /> 
	
	<style type="text/css">
		div#map{
			width:100%;
			height:250px;
		}
	</style>
	
	<c:if test="${not empty error}">
		<div class="alert alert-danger" role="alert">
			${error}
		</div>
	</c:if>

<h1>Create Ad</h1>

<form:form method="post" modelAttribute="adForm" action="createAd" id="adForm" cssClass="form-horizontal" autocomplete="on" enctype="multipart/form-data">
	<fieldset>

		<legend>General Information</legend>

		<label class="control-label">Title</label>
		<form:input class="form-control" path="Title" tabindex="1" maxlength="50" placeholder="Title"/>
		<form:errors path="title" cssClass="help-inline" element="span"/>
		<br>

		<legend>Address</legend>
		
		<label class="control-label">Street</label>
		<form:input class="form-control" id="streetInput" path="street" onblur="geocode()" tabindex="2" maxlength="50" placeholder="Street"/>
		<form:errors path="street" cssClass="help-inline" element="span"/>
		
		<label class="control-label">House Nr.</label>
		<form:input class="form-control" id="houseNrInput" path="houseNr" onblur="geocode()" tabindex="3" maxlength="50" placeholder="House Nr."/>
		<form:errors path="houseNr" cssClass="help-inline" element="span"/>
		
		<label class="control-label">ZIP</label>
		<form:input class="form-control" id="zipInput" path="zip" onblur="geocode()" tabindex="4" maxlength="4" placeholder="ZIP"/>
		<form:errors path="zip" cssClass="help-inline" element="span"/>

		
		<label class="control-label">City</label>
		<form:input class="form-control" id="cityInput" path="city" onblur="geocode()" tabindex="5" maxlength="50" placeholder="City"/>
		<form:errors path="city" cssClass="help-inline" element="span"/>
		<br>
		
		<!-- Lat -->
		<form:input class="form-control" type="hidden" id="latInput" path="lat" maxlength="50"/>
		<!-- Lon -->
		<form:input class="form-control" type="hidden" id="lngInput" path="lng" maxlength="50"/>
		<br>
	
		<div class="well well-sm"><div id="map"></div></div>		

		<legend>Costs</legend>

		<label class="control-label">Rent</label>
		<form:input class="form-control" path="rent" tabindex="6" maxlength="4" placeholder="Rent"/>
		<form:errors path="rent" cssClass="help-inline" element="span"/>
		
		<label class="control-label">Additional Cost</label>
		<form:input class="form-control" path="addCost" tabindex="7" maxlength="3" placeholder="Additional Cost"/>
		<form:errors path="addCost" cssClass="help-inline" element="span"/>
		<br>
		
		<legend>Availability</legend>

		<link rel="stylesheet" href="/Skeleton/lib/datepicker-1.3.0/css/datepicker.css" />
		<link rel="stylesheet" href="/Skeleton/lib/datepicker-1.3.0/css/datepicker3.css" />
		<script src="/Skeleton/lib/datepicker-1.3.0/js/bootstrap-datepicker.js"></script>


		<label class="control-label">Move In Date</label>
		<form:input readonly="true" style="background-color:white" class="datepicker form-control" path="dateIn" tabindex="8" maxlength="10" placeholder="DD.MM.YYYY"/>
		<form:errors path="dateIn" cssClass="help-inline" element="span"/>
		
		<label class="control-label">Move Out Date</label>
		<form:input readonly="true" style="background-color:white" class="datepicker form-control" path="dateOut" tabindex="9" maxlength="10" placeholder="DD.MM.YYYY or empty"/>
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

		<label class="control-label">Room Size</label>
		<form:input class="form-control" path="roomSize" tabindex="10" maxlength="3" placeholder="Room Size in m2"/>
		<form:errors path="roomSize" cssClass="help-inline" element="span"/>
		
		<label class="control-label">Description</label>
		<form:textarea class="form-control" path="description" tabindex="11" maxlength="255" placeholder="are pets allowed?...is there a balcony?...parking?...smokers?"></form:textarea>
		<form:errors path="description" cssClass="help-inline" element="span"/>
		
		<label class="control-label">Distance To Public Transport</label>
		<form:input class="form-control" path="distanceToPublicTransport" tabindex="12" maxlength="4" placeholder="Distance in m."/>
		<form:errors path="distanceToPublicTransport" cssClass="help-inline" element="span"/>
		
		<label class="control-label">Distance To Mall or next Grocery Shopping Possibilities</label>
		<form:input class="form-control" path="distanceToShopping" tabindex="13" maxlength="4" placeholder="Distance in m."/>
		<form:errors path="distanceToShopping" cssClass="help-inline" element="span"/>
		
		<br>
		<legend>Social Informations</legend>

		<label class="control-label">We are...</label>
		<form:input class="form-control" path="us" tabindex="14" maxlength="255" placeholder="Write about you and your roomies."/>
		<form:errors path="us" cssClass="help-inline" element="span"/>
		
		<label class="control-label">Ideal Roomie</label>
		<form:input class="form-control" path="you" tabindex="15" maxlength="255" placeholder="My/Our ideal roomie should be.."/>
		<form:errors path="you" cssClass="help-inline" element="span"/>
		
		<form:input class="form-control" type="hidden" path="imageIds" name="files" id="files"/><br />

		<legend>Images</legend>
			<script src="/Skeleton/js/dropzone.min.js"></script>
			<div class="dropzone" id="file-dropzone"> 
			<div class="dz-message" data-dz-message><span>Click or Drag and Drop to this field to upload images</span></div>
			</div>
			<br />
			<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-floppy-save"></span> Save</button>
			<button type="reset" class="btn btn-warning"><span class="glyphicon glyphicon-remove"></span> Reset</button>

	</fieldset>
</form:form>


<script type="text/javascript" src="/Skeleton/js/ddupload.js"></script>


	<c:if test="${page_error != null }">
		<div class="alert alert-error">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<h4>Error!</h4>
			${page_error}
		</div>
	</c:if>



<c:import url="template/osmEditAd.jsp"/>
	
	

	
<c:import url="template/footer.jsp" />
