<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:import url="template/header.jsp" />

<h1>My Enquiries</h1>

<h2>Manage</h2>
	<div class="panel panel-primary">
		
			<div class="panel-heading"><h5>${enquiry.ad.title}</h5></div>
			<div class="panel-body" >
				<a class="pull-left" >
		    		<img class="media-object" src="/Skeleton/img/<c:out value="${enquiry.ad.street}${enquiry.ad.houseNr}.jpeg"/>" height="100px">
		  		</a>
		  		<p>${enquiry.messageText}</p>
		  		  		
			  	<form:form class="form-horizontal" method="post" modelAttribute="ratingForm" action="submitRating" id="ratingForm" autocomplete="off">
				 	<fieldset>
				 		<div class="control-group<c:if test="${not empty descriptionErrors}"> error</c:if>">
				 			<form:hidden path="enquiryId" id="field-enquiry"/>
				 		
							<label class="control-label" for="field-description">Rate this enquiry from 1 to 10</label>
							<div class="controls">
								<form:radiobuttons path="rating" items="${numberList}"/>
								<form:errors path="rating" cssClass="help-inline" element="span" />
							</div>
						</div>
					
						<div class="form-actions">
							<button class="btn btn-primary" type="submit"><span class="glyphicon glyphicon-floppy-save"></span> Rate</button>
						</div>
				 	</fieldset>
	 		</form:form>
		  		
		  		
		  		
			</div>		
			
			<div class="panel-footer"><b>My rating:</b> ${enquiry.rating}/10</div>
		</div>
<c:import url="template/footer.jsp" />