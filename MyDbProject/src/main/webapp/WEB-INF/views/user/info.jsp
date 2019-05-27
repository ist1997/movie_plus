<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css">
<style type="text/css">
.body {
	margin-top: 20px;
}

.mainImage {
	margin-left: 60px;
	width: 250px;
	height: 400px;
}

.text {
	margin-top: 10px;
	font-family: "Open Sans", Arial, sans-serif;
	font-size: 16px;
}

.rText {
	margin-top: 5px;
	text-align: center;
	font-family: "Open Sans", Arial, sans-serif;
	font-size: 16px;
}

.text h1 {
	margin-bottom: 20px;
}

.title {
	font-weight: 800;
	font-size: 13px;
}

.videoClass video {
	width: 100%;
	margin-top: 50px;
	margin-bottom: 50px;
	height: 450px;
}
</style>
<script src="https://code.jquery.com/jquery-2.2.4.min.js"
	integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>

</head>
<body>
	<script>
		var authCheck = 0;
	</script>
	<div class="row" style="margin-top: 40px; margin-bottom: 40px;">
		<div class="col-md-1 col-xs-12"></div>
		<div class="col-md-7 col-xs-12 text">
			<h1>${filmInfo.name}</h1>
			${filmInfo.info}
			<div class="videoClass">
				<video controls
					src="/videos/film/${filmInfo.id}.mp4?version=${filmInfo.videoVersion}"></video>
				<div style="margin-top: -30px;">
					<div id="amountMarks" style="float: right;font-weight: bold">(${amountOfMarks})</div>
					<div id="markRates" style="float: right;font-weight: bold">${markRate}</div>
					<div id="rateYo" style="float: right;"></div>
				</div>
			</div>
			<h2>Comments</h2>
			<sec:authorize access="isAuthenticated()">
				<sec:authorize access="hasRole('ROLE_USER')">
					<script>
						var userCom = <sec:authentication property="principal.id" />;
						var filmCom = '${filmInfo.id}';
						authCheck = 1;
					</script>
					<div id="errorText" style="color: red;"></div>
					<div class="comment">
						<textarea id="commentSpace" style="width: 85%; height: 70px;"></textarea>
						<div>
							<input type="button" class="btn btn-success" id='CreationCom'
								style="margin-top: 7px; margin-left: 78%;" value="create">
						</div>
					</div>
				</sec:authorize>
			</sec:authorize>
			<div id=commentContainer>
				<c:forEach items="${comments}" var="comment">
					<div class="row" style="margin-top: 20px;">
						<div style="margin-bottom: 7px;">
							<b>${comment.userComment.name}
								${comment.userComment.secondName}</b> <span
								style="margin-left: 5px; font-size: 12px;">${comment.timeOfComment}</span>
						</div>
						<div
							style="width: 85%; float: left; border: 1px solid; min-height: 70px; padding: 7px; border-color: green; border-radius: 10px;">${comment.textOfComment}</div>
						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<div>
								<a class="btn btn-danger"
									style="margin-top: 1px; margin-left: 10px; margin-top: 18px; border-radius: 10px;"
									href="/deleteComment/${filmInfo.id}/${comment.id}">delete</a>
							</div>
						</sec:authorize>
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="col-md-3 col-xs-12" style="text-align: center">
			<div class="row">
				<img
					src="/images/film/${filmInfo.id}.jpg?version=${filmInfo.version}"
					class="mainImage" style="display: block;margin-left: auto;margin-right: auto;">
			</div>
			<div class="row rText title" style="text-align: center">Premier date:</div>
			<div class="row rText">${filmInfo.date}</div>
			<div class="row rText title">Genre:</div>
			<div class="row rText">${filmInfo.ganre.type}</div>
			<div class="row rText title">Country:</div>
			<div class="row rText">${filmInfo.nationality.name}</div>
			<div class="row rText title">Language:</div>
			<div class="row rText">Ukrainian</div>
			<div class="row rText title">Producer:</div>
			<div class="row rText">${filmInfo.producer.name}</div>
			<div class="row rText title">In main roles:</div>
			<div class="row rText">
				<c:forEach items="${filmInfo.actors}" var="actors">	
						${actors.name}
					</c:forEach>
			</div>
			<div class="row rText title">Duration:</div>
			<div class="row rText">${filmInfo.duration}min</div>
			<div class="row rText title">Age limit:</div>
			<div class="row rText">${filmInfo.limit}+</div>
		</div>
	</div>
	<script type="text/javascript">
		  $("#rateYo").rateYo({
			starWidth : "20px",
			rating : ${markRate},
			halfStar : true,
			readOnly : authCheck ? false : true
		});
		$("#rateYo").rateYo().on("rateyo.set", function(e, data) {
			$.ajax({
				url : '/createMark',
				data : ({
					rate : data.rating,
					userId : userCom,
					filmID : filmCom
				}),
				success : function(data) {
                    $("#rateYo").rateYo("destroy");
                    $("#rateYo").rateYo({
                        starWidth : "20px",
                        rating : data.sumOfMarks,
                        halfStar : true,
                    });
                    $("#markRates").html(data.sumOfMarks);
                    $("#amountMarks").html("("+data.amount+")");
				}
			});
		});
		$('#CreationCom')
				.click(
						function() {
							if ($('#commentSpace').val()) {
								$
										.ajax({
											url : '/createComment',
											data : ({
												text : $('#commentSpace').val(),
												userId : userCom,
												filmID : filmCom
											}),
											success : function(data) {
												console.log(data);
												$('#commentContainer')
														.append(
																'<div style="margin-top: 20px;"><div style="margin-bottom:7px;"><b>'
																		+ data.userName
																		+ ' '
																		+ data.userSecondName
																		+ '</b> <span style="margin-left: 5px; font-size: 12px;">'
																		+ data.timeOfComment
																		+ '</span> </div>	<div style="width:85%; border: 1px solid; min-height: 70px;padding:7px; border-color:green;border-radius: 10px;">'
																		+ $(
																				'#commentSpace')
																				.val()
																		+ '</div></div>')
												$('#commentSpace').val("");
												$('#errorText').html("");

											}
										});
							} else {
								$('#errorText').html("Write something!")
							}
						})
	</script>
</body>
</html>