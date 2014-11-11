<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:import url="template/header.jsp" />

<h1>My Enquiries</h1>

<h2>Inbox</h2>
<c:forEach items="${receivedEnquiries}" var="enquiry">
	<div class="panel panel-primary" onclick="javascript:location.href='rateEnquiry?id=${enquiry.enquiryId}'">
		
			<div class="panel-heading"><h5>${enquiry.ad.title}</h5></div>
			<div class="panel-body" >
				<a class="pull-left" >
		    		<img class="media-object" src="/Skeleton/img/<c:out value="${enquiry.ad.street}${enquiry.ad.houseNr}.jpeg"/>" height="100px">
		  		</a>
		  		<p>${enquiry.messageText}</p>
		  		
			</div>		
			
			<div class="panel-footer"><b>My rating:</b> ${enquiry.rating}/10</div>
		</div>
</c:forEach>

<h2>Sent</h2>
<c:forEach items="${sentEnquiries}" var="enquiry">
	<div class="panel panel-primary" onclick="javascript:location.href='ad?id=${enquiry.adId}'">
		
			<div class="panel-heading"><h5>${enquiry.ad.title}</h5></div>
			<div class="panel-body" >
				<a class="pull-left" >
		    		<img class="media-object" src="/Skeleton/img/<c:out value="${enquiry.ad.street}${enquiry.ad.houseNr}.jpeg"/>" height="100px">
		  		</a>
		  		<p>${enquiry.messageText}</p>
			</div>
			
			<div class="panel-footer"><b>Price:</b> CHF ${enquiry.ad.rent}  <b>Room Size:</b> ${enquiry.ad.roomSize}m²</div>
		</div>
</c:forEach>

<c:import url="template/footer.jsp" />