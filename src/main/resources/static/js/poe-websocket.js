$(document).ready(function () {
    var stompClient = null;

    console.log('trying: ');
    var socket = new SockJS('/poe-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/newRide', function (ride) {
            var jsonObject = JSON.parse(ride.body);
            updateRide(jsonObject);
        });
    });

    function sendName() {
        stompClient.send("/app/newRide", {}, JSON.stringify({'fromCity': $("#name").val()}));
    }

    function updateRide(message) {
        console.log(message);
        $("#rides").append("<tr><td>" + message.fromCity + "</td></tr>");
    }

});