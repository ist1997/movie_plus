<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="/resources/css/head.css" type="text/css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<body>

<nav class="navbar navbar-inverse" style="margin-bot:0px;background: black">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a style="margin-top:-13.68px;" class="navbar-brand" href="/"><img src="../resources/img/CinemaLogo.jpg" style="height:50px;"></a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li><a href="/">Home</a></li>
        <li><a href="/films">All films</a></li>
        <li><a href="/imax">About IMAX</a></li>
        <li><a href="/4DX">About 4DX</a></li>
      </ul>
      <sec:authorize access="isAuthenticated()">
	 	       <sec:authorize access="hasRole('ROLE_ADMIN')">
	 		   		<ul class="nav navbar-nav navbar-right">
        			<li class="dropdown" style="margin-top: 0;">
        				<a class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-user"></span> Admin panel
        				<span class="caret"></span></a>
        				<ul class="dropdown-menu">
        					<li><a href="/admin/actor">Actor</a></li>
							<li><a href="/admin/country">Country</a></li>	
							<li><a href="/admin/film">Film</a></li>
							<li><a href="/admin/genre">Genre</a></li>
							<li><a href="/admin/producer">Producer</a></li>
        				</ul>
      				</li>
        			<li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      			</ul>
	 		   </sec:authorize>
	 		   <sec:authorize access="hasRole('ROLE_USER')">
	 		  	    <ul class="nav navbar-nav navbar-right">
        			<li><a href="/club/<sec:authentication property="principal.id"/>" id="wow"><sec:authentication property="principal.email" /></a></li>
        			<li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      			</ul>
	 		  </sec:authorize>
 			</sec:authorize>
            <sec:authorize access="!isAuthenticated()">
           		<ul class="nav navbar-nav navbar-right">
        			<li><a href="/registration"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
        			<li><a href="/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      			</ul>
      		</sec:authorize>
    </div>
  </div>
</nav>
</body>
</html>
