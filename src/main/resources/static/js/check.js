var deviceID;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#table_users").show();
    }
    else {
        $("#table_users").hide();
    }
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("Connected: " + frame);
        setConnected(true);

        userID = getCookie("userID");

        var urlResult = "/topic/sdkCommandResult/" + userID;

        stompClient.subscribe(urlResult, function (greeting) {
            var apiRequest = JSON.parse(greeting.body).apiRequest;
            if (apiRequest == 'EVENT_DEVICE') {
                var status = JSON.parse(greeting.body).data.status;
                if (status) {
                    var selectUserId = JSON.parse(greeting.body).data.selectUserId;
                    handling(selectUserId);
                } else {
                    $("#showUser").empty();
                }
            }
            stopLoading();
        });
        sendRealTime();
    });
}

function sendRealTime() {
    startLoading();
    stompClient.send("/app/sdkCommand", {}, JSON.stringify({'apiRequest': 'EVENT_DEVICE', 'dataInput' : {"userID": userID, "deviceID" : deviceID}}));
}

function handling(selectUserId) {
    var dataAPI;
    gfnAPICall("public/check_event/" + selectUserId, methodGET, headerAPI, dataAPI, callbackFunctionCheck);
}

function callbackFunctionCheck(response) {
    var data = JSON.parse(response.data);
    $("#showUser").empty();
    if (data !== null) {
        createMessage(data);
    } else {
        $("#showUser").append(trStart + '<td class="a-center" colspan="5">No resuft!</td>' + trEnd);
    }
    stopLoading();
}

function createMessage(account) {
    var userID = tdStart + account.id + tdEnd;
    var username = tdStart + account.username + tdEnd;
    var name = tdStart + account.name + tdEnd;
    var address = tdStart + account.address + tdEnd;
    var image = tdStart + account.image + tdEnd;
    $("#showUser").append(trStart + userID + username + name + address + image + trEnd);
}

$(document).ready(function () {
    connect();

    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
});