let showPaswordOld = document.getElementById('show-password-old');
let inputOld = document.getElementById('password-input-old');

showPaswordOld.addEventListener('click', () =>{
    if (inputOld.getAttribute('type') == 'password') {
        showPaswordOld.classList.toggle('view');
        inputOld.setAttribute('type', 'text');
    } else {
        showPaswordOld.classList.remove('view');
        inputOld.setAttribute('type', 'password');
    }
})

let showPaswordNew = document.getElementById('show-password-new');
let inputNew = document.getElementById('password-input-new');

showPaswordNew.addEventListener('click', () =>{
    if (inputNew.getAttribute('type') == 'password') {
        showPaswordNew.classList.toggle('view');
        inputNew.setAttribute('type', 'text');
    } else {
        showPaswordNew.classList.remove('view');
        inputNew.setAttribute('type', 'password');
    }
})