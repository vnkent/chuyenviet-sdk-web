function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);

        userID = getCookie("userID");
        var urlResult = "/topic/sdkCommandResult/" + userID;

        stompClient.subscribe(urlResult, function (greeting) {
            var apiRequest = JSON.parse(greeting.body).apiRequest;
            if (apiRequest == 'LIST_DEVICE_ADD') {
                loadData(greeting);
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
            showDevice(i, lists[i]);
        }
    }
    stopLoading();
}

function sendName() {
    startLoading();
    stompClient.send("/app/sdkCommand", {}, JSON.stringify({'apiRequest': 'LIST_DEVICE_ADD', 'dataInput' : {"userID": userID}}));
}

function addEvent(e) {
    var deviceID = $( "#deviceID" ).val();
    window.location.href = "/event_device/" + deviceID;
}

function showDevice(index, item) {
    var value = item.type + " - " + item.iPAddr_ + " - " + item.port;
    var option = '<option value="' + item.deviceID + '">' + value + '</option>';
    $("#deviceID").append(option);
}

$(document).ready(function () {
    connect();
});