<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>Roomiez.</title>
		
		<link rel="shortcut icon" href="/Skeleton/img/favicon.ico" >
		<link rel="icon" href="/Skeleton/img/favicon.ico" type="image/x-icon">
		
		<link rel="stylesheet" type="text/css" href="/Skeleton/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="/Skeleton/css/custom.css">
		
		<!-- jQuery at the beginning, otherwise e.g. sliders won't work -->
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<script>
			$(document).ready(
					function() {
						$('a[href="'+ this.location.pathname.replace('/Skeleton/', '') + '"]').parent().addClass('active');
						});
		</script>
		
		<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
			<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
	</head>
	<body>
		<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
			<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="search"><span class="glyphicon glyphicon-home"></span> Roomiez.</a>
				</div>
	
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li><a href="search"><span class="glyphicon glyphicon-search"></span> Search</a></li>
						<c:if test="${not empty loggedInUser}">
							<li><a href="createAd"><span class="glyphicon glyphicon-tag"></span> Create Ad</a></li>
							<li><a href="myAds"><span class="glyphicon glyphicon-tags"></span> My Ads</a></li>
							<li><a href="enquiries"><span class="glyphicon glyphicon-calendar"></span> Enquiries 
								<c:if test="${numUnreadEnquiries > 0}"><span class="badge">${numUnreadEnquiries}</span></c:if>
							</a></li>
							<li><a href="notifications"><span class="glyphicon glyphicon-envelope"></span> Notifications 
								<c:if test="${numUnreadNotifications > 0}"><span class="badge">${numUnreadNotifications}</span></c:if>
							</a></li>
							<li><a href="bookmarks"><span class="glyphicon glyphicon-bookmark"></span> Bookmarks</a> </li>
							<li><a href="searches"><span class="glyphicon glyphicon-filter"></span> Searches</a></li>
	
						</c:if>
					</ul>
					<c:if test="${empty loggedInUser}">
						<form class="navbar-form navbar-right" name="f"
							action="<c:url value="/j_spring_security_check"/>" method="post">
							<div class="form-group">
								<div class="input-group">
									<div class="input-group-addon">@</div>
									<input type="email" class="form-control" id="j_username"
										name="j_username" placeholder="E-Mail">
								</div>
							</div>
							<div class="form-group">
								<input type="password" class="form-control" id="j_password"
									name="j_password" placeholder="Password">
							</div>
							<div class="checkbox">
								<label> <input type="checkbox" name="_spring_security_remember_me"> Remember me
								</label>
							</div>
							<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-log-in"></span> Login</button>
							<button type="button" onclick="window.location.href='register'"
								class="btn btn-info"><span class="glyphicon glyphicon-new-window"></span> Register</button>
						</form>
					</c:if>
					<c:if test="${not empty loggedInUser}">
						<form class="navbar-form navbar-right" action="logout" method="get">
							<button type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-log-out"></span> Logout</button>
						</form>
						<p class="navbar-text navbar-right"><span class="glyphicon glyphicon-user"></span> Signed in as <a class="badge" href="profile">${loggedInUser.firstName }
									${loggedInUser.lastName }</a></p>
					</c:if>
				</div><!-- /.navbar-collapse -->
			</div><!-- /.container-fluid -->
		</nav>
		<div class="container">