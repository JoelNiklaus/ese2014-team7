<%@ page language="java" pageEncoding="UTF-8"
  	contentType="text/html;charset=utf-8"%>  
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
  <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
  <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>  


  <c:import url="template/header.jsp" />  

  <h1>Send Enquiry</h1>

  <div class="span1 offset1">Send an enquiry for the following object:</div> 
  
  <div class="panel panel-primary">
		
			<div class="panel-heading"><h5>${ad.title}</h5></div>
			<div class="panel-body" >
				<a class="pull-left" >
		    		<img class="media-object" src="/Skeleton/img/house1.jpeg" height="100px">
		  		</a>
		  		<p>${ad.description}</p>
				
			</div>
				
			<div class="panel-footer"><b>Price:</b> CHF ${ad.rent}  <b>Room Size:</b> ${ad.roomSize}m²</div>
  </div>

 <form:form class="form-horizontal" method="post" modelAttribute="enquiryForm" action="submitEnquiry" id="enquiryForm" autocomplete="off">
 
 	<fieldset>
 		<div class="control-group<c:if test="${not empty descriptionErrors}"> error</c:if>">
 			<label class="control-label" for="ascdas">Receiver ID:</label>
			<div class="controls">
				<form:textarea path="receiverId" id="field-rec-id" tabindex="2" maxlength="255" placeholder="ID" />
				<form:errors path="messageText" cssClass="help-inline" element="span" />
			</div>
 		
			<label class="control-label" for="field-description">Enter your enquiry message:</label>
			<div class="controls">
				<form:textarea path="messageText" id="field-message-text" tabindex="1" maxlength="255" placeholder="Enquiry Message" />
				<form:errors path="messageText" cssClass="help-inline" element="span" />
			</div>
		</div>
	
		<div class="form-actions">
			<button class="btn btn-primary" type="submit"><i class="glyphicon glyphicon-envelope"></i> SUBMIT</button>
		</div>
 	</fieldset>
 
 </form:form>

<c:import url="template/footer.jsp" />