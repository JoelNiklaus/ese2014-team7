<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="template/header.jsp" />

	<div role="tabpanel" class="tab-pane"  id="listTab">
		<c:forEach items="${ads}" var="ad">
			<div class="panel panel-primary" onclick="javascript:location.href='ad?id=${ad.id}'">
					
				<div class="panel-heading"><h5>${ad.title}</h5></div>
					<div class="panel-body" >
					<a class="pull-left" style="padding:1em;" >
					<c:forEach items="${ad.pictures}" varStatus="loopCount" var="pic">
						<c:if test="${loopCount.count eq 1}">
							<img width="150px" class="gallery" src="/Skeleton/img/ad/${pic.fileName}" />
						</c:if>
					</c:forEach>
				</a>
		  		<p  style="padding:1em;">${ad.description}</p>
						</div>
							
						<div class="panel-footer"><b>Area: </b>${ad.city},  <b>Price:</b> CHF ${ad.rent},  <b>Room Size:</b> ${ad.roomSize}m²,  <b>Posted: </b>${ad.postingDateFormatted}</div>
					</div>
				</c:forEach>
			</div>
<!-- 
<a class="btn btn-theme" href="/Skeleton/ad?id=${ad.id}"><h1>${ad.title}</h1> </a>
<a class="btn btn-theme" href="/Skeleton/editAd?id=${ad.id}">Edit Ad</a>
<a class="btn btn-theme" href="/Skeleton/deleteAd?id=${ad.id}">Delete Ad</a>
 -->
<c:import url="template/footer.jsp" />
