<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>TestApp</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="/Skeleton/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="/Skeleton/css/bootstrap.min.css">
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<style>
body {
padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
}
</style>
</head>
<body>
<script>
$(document).ready(function () {
    $('a[href="' + this.location.pathname.replace('/Skeleton/','') + '"]').parent().addClass('active');

});
</script>
<nav class="navbar navbar-default" role="navigation">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Brand</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li><a href="search">Search</a></li>
        <li><a href="map">Map</a></li>
        <c:if test="${not empty loggedInUser}">
	        <li><a href="createAd">Create Ad</a></li>
	        <li class="dropdown">
	          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Profile <span class="caret"></span></a>
	          <ul class="dropdown-menu" role="menu">
	            <li><a href="profile">Settings</a></li>
	            <li class="divider"></li>
	            <li><a href="enquiries">Enquiry</a></li>
	            <li><a href="bookmarks">Bookmarks</a></li>
	            <li class="divider"></li>
	            <li><a href="#">Ads</a></li>
	          </ul>
	        </li>
        </c:if>
      </ul>
      
      	<div class="top-menu">
		<ul class="nav pull-right top-menu">

		</ul>
	</div>
	<div class="navbar-form navbar-right" role="search">
		<c:if test="${empty loggedInUser}">
			<div class="row">
				<form name="f" action="<c:url value="/j_spring_security_check"/>" method="POST">
					<div class="form-group">
						<input type="text" class="form-control" id="j_username" name="j_username" placeholder="Username">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" id="j_password" name="j_password" placeholder="Password">
					</div>
					<button type="submit" class="btn btn-default">Sign In</button>
					<button type="button" onclick="window.location.href='register'" class="btn btn-default">Register</button>
				</form>			
			</div>
		</c:if>
		<c:if test="${not empty loggedInUser}">
			${loggedInUser.firstName } ${loggedInUser.lastName }   <a  class="btn btn-default" href="${pageContext.servletContext.contextPath}/logout">Logout</a></li>
		</c:if>


                    

    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<div class="container">
