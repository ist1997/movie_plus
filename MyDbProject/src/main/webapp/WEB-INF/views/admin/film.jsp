<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
	.filter .control-label{
		text-align: left;
	}
	.filter span{
		display: block;
	}
</style>
<div class="row">
 	<div class="col-md-3 col-xs-12">
 	<form:form class="form-horizontal filter" action="/admin/film" method="GET" modelAttribute="filter">
			<custom:hiddenInputs excludeParams="genresId,search,max, min"/>
			<div class="form-group">
				<div class="col-sm-6">
					<form:input path="min" class="form-control" placeholder="Min"/>
				</div>
				<div class="col-sm-6">
					<form:input path="max" class="form-control" placeholder="Max"/>
				</div>
			</div>
			<div class="form-group">
				<form:input path="search" class="form-control" placeholder="Search by name"/>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-12 " style="text-align:left;font-size:20px;">Ganres:</label>
				<div class="col-sm-12" style="text-align:left;font-size:12px;">
					<form:checkboxes items="${genres}" path="genresId" itemLabel="type" itemValue="id"/>
				</div>
			</div>
			<button type="submit" class="btn btn-warning">Ok</button>
		</form:form>

 	</div>
 	<div class="col-md-9 col-xs-9">
		<div class="row">
			<div class="col-md-9 col-xs-11">
			</div>
					<div class="col-md-1 col-xs-11 text-center">
						<div class="dropdown">
							<button class="btn btn-danger dropdown-toggle" type="button"
								data-toggle="dropdown">
								Sort <span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
								<custom:sort innerHtml="Name asc" paramValue="name" />
								<custom:sort innerHtml="Name desc" paramValue="name,desc" />
								<custom:sort innerHtml="Ganre type asc" paramValue="ganre.type" />
								<custom:sort innerHtml="Ganre type desc" paramValue="ganre.type,desc" />
							</ul>
						</div>
					</div>
					<div class="col-md-2 col-xs-6 text-center">
						<custom:size posibleSizes="1,2,5,10" size="${page.size}" />
					</div>
				</div>
	</div>
 	<div class="col-md-7 col-xs-12">
 		<div class="row">
 			<div class="col-md-12 col-xs-12">
			<form:form class="form-horizontal" action="/admin/film" method="POST" modelAttribute="film" enctype="multipart/form-data">
					<custom:hiddenInputs excludeParams="name, file,videoFile,date,ganre,nationality,producer,price,duration,limit,info"/>
					<form:hidden path="id"/>
					<form:hidden path="version"/>
					<form:hidden path="videoVersion"/>
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
						<label class="col-sm-10 col-sm-offset-2 control-label" for="date" style="color:red;text-align:left;font-size:12px;"><form:errors path="date"/></label>
					</div>
   					<div class="form-group">
     					<label for="date" class="col-sm-2 control-label">Date:</label>
     					<div class="col-sm-10">
     						<form:input style="margin-left:0px;" type="text" class="form-control" path="date" id="date"/>
     					</div>
   					</div>
 					<div class="form-group">
     					<label for="ganre" class="col-sm-2 control-label">Ganre:</label>
     					<div class="col-sm-10">
      						<form:select class="form-control" path="ganre" id="ganre" items="${genres}" itemValue="id" itemLabel="type"/>
     					</div>
   					</div>
   					 	<div class="form-group">
     					<label for="country" class="col-sm-2 control-label">Country:</label>
     					<div class="col-sm-10">
      						<form:select class="form-control" path="nationality" id="country" items="${countries}" itemValue="id" itemLabel="name"/>
     					</div>
   					</div>
   					<div class="form-group">
     					<label for="producer" class="col-sm-2 control-label">Producer:</label>
     					<div class="col-sm-10">
     						<form:select class="form-control" path="producer" id="producer" items="${producers}" itemValue="id" itemLabel="name"/>
     					</div>
   					</div>
   					<div class="form-group">
						<label class="col-sm-10 col-sm-offset-2 control-label" for="price" style="color:red;text-align:left;font-size:12px;"><form:errors path="price"/></label>
					</div>
   					   	<div class="form-group">
     					<label for="price" class="col-sm-2 control-label">Price:</label>
     					<div class="col-sm-10">
     						<form:input type="text" class="form-control" path="price" id="price"/>
     					</div>
   					</div>
   					
   					<div class="form-group">
						<label class="col-sm-10 col-sm-offset-2 control-label" for="duration" style="color:red;text-align:left;font-size:12px;"><form:errors path="duration"/></label>
					</div>
 					<div class="form-group">
     					<label for="duration" class="col-sm-2 control-label">Duration:</label>
     					<div class="col-sm-10">
     						<form:input type="text" class="form-control" path="duration" id="duration"/>
     					</div>
   					</div>
   					
   					<div class="form-group">
						<label class="col-sm-10 col-sm-offset-2 control-label" for="info" style="color:red;text-align:left;font-size:12px;"><form:errors path="info"/></label>
					</div>
 					<div class="form-group">
     					<label for="info" class="col-sm-2 control-label">Text:</label>
     					<div class="col-sm-10">
     						<form:textarea path="info" id="info" style="width:100%; height:200px;"/>
     					</div>
   					</div>
   					
   					<div class="form-group">
						<label class="col-sm-10 col-sm-offset-2 control-label" for="limit" style="color:red;text-align:left;font-size:12px;"><form:errors path="limit"/></label>
					</div>
 					<div class="form-group">
     					<label for="limit" class="col-sm-2 control-label">Limit:</label>
     					<div class="col-sm-10">
     						<form:input type="text" class="form-control" path="limit" id="limit"/>
     					</div>
   					</div>
   				
   				<div class="form-group">
					<label class="col-sm-10 col-sm-offset-2 control-label" for="actors" style="color:red;text-align:left;font-size:12px;"><form:errors path="actors"/></label>
					</div>
   				<div class="form-group ">
                <label for="actors" class="col-sm-2 control-label">Actors:</label>
                <div class="col-sm-10">
                    <form:select path="actors" items="${actors}" multiple="true" itemValue="id" itemLabel="name" class="form-control input-sm" />
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
						<label class="col-sm-10 col-sm-offset-2 control-label" for="videoFile" style="color:red;text-align:left;font-size:12px;"><form:errors path="videoFile"/></label>
					</div>	
  					<div class="form-group">
    					<label for="videoFile" class="col-sm-2 control-label">Trailer</label>
    					<div class="col-sm-10">
      						<input type="file" name="videoFile" id="videoFile">
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
 	</div>	
 	 <div class="col-md-2 col-md-12"></div>
 	<div class="col-md-12 col-md-12">
 	<div class="row">
 			<div class="col-md-1 col-xs-1"><h3>Photo:</h3></div>
			<div class="col-md-2 col-xs-1"><h3 style="padding-left: 30px;">Name:</h3></div>
			<div class="col-md-1 col-xs-1"><h3>Date:</h3></div>
			<div class="col-md-1 col-xs-1"><h3>Genre:</h3></div>
			<div class="col-md-1 col-xs-1"><h3>Country:</h3></div>
			<div class="col-md-2 col-xs-1"><h3>Producer:</h3></div>
			<div class="col-md-1 col-xs-1"><h3>Price:</h3></div>
		</div>
			<c:forEach items="${page.content}" var="film">
				<div class="row" style="margin-bottom: 15px;">
					<div class="col-md-1 col-xs-1"><img src="/images/film/${film.id}.jpg?version=${film.version}" style="margin-top:7px; width: 160px ;height: 236px;"></div>
					<div class="col-md-2 col-xs-1" style="padding-left: 60px;">${film.name}</div>
					<div class="col-md-1 col-xs-1">${film.date}</div>
					<div class="col-md-1 col-xs-1">${film.ganre.type}</div>
					<div class="col-md-1 col-xs-1"><img src="/images/country/${film.nationality.id}.jpg?version=${film.nationality.version}" width="20%" style="margin-right:10px;" >${film.nationality.name}</div>
					<div class="col-md-2 col-xs-1">${film.producer.name}</div>
					<div class="col-md-1 col-xs-1">${film.price}</div>
					<div class="col-md-1 col-xs-1"><a class="btn btn-primary" href="/admin/film/update/${film.id}<custom:allParams/>">update</a></div>
					<div class="col-md-1 col-xs-1"><a class="btn btn-danger" href="/admin/film/delete/${film.id}<custom:allParams/>">delete</a></div>
				</div>
			</c:forEach>
	</div>
</div>
<div class="row">
	<div class="col-md-12 col-xs-12 text-center">
		<custom:pageable page="${page}" cell="<li></li>"
			container="<ul class='pagination'></ul>" />
	</div>
</div>