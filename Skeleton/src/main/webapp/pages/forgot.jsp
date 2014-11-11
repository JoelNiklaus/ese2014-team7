<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="template/header.jsp" />

<h1>Forgot Password</h1>

<c:out value="${error}"/>
<c:out value="${success}"/>

<form:form method="post" modelAttribute="forgotPasswordForm" action="forgot"
	id="forgotPasswordForm" autocomplete="off">

	<h4>ForgotPassword</h4>

	<form:input class="form-control" path="email" maxlength="45"
		placeholder="Email" />
	<form:errors path="email" cssClass="help-inline" element="span" />

	<div class="form-actions">
		<button type="submit" class="btn btn-primary">Send Password</button>
		<button type="reset" class="btn">Reset</button>
	</div>
</form:form>

<c:import url="template/footer.jsp" />