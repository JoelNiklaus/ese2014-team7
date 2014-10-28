<%@ page language="java" pageEncoding="UTF-8"
  	contentType="text/html;charset=utf-8"%>  
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
  <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
  <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>  


  <c:import url="template/header.jsp" />  

  <h1>My Profile</h1>
  
  <p>${message}</p>  

<h2>User Information</h2>

<form:form method="post" modelAttribute="profileForm" action="profileChange" autocomplete="off">
	<form:input class="form-control" path="email" maxlength="45"
 		placeholder="Email" value="${session.user.email}" /> 
 	<form:errors path="email" cssClass="help-inline" element="span" /> 
	<br>
 	<form:input class="form-control" path="firstName" maxlength="35" 
 		placeholder="First Name" value="${session.user.firstName}" /> 
 	<form:errors path="firstName" cssClass="help-inline" element="span" /> 
 	<br> 
 	<form:input class="form-control" path="lastName" maxlength="35" 
 		placeholder="Last Name" value="${session.user.lastName}" /> 
 	<form:errors path="lastName" cssClass="help-inline" element="span" /> 
 	<br> 
 	<form:password class="form-control" path="password" maxlength="45" 
 		placeholder="Password" value="${session.user.password}" /> 
 	<form:errors path="password" cssClass="help-inline" element="span" /> 
 	<br> 
 	<form:password class="form-control" path="passwordConfirm" 
 		maxlength="45" placeholder="Confirm Password" value="${session.user.password}" /> 
 	<form:errors path="passwordConfirm" cssClass="help-inline" 
 		element="span" /> 
 	<br> 
 	<form:input class="form-control" path="street" maxlength="45" 
 		placeholder="Street" value="${address.street}" /> 
 	<form:errors path="street" cssClass="help-inline" element="span" /> 
 	<br> 
 	<form:input class="form-control" path="houseNr" maxlength="45" 
 		placeholder="House Nr." value="${address.houseNr}" /> 
 	<form:errors path="houseNr" cssClass="help-inline" element="span" /> 
 	<br> 
 	<form:input class="form-control" path="zip" maxlength="45" 
 		placeholder="ZIP" value="${address.zip}" /> 
 	<form:errors path="zip" cssClass="help-inline" element="span" /> 
 	<br> 
 	<form:input class="form-control" path="city" maxlength="45" 
 		placeholder="City" value="${address.city}" /> 
 	<form:errors path="city" cssClass="help-inline" element="span" /> 
 	<br>
 	
 	<button class="btn btn-default" type="reset">Reset</button>
	<button class="btn btn-theme" type="submit">Submit</button>
 </form:form>

<h2>Bookmarked Ads</h2>


<h2>Enquiries</h2>


<c:import url="template/footer.jsp" />