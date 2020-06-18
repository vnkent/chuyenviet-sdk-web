
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

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);

        userID = getCookie("userID");
        var urlResult = "/topic/sdkCommandResult/" + userID;

        stompClient.subscribe(urlResult, function (greeting) {
            var apiRequest = JSON.parse(greeting.body).apiRequest;
            if (apiRequest == 'LIST_DEVICE_ADD') {
                loadData(greeting);
            } else if (apiRequest == 'ADD_FINGER') {
                // var isSuccess = JSON.parse(greeting.body).data.status;
                stopLoading();
                // if (isSuccess) {
                //     window.location.href = '/connect_devices';
                // } else {
                //     setTimeout(showMessage('Error add device'), 500);
                // }
            }
        });
        sendName();
    });
}

function loadData(data) {
    $("#devices").empty();
    var jsonData = JSON.parse(data.body).data.list_device;
    var lists = parseJsonToArrayList(jsonData);
    if (lists.length > 0) {
        for (var i = 0; i < lists.length; i++) {
            showGreeting(i, lists[i]);
        }
    }
    stopLoading();
}

function sendName() {
    startLoading();
    stompClient.send("/app/sdkCommand", {}, JSON.stringify({'apiRequest': 'LIST_DEVICE_ADD', 'dataInput' : {"userID": userID}}));
}

function addEvent(e) {
    startLoading();
    var deviceID = $( "#devices" ).val();
    var selectUserId = e.getAttribute('userID');
    var type = e.getAttribute('type');
    var apiRequest = "ADD_CARD";
    if (type == 1) {
        console.log("add finger");
        apiRequest = "ADD_FINGER";
    } else if (type == 2) {
        console.log("add face");
        apiRequest = "ADD_FACE";
    }
    stompClient.send("/app/sdkCommand", {}, JSON.stringify({'apiRequest': apiRequest, 'dataInput' :
            {'userID' : userID, 'selectUserId' : selectUserId, 'deviceID' : deviceID}}));
}

function showGreeting(index, item) {
    var value = item.type + " - " + item.iPAddr_ + " - " + item.port;
    var option = '<option value="' + item.deviceID + '">' + value + '</option>';
    $("#devices").append(option);
}

$(document).ready(function () {
    connect();
});