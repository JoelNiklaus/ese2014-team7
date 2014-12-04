<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<c:import url="template/header.jsp" />

	<h1>Create new account</h1>

<form:form class="form-horizontal" role="form" method="post" modelAttribute="signupForm" action="register"
	id="signupForm" autocomplete="on">
	
	<spring:bind path="email">
		<div class="form-group ${status.error ? 'has-error has-feedback' : ''}">
			<label class="control-label col-sm-3" for="email">E-Mail</label>
			<div class="col-sm-9">
				<form:input path="email" class="form-control" id="email" type="email" maxlength="45" placeholder="E-Mail" />
				<form:errors path="email" class="help-block" element="span" />
				<c:out value="${emailExists}"></c:out>
			</div>
		</div>
	</spring:bind>
	<spring:bind path="firstName">
		<div class="form-group ${status.error ? 'has-error has-feedback' : ''}">
			<label class="control-label col-sm-3" for="firstName">First Name</label>
			<div class="col-sm-9">
				<form:input path="firstName" class="form-control" id="firstName" type="text" maxlength="45" placeholder="First Name" />
				<form:errors path="firstName" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>
	<spring:bind path="lastName">
		<div class="form-group ${status.error ? 'has-error has-feedback' : ''}">
			<label class="control-label col-sm-3" for="lastName">Last Name</label>
			<div class="col-sm-9">
				<form:input path="lastName" class="form-control" id="lastName" type="text" maxlength="45" placeholder="Last Name" />
				<form:errors path="lastName" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>
	<spring:bind path="password">
		<div class="form-group ${status.error ? 'has-error has-feedback' : ''}">
			<label class="control-label col-sm-3" for="password">Password</label>
			<div class="col-sm-9">
				<form:password path="password" class="form-control" id="password" maxlength="45" placeholder="Password" />
				<form:errors path="password" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>
	<spring:bind path="passwordConfirm">
		<div class="form-group ${status.error ? 'has-error has-feedback' : ''}">
			<label class="control-label col-sm-3" for="passwordConfirm">Confirm Password</label>
			<div class="col-sm-9">
				<form:password path="passwordConfirm" class="form-control" id="passwordConfirm" maxlength="45" placeholder="Confirm Password" />
				<form:errors path="passwordConfirm" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>
	<spring:bind path="street">
		<div class="form-group ${status.error ? 'has-error has-feedback' : ''}">
			<label class="control-label col-sm-3" for="street">Street</label>
			<div class="col-sm-9">
				<form:input path="street" class="form-control" id="street" type="text" maxlength="45" placeholder="Street" />
				<form:errors path="street" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>
	<spring:bind path="houseNr">
		<div class="form-group ${status.error ? 'has-error has-feedback' : ''}">
			<label class="control-label col-sm-3" for="houseNr">House Number</label>
			<div class="col-sm-9">
				<form:input path="houseNr" class="form-control" id="houseNr" type="text" maxlength="45" placeholder="House Number" />
				<form:errors path="houseNr" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>	
	<spring:bind path="zip">
		<div class="form-group ${status.error ? 'has-error has-feedback' : ''}">
			<label class="control-label col-sm-3" for="zip">ZIP-Code</label>
			<div class="col-sm-9">
				<form:input path="zip" class="form-control" id="zip" type="text" maxlength="45" placeholder="ZIP-Code" />
				<form:errors path="zip" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>
	<spring:bind path="city">
		<div class="form-group ${status.error ? 'has-error has-feedback' : ''}">
			<label class="control-label col-sm-3" for="city">City</label>
			<div class="col-sm-9">
				<form:input path="city" class="form-control" id="city" type="text" maxlength="45" placeholder="City" />
				<form:errors path="city" class="help-block" element="span" />
			</div>
		</div>
	</spring:bind>

	<div class="form-actions">
		<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-new-window"></span> Sign up</button>
		<button type="reset" class="btn btn-warning"><span class="glyphicon glyphicon-remove"></span> Reset</button>
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
