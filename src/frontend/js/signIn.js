import '../signIn.html';
import '../scss/header.scss';
import '../scss/signIn.scss';

var login = document.getElementById('LoginInput_SignIn')
var password = document.getElementById('PasswordInput_SignIn')
var submit = document.getElementById('SignInBtn');
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
                document.location.href = 'http://localhost:4080/menu.html';
            } else if (xhr.status === 401) {
                authError.innerText = xhr.responseText;
                authError.setAttribute('style', 'display: block;');
            }
        }
    }
}