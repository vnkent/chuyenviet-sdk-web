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

        userID = getCookie("userID");
        var urlResult = "/topic/sdkCommandResult/" + userID;

        stompClient.subscribe(urlResult, function (devices) {
            var apiRequest = JSON.parse(devices.body).apiRequest;
            if (apiRequest == 'LIST_DEVICE_ADD') {
                loadData(devices);
            } else if (apiRequest == 'REMOVE_DEVICE') {
                var status = JSON.parse(devices.body).data.status;
                if (!status) {
                    stopLoading();
                    setTimeout(showMessage('Error remove device'), 500);
                } else {
                    loadData(devices);
                }
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
    if (lists.length == 0) {
        $("#devices").append(trStart + '<td class="a-center" colspan="6">No resuft!' + tdEnd + trEnd);
    }
}

function sendName() {
    startLoading();
    userID = getCookie("userID");
    stompClient.send("/app/sdkCommand", {}, JSON.stringify({'apiRequest': 'LIST_DEVICE_ADD', 'dataInput' : {"userID": userID}}));
}

function removeDevice(deviceID, iPAddress) {
    console.log("Remove device");
    startLoading();
    stompClient.send("/app/sdkCommand", {}, JSON.stringify({'apiRequest': 'REMOVE_DEVICE', 'dataInput' :
            {'userID' : userID, 'deviceID' : deviceID, 'iPAddress' : iPAddress}}));
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

$(document).ready(function () {
    connect();
});