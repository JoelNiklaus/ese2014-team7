<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<c:import url="template/header.jsp" />


<div class="col-md-12">
	<div class="modal-dialog" style="margin-bottom: 0">
		<div class="modal-content">
			<div class="panel-heading">
				<h2 class="panel-title">Sign In</h2>
			</div>
			<div class="panel-body">
				<form class="form-horizontal" role="form" name="f" action="<c:url value="/j_spring_security_check"/>"
					method="post">
					<div class="form-group">
						<label class="col-sm-2 control-label">Email</label>
						<div class="col-sm-10">
							<input class="form-control" type="email" placeholder="E-Mail" id="j_username"
								name="j_username">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Password</label>
						<div class="col-sm-10">
							<input class="form-control" placeholder="Password" type="password" id="j_password"
								name="j_password">
						</div>
					</div>
					<c:if test="${param.error != null}">
						<div class="alert alert-danger">
							Failed to login.
							<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
						                  Reason: <c:out
									value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
							</c:if>
						</div>
					</c:if>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="submit" class="btn btn-primary" value="Login">
							<input type="reset" class="btn btn-warning" value="Reset">
							<a href="forgot">Forgot Password?</a>
							<input type="hidden" id="${_csrf.parameterName}" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<c:import url="template/footer.jsp" />
