'use strict';
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

function showMessage(message) {
    alert(message);
}

function startLoading() {
    $("body").LoadingOverlay("show");
}

function stopLoading() {
    $("body").LoadingOverlay("hide");
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
    removeUser();
    // window.location.href = "/logout";
}