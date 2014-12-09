<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:import url="template/header.jsp" />
<link href="/Skeleton/lib/kartik-v-bootstrap-star-rating/css/star-rating.min.css" media="all" rel="stylesheet" type="text/css" />
<script src="/Skeleton/lib/kartik-v-bootstrap-star-rating/js/star-rating.min.js" type="text/javascript"></script>
<h1>My Enquiries</h1>

<c:if test="${empty unreadEnquiries and empty newReceivedEnquiries and empty ratedReceivedEnquiries and empty sentEnquiries}">
	<div class="jumbotron">
		<p>
			You have not sent or received any enquiries yet. If you want to find apartments, studios, houses etc, you can here <a href="search">search ads</a>.
			When you have found an interesting one you can contact the ad placer by sending an enquiry.
		</p>
	</div>
</c:if>

	<c:if test="${not empty message}">
		<div class="alert alert-success" role="alert">
			${message}
		</div>
	</c:if>

	<h2>Inbox</h2>
	<c:forEach items="${unreadEnquiries}" var="enquiry" >
		<div class="panel panel-info" onclick="javascript:location.href='createVisitAppointment?enquiryId=${enquiry.enquiryId}'">
				
			<div class="panel-heading">
				<b>${enquiry.sender.firstName } ${enquiry.sender.lastName }</b><h5>${enquiry.ad.title}</h5>
			</div>
			<div class="panel-body" >
				<a class="pull-left" style="padding:1em;" >
					<c:if test="${not empty enquiry.sender.profileImage}">
						<img width="100px" class="gallery" src="/Skeleton/img/ad/${enquiry.sender.profileImage.fileName}" />
					</c:if>
					<c:if test="${empty enquiry.sender.profileImage}">
						<img width="100px" class="gallery" src="/Skeleton/img/Profile_Placeholder.jpg" />
					</c:if>
				</a>
			  	<p  style="padding:1em;">${enquiry.messageText}</p>
			</div>		
			<div class="panel-footer" style="height:55px">
				<b>This enquiry has not yet been rated</b> (click on enquiry to rate it)
				<a class="btn btn-danger btn-xs pull-right" href="removeEnquiry?id=${enquiry.enquiryId}"><span class="glyphicon glyphicon-remove"></span>delete</a>
				<a class="btn btn-default pull-right" href="createVisitAppointment?enquiryId=${enquiry.enquiryId}">Send Invitation</a>
			</div>
		</div>
	</c:forEach>


	<c:forEach items="${newReceivedEnquiries}" var="enquiry" >
		<div class="panel panel-default" onclick="javascript:location.href='createVisitAppointment?enquiryId=${enquiry.enquiryId}'">
			
			<div class="panel-heading">
				<b>${enquiry.sender.firstName } ${enquiry.sender.lastName }</b><h5>${enquiry.ad.title}</h5>
			</div>
			<div class="panel-body" >
				<a class="pull-left" style="padding:1em;" >
					<c:if test="${not empty enquiry.sender.profileImage}">
						<img width="100px" class="gallery" src="/Skeleton/img/ad/${enquiry.sender.profileImage.fileName}" />
					</c:if>
					<c:if test="${empty enquiry.sender.profileImage}">
						<img width="100px" class="gallery" src="/Skeleton/img/Profile_Placeholder.jpg" />
					</c:if>
				</a>
		  		<p style="padding:1em;">${enquiry.messageText}</p>
	  		</div>		
			
			<div class="panel-footer" style="height:55px">
				<b>This enquiry has not yet been rated</b>(click on enquiry to rate it) 
				<a class="btn btn-danger btn-xs pull-right" href="removeEnquiry?id=${enquiry.enquiryId}"><span class="glyphicon glyphicon-remove"></span>delete</a>
				<a class="btn btn-default pull-right" href="createVisitAppointment?enquiryId=${enquiry.enquiryId}">Send Invitation</a>
			</div>
		</div>
	</c:forEach>
	
	<c:forEach items="${ratedReceivedEnquiries}" var="enquiry" varStatus="loop">
		<div class="panel panel-default" onclick="javascript:location.href='createVisitAppointment?enquiryId=${enquiry.enquiryId}'">
			
				<div class="panel-heading">
					<b>${enquiry.sender.firstName } ${enquiry.sender.lastName }</b> ${enquiry.ad.title}
				</div>
				<div class="panel-body" >
					<a class="pull-left" style="padding:1em;">
					<c:if test="${not empty enquiry.sender.profileImage}">
						<img width="100px" class="gallery" src="/Skeleton/img/ad/${enquiry.sender.profileImage.fileName}" />
					</c:if>
					<c:if test="${empty enquiry.sender.profileImage}">
						<img width="100px" class="gallery" src="/Skeleton/img/Profile_Placeholder.jpg" />
					</c:if>
			  		</a>
			  		<p style="padding:1em;">${enquiry.messageText}</p>
				</div>		
				
				<div class="panel-footer">
					<div class="row">
						<div class='col-sm-6'>
							<input id="ratingStars${loop.index}" type="number" value="${enquiry.rating}" class="rating" data-size="xxs" />
							<script>
								$("#ratingStars${loop.index}").rating("refresh", {disabled: true, showCaption: false, showClear: false,min: "0", max:"5", step:"1"});
							</script>
						</div>
						<div class='col-sm-6'>
							<a class="btn btn-danger btn-xs pull-right" href="removeEnquiry?id=${enquiry.enquiryId}"><span class="glyphicon glyphicon-remove"></span>delete</a>
						</div>
					</div>
				</div>
			</div>
	</c:forEach>
	
	<h2>Sent</h2>
	<p>${sentEmpty}</p>
	
	<c:set var="class" value="panel-default"/>
	<c:set var="state" value="no invitation"/>
	<c:forEach items="${sentEnquiries}" var="enquiry" varStatus="loop">
		<c:if test="${not empty enquiry.visitAppointments }">
			<c:set var="class" value="panel-default"/>
			<c:set var="state" value="no invitation"/>
		</c:if>
		<c:forEach items="${enquiry.visitAppointments }" var="visitAppointment">
			<c:if test="${visitAppointment.state eq 'NEW' }">
				<c:set var="class" value="panel-info"/>
				<c:set var="state" value="new invitation"/>
			</c:if>
		</c:forEach> 
		<c:forEach items="${enquiry.visitAppointments }" var="visitAppointment">
			<c:if test="${visitAppointment.state eq 'ACCEPTED' }">
				<c:set var="class" value="panel-success"/>
				<c:set var="state" value="<b>Date: </b>${visitAppointment.startDate }"/>
			</c:if>
		</c:forEach>  
		<div class="panel ${class }" onclick="javascript:location.href='manageInvitationRequests?enquiryId=${enquiry.enquiryId}'">
			<div class="panel-heading">
				${enquiry.ad.title} <i class="pull-right">${state }</i>
			</div>
			<div class="panel-body" >
				<a class="pull-left" style="padding:1em;" >
			    	<c:forEach items="${enquiry.ad.pictures}" varStatus="loopCount" var="pic">
			    		<c:if test="${loopCount.count eq 1}">
							<img width="100px" class="gallery" src="/Skeleton/img/ad/${pic.fileName}"/>
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