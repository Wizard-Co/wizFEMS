window.addEventListener('load', function () {
    mainBtnSetting();
});

let selectedRow;

const MCtable = $('#MCtable').DataTable({
    select: true,
    dom: '<"d-none"B><"mb-2 right"f>t<"mt-2 center"p>',
    buttons: [{
        extend: 'excel',
        filename: '설비관리',
        title: '설비관리',
        customize: function (xlsx) {
            let sheet = xlsx.xl.worksheets['sheet1.xml'];
            $('row:first c', sheet).attr('s', '42');
        }
    }],
    language: {
        lengthMenu: "페이지당 _MENU_ 개의 목록 표시",
        search: "통합 검색:",
        zeroRecords: "검색된 항목이 없습니다.",
        info: "_PAGES_ / _PAGE_ 페이지",
        infoEmpty: "검색된 항목이 없습니다.",
        infoFiltered: "(전체 _MAX_개의 항목에서 검색)",
        paginate: {
            previous: "<<",
            next: ">>"
        }
    },
    scrollY: true,
    columns: [
        {data: "mcType", className: 'center'},
        {data: "mcName", className: 'left'},
        {data: "mcNo", className: 'left'},
        {data: "installDate", className: 'center'},
        {data: "elecCapacity", className: 'right'},
        {data: "steamCapacity", className: 'right'},
    ]
});

function mainBtnSetting() {
    document.querySelector("#btnSearch").addEventListener("click", Search);
}

$('#MCtable tbody').on('dblclick', 'tr', function () {
    selectedRow = MCtable.row(this).data();
    let param = {
        mcID: selectedRow.mcID
    }
    openForm('machineDetail', '/basicMgmt/machine/detail?mode=update', param, '');
});

$('#MCtable tbody').on('click', 'tr', function () {
    selectedRow = MCtable.row(this).data();
})
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



