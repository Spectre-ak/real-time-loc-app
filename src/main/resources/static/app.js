var stompClient = null;
var connect=false;
function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

//connecting to the backend
var socket = new SockJS('/gs-guide-websocket');
stompClient = Stomp.over(socket);
stompClient.connect({}, function (frame) {
    setConnected(true);

    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/greetings', function (greeting) {
        console.log(greeting)
        showGreeting(greeting.body);
    });

    stompClient.subscribe('/topic/coordinatesGlobalReceive',function(response){
        //console.log("received coorddinates "+response);
    });

    
});


function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({"name":"aksh"}));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});




function sendMsgToPathVar(){
    stompClient.send("/app/testingPathVariable", {}, JSON.stringify({"name":"randomPaths"}));
}