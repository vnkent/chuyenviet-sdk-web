'use strict';
var stompClient = null;
var usernameInput = document.querySelector('#username');
var passwordInput = document.querySelector('#passowrd');
var nameInput = document.querySelector('#name');
var addressInput = document.querySelector('#address');
var loginForm = document.querySelector('#loginForm');
var registerForm = document.querySelector('#registerForm');

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/loginResult', function (account) {
            if (account) {
                window.location.href = 'index.html';
            }
            stopLoading();
        });
        stompClient.subscribe('/topic/registerResult', function (account) {
            if (account) {
                window.location.href = 'login.html';
            }
            stopLoading();
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendLogin(event) {
    startLoading();
    var username = usernameInput.value.trim();
    var password = passwordInput.value.trim();

    console.log("Username: " + username);
    console.log("Password: " + password);

    if(username && password && stompClient) {
        var account = {
            username: username,
            passowrd: password
        };
        stompClient.send("/app/login", {}, JSON.stringify(account));
    }
    event.preventDefault();
    stopLoading();
}

function sendRegister(event) {
    startLoading();
    var username = usernameInput.value.trim();
    var password = passwordInput.value.trim();
    var name = nameInput.value.trim();
    var address = addressInput.value.trim();
    if(username && password && name && stompClient) {
        var account = {
            username: username,
            passowrd: password,
            name: name,
            address: address
        };
        stompClient.send("/app/register", {}, JSON.stringify(account));
    }
    event.preventDefault();
    stopLoading();
}

}

function startLoading() {
    $("body").LoadingOverlay("show");
}

function stopLoading() {
    $("body").LoadingOverlay("hide");
}

$(document).ready(function () {
    connect();
    if (loginForm) {
        loginForm.addEventListener('submit', sendLogin, true);
    }
    if (registerForm) {
        registerForm.addEventListener('submit', sendRegister, true);
    }
});
