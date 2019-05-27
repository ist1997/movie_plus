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

<div class="row" id="heightOfFilm">
 	<div class="col-md-3 col-xs-12" id="cont">
 	<form:form class="form-horizontal filter " action="/films" method="GET" modelAttribute="filter">
			<custom:hiddenInputs excludeParams="genresId,search,max, min"/>
			<div class="form-group">
			</div>
			<div class="form-group" style="margin-left:0px; padding-top: 30px;">
				<form:input path="search" class="form-control" placeholder="Search film by name"/>
			</div>
			<div class="form-group ">
				<label class="control-label col-sm-12 " style="text-align:left;font-size:20px;">Ganres:</label>
				<div class="col-sm-12" style="text-align:left;font-size:12px;">
					<form:checkboxes items="${genres}" path="genresId" itemLabel="type" itemValue="id"/>
				</div>
			</div>
			<button type="submit" class="btn btn-success">Ok</button>
			<div class=" text-center">
						<div class="dropdown">
							<button class="btn btn-success dropdown-toggle" type="button" class="btn-drop"
								data-toggle="dropdown">
								Sort <span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
								<custom:sort innerHtml="Name asc" paramValue="name" />
								<custom:sort innerHtml="Name desc" paramValue="name,desc" />
								<custom:sort innerHtml="Ganre type asc" paramValue="ganre.type" />
								<custom:sort innerHtml="Ganre type desc" paramValue="ganre.type,desc" />
							</ul>
							<div id="btn-size" class="btn-drop"><custom:size posibleSizes="1,2,5,10" size="${page.size}" /></div>
							
						</div>
					</div>
		</form:form>
		
   <div class="row" style="margin-left:200px;">
   		<h1 style="font-size:40px; font-weight: bold; margin-bottom: 30px; ">Poster Cinema Planet in Lviv</h1>
		<c:forEach items="${page.content}" var="films">
					<div class="bot">
					<div><a href="/info/${films.id}<custom:allParams/>"><img src="/images/film/${films.id}.jpg?version=${films.version}" width="160px" height="236px" style="margin-top:15px;"></a></div>
					<div class="col-md-3 col-xs-1 " style="margin-bottom:25px; margin-top:6px; text-align: center;" id="films-name"><a href="/info/${films.id}<custom:allParams/>">${films.name}</a></div>
					</div>
			</c:forEach>	
   </div>

		<div class="row">
			<div class="col-md-12 col-xs-12 text-center view">
				<custom:pageable page="${page}" cell="<li></li>"
				container="<ul class='pagination'></ul>" />
			</div>
		</div>

 </div>
 </div>
 	
			