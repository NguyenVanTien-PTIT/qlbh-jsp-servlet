<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type"content="text/html;charset=UTF-8">
<title></title>
	<style>
	@import url(https://fonts.googleapis.com/css?family=Roboto:300);

.login-page {
  width: 400px;
  padding: 8% 0 0;
  margin: auto;
}
.form {
  position: relative;
  z-index: 1;
  background: #FFFFFF;
  max-width: 360px;
  margin: 0 auto 100px;
  padding: 45px;
  text-align: center;
  box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
}
.form input {
  
  font-family: "Roboto", sans-serif;
  outline: 0;
  background: #f2f2f2;
  width: 100%;
  border: 0;
  margin: 0 0 15px;
  padding: 15px;
  box-sizing: border-box;
  font-size: 14px;
}
.form button {
  font-family: "Roboto", sans-serif;
  text-transform: uppercase;
  outline: 0;
  background: #4CAF50;
  width: 100%;
  border: 0;
  padding: 15px;
  color: #FFFFFF;
  font-size: 14px;
  -webkit-transition: all 0.3 ease;
  transition: all 0.3 ease;
}
.form button:hover,.form button:active,.form button:focus {
  background: #43A047;
}

.form button:disabled {
	opacity: 0.7;
}

.form .message {
  margin: 15px 0 0;
  color: #b3b3b3;
  font-size: 12px;
}
.form .message a {
  color: #4CAF50;
  text-decoration: none;
}
.form .register-form {
  display: none;
}
.container {
  position: relative;
  z-index: 1;
  max-width: 300px;
  margin: 0 auto;
}
.container:before, .container:after {
  content: "";
  display: block;
  clear: both;
}
.container .info {
  margin: 50px auto;
  text-align: center;
}
.container .info h1 {
  margin: 0 0 15px;
  padding: 0;
  font-size: 36px;
  font-weight: 300;
  color: #1a1a1a;
}
.container .info span {
  color: #4d4d4d;
  font-size: 12px;
}
.container .info span a {
  color: #000000;
  text-decoration: none;
}
.container .info span .fa {
  color: #EF3B3A;
}
body {
  background: #76b852; /* fallback for old browsers */
  background: rgb(141,194,111);
  background: linear-gradient(90deg, rgba(141,194,111,1) 0%, rgba(118,184,82,1) 50%);
  font-family: "Roboto", sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;      
}

.form-message {
	color:red
}

.product-type {
    font-family: "Roboto", sans-serif;
    outline: 0;
    background: #f2f2f2;
    width: 100%;
    border: 0;
    margin: 0 0 15px;
    padding: 15px;
    box-sizing: border-box;
    font-size: 14px;
}

$('.message a').click(function(){
   $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
});
	</style>
    <link href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet" /> 
</head>
<body>
<% String type = (String) request.getAttribute("type"); %>
<div class="login-page">
  <div class="form"><h1>C???p nh???t s???n ph???m</h1>
    	<form id="updateForm" enctype='multipart/form-data'>
			<table>
				<input type="hidden" name="id" id="id" value="${product.getId()}"/>
				<tr>
					<td>T??n s???n ph???m</td>
					<td><input type="text" name="name" id="name" value="${product.getName()}"/></td>

					<span class="form-message" id="form-msg-1"></span></td>
				</tr>
				
				<tr>
					<td>Lo???i s???n ph???m</td>
					<td>
						<select onchange="selectTypeChange('${product.getCode()}')" 
							class="form-select product-type" 
							aria-label="Default select example" 
							name="type" id="type"
						>
						  <option value="">Ch???n lo???i s???n ph???m</option>
						  <option value="NC" >N?????c</option>
						  <option value="TA" >Th???c ??n</option>
						  <option value="KE" >Kem</option>
			  			  <option value="BA" >B??nh</option>
						</select>
						<span class="form-message" id="form-msg-2"></span>
					</td>
				</tr>
				
				<tr>
					<td>M?? s???n ph???m</td>
					<td><input type="text" id="code" name="code" value="${product.getCode()}"/>
					<span class="form-message" id="form-msg-3"></span>
					</td>
				</tr>
				
				<tr>
					<td>H??nh ???nh</td>
					<td><img src="${product.getImage()}" width=100 height=100 /></td>

				</tr>
				
				<tr>
					<td>Thay ?????i h??nh ???nh</td>
					<td><input type="file" name="image" id="image" value="">
					</td>
				</tr>
				
				<tr>
					<td>Gi?? s???n ph???m</td>
					<td><input type="text" id="price" name="price" value="${product.getPrice()}"/>

					<span class="form-message" id="form-msg-5"></span></td>
				</tr>
				
				
				
				<tr>
					  <td colspan="2">
						  <button type="button" onclick="updateProduct()">
						  	Update
						  </button>
						  <input type="Reset" value="Nh???p l???i">
					  </td>
				</tr>
			</table>
		</form>
  </div>
</div>
<script>var typeProduct ='<%=type%>'</script>;
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js" ></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
<script src="js/common.js"></script>
<script src="js/update-product.js"></script>
</body>
</html>
