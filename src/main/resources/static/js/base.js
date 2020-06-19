'use strict';

let ajaxDataType = "json";
let urlServer = window.location.origin + "/";

let methodPOST = "POST";
let methodGET = "POST";

let headerAPI = {
    "Accept": "application/json",
    "Content-Type": "application/json"
};

var stompClient = null;
var userID;
var trStart  = '<tr class="even pointer">';
var trEnd  = "</tr>";
var tdStart  = "<td>";
var tdEnd  = "</td>";

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}

function startLoading() {
    $("body").LoadingOverlay("show");
    return true;
}

function stopLoading() {
    $("body").LoadingOverlay("hide");
    return true;
}

function parseJsonToArrayList(data) {
    var result = [];
    for(var i in data)
        result.push(data[i]);
    return result;
}

/* Cookie Set - name, value, period */
var setCookie = function(name, value, exp) {
    var date = new Date();
    date.setTime(date.getTime() + exp*24*60*60*1000);
    document.cookie = name + '=' + value + ';expires=' + date.toUTCString() + ';path=/';
}

/* Cookie Get */
var getCookie = function(name) {
    var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
    return value? value[2] : null;
}

/* Cookie Delete */
var deleteCookie = function (name) {
    document.cookie = name + '=; expires=Thu, 01 Jan 1999 00:00:10 GMT;';
}

function setName() {
    var name = getCookie("name");
    var lists = document.getElementsByClassName("name_user");
    for (var n = 0; n < lists.length; ++n) {
        lists[n].innerHTML = name;
    }
}

$(document).ready(function () {
    setTimeout(setName(), 1000);
});

function removeUser() {
    deleteCookie("userID");
    deleteCookie("username");
    deleteCookie("name");
    deleteCookie("address");
    deleteCookie("image");
}

function logout() {
    var dataAPI;
    gfnAPICall("public/logout", methodPOST, headerAPI, dataAPI, callbackFunctionLogout);
}

function callbackFunctionLogout(response) {
    removeUser();
    window.location.href = "/";
}

function gfnAPICall(apiDetailUrl, method, header, data, callbackFunction) {
    if (data) {
        $.ajax({
            url     : urlServer + apiDetailUrl,
            method  : method,
            headers : header,
            dataType: ajaxDataType,
            data    : data,
            success : function (response) {
                callbackFunction(response);
            },
            error   : function (jqXHR, textStatus, errorThrown) {
                alert("error." + textStatus + ', ' + errorThrown);
            }
        });
    } else {
        $.ajax({
            url     : urlServer + apiDetailUrl,
            method  : method,
            headers : header,
            dataType: ajaxDataType,
            success : function (response) {
                callbackFunction(response);
            },
            error   : function (jqXHR, textStatus, errorThrown) {
                alert("error." + textStatus + ', ' + errorThrown);
            }
        });
    }
}

function showCommonMessegeWindow(msgBody, msgTitle, msgType, msgLanguage, showTime) {
    if ( msgType == "confirm" ) {
        return confirm(msgBody);
    } else {
        alert (msgBody);
        return "Y";
    }
}
