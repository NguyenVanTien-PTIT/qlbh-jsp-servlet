const btnLogin = document.getElementById('login-btn')

btnLogin.addEventListener("click", () => {
		
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
	
	if(!rs){
		return;
	}
	
	const maNV = document.getElementById("maNV").value
	const password = document.getElementById("password").value
	
  // Goi api login
    $.ajax ({
        url: getHost() + `/login?maNV=${maNV}&password=${password}`,
        type: "POST",
       dataType: "json",
       contentType: "application/json; charset=utf-8",
	    statusCode: {
		    200: function (data) {
		
				const res = (data.responseText)
				
				if(res === 'INVALID'){
					error('Tên tài khoản hoặc mật khẩu không chính xác', '')
				}else {
					success('Đăng nhập thành công', 'Thông báo')
					localStorage.setItem('currentUser', res.replace('UserAccount{', '{').replaceAll("'", '"'))
					setTimeout(() => window.location.href = getHost() + '/home', 500)
				}
		    }
  		}
    });
});

