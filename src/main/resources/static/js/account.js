'use strict';
var stompClient = null;
var usernameInput;
var passwordInput;
var rePasswordInput;
var nameInput;
var addressInput;

function init() {
    usernameInput = document.querySelector('#username');
    passwordInput = document.querySelector('#password');
    rePasswordInput = document.querySelector('#rePassword');
    nameInput = document.querySelector('#name');
    addressInput = document.querySelector('#address');
}

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

// function sendLogin() {
//     startLoading();
//     init();
//     var username = usernameInput.value.trim();
//     var password = passwordInput.value.trim();
//     // if(username && password && stompClient) {
//     //     var account = {
//     //         username: username,
//     //         passowrd: password
//     //     };
//     //
//     //     //stompClient.send("/app/login", {}, JSON.stringify(account));
//     // }
//     stopLoading();
// }

function sendRegister(event) {
    startLoading();
    init();
    var password = passwordInput.value.trim();
    var rePassword = rePasswordInput.value.trim();
    if (password !== rePassword) {
        alert('The password is not the same');
    } else {
        event.preventdefault();
    }
    stopLoading();
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
// if (loginForm) {
//     loginForm.addEventListener('submit', sendLogin, true);
// }
// if (registerForm) {
//     registerForm.addEventListener('submit', sendRegister, true);
// }
