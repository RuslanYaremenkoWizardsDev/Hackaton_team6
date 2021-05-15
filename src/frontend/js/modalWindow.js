// Setting
const settingBtn = document.getElementById('setting-btn');

settingBtn.addEventListener('click', () => {
  document.getElementById("settingDropdown").classList.toggle("show");
})

//change password
const changePasswordBtn = document.getElementById('change-password');
const closePasswordBtn = document.getElementById('close-modal-password');
const exit = document.getElementById('exit');

changePasswordBtn.addEventListener('click', () =>{
  document.getElementById("settingDropdown").classList.remove("show");
  document.getElementById("window-modal-login").classList.remove("show-modal");
  document.getElementById("window-modal").classList.toggle('show-modal');
})
closePasswordBtn.addEventListener('click', () =>{
  document.getElementById("window-modal").classList.remove('show-modal');
})
exit.addEventListener('click', () =>{
  document.getElementById("window-modal").classList.remove('show-modal');
})

//modal change login
const changeLoginBtn = document.getElementById('change-login');
const closeLoginBtn = document.getElementById('close-modal-login');
const exitLogin = document.getElementById('exit-login');
changeLoginBtn.addEventListener('click', () =>{
  document.getElementById("settingDropdown").classList.remove("show");
  document.getElementById("window-modal").classList.remove('show-modal');
  document.getElementById("window-modal-login").classList.toggle("show-modal");
})
closeLoginBtn.addEventListener('click', () =>{
  document.getElementById("window-modal-login").classList.remove("show-modal");
})
exitLogin.addEventListener('click', () =>{
    document.getElementById("window-modal-login").classList.remove('show-modal');
})