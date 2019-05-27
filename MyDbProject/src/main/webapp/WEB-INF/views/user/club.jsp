<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Club</title>
</head>
<body>
	<div id="main-orders" style="min-height:762px; margin-bottom: 50px;">
		<c:if test="${userReservation.size() != 0}">
			<h1 style="text-align: center;"><b>Your orders:</b></h1>
			<div class="row" style="margin-left: 7%;">
		 			<div class="col-md-2 col-xs-1" style="padding-left: 65px;"><h2><b>Film</b></h2></div>
					<div class="col-md-2 col-xs-1"><h2><b>Time</b></h2></div>
					<div class="col-md-2 col-xs-1"><h2><b>Row</b></h2></div>
					<div class="col-md-2 col-xs-1"><h2><b>Seat</b></h2></div>
					<div class="col-md-2 col-xs-1"><h2><b>Price</b></h2></div>	
			</div>	
					<c:forEach items="${userReservation}" var="reservation">
						<div class="row" style="margin-left: 7%;">
							<div class="col-md-2 col-xs-1"><a href="/info/${reservation.filmreserve.id}"><img src="/images/film/${reservation.filmreserve.id}.jpg?version=${reservation.filmreserve.version}"  style="margin-top:7px; width:160px;height: 236px;"></a>
								<div class="row" style="font-size: 11px;margin-left:2px;margin-top:10px; font-weight: 700;line-height: 12px;">${reservation.filmreserve.name}</div>
							</div>
							<div class="col-md-2 col-xs-1" style="font-weight: 700; font-size: 18px; margin-top: 90px; padding-left: 23px;">${reservation.filmTime}</div>
							<div class="col-md-2 col-xs-1" style="font-weight: 700; font-size: 18px; margin-top: 90px; padding-left: 38px;">${reservation.rowNumber}</div>	
							<div class="col-md-2 col-xs-1" style="font-weight: 700; font-size: 18px; margin-top: 90px; padding-left: 38px;">${reservation.seatNumber}</div>	
							<div class="col-md-2 col-xs-1" style="font-weight: 700; font-size: 18px; margin-top: 90px; padding-left: 38px;">${reservation.price}</div>
							<div class="col-md-2 col-xs-1"><a class="btn btn-danger" style=" margin-top: 90px; width: 160px; height: 35px;" href="/delete/${reservation.user.id}/${reservation.id}"><b>cancel reservation</b></a></div>		
						</div> 
					</c:forEach>		
		</c:if>	
	<c:if test="${userReservation.size() == 0}"><h1 style="text-align: center;padding-top: 70px;"><b>You didn't reserve any seat!</b></h1></c:if>
	</div>
</body>
</html>