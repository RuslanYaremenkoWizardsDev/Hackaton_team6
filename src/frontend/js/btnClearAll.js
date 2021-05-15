const clearAllBtn = document.getElementById('clear-all');
const closeClear = document.getElementById('close-clear');
const exitClear = document.getElementById('exit-clear');

clearAllBtn.addEventListener('click', () =>{
  document.getElementById("window-clear-all").classList.toggle("show-modal-clear");;
})

closeClear.addEventListener('click', () =>{
  document.getElementById("window-clear-all").classList.remove("show-modal-clear");;
})
exitClear.addEventListener('click', () =>{
  document.getElementById("window-clear-all").classList.remove("show-modal-clear");;
})