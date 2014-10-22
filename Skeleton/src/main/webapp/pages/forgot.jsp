<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="template/header.jsp" />

<h1>Forgot Password</h1>

<form:form method="post" modelAttribute="forgotPasswordForm" action="mailPassword" id="forgotPasswordForm" cssClass="form-horizontal"  autocomplete="off">
    <fieldset>
        <legend>Forgot Password:</legend>

        <c:set var="emailErrors"><form:errors path="email"/></c:set>
        <div class="control-group<c:if test="${not empty emailErrors}"> error</c:if>">
            <label class="control-label" for="field-email">Email</label>

            <div class="controls">
                <form:input path="email" id="field-email-login" tabindex="1" maxlength="45" placeholder="Email"/>
                <form:errors path="email" cssClass="help-inline" element="span"/>
            </div>
        </div>
                
        <div class="form-actions">
            <button type="submit" class="btn btn-primary">Send Password</button>
            <button type="reset" class="btn">Reset</button>
        </div>
    </fieldset>
</form:form>

<c:import url="template/footer.jsp" />