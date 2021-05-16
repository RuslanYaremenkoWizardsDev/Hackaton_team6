import '../index.html';
import '../scss/header.scss';
import '../scss/signIn.scss';

const togglePassword = document.querySelector('#togglePassword');
const password = document.querySelector('#PasswordInput_SignIn');

togglePassword.addEventListener('click', function (e) {
    // toggle the type attribute
    const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
    password.setAttribute('type', type);
    // toggle the eye slash icon
    this.classList.toggle('fa-eye-slash');
});