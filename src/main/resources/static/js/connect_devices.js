var stompClient = null;
var trStart  = '<tr class="even pointer">';
var trEnd  = "</tr>";
var tdStart  = "<td>";
var tdEnd  = "</td>";

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#connect_devices").show();
    }
    else {
        $("#connect_devices").hide();
    }
    $("#devices").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/sdkCommandResult', function (devices) {
            $("#devices").empty();
            var jsonData = JSON.parse(devices.body).data;
            console.log(jsonData);
            var lists = parseJsonToArrayList(jsonData);
            if (lists.length > 0) {
                for (var i = 0; i < lists.length; i++) {
                    showDevice(i, lists[i]);
                }
            }
            stopLoading();
            if (lists.length == 0) {
                $("#devices").append(trStart + '<td class="a-center" colspan="8">No resuft!' + tdEnd + trEnd);
            }
        });
        sendName();
    });
}

function parseJsonToArrayList(data) {
    var result = [];
    for(var i in data)
        result.push(data[i]);
    return result;
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    startLoading();
    stompClient.send("/app/sdkCommand", {}, JSON.stringify({'apiRequest': 'LIST_DEVICE_ADD', 'dataInput' : {}}));
}

function removeDevice(deviceID, iPAddress) {
    console.log("Remove device");
    startLoading();
    stompClient.send("/app/sdkCommand", {}, JSON.stringify({'apiRequest': 'REMOVE_DEVICE', 'dataInput' :
            {'deviceID' : deviceID, 'iPAddress' : iPAddress}}));
}

function showDevice(index, message) {
    var stt = tdStart + (index + 1) + tdEnd;
    var deviceID = tdStart + message.deviceID + tdEnd;
    var type = tdStart + message.type + tdEnd;
    var addressIP = tdStart + message.iPAddr_ + tdEnd;
    var status = tdStart + message.status + tdEnd;
    var removeAction = '<a href="javascript:removeDevice(' + message.deviceID + ',\'' + message.iPAddr_ + '\');">Remove</a>';
    var actionTD = tdStart + removeAction + tdEnd;
    $("#devices").append(trStart + stt + deviceID + type + addressIP + status + actionTD + trEnd);
}

function startLoading() {
    $("body").LoadingOverlay("show");
}

function stopLoading() {
    $("body").LoadingOverlay("hide");
}

$(document).ready(function () {
    connect();
});