/**
*패키지명 :
*파일명: customDetail.js
*작성일: 2024-09-23
*개발자: daehyun
***************************************************
*변경일자            변경자             변경내용
***************************************************
 * 2024-09-19      최대현             최초 코드작성
 * 2024-09-23      최대현             추가모드 시 내용 초기화 숫자입력란 숫자만 입력 가능
*/

let form = document.getElementById('addForm');
const customNo = document.getElementById('customNo');
const contacts = document.getElementById('contacts');
const chargePhoneNumber = document.getElementById('chargePhoneNumber')
let customID;

form.addEventListener('submit', (e) => {
    e.preventDefault();
});

window.addEventListener('load', function() {
    customID = document.getElementById('customID').value;
    formatInput(customNo, '-',12,3,5);
    formatInput(contacts, '',0);
    formatInput(chargePhoneNumber,'',0);
    cboBnsDisable();
    mainBtnSetting();
});

function mainBtnSetting(){
    document.querySelector("#btnClose").addEventListener('click', ()=>{window.close();} )
    document.querySelector("#btnUpdate").addEventListener('click', Update);
    document.querySelector("#btnDelete").addEventListener('click',Delete);
    document.querySelector("#btnSave").addEventListener('click',Save)
}

function loadDaumPostcodeScript(callback) {
    if (window.daum) {
        callback();
        return;
    }

    //주소찾기 객체가 안 만들어졌을 때 만듦
    const script = document.createElement('script');
    script.src = '//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js';
    script.onload = callback;
    document.head.appendChild(script);
}

function execPostCode() {
    loadDaumPostcodeScript(function() {
        new daum.Postcode({
            oncomplete: function(data) {
                var addr = '';

                //도로명주소 : R 지번주소 : J
                if (data.userSelectedType === 'R') {
                    addr = data.roadAddress;
                } else {
                    addr = data.jibunAddress;
                }

                document.getElementById('zipCode').value = data.zonecode;
                document.getElementById("customAddr").value = addr;
                document.getElementById("customDetailAddr").focus();
            }
        }).open();
    });
}


function Save(){
    checkEmail();
    if (form.checkValidity()) {
        const payload = new FormData(form);

        fetch('/basicMgmt/custom/save', {
            method: 'post',
            body: payload,
            headers: {},
        })
            .then(res => {
                if (!res.ok) console.log('http error: ', res)
                else alert("저장 되었습니다.")
            })
            .then(() => {
                window.open('', '_self').close();
                opener.Search();
            })
            .catch(error => console.log('Unexpected error: ', error));
    }
}


function Update(){
    const userConfirm = confirm("저장 하시겠습니까?");
    if(userConfirm){
        if(customID){

            const emailInput = document.getElementById('chargeEmail');
            const emailError = document.getElementById('emailError');

            if (emailInput.value) {
                if (isValidEmail(emailInput.value)) {
                    emailError.style.display = 'none';
                } else {
                    emailError.style.display = 'block';
                    return;
                }
            } else {
                emailError.style.display = 'none';
            }

            if (form.checkValidity()) {
                const payload = new FormData(form);

                fetch('/basicMgmt/custom/update', {
                    method: 'post',
                    body: payload,
                    headers: {},
                })
                    .then(res => {
                        if (!res.ok) console.log('http error: ', res)
                        else alert("수정 되었습니다.")
                    })
                    .then(() => {
                        window.open('', '_self').close();
                        opener.Search();
                    })
                    .catch(error => console.log('Unexpected error: ', error));
            }
        }
    }
}

function Delete(){
    let kCustom = document.getElementById('kCustom').value
    const userConfirm = confirm("업체 : "+ kCustom+ "를 삭제하시겠습니까?")
    if(userConfirm){
        if (!!customID) {
            checkEmail();
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


function cboBnsDisable() {
    const businessType01 = document.getElementById("businessType01");
    const businessType02 = document.getElementById("businessType02");
    const customSelect = document.getElementById("cboBnsType");

    toggleSelect(!businessType01.checked, customSelect);

    businessType01.addEventListener("change", function() {
        toggleSelect(!this.checked, customSelect);
    });
    businessType02.addEventListener("change", function() {
        toggleSelect(this.checked, customSelect);
    });
}

function toggleSelect(isEnabled, selectElement) {
    selectElement.disabled = !isEnabled;

    if (!isEnabled) {
        selectElement.style.backgroundColor = '#f0f0f0';
        selectElement.style.color = '#888';
    } else {
        selectElement.style.backgroundColor = '';
        selectElement.style.color = '';
    }
}

function formatInput(inputElement, insertChar, maxLength, ...positions) {

    inputElement.addEventListener('input', function () {

        let value = this.value.replace(/[^0-9!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/g, '');
        let formattedValue = value;

        if(insertChar !== '' && maxLength !== 0){
            value = this.value.replace(/\D/g, '');
            const insertCount = positions.length;
            if (value.length + insertCount > maxLength) {
                value = value.slice(0, maxLength - insertCount);
            }

            positions.sort((a, b) => a - b);
            formattedValue = value;
            for (let i = 0; i < positions.length; i++) {
                const position = positions[i] + i;
                if (position < formattedValue.length) {
                    formattedValue = formattedValue.slice(0, position) + insertChar + formattedValue.slice(position);
                }
            }
        }

        this.value = formattedValue;
    });
}

function checkEmail(){
    const emailInput = document.getElementById('chargeEmail');
    const emailError = document.getElementById('emailError');

    if (emailInput.value) {
        if (isValidEmail(emailInput.value)) {
            emailError.style.display = 'none';
        } else {
            emailError.style.display = 'block';
            return;
        }
    } else {
        emailError.style.display = 'none';
    }
}

function validateEmail(input) {
    const emailError = document.getElementById('emailError');

    if (input.value && !isValidEmail(input.value)) {
        input.classList.add('is-invalid');
        input.classList.remove('is-valid');
        emailError.style.display = 'block';
    } else {
        input.classList.remove('is-invalid');
        input.classList.remove('is-valid');
        emailError.style.display = 'none';
    }
}

function isValidEmail(email) {
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return emailPattern.test(email);
}











