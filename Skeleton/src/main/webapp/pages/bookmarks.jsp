<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:import url="template/header.jsp" />

<h1>My Bookmarked Ads</h1>

<c:if test="${empty bookmarks}">
	<div class="jumbotron">
		<p>You have not bookmarked any ads yet. If you want to find apartments, studios, houses etc, you can here <a href="search">search ads</a>.
			When you have found an interesting one you can bookmark it to remember it.</p>
	</div>
</c:if>

<c:if test="${not empty message}">
	<div class="alert alert-success" role="alert">${message}</div>
</c:if>

<c:forEach items="${bookmarks}" var="bookmark">
	<div class="panel panel-primary"
		onclick="javascript:location.href='ad?id=${bookmark.adId}'">

		<div class="panel-heading">
			<h5>${bookmark.ad.title}</h5>
		</div>
		<div class="panel-body">
			<a style="padding:1em;" class="pull-left" >		
				<c:if test="${not empty bookmark.ad.pictures}">
					<c:forEach items="${bookmark.ad.pictures}" varStatus="loopCount" var="pic">
						<c:if test="${loopCount.count eq 1}">
							<img width="150px;" class="gallery" src="/Skeleton/img/ad/${pic.fileName}" />
						</c:if>
					</c:forEach>
				</c:if>
				<c:if test="${empty bookmark.ad.pictures}">
					<img width="150px" class="gallery" src="/Skeleton/img/Placeholder.png" />
				</c:if>
	  		</a>
			<p style="padding: 10px;">${bookmark.ad.description}</p>
		</div>

		<div class="panel-footer">
			<b>Area: </b>${bookmark.ad.city}, <b>Rent:</b> CHF ${bookmark.ad.rent}, <b>Room
							Size:</b> ${bookmark.ad.roomSize}m², <b>Posted: </b>${bookmark.ad.postingDateFormatted}
			<a class="btn btn-danger btn-xs pull-right" href="removeBookmark?id=${bookmark.bookmarkId}"><span class="glyphicon glyphicon-remove"></span>delete</a>
		</div>
	</div>
</c:forEach>

<c:import url="template/footer.jsp" />