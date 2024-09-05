let form = document.getElementById('addForm');
form.addEventListener('submit', (e) => {
    e.preventDefault();
});
document.getElementById('btnSave').addEventListener('click', function () {

    if (form.checkValidity()) {
        const payload = new FormData(form);

        fetch('/basicMgmt/machine/save', {
            method: 'post',
            body: payload,
            headers: {},
        })
            .then(res => {
                if (!res.ok) console.log('http error: ', res)
            })
            .then(() => {
                window.open('', '_self').close();
                opener.Search();
            })
            .catch(error => console.log('Unexpected error: ', error));
    }
})

document.getElementById('btnDelete').addEventListener('click', function () {
    let mcID = document.getElementById('mcID').value;

    if (!!mcID) {

        let baseUrl = '/basicMgmt/machine/delete';
        let param = new URLSearchParams({
            mcID: mcID
        });
        let urlWithParam = `${baseUrl}?${param}`

        fetch(urlWithParam)
            .then(res => {
                if (!res.ok) console.log('http error: ', res)
            })
            .then(() => {
                window.open('', '_self').close();
                opener.Search();
            })
            .catch(error => console.log('Unexpected error: ', error))
    }
})

document.getElementById('btnUpdate').addEventListener('click', function () {

    if (form.checkValidity()) {
        const payload = new FormData(form);

        fetch('/basicMgmt/machine/update', {
            method: 'post',
            body: payload,
            headers: {},
        })
            .then(res => {
                if (!res.ok) console.log('http error: ', res)
            })
            .then(() => {

                opener.Search();
            })
            .catch(error => console.log('Unexpected error: ', error));
    }
})

document.getElementById('btnClose').addEventListener('click', function () {
    window.open('', '_self').close();
})
