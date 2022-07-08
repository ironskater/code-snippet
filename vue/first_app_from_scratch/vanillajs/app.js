const buttonEl = document.querySelector('button');
const inputEl = document.querySelector('input');
const listEl = document.querySelector('ul');

function addGoal() {
    const listItemEl = document.createElement('li');
    listItemEl.textContent = inputEl.value;
    listEl.appendChild(listItemEl);
    inputEl.value = '';
}

buttonEl.addEventListener('click', addGoal);