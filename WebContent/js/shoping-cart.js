var bodyTable = document.getElementById('table-product-cart')

loadProduct()

function loadProduct(){
	const carts = getCarts()
	
	if(carts) {
		
		bodyTable.innerHTML = ''
		
		let totalPrice = 0;
		
		carts.forEach( cart => {
			totalPrice += cart.quantity * cart.product.price
			
			bodyTable.innerHTML += `
			 	<tr>
		            <td class="shoping__cart__item">
		                <img class="img-product" src="${cart.product.image}" alt="">
		                <h5>${cart.product.name}</h5>
		            </td>
		            <td class="shoping__cart__price">
		               	${formatCurrency(cart.product.price * 1)}
		            </td>
		            <td class="shoping__cart__quantity">
		                <div class="quantity">
		                    <div class="pro-qty">
								<span class="dec qtybtn" onclick="decrease('${cart.product.id}')">-</span>
		                        <input type="text" value="${cart.quantity}">
								<span class="inc qtybtn" onclick="increase('${cart.product.id}')">+</span>
		                    </div>
		                </div>
		            </td>
		            <td class="shoping__cart__total">
		                ${formatCurrency(cart.quantity * cart.product.price)}
		            </td>
		            <td class="shoping__cart__item__close">
		                <span class="icon_close" onclick="removeItem('${cart.product.id}')"></span>
		            </td>
		        </tr>
			`
		})	
		
		const subtotal = document.getElementById('subtotal-cart')
		const total = document.getElementById('total-cart')
		
		subtotal.innerHTML = formatCurrency(totalPrice)
		total.innerHTML = formatCurrency(totalPrice)
	}	
}

function proceedCheckout(){
	const carts = getCarts()
	
	if(!carts || (carts && carts.length <= 0)){
		error('Vui long chon san pham', 'Notify')
		return
	}
	
	setTimeout(() => window.location.href = getHost() + '/checkout')
}

function decrease(productId) {
	const carts = getCarts()
	
	let i = -1;
	
	const cart = carts.find((cart, index) => {
		i = index;
		return cart.product.id == productId 
	})
	
	if(cart.quantity == 1){
		deleteItem(i, carts)	
	} else {
		cart.quantity = cart.quantity - 1	
	}
		
	localStorage.setItem('cart', JSON.stringify(carts))
	
	loadProduct()
}

function increase(productId) {
	const carts = getCarts()
	
	const cart = carts.find((cart) => cart.product.id == productId )
	
	cart.quantity = cart.quantity + 1
	localStorage.setItem('cart', JSON.stringify(carts))
	
	loadProduct()
}

function removeItem(productId) {
	const carts = getCarts()
	
	let i = -1;
	
	const cart = carts.find((cart, index) => {
		i = index;
		return cart.product.id == productId 
	})
	
	deleteItem(i, carts)	
		
	localStorage.setItem('cart', JSON.stringify(carts))
	
	loadProduct()
}

function deleteItem(index, productList){
	
	console.log(index)
	for( let i = 0; i < productList.length; i++){ 
		if(i==index){
			productList.splice(i, 1); 	
			break
		}
	}
}

