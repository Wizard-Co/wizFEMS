window.addEventListener('load', function () {
    mainBtnSetting();
});

let selectedRow;

let userTable = new DataTable('#userTable', {
    columns: [
        {data: "num", className: 'center', width: '5rem'},
        {data: "userID", className: 'left'},
        {data: "name", className: 'left'},
        {data: "authType", className: 'center'},
        {data: "useYN", className: 'center'},
        {data: "createDate", className: 'center'},
    ]
})

let tbody = document.querySelector('#userTable tbody');
tbody.onclick = function () {
    let rowElement = event.target.closest('tr');
    selectedRow = userTable.row(rowElement).data();
};
tbody.ondblclick = function (event) {
    let param = {
        userID: selectedRow.userID
    }
    openForm('userDetail', '/systemMgmt/user/detail?mode=update', param, '');
}

function mainBtnSetting() {
    document.getElementById('btnSearch').addEventListener("click", Search);
}

document.getElementById('btnAdd').addEventListener('click', function () {
    openForm('machineDetail', '/systemMgmt/user/add?mode=add', '', '');
})

document.getElementById('btnDelete').addEventListener('click', function () {
    if (!!selectedRow) {
        let baseUrl = '/systemMgmt/user/delete';
        let param = new URLSearchParams({
            userID: selectedRow.userID
        });
        let urlWithParam = `${baseUrl}?${param}`

        fetch(urlWithParam)
            .then(response => {
                if (!response.ok) console.log('http error: ', response);
            })
            .then(() => {
                Search();
            })
            .catch(error => console.log('Unexpected error: ', error));
    }
})

function Search() {
    let param = {
        authTypeID: document.getElementById('cboUserType').value,
        userID: document.getElementById('inputNameSrh').value,
    }

    loading.visible();
    fetch("/systemMgmt/user/search", {
        method: "POST",
        body: JSON.stringify(param),
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then((response) => response.json())
        .then((data) => {
            for(let i =0; i < data.length; i++){
                data[i]['num'] = i+1;
            }
                userTable.clear().rows.add(data).draw();
                loading.invisible();
            }
        );
}


