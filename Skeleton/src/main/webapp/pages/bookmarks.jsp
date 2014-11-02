<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:import url="template/header.jsp" />

<h1>My Bookmarked Ads</h1>

${message}

<c:forEach items="${bookmarks}" var="bookmark">
	<div class="panel panel-primary"
		onclick="javascript:location.href='ad?id=${bookmark.adId}'">

		<div class="panel-heading">
			<h5>${bookmark.ad.title}</h5>
			<a class="btn btn-primary" href="removeBookmark?id=${bookmark.bookmarkId}">remove Bookmark</a>
		</div>
		<div class="panel-body">
			<a class="pull-left"> <img class="media-object"
				src="/Skeleton/img/<c:out value="${bookmark.ad.street}${bookmark.ad.houseNr}.jpeg"/>"
				height="100px">
			</a>
		</div>

		<div class="panel-footer">
			<b>Price:</b> CHF ${bookmark.ad.rent} <b>Room Size:</b>
			${bookmark.ad.roomSize}m²
		</div>
	</div>
</c:forEach>

<c:import url="template/footer.jsp" />