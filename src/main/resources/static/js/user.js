$(document).ready(function () {

});

function showFile(e) {
    const contentDiv = document.getElementById('show');
    contentDiv.innerHTML = '<object style="width: 100%; height: 100%;" type="text/html" data="'
        + e.getAttribute('data') + '"></object>';
}