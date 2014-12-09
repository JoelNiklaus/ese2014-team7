<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="template/header.jsp" />
<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<link rel="stylesheet" href="/Skeleton/lib/datetimepicker/css/bootstrap-datetimepicker.min.css" />
<script src="/Skeleton/lib/moment/moment.min.js"></script>
<script src="/Skeleton/lib/moment/locales.min.js"></script>
<script src="/Skeleton/lib/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>

<div style="background-color:#fbfbfb; padding:1em; border: 1px solid; border-radius: 5px; border-color:#aaaaaa">
	<div class="row">
		<div class="col-sm-1">
			<c:if test="${not empty enquiry.sender.profileImage}">
				<img width="80px" class="gallery" src="/Skeleton/img/ad/${enquiry.sender.profileImage.fileName}" />
			</c:if>
			<c:if test="${empty enquiry.sender.profileImage}">
				<img width="80px" class="gallery" src="/Skeleton/img/Profile_Placeholder.jpg" />
			</c:if>
		</div>
		<div class='col-sm-9'>
			<h4>Enquiry Message</h4>
			<p>${enquiry.messageText}</p>
		</div>
		<div class='col-sm-2'>
			<button class="btn btn-info" onclick="javascript:location.href='ad?id=${enquiry.adId}'"><span class="glyphicon glyphicon-tag"></span> view Ad</button>
			<button class="btn btn-info" onclick="javascript:location.href='otherProfileView?id=${receiver.id}'"><span class="glyphicon glyphicon-user"></span>  Prospects Profile</button>
		</div>
	</div>
</div>
<br />

<div style="background-color:#fbfbfb; padding:1em; border: 1px solid; border-radius: 5px; border-color:#aaaaaa">
	<div class="container">
		<div class='col-sm-6'>
			<h4>Sent Invitations</h4>
		</div>
	</div>
	<div class="container">
		<c:forEach items="${enquiry.visitAppointments}" var="visitAppointment">
			<div role="alert" class="col-sm-11 <c:if test="${ visitAppointment.state eq 'NEW'}">alert alert-info</c:if>
								<c:if test="${ visitAppointment.state eq 'ACCEPTED'}">alert alert-success</c:if>
								<c:if test="${ visitAppointment.state eq 'REJECTED'}">alert alert-danger</c:if>" >
				${visitAppointment.state} <br />
				from: ${visitAppointment.startDate} to:${visitAppointment.startDate} <br/>
				Message: ${visitAppointment.comment}
				<a class="btn btn-default btn-xs pull-right" href="setVisitAppointmentStateAccepted?id=${visitAppointment.id}&enquiryId=${enquiry.enquiryId}"><span class="glyphicon glyphicon-thumbs-up"></span>Accept</a>
				<a class="btn btn-default btn-xs pull-right" href="setVisitAppointmentStateRejected?id=${visitAppointment.id}&enquiryId=${enquiry.enquiryId}"><span class="glyphicon glyphicon-thumbs-down"></span>Decline</a>
			</div>
		</c:forEach>
	</div>
</div>
<br />
<a class="btn btn-default pull-right" href="enquiries">Back</a>



<c:if test="${page_error != null }">
	<div class="alert alert-error">
		<button type="button" class="close" data-dismiss="alert">&times;</button>
		<h4>Error!</h4>
		${page_error}
	</div>
</c:if>
<c:import url="template/footer.jsp" />
