isAuthentication()
var spanHTML = document.getElementById('current-user-login')
var spanHTML2 = document.getElementById('current-user-login2')
const user = JSON.parse(localStorage.getItem('currentUser'))
spanHTML.innerText = user.fullName
spanHTML2.innerText = user.fullName

checkRole()

function checkRole(){
	
	const user = getCurrentUser()
	if(!user.roles.includes('QUAN_LY')){
		const elements = document.getElementsByClassName('is-admin')
		for (let i = 0; i < elements.length; i++) {
		   elements[i].style.display = 'none'
		}
	}
}

function deleteProduct(id) {

	$.ajax ({
        url: getHost() + `/delete-product?id=${id}`,
        type: "POST",
       dataType: "json",
       contentType: "application/json; charset=utf-8",
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
				
				success('Xóa thành công', 'Thông báo')
				setTimeout(() => window.location.reload(), 500)
				
		    }
  		}
    });
}