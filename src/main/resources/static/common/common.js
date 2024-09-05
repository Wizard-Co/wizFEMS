// DataTable.type('num', 'className', '');

document.addEventListener('DOMContentLoaded', function () {
    let loading = document.getElementById('loading');
    validate();
})

const urlParam = new URLSearchParams(window.location.search);
const mode = urlParam.get('mode');

if (mode === 'add') {
    hideElementsByID('btnUpdate', 'btnDelete');
} else if (mode === 'update') {
    hideElementsByID('btnSave');
}

function hideElementesByClass(classname) {
    let icon = document.querySelectorAll(classname);
    icon.forEach(x => x.disabled = true);
}

function showElementsByClass(classname) {
    let icon = document.querySelectorAll(classname);
    icon.forEach(x => x.disabled = false);
}

function showElementsByID(...id) {
    id.forEach(x => {
        let btn = document.getElementById(x);
        btn.style.display = 'inline';
    })
}

function hideElementsByID(...id) {
    id.forEach(x => {
        let btn = document.getElementById(x);
        btn.style.display = 'none';
    })
}

function getChecked(id) {
    let isChecked;
    let ele = document.getElementById(id);
    isChecked = ele.checked;
    return isChecked;
}

function readOnly(classname) {
    let lst = document.querySelectorAll(classname);
    lst.forEach(x => x.disabled = true);
}

function writeOnly(classname) {
    let lst = document.querySelectorAll(classname);
    lst.forEach(x => x.disabled = false);
}

function initInput(classname) {
    let lst = document.querySelectorAll(classname);
    lst.forEach(x => x.value = '');
}

function getCombo(id) {
    let obj = id.options[id.selectedIndex];
    return obj;
}

function openForm(popName, url, param, option) {
    if (option === '') option = 'width=900 height=700';

    let pop = window.open('', popName, option);

    let form = document.createElement('form');
    form.method = 'POST';
    form.action = url;
    form.target = popName;

    for (let key in param) {
        let input = document.createElement('input');
        input.type = 'hidden';
        input.id = key;
        input.name = key;
        input.value = param[key];
        form.appendChild(input);
    }

    pop.document.body.appendChild(form);
    form.submit();

}

function validate() {
    'use strict'

    const forms = document.querySelectorAll('.needs-validation')

    Array.from(forms).forEach(form => {
        form.addEventListener('submit', event => {
            if (!form.checkValidity()) {
                event.preventDefault()
                event.stopPropagation()
            }
            form.classList.add('was-validated')
        }, false)
    })
}


