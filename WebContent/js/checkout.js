$( document ).ready(function() {
	loadProduct()
})

function loadProduct(){
	var yourOrder = document.getElementById('checkout-order')
	
	yourOrder.innerHTML = 
	`
	 	<h4>Your Order</h4>
    	<div class="checkout__order__products">Products <span>Total</span></div>
        <ul>
	`
	
	const carts = getCarts()
	
	let htmlProduct = '';
	
	let totalPrice = 0;
	if(carts) {
		
		carts.forEach( cart => {
			const total = cart.quantity * cart.product.price
			totalPrice += total
			htmlProduct += `<li style="display: flex;justify-content: space-between;">
				${cart.product.name} <span>${formatCurrency(total)}
				</span>
				</li>`
		})	
	}	
	
	yourOrder.innerHTML += htmlProduct
	yourOrder.innerHTML += 
	`
	 	</ul>
        <div class="checkout__order__subtotal">Subtotal <span>${formatCurrency(totalPrice)}</span></div>
        <div class="checkout__order__total">Total <span>${formatCurrency(totalPrice)}</span></div>
 
      
        <button type="button" onclick="checkout()" class="site-btn">PLACE ORDER</button>
	`
}


function checkout(){
	const carts = getCarts()
	
	if(!carts || (carts && carts.length <= 0)){
		error('Vui long chon san pham', 'Notify')
		return
	}
	
	const name = document.getElementById('fullName').value;
	const address = document.getElementById('address').value
	const phone = document.getElementById('phone').value
	
	if(name.trim() === '' || address.trim() === '' || phone.trim() === ''){
		error('Vui long nhap day du thong tin khach hang')
		return
	}
	
	let totalPrice = 0;
	carts.forEach( cart => {
			totalPrice += cart.quantity * cart.product.price})

	const hoadon = {
		maNV: getCurrentUser().maNV,
		tenKH: name.trim(),
		sdt: phone.trim(),
		diaChi: address.trim(),
		tongTien: totalPrice,
		carts: carts
	}
	
	console.log(hoadon)
	
   $.ajax ({
	    url: getHost() + `/checkout`,
	    type: "POST",
	   	dataType: "json",
		data: JSON.stringify(hoadon),
	   	contentType: "application/json; charset=utf-8",
	    statusCode: {
		    200: function () {	
				localStorage.removeItem('cart')
				success('Cam on quy khach', 'Thanh toan thanh cong')
				setTimeout(() => window.location.href = getHost() + '/shop', 100)
		    }
		}
    });
}



