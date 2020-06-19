function sendRegister() {
    var username = $("#username").val();
    var name = $("#name").val();
    var address = $("#address").val();
    var password = $("#password").val();
    var rePassword = $("#rePassword").val();

    if (username === "" || name === "" || password === "" || rePassword === "") {
        if (username === "") {
            $("#username").focus();
        } else if (name === "") {
            $("#name").focus();
        } else if (password === "") {
            $("#password").focus();
        } else if (rePassword === "") {
            $("#rePassword").focus();
        }
    } else {
        if (password !== rePassword) {
            showCommonMessegeWindow("Password authentication is incorrect");
        } else {
            startLoading();
            var apiBody = {
                "username": username,
                "password": password,
                "name": name,
                "address": address,
                "image": ""
            };
            var dataAPI = JSON.stringify(apiBody);
            gfnAPICall("public/register", methodPOST, headerAPI, dataAPI, callbackFunctionRegister);
        }
    }
}

function callbackFunctionRegister(response) {
    stopLoading();
    if (response.code === "200") {
        $("#message").hide();
        if (showCommonMessegeWindow("Register account success", "Message", true)) {
            window.location.href = "/login";
        }
    } else {
        $("#message").show();
    }
}

function sendLogin() {
    var username = $("#username").val();
    var password = $("#password").val();
    if (username === "" || password === "") {
        if (username === "") {
            $("#username").focus();
        } else if (password === "") {
            $("#password").focus();
        }
    } else {
        startLoading();
        var apiBody = {
            "username": username,
            "password": password
        };
        var dataAPI = JSON.stringify(apiBody);
        gfnAPICall("public/login", methodPOST, headerAPI, dataAPI, callbackFunctionLogin);
    }
}

function callbackFunctionLogin(response) {
    stopLoading();
    if (response.code === "200") {
        $("#message").hide();
        var account = JSON.parse(response.data);
        userID = account.id;
        setCookie("userID", account.id, true);
        setCookie("username", account.username, true);
        setCookie("name", account.name, true);
        setCookie("address", account.address, true);
        setCookie("image", account.image, true);
        window.location.href = "/";
    } else {
        $("#message").show();
    }
}

$(document).ready(function () {
    $("#message").hide();
});

