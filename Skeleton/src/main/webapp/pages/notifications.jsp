<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:import url="template/header.jsp" />

<c:if test="${not empty unreadNotifications or not empty notifications}">
	<div class="pull-right">
	    <button id="clearNotifications" class="btn btn-danger" onclick="javascript:location.href='clearNotifications'"><span class="glyphicon glyphicon-trash"></span> Clear Notifications</button>
	</div>
</c:if>

<h1>My Notification Center</h1>

<c:if test="${empty unreadNotifications and empty notifications}">
	<div class="jumbotron">
		<p>You have not received any notifications yet. When a user creates an ad which matches one of your saved searches you will get a notification here. You can here <a href="search">search ads</a>. Under "advanced Search" you can save your search.</p>
	</div>
</c:if>

<c:if test="${not empty message}">
	<div class="alert alert-success" role="alert">
		${message}
	</div>
</c:if>

<c:forEach items="${unreadNotifications}" var="notification">
		<%-- the onclick link should lead to notificationController, which should then return the ad model --%>
		<div class="panel panel-info" onclick="javascript:location.href='openNotification?adId=${notification.adId}&notificationId=${notification.id}'">

		<div class="panel-heading">
				<h5>New Search Match: ${notification.ad.title}</h5>
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
	<div class="panel panel-default" onclick="javascript:location.href='openNotification?adId=${notification.adId}&notificationId=${notification.id}'">

		<div class="panel-heading">
				<h5>Search Match: ${notification.ad.title}</h5>
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

<c:import url="template/footer.jsp" />