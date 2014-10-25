<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

    <title>TestApp</title>

    <!-- Bootstrap core CSS -->
    <link href="/Skeleton/dashgum/css/bootstrap.css" rel="stylesheet">
    <!--external css-->
    <link href="/Skeleton/dashgum/font-awesome/css/font-awesome.css" rel="stylesheet" />
        
    <!-- Custom styles for this template -->
    <link href="/Skeleton/dashgum/css/style.css" rel="stylesheet">
    <link href="/Skeleton/dashgum/css/style-responsive.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

      <!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->

	  <div id="login-page">
	  	<div class="container">
	  	
		      <form:form class="form-login" method="post" modelAttribute="loginForm" action="login" id="loginForm" autocomplete="off">
		        <h2 class="form-login-heading">sign in now</h2>
		        <div class="login-wrap">
		            <form:input class="form-control" path="email" id="field-email-login" maxlength="45" placeholder="E-Mail"/>
                	<form:errors path="email" cssClass="help-inline" element="span"/>
		            <br>
		            <form:password class="form-control" path="password" id="field-password-login" maxlength="45" placeholder="Password"/>
                	<form:errors path="password" cssClass="help-inline" element="span"/>
		            <label class="checkbox">
		            	<span class="pull-left">
		                    <a href="${pageContext.servletContext.contextPath}/">Back to the home page</a>
		                </span>
		                <span class="pull-right">
		                    <a data-toggle="modal" href="#forgot">Forgot Password ?</a>
		                </span>
		            </label>
		            <button class="btn btn-theme btn-block" type="submit"><i class="fa fa-lock"></i> SIGN IN</button>
		            <hr>
		            
<!-- 		            <div class="login-social-link centered"> -->
<!-- 		            <p>or you can sign in via your social network</p> -->
<!-- 		                <button class="btn btn-facebook" type="submit"><i class="fa fa-facebook"></i> Facebook</button> -->
<!-- 		                <button class="btn btn-twitter" type="submit"><i class="fa fa-twitter"></i> Twitter</button> -->
<!-- 		            </div> -->
		            <div class="registration">
		                Don't have an account yet?<br/>
		                <a data-toggle="modal" href="#register">Create an account</a>
		            </div>
		        </div>
			  </form:form>
			  
			  
			  
	          <!-- Modal Forgot -->
	          <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="forgot" class="modal fade">
	              <div class="modal-dialog">
	                  <div class="modal-content">
	                 	  <form:form method="post" modelAttribute="forgotPasswordForm" action="forgot" id="forgotPasswordForm" autocomplete="off">
		                      <div class="modal-header">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">Forgot Password ?</h4>
		                      </div>
		                      <div class="modal-body">
		                          <p>Enter your e-mail address below to reset your password.</p>
		                          <form:input class="form-control placeholder-no-fix" path="email" maxlength="45" placeholder="E-Mail"/>
               					  <form:errors path="email" cssClass="help-inline" element="span"/>
		                      </div>
		                      <div class="modal-footer">
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Cancel</button>
		                          <button class="btn btn-theme" type="submit">Submit</button>
		                      </div>
	                      </form:form>
	                  </div>
	              </div>
	          </div>
	          <!-- modal forgot -->
		
			  <!-- Modal Register -->
	          <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="register" class="modal fade">
	              <div class="modal-dialog">
	                  <div class="modal-content">
	                      <form:form method="post" modelAttribute="signupForm" action="register" id="signupForm" autocomplete="off">
		                      <div class="modal-header">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">Create new account</h4>
		                      </div>
		                      <div class="modal-body">
		                      
									<form:input class="form-control" path="email" maxlength="45" placeholder="Email"/>
					                <form:errors path="email" cssClass="help-inline" element="span"/>
		                      		<br>
		                      		<form:input class="form-control" path="firstName" maxlength="35" placeholder="First Name"/>
              					  	<form:errors path="firstName" cssClass="help-inline" element="span"/>
              					  	<br>
              					  	<form:input class="form-control" path="lastName" maxlength="35" placeholder="Last Name"/>
                					<form:errors path="lastName" cssClass="help-inline" element="span"/>
                					<br>
                					<form:password class="form-control" path="password" maxlength="45" placeholder="Password"/>
               						<form:errors path="password" cssClass="help-inline" element="span"/>
               						<br>
               						<form:password class="form-control" path="passwordConfirm" maxlength="45" placeholder="Confirm Password"/>
                					<form:errors path="passwordConfirm" cssClass="help-inline" element="span"/>
                					<br>
                					<form:input class="form-control" path="street" maxlength="45" placeholder="Street"/>
					                <form:errors path="street" cssClass="help-inline" element="span"/>
					                <br>
					                <form:input class="form-control" path="houseNr" maxlength="45" placeholder="House Nr."/>
                					<form:errors path="houseNr" cssClass="help-inline" element="span"/>
                					<br>
                					<form:input class="form-control" path="zip" maxlength="45" placeholder="ZIP"/>
					                <form:errors path="zip" cssClass="help-inline" element="span"/>
						            <br>
                					<form:input class="form-control" path="city" maxlength="45" placeholder="City"/>
               						<form:errors path="city" cssClass="help-inline" element="span"/>
                					<br>
                					
		                      </div>
		                      <div class="modal-footer">
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Cancel</button>
		                          <button class="btn btn-theme" type="submit">Submit</button>
		                      </div>
	                      </form:form>
	                  </div>
	              </div>
	          </div>
	          <!-- modal register -->      	  	
	  	
	  	</div>
	  </div>

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="/Skeleton/dashgum/js/jquery.js"></script>
    <script src="/Skeleton/dashgum/js/bootstrap.min.js"></script>

    <!--BACKSTRETCH-->
    <!-- You can use an image of whatever size. This script will stretch to fit in any screen size.-->
    <script type="text/javascript" src="/Skeleton/dashgum/js/jquery.backstretch.min.js"></script>
    <script>
        $.backstretch("/Skeleton/dashgum/img/login-bg.jpg", {speed: 500});
    </script>


  </body>
</html>
