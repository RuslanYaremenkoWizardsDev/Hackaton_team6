import '../createAccount.html';
import '../scss/header.scss';
import '../scss/createAccount.scss';
const usertogglePassword = document.querySelector('#togglePassword');
const userpassword = document.querySelector('#PasswordInput_SignUp');
const userconfpassword = document.querySelector('#ConfirmPasswordInput');
const adminpassword = document.querySelector('#AdminPasswordInput_SignUp');
const adminconfpassword = document.querySelector('#AdminConfirmPasswordInput');
const admintogglePassword = document.querySelector('#AdmintogglePassword');
const userBlock = document.getElementById(("user__block"));
const adminBlock = document.getElementById(("admin__block"));
const userButton = document.getElementById("userButton");
const adminButton = document.getElementById("adminButton");

adminButton.addEventListener('click', function (e) {
    userBlock.style.display = "none";
    adminBlock.style.display = "block";
    adminBlock.style.height = "590px";
});

userButton.addEventListener('click', function (e) {
    userBlock.style.display = "block";
    adminBlock.style.display = "none"
});

usertogglePassword.addEventListener('click', function (e) {
    // toggle the type attribute
    const type1 = userpassword.getAttribute('type') === 'password' ? 'text' : 'password';
    const type2 = userconfpassword.getAttribute('type') === 'password' ? 'text' : 'password';
    userpassword.setAttribute('type', type1);
    userconfpassword.setAttribute('type', type2);
    // toggle the eye slash icon
    this.classList.toggle('fa-eye-slash');
});
admintogglePassword.addEventListener('click', function (e) {
    // toggle the type attribute
    const type3 = adminpassword.getAttribute('type') === 'password' ? 'text' : 'password';
    const type4 = adminconfpassword.getAttribute('type') === 'password' ? 'text' : 'password';
    adminpassword.setAttribute('type', type3);
    adminconfpassword.setAttribute('type', type4);
    // toggle the eye slash icon
    this.classList.toggle('fa-eye-slash');
});

