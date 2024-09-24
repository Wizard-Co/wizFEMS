let form = document.getElementById('addForm');
form.addEventListener('submit', (e) => {
    e.preventDefault();
});

document.getElementById('btnSave').addEventListener('click', function () {

    if (checkPassword()) {

        if (form.checkValidity()) {
            const payload = new FormData(form);
            const result = document.querySelector('.form-result');

            fetch('/systemMgmt/user/save', {
                method: 'post',
                body: payload,
                headers: {},
            })
                .then(res => {
                    if(!res.ok){
                        return res.text().then(err => { throw new Error(err)});
                    }
                })
                .then(() => {
                    window.open('', '_self').close();
                    opener.Search();
                })
                .catch(error => {
                    console.dir(error);
                    result.innerHTML = `저장에 실패하였습니다. 오류 발생: ${error.message}`
                });
        }
    }
})

document.getElementById('btnUpdate').addEventListener('click', function () {

    if (checkPassword()) {

        if (form.checkValidity()) {
            const payload = new FormData(form);
            const result = document.querySelector('.form-result');

            fetch('/systemMgmt/user/update', {
                method: 'post',
                body: payload,
                headers: {},
            })
                .then(res => {
                    if(!res.ok){
                        return res.text().then(err => { throw new Error(err)});
                    }
                })
                .then(() => {
                    result.innerHTML = '저장이 완료되었습니다';
                    refreshForm();
                    opener.Search();
                })
                .catch(error => {
                    console.dir(error);
                    result.innerHTML = `저장에 실패하였습니다. 오류 발생: ${error.message}`
                });
        }
    }
})

document.getElementById('btnDelete').addEventListener('click', function () {
    let userID = document.getElementById('userID').value;

    if (!!userID) {

        let baseUrl = '/systemMgmt/user/delete';
        let param = new URLSearchParams({
            userID: userID
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

document.getElementById('btnClose').addEventListener('click', function () {
    window.open('', '_self').close();
})

function checkPassword() {
    let password = document.getElementById('password').value;
    let chkpassword = document.getElementById('chkPassword').value;

    if (password !== chkpassword) {
        alert("비밀번호가 일치하지 않습니다");
        return false;
    } else {
        return true;
    }

}