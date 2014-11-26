<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="template/header.jsp" />

<a class="btn btn-theme" href="/Skeleton/ad?id=${ad.id}"><h1>${ad.title}</h1> </a>
<a class="btn btn-theme" href="/Skeleton/editAd?id=${ad.id}">Edit Ad</a>
<a class="btn btn-theme" href="/Skeleton/deleteAd?id=${ad.id}">Delete Ad</a>

<c:import url="template/footer.jsp" />
