<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:import url="template/header.jsp" />

<h1>My Enquiries</h1>

<h2>Inbox</h2>
You have 0 enquiries.

<h2>Sent</h2>
<c:forEach items="${sentEnquiries}" var="enquiry">
	<div class="panel panel-primary">
		
			<div class="panel-heading"><h5>Enquiry</h5></div>
			<div class="panel-body" >
				<a class="pull-left" >
		    		<img class="media-object" src="/Skeleton/img/house1.jpeg" height="100px">
		  		</a>
		  		<p>${enquiry.messageText}</p>
				
			</div>
		</div>
</c:forEach>

<c:import url="template/footer.jsp" />