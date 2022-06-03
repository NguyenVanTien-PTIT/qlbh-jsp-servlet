const btn = document.getElementById('btn-register');

btn.addEventListener("click", () => {
	validate()
})

function validate() {
	
	let isValid = true
	let rs = true;
	
	// MaNV
	const element1Err = document.getElementById('form-msg-1');
	const rsMaNV = validateMaNV();
	
	isValid = rsMaNV.isValid
	
	if(!isValid){
		rs = isValid
	}
	element1Err.innerText = rsMaNV.error	
	
	// Password
	const element2Err = document.getElementById('form-msg-2');
	const rsPassword = validatePassword();
	
	isValid = rsPassword.isValid
	
	if(!isValid){
		rs = isValid	
	}
	element2Err.innerText = rsPassword.error
	
	// Ho va ten
	const element3Err = document.getElementById('form-msg-3');
	const rsFullName = validateFullName();
	
	isValid = rsFullName.isValid
	
	if(!isValid){
		rs = isValid	
	}
	element3Err.innerText = rsFullName.error
	
	// Phone
	const element4Err = document.getElementById('form-msg-4');
	const rsPhone = validatePhone();
	
	isValid = rsPhone.isValid
	
	if(!isValid){
		rs = isValid	
	}
	element4Err.innerText = rsPhone.error
	
	// Email
	const element5Err = document.getElementById('form-msg-5');
	const rsEmail = validateEmail();
	
	isValid = rsEmail.isValid
	
	if(!isValid){
		rs = isValid	
	}
	element5Err.innerText = rsEmail.error
	
	if(!rs){
		return;
	}
	
	const f1 = document.getElementById("maNV").value
	const f2 = document.getElementById("password").value
	const f3 = document.getElementById("fullName").value
	const f4 = document.getElementById("phone").value
	const f5 = document.getElementById("email").value
	const data = {maNV: f1, password: f2, fullName: f3, phone: f4, email: f5}
	signup(data)

}

function signup(data) {
	 // Goi api signup
    $.ajax ({
        url: getHost() + `/sign-up?maNV=${data.maNV}
				&password=${data.password}&fullName=${data.fullName}
				&phone=${data.phone}&email=${data.email}`,
        type: "POST",
       dataType: "json",
       contentType: "application/json; charset=utf-8",
	    statusCode: {
		    200: function (data) {
		
				const res = (data.responseText)
				
				if(res === 'INVALID'){
					error('Mã nhân viên đã tồn tại')
				}else {
					success('Đăng ký thành công', 'Thông báo')
					document.getElementById('form-register').reset();
				}
		    }
  		}
    });
}
