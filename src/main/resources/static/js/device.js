var stompClient = null;
var trStart  = '<tr class="even pointer">';
var trEnd  = "</tr>";
var tdStart  = "<td>";
var tdEnd  = "</td>";

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
        // var urlparameter;
        // if(window.location.href.indexOf("driverId") > -1){
        //     urlparameter = getUrlVars()["driverId"];
        // }
        // urlparameter;
        stompClient.subscribe('/topic/sdkCommandResult', function (greeting) {
            $("#greetings").empty();
            if (typeof greeting === "boolean") {
                stopLoading();
                if (greeting) {
                    window.location.href = 'connect_devices.html';
                } else {
                    alert('Error add device');
                }
            } else {
                var jsonData = JSON.parse(greeting.body).data;
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
    stompClient.send("/app/sdkCommand", {}, JSON.stringify({'apiRequest': 'SEARCH_DEVICE', 'dataInput' : {}}));
}

function addDevice(deviceID, iPAddress, port) {
    console.log("add device");
    stompClient.send("/app/sdkCommand", {}, JSON.stringify({'apiRequest': 'ADD_DEVICE', 'dataInput' :
            {'deviceID' : deviceID, 'iPAddress' : iPAddress, 'port' : port}}));
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

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});

function startLoading() {
    $("body").LoadingOverlay("show");
}

function stopLoading() {
    $("body").LoadingOverlay("hide");
}

$(document).ready(function () {
    connect();
}
)