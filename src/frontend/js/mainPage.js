import '../mainPage.html';
import '../scss/header.scss';
import '../scss/mainPage.scss';

//dropdown databases
let dataBasesBtn = document.getElementById('databases-btn');
let optionsDropdown = document.getElementById('options');

dataBasesBtn.addEventListener('click', () => {
   document.getElementById("options").classList.toggle("showDropdown");
   document.getElementById("databases-btn").classList.toggle("change-border");
})

optionsDropdown.addEventListener('click', () => {
  document.getElementById("options").classList.remove('showDropdown');
})

//sorting by
let sortingBtn = document.getElementById('sorting-btn');
let sortingBy = document.getElementById('sorting-by');

sortingBtn.addEventListener('click', () => {
   document.getElementById("sorting-by").classList.toggle("showDropdown");
   document.getElementById("sorting-btn").classList.toggle("change-border");
})

sortingBy.addEventListener('click', () => {
  document.getElementById("sorting-by").classList.remove('showDropdown');
})