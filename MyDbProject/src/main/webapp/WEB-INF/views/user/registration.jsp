<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
 <!DOCTYPE HTML>
<html>
	<head>
		<meta charset ="utf-8">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="/resources/css/logAndRegStyles.css" type="text/css">
		<style type="text/css">
		
		</style>
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script type="text/javascript">
		 	function doAjax() {
		 		$.ajax({
		 			url:'films/checkStrength',
		 			data: ({password: $('#password').val()}),
		 			success: function(data){
		 				$('#strengthValue').html(data);
		 			}
		 		});
		 	}
		</script>
	</head>
	<div class="row" style="background:url(/resources/img/40125669-cinema-wallpapers.jpg) no-repeat center;
	background-size: cover; margin-top:-20px;">
	
  		<div class="login-wrap">
	<div class="login-html">
		<input id="tab-2" type="radio" name="tab" class="sign-up" checked><label for="tab-2" class="tab">Sign Up</label>
		<div class="login-form">
 	<div class="sign-up-htm">
				<form:form class="form-horizontal" action="/registration" method="POST" modelAttribute="user" enctype="multipart/form-data">  			
				<div class="group">
					<label for="name" style="margin-left:-150px;" class="label">Name</label>
					<label class="col-sm-10 col-sm-offset-0 control-label" for="name" style="color:red;text-align:left;font-size:12px; margin-top:-10px;"><form:errors path="name"/></label>
					<input id="name" name="Name" type="text" class="input">
				</div>
				<div class="group">
					<label for="secondName" style="margin-left:-130px;" class="label">SecondName</label>
					<label class="col-sm-10 col-sm-offset-0 control-label" for="secondName" style="color:red;text-align:left;font-size:12px; margin-top:-10px;"><form:errors path="secondName"/></label>
					<input id="secondName" name="secondName" type="text" class="input">
				</div>
				<div class="group">
					<label for="mobile" style="margin-left:-145px;" class="label">Mobile</label>
					<label class="col-sm-10 col-sm-offset-0 control-label" for="mobile" style="color:red;text-align:left;font-size:12px; margin-top:-10px;"><form:errors path="mobile"/></label>
					<input id="mobile" name="mobile" type="text" class="input">
				</div>
				<div class="group">
					<label for="email" style="margin-left:-150px;" class="label">Email </label>
					<label class="col-sm-10 col-sm-offset-0 control-label" for="email" style="color:red;text-align:left;font-size:12px; margin-top:-10px;"><form:errors path="email"/></label>
					<input id="email" name="email" type="text" class="input">
				</div>
				<div class="group">
					<label for="pass" style="margin-left:-135px;" class="label">Password</label>
					<label class="col-sm-10 col-sm-offset-0 control-label" for="password" style="color:red;text-align:left;font-size:12px; margin-top:-10px;"><form:errors path="password"/></label>	
					<input id="password" name="password" type="password" class="input" data-type="password" onkeyup="doAjax()">
					<span id="strengthValue" style="margin-left: 86%; margin-top: 10px;font-size: 15px;"></span>
				</div>
				<div class="group">
					<input type="submit" class="button" value="Sign Up">
				</div>
				<div class="hr"></div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>