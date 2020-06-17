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
function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
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
            if (apiRequest == 'SEARCH_DEVICE') {
                loadData(greeting);
            } else if (apiRequest == 'ADD_DEVICE') {
                var isSuccess = JSON.parse(greeting.body).data.status;
                stopLoading();
                if (isSuccess) {
                    window.location.href = '/connect_devices';
                } else {
                    setTimeout(showMessage('Error add device'), 500);
                }
            }
        });
        sendName();
    });
}

function loadData(data) {
    $("#greetings").empty();
    var jsonData = JSON.parse(data.body).data.list_device;
    var lists = parseJsonToArrayList(jsonData);
    if (lists.length > 0) {
        for (var i = 0; i < lists.length; i++) {
            showGreeting(i, lists[i]);
        }
    }
    stopLoading();
    if (lists.length == 0) {
        $("#greetings").append(trStart + '<td class="a-center" colspan="8">No resuft!' + tdEnd + trEnd);
    }
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
    stompClient.send("/app/sdkCommand", {}, JSON.stringify({'apiRequest': 'SEARCH_DEVICE', 'dataInput' : {"userID": userID}}));
}

function addDevice(deviceID, iPAddress, port) {
    console.log("add device");
    stompClient.send("/app/sdkCommand", {}, JSON.stringify({'apiRequest': 'ADD_DEVICE', 'dataInput' :
            {'userID' : userID, 'deviceID' : deviceID, 'iPAddress' : iPAddress, 'port' : port}}));
}

function showGreeting(index, message) {
    var stt = tdStart + (index + 1) + tdEnd;
    var deviceId = tdStart + message.deviceID + tdEnd;
    var type = tdStart + message.type + tdEnd;
    var useDHCP = tdStart + message.useDHCP + tdEnd;
    var connectionMode = tdStart + message.connectionMode + tdEnd;
    var iPAddr = tdStart + message.iPAddr_ + tdEnd;
    var port = tdStart + message.port + tdEnd;
    var addAction = '<a href="javascript:addDevice(' + message.deviceID + ',\'' + message.iPAddr_ + '\',' + message.port + ');">Add device</a>';
    var actionTd = tdStart + addAction + tdEnd;
    $("#greetings").append(trStart + stt + deviceId + type + useDHCP + connectionMode + iPAddr + port + actionTd + trEnd);
}

$(document).ready(function () {

    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });

    connect();
});