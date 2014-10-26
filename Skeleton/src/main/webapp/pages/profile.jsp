<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<c:import url="template/header.jsp" />

<h1>My Profile</h1>

<%-- <div  align="left" style="border:1px solid #ccc;"> --%>

<h2>User Information</h2>

<%-- <form:form method="post" modelAttribute="signupForm" action="changeProfile" autocomplete="off"> --%>
<%-- 	<form:input class="form-control" path="email" maxlength="45" --%>
<%-- 		placeholder="Email" /> --%>
<%-- 	<form:errors path="email" cssClass="help-inline" element="span" /> --%>
<!-- 	<br> -->
<%-- 	<form:input class="form-control" path="firstName" maxlength="35" --%>
<%-- 		placeholder="First Name" /> --%>
<%-- 	<form:errors path="firstName" cssClass="help-inline" element="span" /> --%>
<!-- 	<br> -->
<%-- 	<form:input class="form-control" path="lastName" maxlength="35" --%>
<%-- 		placeholder="Last Name" /> --%>
<%-- 	<form:errors path="lastName" cssClass="help-inline" element="span" /> --%>
<!-- 	<br> -->
<%-- 	<form:password class="form-control" path="password" maxlength="45" --%>
<%-- 		placeholder="Password" /> --%>
<%-- 	<form:errors path="password" cssClass="help-inline" element="span" /> --%>
<!-- 	<br> -->
<%-- 	<form:password class="form-control" path="passwordConfirm" --%>
<%-- 		maxlength="45" placeholder="Confirm Password" /> --%>
<%-- 	<form:errors path="passwordConfirm" cssClass="help-inline" --%>
<%-- 		element="span" /> --%>
<!-- 	<br> -->
<%-- 	<form:input class="form-control" path="street" maxlength="45" --%>
<%-- 		placeholder="Street" /> --%>
<%-- 	<form:errors path="street" cssClass="help-inline" element="span" /> --%>
<!-- 	<br> -->
<%-- 	<form:input class="form-control" path="houseNr" maxlength="45" --%>
<%-- 		placeholder="House Nr." /> --%>
<%-- 	<form:errors path="houseNr" cssClass="help-inline" element="span" /> --%>
<!-- 	<br> -->
<%-- 	<form:input class="form-control" path="zip" maxlength="45" --%>
<%-- 		placeholder="ZIP" /> --%>
<%-- 	<form:errors path="zip" cssClass="help-inline" element="span" /> --%>
<!-- 	<br> -->
<%-- 	<form:input class="form-control" path="city" maxlength="45" --%>
<%-- 		placeholder="City" /> --%>
<%-- 	<form:errors path="city" cssClass="help-inline" element="span" /> --%>
<!-- 	<br> -->
<%-- </form:form> --%>


<table cellpadding="5">
	<tr>
		<td><strong>First Name</strong></td>
		<td><c:out value="${session.user.firstName}" /></td>
	</tr>
	<tr>
		<td><strong>Last Name</strong></td>
		<td><c:out value="${session.user.lastName}" /></td>
	</tr>
	<tr>
		<td><strong>E-Mail</strong></td>
		<td><c:out value="${session.user.email}" /></td>
	</tr>
	<tr>
		<td><strong>Street</strong></td>
		<td><c:out value="${address.street}" />, <c:out
				value="${address.houseNr}" /></td>
	</tr>
	<tr>
		<td><strong>City</strong></td>
		<td><c:out value="${address.city}" />, <c:out
				value="${address.zip}" /></td>
	</tr>
</table>

<h2>Bookmarked Ads</h2>


<h2>Enquiries</h2>


<c:import url="template/footer.jsp" />