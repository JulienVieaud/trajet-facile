$(document).ready(function () {
    var stompClient = null;

    console.log('trying: ');
    var socket = new SockJS('/poe-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/newRide', function (ride) {
            updateRide(JSON.parse(ride.body).content);
        });
    });


    function sendName() {
        stompClient.send("/app/newRide", {}, JSON.stringify({'fromCity': $("#name").val()}));
    }

    function updateRide(message) {
        $("#greetings").append("<tr><td>" + message + "</td></tr>");



    }

    $(function () {
        $("form").on('submit', function (e) {
            e.preventDefault();
        });

        $("#send").click(function () {
            sendName();
        });
    });
});