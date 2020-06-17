function sendRegister(event) {
    startLoading();
    var passwordInput = document.querySelector('#password');
    var rePasswordInput = document.querySelector('#rePassword');
    var password = passwordInput.value.trim();
    var rePassword = rePasswordInput.value.trim();
    if (password !== rePassword) {
        stopLoading();
        setTimeout(showMessage('The password is not the same'), 1000);
        return false;
    } else {
        event.preventdefault();
    }
}
