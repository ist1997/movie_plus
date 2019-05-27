
(function () {
	var len = hall.length;
	 $.ajax({
		  method: "GET",
		  url: "/orders/reservedseats.do",
		  data: { filmID:filmID,filmTime:time }
		})
		  .done(function( msg ) {
		    for(var i=0; i<len;i++){
		    	for (var j=0; j<msg.length;j++){
		    		if(hall[i].Row==msg[j].rowNumb && hall[i].Place==msg[j].seatNumb){
		    			hall[i].booked=true;
		    		}
		    	}
		    }
		    build();
		    $(".sit").each(function (index, elem) {
		        orderLogic($(this), index, elem);
		    });
		  });
    
    var id = function (id) {
        return document.getElementById(id);
    }
    var build = function () {
        for (var i = 0; i < len; i++) {
            var div = document.createElement("div");
            div.innerHTML = hall[i].Place;
            if (hall[i].booked) {
                div.className = "sit booked";
            } else {
                div.className = "sit active";
            };

            id("places").appendChild(div);
        };
    };
    var selected = [];
    var orderLogic = function (t, index, elem) {
        if (t.hasClass("active")) {
            $(elem).click(function () {
                if ($(this).css("background-color") == "rgb(255, 255, 255)") {
                    selected.push(index);
                    $(this).css({
                        backgroundColor: "darkgreen",
                        color: "white"
                    });

                } else {
                    selected.splice(selected.indexOf(index), 1);
                    $(this).css({
                        backgroundColor: "white",
                        color: "darkgreen"
                    })
                }

                if (selected.length === 1) {
                    $("#count").html("Ви забронювали 1 квиток");
                    $("#order-btn").css({
                        display: "inline-block"
                    })
                } else if (selected.length === 0) {
                    $("#order-btn").css({
                        display: "none"
                    })
                    $("#count").html("Ви забронювали " + selected.length + " квитків");
                } else {
                    $("#count").html("Ви забронювали " + selected.length + " квитків");
                }
                $("#price").html(selected.length * price + " грн.");
                console.log(selected);
                id("orders-size").innerHTML = "";
                for (var i = 0; i < selected.length; i++) {
                    var p = document.createElement("p");
                    p.className = "order-info";
                    var span1 = document.createElement("span");
                    span1.className = "order-row";
                    span1.innerHTML = "Ряд: " + hall[selected[i]].Row;
                    span2 = document.createElement("span");
                    span2.className = "order-place";
                    span2.innerHTML = "Місце: " + hall[selected[i]].Place;
                    span3 = document.createElement("span");
                    span3.className = "order-price";
                    span3.innerHTML = "Ціна: "+price+"грн.";
                    p.appendChild(span1);
                    p.appendChild(span2);
                    p.appendChild(span3);
                    var img = document.createElement("img");
                    img.className = "cancel";
                    img.src = "/resources/img/cancel.png";
                    img.alt = selected[i];
                    p.appendChild(img);
                    id("orders-size").appendChild(p);
                };
                $(".cancel").click(function () {
                    var i = parseInt($(this).attr("alt"));
                    console.log(selected.indexOf(i));
                    $(".order-info").eq(selected.indexOf(i)).remove();
                    $(".sit").eq(selected[selected.indexOf(i)]).css({
                        backgroundColor: "white",
                        color: "darkgreen"
                    })
                    console.log(i);
                    selected.splice(selected.indexOf(i), 1);
                    console.log(selected);
                    if (selected.length === 1) {
                        $("#count").html("Ви забронювали 1 квиток");
                        $("#order-btn").css({
                            display: "inline-block"
                        })
                    } else if (selected.length === 0) {
                        $("#order-btn").css({
                            display: "none"
                        })
                        $("#count").html("Ви забронювали " + selected.length + " квитків");
                    } else {
                        $("#count").html("Ви забронювали " + selected.length + " квитків");
                    }
                    $("#price").html(selected.length * price + " грн.");
                });
            });
        };
    }

    $("#order-btn").click(function () {
    	var add=[];
        for (var i = 0; i < selected.length; i++) {
            hall[selected[i]].booked = true;
            add.push(hall[selected[i]]);
            $(".sit").eq(selected[i]).addClass("booked");
            $(".sit").eq(selected[i]).removeClass("active");
            $(".sit").eq(selected[i]).css({
                border: "2px solid silver",
                background: "white",
                color: "silver"
            });
        }
        selected = [];
        $("#order-btn").css({
            display: "none"
        });
        id("orders-size").innerHTML = "";
        $("#count").html("Ви забронювали " + selected.length + " квитків");
        $("#price").html(selected.length * price + " грн.");
        $(".sit").off();
        $(".sit").each(function (index, elem) {
            orderLogic($(this), index, elem);
        });
//        $.ajax({
// 			url:'/orders/create',
// 			data: ({filmName:name,filmTime:time,price: price,userId:userID,rowNumber:userID,seatNumber:1}),
// 			success: function(data){
// 				console.log(data);	
// 			},
// 			error: function(xhr, status, error) {
// 				
// 				alert(error);
// 				 
// 				}
// 		});
        for (var i=0;i<add.length;i++){
				 $.ajax({
					  method: "GET",
					  url: "/orders/create",
					  data: { filmID:filmID,filmTime:time,price: price,userId:userID,rowNumber:add[i].Row,seatNumber:add[i].Place },
					  dataType: 'JSON'
					})
					  .done(function( msg ) {
						  console.log('gg');
					    alert( "Data Saved: " + msg );
					  });
        }
        	    event.preventDefault();
        	  });

})();


