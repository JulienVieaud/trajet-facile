$(document).ready(function () {
    var stompClient = null;
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
        var tableRides = $("#rides");
        var htmlRideLine;

        $.ajax({
            url: "/ride/" + message.id,
            datatype: "html",
            type: 'GET',
            success: function (html, status) {
                tableRides.append("<tr>" + html + "</tr>");
            }
        });
    }

    $("#search").on("keyup", function () {
        var txt = $("#search").val();
        delay(function () {
            $.ajax({
                url: "/ride/searchAjax?search=" + txt,
                datatype: "html",
                type: 'GET',
                success: function (html, status) {
                    $("#rides").html(html);
                }
            });
        }, 150);
    });

    var delay = (function () {
        var timer = 0;
        return function (callback, ms) {
            clearTimeout(timer);
            timer = setTimeout(callback, ms);
        };
    })();

});