window.addEventListener('load', function () {
    mainBtnSetting();
    spreadCalander();
    setDate();
});



function mainBtnSetting() {


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