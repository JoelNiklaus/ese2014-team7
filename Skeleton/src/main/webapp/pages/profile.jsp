<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="template/header.jsp" />

<h1>My Profile</h1>

<%-- <div  align="left" style="border:1px solid #ccc;"> --%>


	<table cellpadding="5">
		<tr>
			<td><strong>First Name</strong></td> 
			<td><c:out value="${user.firstName}"/></td>
		</tr>
		<tr>
			<td><strong>Last Name</strong></td> 
			<td><c:out value="${user.lastName}"/></td>
		</tr>
		<tr>
			<td><strong>E-Mail</strong></td> 
			<td><c:out value="${user.email}"/></td>
		</tr>
		<tr>
			<td><strong>Street</strong></td> 
			<td>[TBA]</td>
		</tr>
		<tr>
			<td><strong>City</strong></td> 
			<td>[TBA]</td>
		</tr>
	</table>



<c:import url="template/footer.jsp" />