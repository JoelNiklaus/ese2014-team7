<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="template/header.jsp" />

<h1>My Ads</h1>

<c:if test="${empty ads}">
	<div class="jumbotron">
		<p>You have not created any ads yet. If you want to put online your apartment, studio, house etc., you can here <a href="createAd">create an ad</a>.</p>
	</div>
</c:if>

<c:if test="${not empty success}">
	<div class="alert alert-success" role="alert">
		${success}
	</div>
</c:if>

<div role="tabpanel" class="tab-pane" id="listTab">
	<div class="row">
		<c:forEach items="${ads}" var="ad">
			<div class="col-sm-4 col-md-3">
				<div class="panel panel-primary"
					onclick="javascript:location.href='ad?id=${ad.id}'">

					<div class="panel-heading" style="min-height: 70px; max-height: 70px"><h5>${ad.title}</h5></div>
					<div class="panel-body" style="min-height: 300px; max-height: 300px">
						<a style="padding: 1em;"> <c:forEach
								items="${ad.pictures}" varStatus="loopCount" var="pic">
								<c:if test="${loopCount.count eq 1}">
									<img width="150px" class="gallery"
										src="/Skeleton/img/ad/${pic.fileName}" />
								</c:if>
							</c:forEach>
						</a>
						<p style="padding: 1em;">${ad.description}</p>
					</div>

					<div class="panel-footer" style="min-height: 80px; max-height: 80px">
						<b>Area: </b>${ad.city}, <b>Rent:</b> CHF ${ad.rent}, <b>Room
							Size:</b> ${ad.roomSize}m², <b>Posted: </b>${ad.postingDateFormatted}</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>

<c:import url="template/footer.jsp" />
