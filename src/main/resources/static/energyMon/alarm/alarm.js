window.addEventListener('load', function () {
    mainBtnSetting();
    spreadCalander();
    setDate();
});


const alarmTable = $('#alarmTable').DataTable({
    select: true,
    searching : false,
    dom: '<"d-none"B><"mb-2 right"f>t<"mt-2 center"p>',
    buttons: [{
        extend: 'excel',
        filename: '알람경보',
        title: '알람경보',
        customize: function (xlsx) {
            let sheet = xlsx.xl.worksheets['sheet1.xml'];
            $('row:first c', sheet).attr('s', '42');

            let colNodes = $('col', sheet);
            colNodes.each(function (index) {
                if (index === 4) {
                    $(this).attr('width', 50);
                }
                else{
                    $(this).attr('width', 20);
                }
            });
        }
    },{
        extend: 'print',
        title: '알람경보',
        customize: function (win) {
            $(win.document.body).find('table')
                .addClass('print-border');
        }
    }],
    language: {
        lengthMenu: "페이지당 _MENU_ 개의 목록 표시",
        zeroRecords: "검색된 항목이 없습니다.",
        info: "_PAGES_ / _PAGE_ 페이지",
        infoEmpty: "검색된 항목이 없습니다.",
        infoFiltered: "(전체 _MAX_개의 항목에서 검색)",
        emptyTable : "검색된 항목이 없습니다.",
        paginate: {
            previous: "<<",
            next: ">>"
        }
    },
    scrollY: true,
    columns: [
        {
            data: null,
            render: function (data, type, row, meta) {
                return meta.row + 1;
            },
            className: 'center'
        },
        {data: "workDate", className: 'center'},
        {data: "workTime", className: 'center'},
        {data: "alarmType", className: 'center'},
        {data: "alarmInfo", className: 'left'},
        {data: "mcName", className: 'center'},
        {data: "comments", className: 'left'},
    ],
    columnDefs: [
        { targets: [0], width: "5%" },
        { targets: [1], width: "8%" },
        { targets: [2], width: "8%" },
        { targets: [3], width: "8%" },
        { targets: [4], width: "15%" },
        { targets: [5], width: "7%" },
        { targets: [6], width: "20%" },
    ],
});


function mainBtnSetting() {
    document.querySelector("#btnSearch").addEventListener("click", Search);
    document.getElementById('btnExcel').addEventListener("click", function () {
        if (alarmTable.rows().count() > 0) {
            const dtExcel = document.querySelector('.dt-button.buttons-excel')
            dtExcel.click();
        } else {
            alert("엑셀로 내보낼 데이터가 없습니다.")
        }
    });

    document.getElementById('btnPrint').addEventListener("click", function () {
        if (alarmTable.rows().count() > 0) {
            const print = document.querySelector('.dt-button.buttons-print')
            print.click();
        } else {
            alert("출력할 데이터가 없습니다.");
        }
    });

}

function Search() {
    let param= {
        mcID: document.getElementById('mcID').value,
        sDate : document.getElementById('sDate').value.replace(/-/g,''),
        eDate : document.getElementById('eDate').value.replace(/-/g,''),
        alarmLevel :  parseInt(document.getElementById('alarmLevel').value),
    }

    loading.visible();
    fetch("/energyMon/alarm/search", {
        method: "POST",
        body: JSON.stringify(param),
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then((response) => response.json())
        .then((data) => {
                alarmTable.clear().rows.add(data).draw();
                loading.invisible();
            }
        );
}

function spreadCalander(){
    const sDate = document.getElementById('sDate');
    const eDate = document.getElementById('eDate');
    sDate.addEventListener('focus',function (){this.showPicker()})
    eDate.addEventListener('focus',function (){this.showPicker()})
}

function setDate(){

    const today = new Date();

    let sdate = new Date(today);
    sdate.setDate(today.getDate() - 7);
    document.getElementById('sDate').value = sdate.toISOString().split('T')[0];

    let edate = today;
    edate.setDate(today.getDate());
    document.getElementById('eDate').value = edate.toISOString().split('T')[0];

}