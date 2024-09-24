/**
*패키지명 :
*파일명: custom.js
*작성일: 2024-09-23
*개발자: daehyun
***************************************************
*변경일자            변경자             변경내용
***************************************************
 * 2024-09-19      최대현              최초작성
 * 2024-09-23      최대현              조회,삭제 추가
*/

window.addEventListener('load', function () {
    mainBtnSetting();
});

let selectedRow;
let lastClickedTime = 0;
const delay = 190;

const customTable = $('#customTable').DataTable({
    select: true,
    searching : false,
    dom: '<"d-none"B><"mb-2 right"f>t<"mt-2 center"p>',
    buttons: [{
        extend: 'excel',
        filename: '업체관리',
        title: '업체관리',
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
        title: '업체관리',
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
        {data: "agentCustom", className: 'left'},
        {data: "kCustom", className: 'left'},
        {data: "customNo", className: 'center'},
        {data: "address", className: 'left'},
        {data: "chief", className: 'center'},
        {data: "contacts", className: 'right'},
        {data: "useYN", className: 'center'},
    ],
    columnDefs: [
        { targets: [0], width: "5%" },
        { targets: [1], width: "8%" },
        { targets: [2], width: "8%" },
        { targets: [3], width: "10%" },
        { targets: [4], width: "30%" },
        { targets: [5], width: "7%" },
        { targets: [6], width: "7%" },
        { targets: [7], width: "8%" },

    ],
});

function mainBtnSetting() {
    document.querySelector("#btnSearch").addEventListener("click", Search);
    document.querySelector("#btnDelete").addEventListener("click", Delete);
    document.getElementById('btnAdd').addEventListener('click', function () {
        openForm('customDetail', '/basicMgmt/custom/add?mode=add', '', '');
    })
    document.getElementById('btnExcel').addEventListener("click", function () {
        if (customTable.rows().count() > 0) {
            const dtExcel = document.querySelector('.dt-button.buttons-excel')
            dtExcel.click();
        } else {
            alert("엑셀로 내보낼 데이터가 없습니다.")
        }
    });

    document.getElementById('btnPrint').addEventListener("click", function () {
        if (customTable.rows().count() > 0) {
            const print = document.querySelector('.dt-button.buttons-print')
            print.click();
        } else {
            alert("출력할 데이터가 없습니다.");
        }
    });
}

$('#customTable tbody').on('dblclick', 'tr', function () {
    selectedRow = customTable.row(this).data();
    let param = {
        customID: selectedRow.customID
    }
    customTable.row(this).select();
    //url에 쿼리스트링으로 값을 보내 mode의 값에 따라 버튼을 활성화 비활성화
    //common.js에서 조정하고 있음
    openForm('customDetail', '/basicMgmt/custom/detail?mode=update', param, '');
});


$('#customTable tbody').on('click', 'tr', function () {
    const currentTime = new Date().getTime();
    let clickedRow = customTable.row(this).data();

    if (selectedRow === clickedRow && (currentTime - lastClickedTime) < delay) {
        return;
    }

    if(clickedRow === selectedRow){
        selectedRow = null;
    }else{
        selectedRow = clickedRow;
    }

    lastClickedTime = currentTime;
});

function Search() {
    let param= {
        kCustom: document.getElementById('inputCustomNameSrh').value,
    }

    loading.visible();
    fetch("/basicMgmt/custom/search", {
        method: "POST",
        body: JSON.stringify(param),
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then((response) => response.json())
        .then((data) => {
                customTable.clear().rows.add(data).draw();
                loading.invisible();
            }
        );
}

function Delete(){
    if(selectedRow){
        let kCustom = selectedRow.kCustom;
        let customID = selectedRow.customID;
        let $clickedTr = $(this);

        const userConfirm = confirm("업체 : "+ kCustom+ "를 삭제하시겠습니까?")
        if(userConfirm){
            if (!!customID) {
                let baseUrl = '/basicMgmt/custom/delete';
                let param = new URLSearchParams({
                    customID: customID
                });
                let urlWithParam = `${baseUrl}?${param}`

                fetch(urlWithParam)
                    .then(res => {
                        if (!res.ok) console.log('http error: ', res)
                        return res.text();
                    })
                    .then(responseMessage => {
                        alert(responseMessage);
                        if (responseMessage.includes("삭제 되었습니다.")) {
                            Search();
                        }
                    })
                    .catch(error => console.log('Unexpected error: ', error))
            }
        }
    }
    else{
        alert("삭제할 업체를 선택하세요.")
    }
}



