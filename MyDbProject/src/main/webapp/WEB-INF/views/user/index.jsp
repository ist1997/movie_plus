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
        <link rel="stylesheet" href="/resources/css/filmStyle.css" type="text/css">
        <link rel="stylesheet" href="/resources/css/slick.css">
		<link rel="stylesheet" href="/resources/css/slick-th.css">
		<link rel="stylesheet" href="/resources/css/sliderStyle.css">
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</head>
<style>
	.filter .control-label{
		text-align: left;
	}
	.filter span{
		display: block;
	}
</style>
<section class="slider single-item" style="width:100%; margin-top:-20px;">
	 <div>
      <img src="resources/img/alfa-film-2018-goda-1.jpg">
    </div>
    <div>
      <img src="resources/img/johnnyenglish2_2.jpg">
    </div>
    <div>
      <img src="resources/img/image_youtube_26979.jpg">
    </div>
    <div>
      <img src="resources/img/kinoplexx-rampage-background-site.jpg">
    </div>
    <div>
      <img src="resources/img/maxresdefault.jpg">
    </div>
    <div>
      <img src="resources/img/maxresdefault (1).jpg">
    </div>
    <div>
      <img src="resources/img/Mesnyky-3-Vijna-neskinchennosti-pereyihaly.jpg">
    </div>
    <div>
      <img src="resources/img/trailer_13572.jpg">
    </div>
</section>
	
	
 <script src="https://code.jquery.com/jquery-2.2.0.min.js" type="text/javascript"></script>
  <script src="/resources/js/slick.min.js" type="text/javascript" charset="utf-8"></script>
  <script src="/resources/js/main.js"></script>

   <div class="row" style="margin-left:200px;">
   		<h1 style="font-size:40px; font-weight: bold; margin-bottom: 30px; ">Poster of all films from different cinemas	 in Lviv:</h1>
		<c:forEach items="${page.content}" var="films">
					<div class="bot">	
					<div><a href="/info/${films.id}<custom:allParams/>"><img src="/images/film/${films.id}.jpg?version=${films.version}" width="233px" height="344px" style="margin-top:15px;"></a></div>
					<div class="col-md-3 col-xs-1 " style="margin-bottom:25px;margin-left:22px; margin-top:6px;" id="films-name"><a href="/info/${films.id}<custom:allParams/>" style="font-size:20px;line-height:24px;font-weight:500">${films.name}</a></div>
					</div>
			</c:forEach>	
   </div>

		<div class="row">
			<div class="col-md-12 col-xs-12 text-center view">
				<custom:pageable page="${page}" cell="<li></li>"
				container="<ul class='pagination'></ul>" />
			</div>
		</div>

		

 	
			