<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<c:import url="template/header.jsp" />


<h1>Create Ad</h1>

<form:form method="post" modelAttribute="adForm" action="createAd"
	id="adForm" cssClass="form-horizontal" autocomplete="off">
	<fieldset>

		<legend>General Information</legend>

		<c:set var="titleErrors">
			<form:errors path="title" />
		</c:set>
		<div
			class="control-group<c:if test="${not empty titleErrors}"> error</c:if>">
			<label class="control-label" for="field-title">Title</label>

			<div class="controls">
				<form:input path="title" id="field-title" tabindex="1"
					maxlength="160" placeholder="Descriptive title" />
				<form:errors path="title" cssClass="help-inline" element="span" />
			</div>
		</div>

		<legend>Address</legend>

		<div class="control-group">
			<label class="control-label" for="field-street">Street</label>
			<div class="controls">
				<form:input path="street" id="field-street" tabindex="1"
					maxlength="50" placeholder="Street" />
				<form:errors path="street" cssClass="help-inline" element="span" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="field-houseNr">House Nr.</label>
			<div class="controls">
				<form:input path="houseNr" id="field-houseNr" tabindex="3"
					maxlength="10" placeholder="House Nr." />
				<form:errors path="houseNr" cssClass="help-inline" element="span" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="field-zip">ZIP</label>
			<div class="controls">
				<form:input path="zip" id="field-zip" tabindex="4" maxlength="4"
					placeholder="ZIP" />
				<form:errors path="zip" cssClass="help-inline" element="span" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="field-city">City</label>
			<div class="controls">
				<form:input path="city" id="field-city" tabindex="5" maxlength="50"
					placeholder="City" />
				<form:errors path="city" cssClass="help-inline" element="span" />
			</div>
		</div>

		<legend>Costs</legend>

		<c:set var="rentErrors">
			<form:errors path="rent" />
		</c:set>
		<div
			class="control-group<c:if test="${not empty rentErrors}"> error</c:if>">
			<label class="control-label" for="field-rent">Rent</label>
			<div class="controls">
				<form:input path="rent" id="field-rent" tabindex="6" maxlength="6"
					placeholder="0" />
				<form:errors path="rent" cssClass="help-inline" element="span" />
			</div>
		</div>

		<c:set var="addCostErrors">
			<form:errors path="addCost" />
		</c:set>
		<div
			class="control-group<c:if test="${not empty additionalCostsErrors}"> error</c:if>">
			<label class="control-label" for="field-addCost">Additional
				Costs</label>
			<div class="controls">
				<form:input path="addCost" id="field-rent" tabindex="7"
					maxlength="6" placeholder="0" />
				<form:errors path="addCost" cssClass="help-inline" element="span" />
			</div>
		</div>

		<legend>Availability</legend>

		<c:set var="dateInErrors">
			<form:errors path="dateIn" />
		</c:set>
		<div
			class="control-group<c:if test="${not empty dateInErrors}"> error</c:if>">
			<label class="control-label" for="field-dateIn">Move In Date</label>
			<div class="controls">
				<form:textarea path="dateIn" id="field-dateIn" tabindex="8"
					maxlength="10" placeholder="DD.MM.YYYY" />
				<form:errors path="dateIn" cssClass="help-inline" element="span" />
			</div>
		</div>

		<c:set var="dateOutErrors">
			<form:errors path="dateOut" />
		</c:set>
		<div
			class="control-group<c:if test="${not empty dateInErrors}"> error</c:if>">
			<label class="control-label" for="field-dateOut">Move Out
				Date</label>
			<div class="controls">
				<form:textarea path="dateOut" id="field-dateOut" tabindex="9"
					maxlength="10" placeholder="DD.MM.YYYY or leave empty if unknown" />
				<form:errors path="dateOut" cssClass="help-inline" element="span" />
			</div>
		</div>

		<legend>Additional Information</legend>

		<c:set var="typeErrors">
			<form:errors path="type" />
		</c:set>
		<div
			class="control-group<c:if test="${not empty typeErrors}"> error</c:if>">
			<label class="control-label" for="field-adType">Type</label>
			<div class="controls">
				<form:select path="type" id="field-adType" tabindex="10">
					<option value="House">House</option>
					<option value="Apartment">Apartment</option>
				</form:select>
			</div>
		</div>

		<c:set var="roomSizeErrors">
			<form:errors path="roomSize" />
		</c:set>
		<div
			class="control-group<c:if test="${not empty roomSizeErrors}"> error</c:if>">
			<label class="control-label" for="field-description">Room
				Size</label>
			<div class="controls">
				<form:textarea path="roomSize" id="field-roomSize" tabindex="11"
					maxlength="3" placeholder="Size of the room in m2" />
				<form:errors path="roomSize" cssClass="help-inline" element="span" />
			</div>
		</div>

		<c:set var="descriptionErrors">
			<form:errors path="description" />
		</c:set>
		<div
			class="control-group<c:if test="${not empty descriptionErrors}"> error</c:if>">
			<label class="control-label" for="field-description">Description</label>
			<div class="controls">
				<form:textarea path="description" id="field-description"
					tabindex="12" maxlength="255" placeholder="Description of the Room" />
				<form:errors path="description" cssClass="help-inline"
					element="span" />
			</div>
		</div>

		<c:set var="usErrors">
			<form:errors path="us" />
		</c:set>
		<div
			class="control-group<c:if test="${not empty usErrors}"> error</c:if>">
			<label class="control-label" for="field-us">We are</label>
			<div class="controls">
				<form:textarea path="us" id="field-us" tabindex="13" maxlength="255"
					placeholder="Write about you and your roomies" />
				<form:errors path="us" cssClass="help-inline" element="span" />
			</div>
		</div>

		<c:set var="youErrors">
			<form:errors path="you" />
		</c:set>
		<div
			class="control-group<c:if test="${not empty youSizeErrors}"> error</c:if>">
			<label class="control-label" for="field-you">Ideal Roomie</label>
			<div class="controls">
				<form:textarea path="you" id="field-you" tabindex="14"
					maxlength="255" placeholder="My/Our ideal roomie should..." />
				<form:errors path="you" cssClass="help-inline" element="span" />
			</div>
		</div>

		<legend>Images</legend>



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
