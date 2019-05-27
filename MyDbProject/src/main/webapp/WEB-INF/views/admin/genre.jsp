<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<div class="row">
	<div class="col-md-3 col-xs-12">
			<form:form class="form-inline" action="/admin/genre" method="GET" modelAttribute="filter">
			<custom:hiddenInputs excludeParams="search"/>
			<div class="form-group">
				<form:input path="search" class="form-control" placeholder="Search"/>
			</div>
			<button type="submit" class="btn btn-warning">Ok</button>
		</form:form>
	</div>
	<div class="col-md-7 col-xs-12">
		<div class="row">
			<div class="col-md-12 col-xs-12">
				<form:form class="form-horizontal" action="/admin/genre" method="POST" modelAttribute="genre">
					<div class="form-group">
						<label class="col-sm-10 col-sm-offset-2 control-label" for="type" style="color:red;text-align:left;font-size:12px;"><form:errors path="type"/></label>
					</div>
					<div class="form-group">
    					<label for="type" class="col-sm-2 control-label">Type:</label>
    					<div class="col-sm-10">
      						<form:input class="form-control" path="type" id="type"/>
    					</div>
  					</div>
  					<div class="form-group">
    					<div class="col-sm-offset-2 col-sm-10">
      						<button type="submit" class="btn btn-success">Create</button>
    					</div>
  					</div>
				</form:form>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4 col-xs-4"><h3>Type</h3></div>
		</div>
			<c:forEach items="${page.content}" var="genre">
				<div class="row">
					<div class="col-md-4 col-xs-4">${genre.type}</div>
					<div class="col-md-4 col-xs-4"><a class="btn btn-primary" href="/admin/genre/update/${genre.id}<custom:allParams/>">update</a></div>
					<div class="col-md-4 col-xs-4"><a class="btn btn-danger" href="/admin/genre/delete/${genre.id}<custom:allParams/>">delete</a></div>
				</div>
			</c:forEach>
	</div>
<div class="col-md-2 col-xs-12">
		<div class="row">
					<div class="col-md-6 col-xs-6 text-center">
						<div class="dropdown">
							<button class="btn btn-danger dropdown-toggle" type="button"
								data-toggle="dropdown">
								Sort <span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
								<custom:sort innerHtml="Type asc" paramValue="type" />
								<custom:sort innerHtml="Type desc" paramValue="type,desc" />
							</ul>
						</div>
					</div>
					<div class="col-md-6 col-xs-6 text-center">
						<custom:size posibleSizes="1,2,5,10" size="${page.size}" />
					</div>
				</div>
	</div>
</div>
<div class="row">
	<div class="col-md-12 col-xs-12 text-center">
		<custom:pageable page="${page}" cell="<li></li>"
			container="<ul class='pagination'></ul>" />
	</div>
</div>

