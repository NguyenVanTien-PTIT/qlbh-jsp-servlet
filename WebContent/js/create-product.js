isAuthentication()

function selectTypeChange(productCode){
	const type = document.getElementById("type").value
	
	const elementCode = document.getElementById("code")
	
	if(type === '') {
		elementCode.value = "";
		return
	}
	
	elementCode.value = productCode.replace('PREFIX', type)
}

function createProduct(){
		
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
	
	// Anh
	const element4Err = document.getElementById('form-msg-4');
	const rsFile = validateFile();

	isValid = rsFile.isValid
	
	if(!isValid){
		rs = isValid	
	}
	element4Err.innerText = rsFile.error
	
	// Giá
	const element5Err = document.getElementById('form-msg-5');
	const rsPrice = validateProductPrice();
	
	isValid = rsPrice.isValid
	
	if(!isValid){
		rs = isValid	
	}
	element5Err.innerText = rsPrice.error
	
	if(!rs) return
	
	const form = $('#createForm')[0];
    const data = new FormData(form)
	
	$.ajax({
          type: 'Post',
          url: getHost() + '/create-product',
          data : data,
          processData : false,
          contentType : false,
          cache : false,
          statusCode: {
		    200: function (data) {
				const res = (data.responseText)
				
				if(res === 'INVALID'){
					error('Mã sản phẩm đã tồn tại', '')
				}else {
					success('Thêm mới sản phẩm thành công', 'Thông báo')
					setTimeout(() => 
					window.location.href = getHost() + '/products', 500)
				}
		    }
  		}
    });

}