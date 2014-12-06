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
				<a class="btn btn-danger btn-xs pull-right" href="removeVisitAppointment?id=${visitAppointment.id}&enquiryId=${enquiry.enquiryId}"><span class="glyphicon glyphicon-remove"></span>delete</a>
			</div>
		</c:forEach>
	</div>
</div>
<br />
<div style="background-color:#f1f1f1; padding:1em; border: 1px solid; border-radius: 5px; border-color:#aaaaaa">
	<div class="container">
		<div class='col-sm-6'>
			<h4>Send New Invitation</h4>
		</div>
	</div>
	<form:form method="post" action="createVisitAppointment" modelAttribute="visitAppointmentForm">
		<fieldset>
			<div class="container">
			    <div class="row">
			        <div class='col-sm-6'>
			            <div class="form-group">
							<form:input class="form-control" path="enquiryId" type="hidden"/>
			            </div>
			        </div>
			 
			    </div>
			</div>
			<div class="container">
			    <div class="col-sm-11" >
			       <div class='col-md-6'>
			            <div class="form-group">
			                <div >
								<label>Start Date</label>
			                </div>
			            </div>
			        </div>
			        <div class='col-md-6'>
			            <div class="form-group">
			                <div >
			                	<label>End Date</label>
			                </div>
			            </div>
			        </div>
			    </div>
			</div>
			<div class="container">
			    <div class="col-sm-11">
			       <div class='col-md-6'>
			            <div class="form-group">
			                <div class='input-group date' id='startDatePicker'>
			                    <form:input class="form-control"  path="startDate" maxlength="35"/>
			                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
			                    </span>
			                </div>
			            </div>
			        </div>
			        <div class='col-md-6'>
			            <div class="form-group">
			                <div class='input-group date' id='endDatePicker'>
			                    <form:input class="form-control"  path="endDate" maxlength="35"/>
			                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
			                    </span>
			                </div>
			            </div>
			        </div>
			    </div>
			    <script type="text/javascript">
			        $(function () {
			            $('#startDatePicker').datetimepicker({
			            	language: 'de',
			            	todayBtn: "linked",
			            	calendarWeeks: true,
			            	todayHighlight: true
			            });
			            $('#endDatePicker').datetimepicker({
			            	language: 'de',
			            	todayBtn: "linked",
			            	calendarWeeks: true,
			            	todayHighlight: true
			            });
			            $("#startDatePicker").on("dp.change",function (e) {
			               $('#endDatePicker').data("DateTimePicker").setMinDate(e.date);
			            });
			            $("#endDatePicker").on("dp.change",function (e) {
			               $('#startDatePicker').data("DateTimePicker").setMaxDate(e.date);
			            });
			        });
			    </script>
			</div>
			<div class="container">
			    <div class='col-sm-6'>
					<label>Invitation Message</label>
				</div>
			</div>
			<div class="container">
			    <div class='col-sm-11'>
					<form:textarea class="form-control" path="comment"/>
				</div>
			</div>
	        
			<br />
			<div class="form-actions">
				<div class='col-sm-11'>
					<a class="pull-right">
						<button type="submit" class="btn btn-primary">Invite</button>
					</a>
				</div>
			</div>
		</fieldset>
	</form:form>
</div>	

<c:if test="${page_error != null }">
	<div class="alert alert-error">
		<button type="button" class="close" data-dismiss="alert">&times;</button>
		<h4>Error!</h4>
		${page_error}
	</div>
</c:if>
<c:import url="template/footer.jsp" />
