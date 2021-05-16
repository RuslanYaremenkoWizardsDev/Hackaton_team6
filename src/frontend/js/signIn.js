import '../index.html';
import '../createAccount.html';
import '../scss/header.scss';
import '../scss/signIn.scss';
import '../scss/createAccount.scss';

const authorizationInit = () => {
    var login = document.getElementById('LoginInput_SignIn')
    var password = document.getElementById('PasswordInput_SignIn')
    var submit = document.getElementById('SignInButton');
    submit.addEventListener('click', loginAutorize)

    function loginAutorize() {
        //то что мы будем отправлять
        var data = {
            login: login.value.trim(),
            password: password.value,
        };
        //парсим в json
        var json = JSON.stringify(data);
        //создаем реквест, у него тут же можно засетить хедеры куки и т д
        var xhr = new XMLHttpRequest();
        xhr.open('POST', 'http://localhost:8081/authorization');
        //контент тайп хедер нужен обьязательно
        xhr.setRequestHeader('Content-Type', 'application/json; charset=utf-8');
        //отправляем
        xhr.send(json);
        //прописываем действия при соединении
        xhr.onload = () => {
            //реди стейт не задумываемся - всегда четверку пхаем
            if (xhr.readyState === 4) {
                //статус который вам будет в ответ приходить, от него отталкиваемся что нам делать, вытаскиваем данные через xhr.body
                if (xhr.status === 200) {
                    document.location.href = 'http://localhost:7070/mainPage.html';
                } else if (xhr.status === 401) {
                    authError.innerText = xhr.responseText;
                    authError.setAttribute('style', 'display: block;');
                }
            }
        }
    }
}

const registrationInit = () => {
    var loginUser = document.getElementById('UserNameInput_SignUp')
    var passwordUser = document.getElementById('PasswordInput_SignUp')
    var SignUp = document.getElementById('SignupBtnId');
    SignUp.addEventListener('click', signUp)

    console.log(loginUser, passwordUser)
    function signUp() {

        //то что мы будем отправлять
        var data = {
            login: loginUser.value.trim(),
            password: passwordUser.value,
            role: 'user',
        };
        console.log(data);
        //парсим в json
        var json = JSON.stringify(data);
        //создаем реквест, у него тут же можно засетить хедеры куки и т д
        var xhr = new XMLHttpRequest();
        xhr.open('POST', 'http://localhost:8081/registration');
        //контент тайп хедер нужен обьязательно
        xhr.setRequestHeader('Content-Type', 'application/json; charset=utf-8');
        //отправляем

        //прописываем действия при соединении
        xhr.onload = () => {
            //реди стейт не задумываемся - всегда четверку пхаем
            if (xhr.readyState === 4) {
                //статус который вам будет в ответ приходить, от него отталкиваемся что нам делать, вытаскиваем данные через xhr.body
                if (xhr.status === 200) {
                    document.location.href = 'http://localhost:7070/signIn.html';
                } else if (xhr.status === 401) {
                    authError.innerText = xhr.responseText;
                    authError.setAttribute('style', 'display: block;');
                }
            }
        }
        xhr.send(json);
    }
}

const registrationInitAdmin = () => {
    var loginAdmin = document.getElementById('AdminNameInput_SignUp')
    var passwordAdmin = document.getElementById('AdminPasswordInput_SignUp')
    var SignUpAdmin = document.getElementById('SignupBtnId');
    SignUpAdmin.addEventListener('click', signUp)

    console.log(loginUser, passwordUser)
    function signUp() {

        //то что мы будем отправлять
        var data = {
            login: loginAdmin.value.trim(),
            password: passwordAdmin.value,
            role: 'admin',
        };
        console.log(data);
        //парсим в json
        var json = JSON.stringify(data);
        //создаем реквест, у него тут же можно засетить хедеры куки и т д
        var xhr = new XMLHttpRequest();
        xhr.open('POST', 'http://localhost:8081/registration');
        //контент тайп хедер нужен обьязательно
        xhr.setRequestHeader('Content-Type', 'application/json; charset=utf-8');
        //отправляем

        //прописываем действия при соединении
        xhr.onload = () => {
            //реди стейт не задумываемся - всегда четверку пхаем
            if (xhr.readyState === 4) {
                //статус который вам будет в ответ приходить, от него отталкиваемся что нам делать, вытаскиваем данные через xhr.body
                if (xhr.status === 200) {
                    document.location.href = 'http://localhost:7070/signIn.html';
                } else if (xhr.status === 401) {
                    authError.innerText = xhr.responseText;
                    authError.setAttribute('style', 'display: block;');
                }
            }
        }
        xhr.send(json);
    }
}
console.log(window.location.pathname);
if(window.location.pathname === '/index.html'){
    authorizationInit();
}
if(window.location.pathname === '/createAccount.html'){
    registrationInit()
}
if(window.location.pathname === '/createAccount.html'){
    registrationInitAdmin()
}