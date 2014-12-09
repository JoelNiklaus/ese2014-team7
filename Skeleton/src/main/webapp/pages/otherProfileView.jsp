<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:import url="template/header.jsp" />

<div class="jumbotron">
<h1>${otherUser.firstName } ${otherUser.lastName }</h1>
<div class="row">
	<div class="col-sm-4">
		<c:if test="${not empty otherUser.profileImage}">
			<img width="300px" class="gallery" src="/Skeleton/img/ad/${otherUser.profileImage.fileName}" />
		</c:if>
		<c:if test="${empty otherUser.profileImage}">
			<img width="300px" class="gallery" src="/Skeleton/img/Profile_Placeholder.jpg" />
		</c:if>
	</div>
	<div class="col-sm-4">
		<p>
			<a href="mailto:${otherUser.email }">${otherUser.email }</a><br> ${otherUser.address.street }
			${otherUser.address.houseNr }<br> ${otherUser.address.zip } ${otherUser.address.city }<br>
		</p>
	</div>
	<div class="col-sm-4">
		<p>${otherUser.description }</p>
	</div>
</div>
</div>
<c:import url="template/footer.jsp" />