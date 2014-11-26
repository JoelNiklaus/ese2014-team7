<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:import url="template/header.jsp" />

<h1>My Notification Center</h1>

${message}

<c:forEach items="${unreadNotifications}" var="notification">
		<div class="panel panel-info" onclick="javascript:location.href='ad?id=${notification.adId}'">

		<div class="panel-heading">
			<h5>${notification.ad.title}</h5>
			<a class="btn btn-primary" href="removeNotification?id=${notification.id}">remove notification</a>
		</div>
		<div class="panel-body">
			<a class="pull-left"> <img class="media-object"
				src="/Skeleton/img/<c:out value="${notification.ad.street}${notification.ad.houseNr}.jpeg"/>"
				height="100px">
			</a>
			
			<div>${notification.notificationText}</div>
			
		</div>

		<div class="panel-footer">
			<b>Price:</b> CHF ${notification.ad.rent} <b>Room Size:</b>
			${notification.ad.roomSize}m²
		</div>
	</div>
</c:forEach>

<c:forEach items="${notifications}" var="notification">
	<div class="panel panel-default" onclick="javascript:location.href='ad?id=${notification.adId}'">

		<div class="panel-heading">
			<h5>${notification.ad.title}</h5>
			<a class="btn btn-primary" href="removeNotification?id=${notification.id}">remove notification</a>
		</div>
		<div class="panel-body">
			<a class="pull-left"> <img class="media-object"
				src="/Skeleton/img/<c:out value="${notification.ad.street}${notification.ad.houseNr}.jpeg"/>"
				height="100px">
			</a>
		</div>

		<div class="panel-footer">
			<b>Price:</b> CHF ${notification.ad.rent} <b>Room Size:</b>
			${notification.ad.roomSize}m²
		</div>
	</div>
</c:forEach>

<c:import url="template/footer.jsp" />