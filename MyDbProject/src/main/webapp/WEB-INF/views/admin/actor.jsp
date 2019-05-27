<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<style>
	.filter .control-label{
		text-align: left;
	}
	.filter span{
		display: block;
	}
</style>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<div class="row">
 	<div class="col-md-3 col-xs-12">
 	<form:form class="form-horizontal filter" action="/admin/actor" method="GET" modelAttribute="filter">
			<custom:hiddenInputs excludeParams="countriesId,search"/>
			<div class="form-group">
				<form:input path="search" class="form-control" placeholder="Search by name"/>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-12 " style="text-align:left;font-size:20px;">Countries:</label>
				<div class="col-sm-12" style="text-align:left;font-size:12px;">
					<form:checkboxes items="${countries}" path="countriesId" itemLabel="name" itemValue="id"/>
				</div>
			</div>
			<button type="submit" class="btn btn-warning">Ok</button>
		</form:form>
 	</div>
 	<div class="col-md-7 col-xs-12">
 		<div class="row">
 			<div class="col-md-12 col-xs-12">
					<form:form class="form-horizontal" action="/admin/actor" method="POST" modelAttribute="actor" enctype="multipart/form-data">
					<custom:hiddenInputs excludeParams="name, file,age,nationality"/>
					<form:hidden path="id"/>
					<form:hidden path="version"/>
 					<div class="form-group">
						<label class="col-sm-10 col-sm-offset-2 control-label" for="name" style="color:red;text-align:left;font-size:12px;"><form:errors path="name"/></label>
					</div>
 					<div class="form-group">
     					<label for="name" class="col-sm-2 control-label">Name:</label>
     					<div class="col-sm-10">
     						<form:input type="text" class="form-control" path="name" id="name"/>
     					</div>
   					</div>
   					 	<div class="form-group">
     					<label for="country" class="col-sm-2 control-label">Country:</label>
     					<div class="col-sm-10">
      						<form:select class="form-control" path="nationality" id="country" items="${countries}" itemValue="id" itemLabel="name"/>
     					</div>
   					</div>
   					 <div class="form-group">
						<label class="col-sm-10 col-sm-offset-2 control-label" for="age" style="color:red;text-align:left;font-size:12px;"><form:errors path="age"/></label>
					</div>
   					 <div class="form-group">
     					<label for="age" class="col-sm-2 control-label">Age:</label>
     					<div class="col-sm-10">
     						<form:input type="text" class="form-control" path="age" id="age"/>
     					</div>
   					</div>
   					 <div class="form-group">
						<label class="col-sm-10 col-sm-offset-2 control-label" for="file" style="color:red;text-align:left;font-size:12px;"><form:errors path="file"/></label>
					</div>
   					<div class="form-group">
    					<label for="file" class="col-sm-2 control-label">Image</label>
    					<div class="col-sm-10">
      						<input type="file" name="file" id="file">
    					</div>
  					</div>
   					<div class="form-group">
     					<div class="col-sm-offset-10 col-sm-2">
       						<button type="submit" class="btn btn-success">Create</button>
     					</div>
   					</div>
				</form:form>
 			</div>
 		</div> 
 	<div class="row">
 			<div class="col-md-2 col-xs-1"><h3 style="padding-left: 30px;">Photo</h3></div>
			<div class="col-md-2 col-xs-1"><h3>Name</h3></div>
			<div class="col-md-2 col-xs-1"><h3>Country</h3></div>
			<div class="col-md-2 col-xs-1"><h3>Age</h3></div>
		</div>
			<c:forEach items="${page.content}" var="actor">
				<div class="row">
					<div class="col-md-2 col-xs-1"><img src="/images/actor/${actor.id}.jpg?version=${actor.version}" width="100%" style="margin-top:7px; border-radius: 70px;"></div>
					<div class="col-md-2 col-xs-1">${actor.name}</div>
					<div class="col-md-2 col-xs-1"><img src="/images/country/${actor.nationality.id}.jpg?version=${actor.nationality.version}"  style="margin-right:10px;width:33px;height: 18px;" >${actor.nationality.name}</div>
					<div class="col-md-2 col-xs-1">${actor.age}</div>
					<div class="col-md-2 col-xs-1"><a class="btn btn-primary" style=" margin-top: 1px;" href="/admin/actor/update/${actor.id}<custom:allParams/>">update</a></div>
					<div class="col-md-2 col-xs-1"><a class="btn btn-danger" style="margin-top: 1px;" href="/admin/actor/delete/${actor.id}<custom:allParams/>">delete</a></div>	
					
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
								<custom:sort innerHtml="Name asc" paramValue="name" />
								<custom:sort innerHtml="Name desc" paramValue="name,desc" />
								<custom:sort innerHtml="Country name asc" paramValue="nationality.name" />
								<custom:sort innerHtml="Country name desc" paramValue="nationality.name,desc" />
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