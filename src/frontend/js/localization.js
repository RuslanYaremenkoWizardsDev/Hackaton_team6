const localizationSwitch = document.getElementById('localization-switch');
const dropdownLang = document.getElementById('dropdown-content');

localizationSwitch.addEventListener('click', function (e) {
  dropdownLang.style.display = "block";
});

dropdownLang.addEventListener('click', function (e) {
  dropdownLang.style.display = "none";
});