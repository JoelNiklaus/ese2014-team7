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

<div class="container">
	<div class='col-sm-6'>
		<label>Enquiry Message</label>
	</div>
</div>
<div class="container">
	<div class='col-sm-6'>
		${enquiry.messageText}
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
		    <div class="col-sm-6" >
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
		    <div class="col-sm-6">
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
		    <div class='col-sm-6'>
				<form:textarea class="form-control" path="comment"/>
			</div>
		</div>
		        
		<br />
		<div class="form-actions">
			<div class='col-sm-6'>
				<a class="pull-right">
					<button type="submit" class="btn btn-primary">Invite</button>
				</a>
			</div>
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
