<%@ page language="java" pageEncoding="UTF-8"
  	contentType="text/html;charset=utf-8"%>  
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
  <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
  <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>  


  <c:import url="template/header.jsp" />  

  <h1>Send Enquiry</h1>

  Send enquiry for object "<c:out value="${ad.title}"></c:out>"

<c:import url="template/footer.jsp" />