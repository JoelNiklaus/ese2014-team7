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

<div style="background-color:#f1f1f1; padding:1em; border: 1px solid; border-radius: 5px; border-color:#aaaaaa">
	<div class="container">
		<div class='col-sm-6'>
			<h4>Enquiry Message</h4>
		</div>
	</div>
	<div class="container">
		<div class='col-sm-6'>
			${enquiry.messageText}
		</div>
	</div>
</div>
<br />

<div style="background-color:#f1f1f1; padding:1em; border: 1px solid; border-radius: 5px; border-color:#aaaaaa">
	<div class="container">
		<div class='col-sm-6'>
			<h4>Sent Invitations</h4>
		</div>
	</div>
	<div class="container">
		<c:forEach items="${enquiry.visitAppointments}" var="visitAppointment">
			<div class='col-sm-11' style="background-color:#ffffff; padding:1em; border: 1px solid; border-radius: 2px; border-color:#aaaaaa">
				${visitAppointment.state} from: ${visitAppointment.startDate} to:${visitAppointment.startDate} <br/>
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
