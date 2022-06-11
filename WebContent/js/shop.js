function addToCart(id, code, image, type, price, name) {
	const product = {id, code, image, type, price, name}
	let productList = JSON.parse(localStorage.getItem("cart"))
	if(!productList){
		productList = [];
	}
	
	console.log(productList)
	
	let index = 0;
	
	let pro = productList.find((e, i) => {
		if(e.product.id == product.id){
			index = i
			return true
		}
		return false
	});
	
	if(pro) {
		deleteItem(index, productList)		
		pro.quantity = pro.quantity + 1;
	}else {
		pro = {product: product, quantity: 1}
	}

	productList.push(pro)
	console.log(productList)
	
	localStorage.setItem("cart", JSON.stringify(productList))
	success('Thêm vào giỏ hàng thành công', 'Thông báo')
}

function deleteItem(index, productList){
	for( let i = 0; i < productList.length; i++){ 
		if(i==index){
			productList.splice(i, 1); 	
			break
		}
	}
}