window.addEventListener('load', function () {
    mainBtnSetting();
});

let selectedRow;

const MCtable = new DataTable('#MCtable', {
        buttons: [{
        extend: 'excel',
        filename: '설비관리',
        title: '설비관리',
        customize: function (xlsx) {
            let sheet = xlsx.xl.worksheets['sheet1.xml'];
            $('row:first c', sheet).attr('s', '42');
        }
    }],
    columns: [
        {data: "mcType", className: 'center'},
        {data: "mcName", className: 'left'},
        {data: "mcNo", className: 'left'},
        {data: "installDate", className: 'center'},
        {data: "elecCapacity", className: 'right'},
        {data: "steamCapacity", className: 'right'},
    ]
})

let tbody = document.querySelector('#MCtable tbody');
tbody.onclick = function () {
    let rowElement = event.target.closest('tr');
    selectedRow = MCtable.row(rowElement).data();
};
tbody.ondblclick = function (event) {
    let param = {
        mcID: selectedRow.mcID
    }
    openForm('machineDetail', '/basicMgmt/machine/detail?mode=update', param, '');
}

function mainBtnSetting() {
    document.getElementById('btnSearch').addEventListener("click", Search);
}

document.getElementById('btnAdd').addEventListener('click', function () {
    openForm('machineDetail', '/basicMgmt/machine/add?mode=add', '', '');
})

document.getElementById('btnDelete').addEventListener('click', function () {
    if (!!selectedRow) {
        let baseUrl = '/basicMgmt/machine/delete';
        let param = new URLSearchParams({
            mcID: selectedRow.mcID
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


// table filter
document.getElementById('inputMcNameSrh').addEventListener('keyup', function () {
    let input = this.value;
    MCtable.column(1).search(input).draw();
})

function Search() {
    let param = {
        mcType: document.getElementById('cboMcTypeSrh').value,
        mcName: document.getElementById('inputMcNameSrh').value,
        mcNo: document.getElementById('inputMcNOSrh').value,
    }

    loading.visible();
    fetch("/basicMgmt/machine/search", {
        method: "POST",
        body: JSON.stringify(param),
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then((response) => response.json())
        .then((data) => {
            MCtable.clear().rows.add(data).draw();
            loading.invisible();
            }
        );
}


document.getElementById('btnExcel').addEventListener("click", function () {

    const dtExcel = document.querySelector('.dt-button.buttons-excel')
    dtExcel.click();
});



