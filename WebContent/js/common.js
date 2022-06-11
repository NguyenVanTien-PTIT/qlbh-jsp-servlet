var regex = /^[A-Za-z0-9 ]+$/

function success(msg, title) { toastr.success(msg, title); }
function error(msg, title) { toastr.error(msg, title); }

function getHost() { 
	return 'http://localhost:8080/qlbanhang'
 }

function getCarts(){
	return JSON.parse(localStorage.getItem('cart'))
}

function validateMaNV(){
	const maNV = document.getElementById("maNV").value
	let isValid = true;
	let error = ''
	
	if(maNV.trim() === ''){
		error = 'Không được để trống mã nhân viên'
		isValid = false;
	}
	if(maNV.length < 4){
		error = 'Mã nhân viên không nhập dưới 4 kí tự';
		isValid = false;
	}
	
	if(maNV.length > 8){
		error = 'Mã nhân viên không nhập trên 8 kí tự';
		isValid = false;
	}
	
	if(!regex.test(maNV)){
		error = 'Mã nhân viên không nhập ký tự đặc biệt';
		isValid = false;
	}
	
	return {isValid, error}
}

function validatePassword(){
	const password = document.getElementById("password").value
	let isValid = true;
	let error = ''
	
	if(password.trim() === ''){
		error = 'Không được để trống mật khẩu'
		isValid = false;
		return {isValid, error}
	}
	if(password.length < 6){
		error = 'Mật khẩu không nhập dưới 6 kí tự';
		isValid = false;
		return {isValid, error}
	}
	
	if(password.length > 15){
		error = 'Mật khẩu không nhập trên 15 kí tự';
		isValid = false;
		return {isValid, error}
	}
	
	return {isValid, error}
}

function validateFullName(){
	const fullName = document.getElementById("fullName").value
	let isValid = true;
	let error = ''
	
	if(fullName.trim() === ''){
		return {isValid, error}
	}
	if(fullName.length < 8){
		error = 'Họ và tên không nhập dưới 8 kí tự';
		isValid = false;
		return {isValid, error}
	}
	
	if(fullName.length > 30){
		error = 'họ và tên không nhập trên 30 kí tự';
		isValid = false;
		return {isValid, error}
	}
	
	return {isValid, error}
}

function validatePhone(){
	const phone = document.getElementById("phone").value
	let isValid = true;
	let error = ''
	
	if(phone.trim() === '') return {isValid, error}
	
	if(!isVietnamesePhoneNumber(phone)){
		error = 'Số điện thoại không hợp lệ';
		isValid = false;
	}
	
	return {isValid, error}
}

function validateEmail(){
	const email = document.getElementById("email").value
	let isValid = true;
	let error = ''
	
	if(email.trim() === '') return {isValid, error}
	
	if(!isEmailValid(email)){
		error = 'Email không hợp lệ';
		isValid = false;
	}
	
	return {isValid, error}
}

function validateProductName(){
	const name = document.getElementById("name").value
	let isValid = true;
	let error = ''
	
	if(name.trim() === ''){
		error = 'Tên sản phẩm nhập từ 4 → 30 kí tự';
		isValid = false;
		return {isValid, error}
	}
	
	if(name.length < 4){
		error = 'Tên sản phẩm không nhập dưới 4 kí tự';
		isValid = false;
		return {isValid, error}
	}
	
	if(name.length > 30){
		error = 'Tên sản phẩm không nhập trên 30 kí tự';
		isValid = false;
		return {isValid, error}
	}
	
	return {isValid, error}
}

function validateProductType(){
	const type = document.getElementById("type").value
	let isValid = true;
	let error = ''
	if(type.trim() === ''){
		error = 'Vui lòng chọn loại sản phẩm';
		isValid = false;
	}
	
	return {isValid, error}
}

function validateProductCode(){
	const code = document.getElementById("code").value
	let isValid = true;
	let error = ''
	
	if(code.trim() === ''){
		error = 'Mã sản phẩm nhập từ 4 → 8 kí tự';
		isValid = false;
		return {isValid, error}
	}
	
	if(!regex.test(code)){
		error = 'Mã sản phẩm không nhập ký tự đặc biệt';
		isValid = false;
	}
	return {isValid, error}
}

function validateFile(){
	let isValid = true;
	let error = ''
	const fileInput = document.getElementById('image');
	if(fileInput.value === ''){
		error = 'Vui lòng chọn hình ảnh';
		isValid = false;
	}
	
	return {isValid, error}
}

function validateProductPrice(){
	const price = document.getElementById("price").value
	let isValid = true;
	let error = ''
	
	if(price === '' || price === undefined || price === null){
		error = 'Giá sản phẩm không được bỏ trống';
		isValid = false;
		return {isValid, error}
	}
	
	if(price.toString().length < 5){
		error = 'Giá sản phẩm không nhập dưới 5 kí tự';
		isValid = false;
	}
	return {isValid, error}
}

function isVietnamesePhoneNumber(number) {
  return /(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})\b/.test(number);
}

function isEmailValid(email) {
  const emailRegexp = new RegExp(
    /^[a-zA-Z0-9][\-_\.\+\!\#\$\%\&\'\*\/\=\?\^\`\{\|]{0,1}([a-zA-Z0-9][\-_\.\+\!\#\$\%\&\'\*\/\=\?\^\`\{\|]{0,1})*[a-zA-Z0-9]@[a-zA-Z0-9][-\.]{0,1}([a-zA-Z][-\.]{0,1})*[a-zA-Z0-9]\.[a-zA-Z0-9]{1,}([\.\-]{0,1}[a-zA-Z]){0,}[a-zA-Z0-9]{0,}$/i
  )

  return emailRegexp.test(email)
}

function isAuthentication(){
	const user = JSON.parse(localStorage.getItem('currentUser'))
	if(user) return;
	
	setTimeout(() => window.location.href = getHost() + '/login')
}

function getCurrentUser(){
	return JSON.parse(localStorage.getItem('currentUser'))	
}

function checkRole(){
	const user = getCurrentUser()
	if(!user.roles.includes('QUAN_LY')){
		setTimeout(() => window.location.href = getHost() + '/products')
	}
}

function formatCurrency(currencyValue) {
	return currencyValue.toLocaleString('it-IT', {style : 'currency', currency : 'VND'})
}
