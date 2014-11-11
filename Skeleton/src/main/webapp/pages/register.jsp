<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<c:import url="template/header.jsp" />

<form:form method="post" modelAttribute="signupForm" action="register"
	id="signupForm" autocomplete="off">

	<h4>Create new account</h4>

	<form:input class="form-control" path="email" maxlength="45"
		placeholder="Email" />
	<form:errors path="email" cssClass="help-inline" element="span" />
	<br>
	<form:input class="form-control" path="firstName" maxlength="35"
		placeholder="First Name" />
	<form:errors path="firstName" cssClass="help-inline" element="span" />
	<br>
	<form:input class="form-control" path="lastName" maxlength="35"
		placeholder="Last Name" />
	<form:errors path="lastName" cssClass="help-inline" element="span" />
	<br>
	<form:password class="form-control" path="password" maxlength="45"
		placeholder="Password" />
	<form:errors path="password" cssClass="help-inline" element="span" />
	<br>
	<form:password class="form-control" path="passwordConfirm"
		maxlength="45" placeholder="Confirm Password" />
	<form:errors path="passwordConfirm" cssClass="help-inline"
		element="span" />
	<br>
	<form:input class="form-control" path="street" maxlength="45"
		placeholder="Street" />
	<form:errors path="street" cssClass="help-inline" element="span" />
	<br>
	<form:input class="form-control" path="houseNr" maxlength="45"
		placeholder="House Nr." />
	<form:errors path="houseNr" cssClass="help-inline" element="span" />
	<br>
	<form:input class="form-control" path="zip" maxlength="45"
		placeholder="ZIP" />
	<form:errors path="zip" cssClass="help-inline" element="span" />
	<br>
	<form:input class="form-control" path="city" maxlength="45"
		placeholder="City" />
	<form:errors path="city" cssClass="help-inline" element="span" />
	<br>

	<div class="form-actions">
		<button type="submit" class="btn btn-primary">Sign up</button>
		<button type="reset" class="btn">Reset</button>
		<a href="forgot">Forgot Password?</a>
	</div>
</form:form>

<c:if test="${page_error != null }">
	<div class="alert alert-error">
		<button type="button" class="close" data-dismiss="alert">&times;</button>
		<h4>Error!</h4>
		${page_error}
	</div>
</c:if>
<c:import url="template/footer.jsp" />
