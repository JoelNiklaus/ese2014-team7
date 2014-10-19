<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="template/header.jsp" />


<h1>Login or Register</h1>


<form:form method="post" modelAttribute="loginForm" action="login" id="signupForm" cssClass="form-horizontal"  autocomplete="off">
    <fieldset>
        <legend>Login:</legend>

        <c:set var="emailErrors"><form:errors path="email"/></c:set>
        <div class="control-group<c:if test="${not empty emailErrors}"> error</c:if>">
            <label class="control-label" for="field-email">Email</label>

            <div class="controls">
                <form:input path="email" id="field-email-login" tabindex="1" maxlength="45" placeholder="Email"/>
                <form:errors path="email" cssClass="help-inline" element="span"/>
            </div>
        </div>
        
        <%-- TODO: not sure what that emailErrors does, but probably not appropriate here... --%>
        <div class="control-group<c:if test="${not empty emailErrors}"> error</c:if>">
            <label class="control-label" for="field-password-login">Password</label>

            <div class="controls">
                <form:password path="password" id="field-password-login" tabindex="2" maxlength="45" placeholder="Password"/>
                <form:errors path="password" cssClass="help-inline" element="span"/>
            </div>
        </div>
        
        <div class="form-actions">
            <button type="submit" class="btn btn-primary">Login</button>
            <button type="button" class="btn">Cancel</button>
        </div>
    </fieldset>
</form:form>


<form:form method="post" modelAttribute="signupForm" action="register" id="signupForm" cssClass="form-horizontal"  autocomplete="off">
    <fieldset>
        <legend>Not a member? Register here:</legend>

        <c:set var="emailErrors"><form:errors path="email"/></c:set>
        <div class="control-group<c:if test="${not empty emailErrors}"> error</c:if>">
            <label class="control-label" for="field-email">Email</label>

            <div class="controls">
                <form:input path="email" id="field-email" tabindex="3" maxlength="45" placeholder="Email"/>
                <form:errors path="email" cssClass="help-inline" element="span"/>
            </div>
        </div>
        <c:set var="firstNameErrors"><form:errors path="firstName"/></c:set>
        <div class="control-group<c:if test="${not empty firstNameErrors}"> error</c:if>">
            <label class="control-label" for="field-firstName">First Name</label>
            <div class="controls">
                <form:input path="firstName" id="field-firstName" tabindex="4" maxlength="35" placeholder="First Name"/>
                <form:errors path="firstName" cssClass="help-inline" element="span"/>
            </div>
        </div>
        <c:set var="lastNameErrors"><form:errors path="lastName"/></c:set>
        <div class="control-group<c:if test="${not empty lastNameErrors}"> error</c:if>">
            <label class="control-label" for="field-lastName">Last Name</label>
            <div class="controls">
                <form:input path="lastName" id="field-lastName" tabindex="5" maxlength="35" placeholder="Last Name"/>
                <form:errors path="lastName" cssClass="help-inline" element="span"/>
            </div>
        </div>
        
        <%-- TODO: not sure what that emailErrors does, but probably not appropriate here... --%>
        <%-- TODO: maybe check for some pw rules, such as length, using number etc. --%>
        <div class="control-group<c:if test="${not empty emailErrors}"> error</c:if>">
            <label class="control-label" for="field-password">Password</label>

            <div class="controls">
                <form:password path="password" id="field-password" tabindex="6" maxlength="45" placeholder="Password"/>
                <form:errors path="password" cssClass="help-inline" element="span"/>
            </div>
        </div>
        
        <div class="control-group">
            <label class="control-label" for="field-password">Confirm Password</label>

            <div class="controls">
                <form:password path="passwordConfirm" id="field-password-confirm" tabindex="7" maxlength="45" placeholder="Confirm Password"/>
                <form:errors path="passwordConfirm" cssClass="help-inline" element="span"/>
            </div>
        </div>
        
        <div class="control-group">
            <label class="control-label" for="field-street">Street</label>
            <div class="controls">
                <form:input path="street" id="field-street" tabindex="8" maxlength="45" placeholder="Street"/>
                <form:errors path="street" cssClass="help-inline" element="span"/>
            </div>
        </div>
        
         <div class="control-group">
            <label class="control-label" for="field-houseNr">House Nr.</label>
            <div class="controls">
                <form:input path="houseNr" id="field-houseNr" tabindex="9" maxlength="45" placeholder="House Nr."/>
                <form:errors path="houseNr" cssClass="help-inline" element="span"/>
            </div>
        </div>
        
         <div class="control-group">
            <label class="control-label" for="field-city">City</label>
            <div class="controls">
                <form:input path="city" id="field-city" tabindex="10" maxlength="45" placeholder="City"/>
                <form:errors path="city" cssClass="help-inline" element="span"/>
            </div>
        </div>
        
        <div class="control-group">
            <label class="control-label" for="field-zip">Zip Code</label>
            <div class="controls">
                <form:input path="zip" id="field-zip" tabindex="11" maxlength="45" placeholder="ZIP"/>
                <form:errors path="zip" cssClass="help-inline" element="span"/>
            </div>
        </div>
        
        <div class="form-actions">
            <button type="submit" class="btn btn-primary">Sign up</button>
            <button type="button" class="btn">Cancel</button>
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
