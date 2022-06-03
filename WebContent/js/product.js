isAuthentication()
function deleteProduct(id) {
	console.log(id)
	
	$.ajax ({
        url: getHost() + `/delete-product?id=${id}`,
        type: "POST",
       dataType: "json",
       contentType: "application/json; charset=utf-8",
	    statusCode: {
		    200: function (data) {
		
				const res = (data.responseText)
				
				if(res === 'INVALID'){
					error('Có lỗi xảy ra', '')
				}else {
					success('Xóa thành công', 'Thông báo')
					setTimeout(() => window.location.reload(), 500)
				}
		    }
  		}
    });
}