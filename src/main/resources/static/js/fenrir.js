//Get the To The Top Button
var myToTheTopButton = document.getElementById("toTheTopBtn")?document.getElementById("toTheTopBtn"):null;
/**
 * When the user scrolls down 20px from the top of the document, show the button
 * @returns {undefined}
 */
window.onscroll = function () {
    if(myToTheTopButton !== null || myToTheTopButton !== undefined) {
        scrollFunction();
    }
};

function scrollFunction() {
    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
        myToTheTopButton.style.display = "block";
    } else {
        myToTheTopButton.style.display = "none";
    }
}
/**
 * When the user clicks on the button, scroll to the top of the document
 * @returns {undefined}
 */
function topFunction() {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}

$(document).ready(function () {
    $("#locales").change(function () {
        var selectedOption = $('#locales').val();
        if (selectedOption !== '') {
            window.location.replace('?lang=' + selectedOption);
        }
    });

    $("#inputPassword + .input-group-addon .glyphicon").on("click", function(event) {
        event.preventDefault();
        // toggle our classes for the eye icon
        $(this).toggleClass("glyphicon-eye-close").toggleClass("glyphicon-eye-open"); // toggle our classes for the eye icon
        $("#inputPassword").togglePassword(); // activate the hideShowPassword plugin
    });

    $("#currentPassword + .input-group-addon .glyphicon").on('click', function (event) {
        event.preventDefault();
        // toggle our classes for the eye icon
        $(this).toggleClass("glyphicon-eye-close").toggleClass("glyphicon-eye-open"); // toggle our classes for the eye icon
        $("#currentPassword").togglePassword(); // activate the hideShowPassword plugin
    });

    $("#newPassword + .input-group-addon .glyphicon").on('click', function (event) {
        event.preventDefault();
        // toggle our classes for the eye icon
        $(this).toggleClass("glyphicon-eye-close").toggleClass("glyphicon-eye-open"); // toggle our classes for the eye icon
        $("#newPassword").togglePassword(); // activate the hideShowPassword plugin
    });

    $("#signup-password-confirm + .input-group-addon .glyphicon").on('click', function (event) {
        event.preventDefault();
        // toggle our classes for the eye icon
        $(this).toggleClass("glyphicon-eye-close").toggleClass("glyphicon-eye-open"); // toggle our classes for the eye icon
        $("#signup-password-confirm").togglePassword(); // activate the hideShowPassword plugin
    });
});