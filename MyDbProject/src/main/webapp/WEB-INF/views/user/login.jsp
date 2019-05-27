<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <!DOCTYPE HTML>
<html>
	<head>
		<meta charset ="utf-8">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="/resources/css/logAndRegStyles.css" type="text/css">
	</head>
 <div class="row" style="background:url(/resources/img/40125669-cinema-wallpapers.jpg) no-repeat center;
	background-size: cover; margin-top:-20px;">
	
  		<div class="login-wrap">
	<div class="login-html">
		<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab" >Sign In</label>
		<input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab" style="color:#4C9900"></label>
		<div class="login-form">
			<div class="sign-in-htm">
			<form:form class="form-horizontal" action="/login" method="POST">
 	
 	 	
				<div class="group">
					<label for="login" class="label" style="margin-left:-160px;">Email</label>
					<c:if test="${param.fail}">
		<div class="col-sm-12 col-xs-12 text-left " id="alarm" style="color:red;text-align:left;font-size:12px; ">
 			Invalid email address or password
 		</div>
 	</c:if>
					<input id="login" name="login" type="text" class="input">
				</div>
				<div class="group">
					<label for="pass" class="label" style="margin-left:-150px;">Password</label>
					<input id="pass" type="password" name="password" class="input" data-type="password">
				</div>
				<div class="group">
					<input type="submit" class="button" value="Sign In">
				</div>
				<div class="hr"></div>
				</form:form>
			</div>
		</div>
	</div>
</div>
 		
 	</div>

 </html>