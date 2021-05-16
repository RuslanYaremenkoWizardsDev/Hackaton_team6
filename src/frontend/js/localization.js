
//Localization
let localizationSwitch = document.getElementById('localization-switch');
let dropdownLang = document.getElementById('dropdown-languages');

localizationSwitch.addEventListener('click', () => {
  document.getElementById("dropdown-languages").classList.toggle("show");
})

dropdownLang.addEventListener('click', () => {
  document.getElementById("dropdown-languages").classList.remove('show');
})