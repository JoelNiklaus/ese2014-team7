<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="template/header.jsp" />

<h1>HTTP 404</h1>

The requested File was not found!<br /><br />

HTTP/1.0 Room 404 Object Not Found


<br />
<br />
  	<center><h2>The history of 404</h2></center>
  	<br />


Before the beginning of time, when the Internet was still very much under the spell of bare Unix shells and Gopher, before SLIP or PPP became widely used, an ambitious group of young scientists at CERN (Switzerland) started working on what was to become the media revolution of the nineties: the World Wide Web, later to be known as WWW, or simply 'the Web'. Their aim: to create a database infrastructure that offered open access to data in various formats: multi-media. The ultimate goal was clearly to create a protocol that would combine text and pictures and present it as one document, and allow linking to other such documents: hypertext.
<br />
Because these bright young minds were reluctant to reveal their progress (and setbacks) to the world, they started developing their protocol in a closed environment: CERN's internal network. Many hours were spend on what later became the world-wide standard for multimedia documents. Using the physical lay-out of CERN's network and buildings as a metaphor for the 'real world' they situated different functions of the protocol in different offices within CERN.
<br />
In an office on the fourth floor (room 404), they placed the World Wide Web's central database: any request for a file was routed to that office, where two or three people would manually locate the requested files and transfer them, over the network, to the person who made that request.
<br />
When the database started to grow, and the people at CERN realised that they were able to retrieve documents other than their own research-papers, not only the number of requests grew, but also the number of requests that could not be fulfilled, usually because the person who requested a file typed in the wrong name for that file. Soon these faulty requests were answered with a standard message: 'Room 404: file not found'.
<br />
Later, when these processes were automated and people could directly query the database, the messageID's for error messages remained linked to the physical location the process took place: '404: file not found'.
<br />
The room numbers remained in the error codes in the official release of HTTP (Hyper Text Transfer Protocol) when the Web left CERN to conquer the world, and are still displayed when a browser makes a faulty request to a Web server. In memory of the heroic boys and girls that worked deep into the night for all those months, in those small and hot offices at CERN, Room 404 is preserved as a 'place on the Web'. None of the other rooms are still used for the Web. Room 404 is the only and true monument to the beginning of the Web, a tribute to a place in the past, where the future was shaped.
<br />
<br />
Source: <a href="http://www.room404.com/page.php?pg=homepage">www.room404.com</a>

<c:import url="template/footer.jsp" />