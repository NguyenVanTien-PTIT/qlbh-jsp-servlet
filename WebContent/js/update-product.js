isAuthentication()
checkRole()

document.getElementById("type").value = typeProduct

function selectTypeChange(productCode){
	const type = document.getElementById("type").value
	
	const elementCode = document.getElementById("code")
	
	if(type === '') {
		elementCode.value = "";
		return
	}
	let prefix = productCode.slice(0, 2);
	elementCode.value = productCode.replace(prefix, type)
}

function updateProduct(){
		
	let isValid = true
	let rs = true;
	
	// Ten sp
	const element1Err = document.getElementById('form-msg-1');
	const rsName = validateProductName();
	
	isValid = rsName.isValid
	
	if(!isValid){
		rs = isValid
	}
	element1Err.innerText = rsName.error	
	
	// Loại sp
	const element2Err = document.getElementById('form-msg-2');
	const rsType = validateProductType();
	
	isValid = rsType.isValid
	
	if(!isValid){
		rs = isValid	
	}
	element2Err.innerText = rsType.error
	// Mã sp
	const element3Err = document.getElementById('form-msg-3');
	const rsCode = validateProductCode();
	
	isValid = rsCode.isValid
	
	if(!isValid){
		rs = isValid	
	}
	element3Err.innerText = rsCode.error
	
	// Giá
	const element5Err = document.getElementById('form-msg-5');
	const rsPrice = validateProductPrice();
	
	isValid = rsPrice.isValid
	
	if(!isValid){
		rs = isValid	
	}
	element5Err.innerText = rsPrice.error
	
	if(!rs) return
	
	const form = $('#updateForm')[0];
    const data = new FormData(form)

	$.ajax({
          type: 'Post',
          url: getHost() + '/update-product',
          data : data,
          processData : false,
          contentType : false,
          cache : false,
          statusCode: {
		    200: function (data) {
				const res = (data.responseText)
				
				if(res === 'INVALID'){
					error('Sản phẩm đã bị xóa hoặc không tồn tại', '')
					return
				}
				
				if(res === 'EXIST_CTHD') {
					error('Sản phẩm đã được đặt hàng. Không được xóa!', '')
					return
				}
				success('Cập nhật sản phẩm thành công', 'Thông báo')
				setTimeout(() => 
				window.location.href = getHost() + '/products', 500)
		    }
  		}
    });

}