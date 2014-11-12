<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<c:import url="template/header.jsp" />

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

<h1>Create Ad</h1>

<form:form method="post" modelAttribute="adForm" action="createAd" id="adForm" cssClass="form-horizontal" autocomplete="on" enctype="multipart/form-data">
	<fieldset>

		<legend>General Information</legend>

		<label class="control-label" for="field-title">Title</label>
		<form:input class="form-control" path="Title" tabindex="1" maxlength="50" placeholder="Title"/>
		<form:errors path="title" cssClass="help-inline" element="span"/>
		<br>

		<legend>Address</legend>
		
		<label class="control-label" for="field-street">Street</label>
		<form:input class="form-control" path="street" tabindex="2" maxlength="50" placeholder="Street"/>
		<form:errors path="street" cssClass="help-inline" element="span"/>
		
		<label class="control-label" for="field-houseNr">House Nr.</label>
		<form:input class="form-control" path="houseNr" tabindex="3" maxlength="50" placeholder="House Nr."/>
		<form:errors path="street" cssClass="help-inline" element="span"/>
		
		<label class="control-label" for="field-zip">ZIP</label>
		<form:input class="form-control" path="zip" tabindex="4" maxlength="4" placeholder="ZIP"/>
		<form:errors path="zip" cssClass="help-inline" element="span"/>

		
		<label class="control-label" for="field-city">City</label>
		<form:input class="form-control" path="city" tabindex="5" maxlength="50" placeholder="City"/>
		<form:errors path="city" cssClass="help-inline" element="span"/>
		<br>

		<legend>Costs</legend>

		<label class="control-label" for="field-rent">Rent</label>
		<form:input class="form-control" path="rent" tabindex="6" maxlength="4" placeholder="Rent"/>
		<form:errors path="rent" cssClass="help-inline" element="span"/>
		
		<label class="control-label" for="field-addCost">Additional Cost</label>
		<form:input class="form-control" path="addCost" tabindex="7" maxlength="3" placeholder="Additional Cost"/>
		<form:errors path="addCost" cssClass="help-inline" element="span"/>
		<br>

		<legend>Availability</legend>

		<label class="control-label" for="field-dateIn">Move In Date</label>
		<form:input class="form-control" path="dateIn" tabindex="8" maxlength="10" placeholder="DD.MM.YYYY"/>
		<form:errors path="dateIn" cssClass="help-inline" element="span"/>
		
		<label class="control-label" for="field-dateOut">Move Out Date</label>
		<form:input class="form-control" path="dateOut" tabindex="9" maxlength="10" placeholder="DD.MM.YYYY or empty"/>
		<form:errors path="dateOut" cssClass="help-inline" element="span"/>
		<br>

		<legend>Additional Information</legend>

		<label class="control-label" for="field-roomSize">Room Size</label>
		<form:input class="form-control" path="roomSize" tabindex="10" maxlength="3" placeholder="Room Size in m2"/>
		<form:errors path="roomSize" cssClass="help-inline" element="span"/>
		
		<label class="control-label" for="field-description">Description</label>
		<form:input class="form-control" path="description" tabindex="11" maxlength="255" placeholder="Description of the room and the flat."/>
		<form:errors path="description" cssClass="help-inline" element="span"/>

		<label class="control-label" for="field-us">We are...</label>
		<form:input class="form-control" path="us" tabindex="12" maxlength="255" placeholder="Write about you and your roomies."/>
		<form:errors path="us" cssClass="help-inline" element="span"/>
		
		<label class="control-label" for="field-you">Ideal Roomie</label>
		<form:input class="form-control" path="you" tabindex="13" maxlength="255" placeholder="My/Our ideal roomie should be.."/>
		<form:errors path="you" cssClass="help-inline" element="span"/>

		<legend>Images</legend>

		<table border="1" cellpadding="5" width="700">
				<tr>
					<label for="image"> Image (in JPEG format only, and max 5MB):</label>
					<input name="image" type="file" />
					</br>
				</tr>
				<!--
				<tr>
					<label for="image"> Ad Image 1 (in JPEG format only, and
						max 700kb):</label>
					<input name="image" type="file" />
					</br>
				</tr>
				<tr>
					<label for="image">Ad Image 2 (in JPEG format only, and max
						700kb):</label>
					<input name="image" type="file" />
					</br>
				</tr>
				<tr>
					<label for="image">Ad Image 3 (in JPEG format only, and max
						700kb):</label>
					<input name="image" type="file" />
					</br>
				</tr>
				-->

			</table>

		<div class="form-actions">
			<button type="submit" class="btn btn-primary">Create Ad</button>
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


<c:import url="template/footer.jsp" />
