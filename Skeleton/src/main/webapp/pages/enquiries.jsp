<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:import url="template/header.jsp" />

<h1>My Enquiries</h1>

	<c:if test="${not empty message}">
		<div class="alert alert-success" role="alert">
			${message}
		</div>
	</c:if>

	<h2>Inbox</h2>
	<c:forEach items="${unreadEnquiries}" var="enquiry">
		<div class="panel panel-info" onclick="javascript:location.href='rateEnquiry?id=${enquiry.enquiryId}'">
				
			<div class="panel-heading">
				<h5>${enquiry.ad.title}</h5>
			</div>
			<div class="panel-body" >
				<a class="pull-left" style="padding:1em;" >
					<c:forEach items="${enquiry.ad.pictures}" varStatus="loopCount" var="pic">
						<c:if test="${loopCount.count eq 1}">
							<img width="150px" class="gallery" src="/Skeleton/img/ad/${pic.fileName}" />
						</c:if>
					</c:forEach>
				</a>
			  	<p  style="padding:1em;">${enquiry.messageText}</p>
			</div>		
			<div class="panel-footer" style="height:55px">
				<b>This enquiry has not yet been rated</b> (click on enquiry to rate it)
				<a class="btn btn-danger pull-right" href="removeEnquiry?id=${enquiry.enquiryId}"><span class="glyphicon glyphicon-remove"></span>delete</a>
				<a class="btn btn-default pull-right" href="createVisitAppointment?enquiryId=${enquiry.enquiryId}">Send Invitation</a>
			</div>
		</div>
	</c:forEach>


	<c:forEach items="${newReceivedEnquiries}" var="enquiry">
		<div class="panel panel-default" onclick="javascript:location.href='rateEnquiry?id=${enquiry.enquiryId}'">
			
			<div class="panel-heading">
				<h5>${enquiry.ad.title}</h5>
			</div>
			<div class="panel-body" >
				<a class="pull-left" style="padding:1em;" >
				<c:forEach items="${enquiry.ad.pictures}" varStatus="loopCount" var="pic">
					<c:if test="${loopCount.count eq 1}">
						<img width="150px" class="gallery" src="/Skeleton/img/ad/${pic.fileName}" />
					</c:if>
				</c:forEach>
				</a>
		  		<p style="padding:1em;">${enquiry.messageText}</p>
	  		</div>		
			
			<div class="panel-footer" style="height:55px">
				<b>This enquiry has not yet been rated</b>(click on enquiry to rate it) 
				<a class="btn btn-danger pull-right" href="removeEnquiry?id=${enquiry.enquiryId}"><span class="glyphicon glyphicon-remove"></span>delete</a>
				<a class="btn btn-default pull-right" href="createVisitAppointment?enquiryId=${enquiry.enquiryId}">Send Invitation</a>
			</div>
		</div>
	</c:forEach>
	
	<c:forEach items="${ratedReceivedEnquiries}" var="enquiry">
		<div class="panel panel-default" onclick="javascript:location.href='rateEnquiry?id=${enquiry.enquiryId}'">
			
				<div class="panel-heading">
					<h5>${enquiry.ad.title}</h5>
				</div>
				<div class="panel-body" >
					<a class="pull-left" >
			    		<c:forEach items="${enquiry.ad.pictures}" varStatus="loopCount" var="pic">
							<c:if test="${loopCount.count eq 1}">
								<img width="150px" class="gallery" src="/Skeleton/img/ad/${pic.fileName}"/>
							</c:if>
						</c:forEach>
			  		</a>
			  		<p>${enquiry.messageText}</p>
			  		
				</div>		
				
				<div class="panel-footer"
					><b>My rating:</b> ${enquiry.rating}/10
					<a class="btn btn-danger btn-xs pull-right" href="removeEnquiry?id=${enquiry.enquiryId}"><span class="glyphicon glyphicon-remove"></span>delete</a>
				</div>
			</div>
	</c:forEach>
	
	<h2>Sent</h2>
	<p>${sentEmpty}</p>
	<c:forEach items="${sentEnquiries}" var="enquiry">
		<div class="panel panel-default" onclick="javascript:location.href='ad?id=${enquiry.adId}'">
			
			<div class="panel-heading">
				<h5>${enquiry.ad.title}</h5>
			</div>
			<div class="panel-body" >
				<a class="pull-left" >
			    	<c:forEach items="${enquiry.ad.pictures}" varStatus="loopCount" var="pic">
			    		<c:if test="${loopCount.count eq 1}">
							<img width="150px" class="gallery" src="/Skeleton/img/ad/${pic.fileName}"/>
						</c:if>
					</c:forEach>
		  		</a>			  		
		  		<p>${enquiry.messageText}</p>
			</div>
				
			<div class="panel-footer" style="height:55px">
				<b>Price:</b> CHF ${enquiry.ad.rent}  <b>Room Size:</b> ${enquiry.ad.roomSize}m²
				<a class="btn btn-danger pull-right" href="removeEnquiry?id=${enquiry.enquiryId}"><span class="glyphicon glyphicon-remove"></span>delete</a>	
				<a class="btn btn-default pull-right" href="manageInvitationRequests?enquiryId=${enquiry.enquiryId}">Manage Received Invitations</a>			
			</div>
		</div>
	</c:forEach>

<c:import url="template/footer.jsp" />