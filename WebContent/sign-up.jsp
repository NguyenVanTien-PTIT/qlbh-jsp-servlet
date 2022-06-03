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

.form-message {
	color:red
}

.form button:disabled {
	opacity: 0.7;
}

body {
  background: #76b852; /* fallback for old browsers */
  background: rgb(141,194,111);
  background: linear-gradient(90deg, rgba(141,194,111,1) 0%, rgba(118,184,82,1) 50%);
  font-family: "Roboto", sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;      
}

$('.message a').click(function(){
   $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
});
	</style>
    <link href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet" />
</head>
<body>

<div class="login-page">
  <div class="form"><h1>SIGN IN</h1>
        <form id="form-register">
			<table>
				 <tr>
                    <td>Mã Nhân Viên</td>
                    <td><input type="text" name="maNV" id = "maNV" value='<%=request.getAttribute("maNV")%>'/>
                    <span class="form-message" id="form-msg-1"></span>
                    </td>
                </tr>
                
                 <tr>
                    <td>Mật khẩu</td>
                    <td><input type="password" name="password" id = "password"/ style="margin-top: 20px;"/>
                    <span class="form-message" id="form-msg-2"></span>
                    </td>
                </tr>
                	
                <tr>
                    <td>Họ và tên</td>
                    <td><input type="text" name="fullName" id = "fullName"/ style="margin-top: 20px;"/>
                    <span class="form-message" id="form-msg-3"></span>
                    </td>
                </tr>
                
                  <tr>
                    <td>Số điện thoại</td>
                    <td><input type="text" name="phone" id = "phone"/ style="margin-top: 20px;"/>
                    <span class="form-message" id="form-msg-4"></span>
                    </td>
                </tr>
                
                <tr>
                    <td>Email</td>
                    <td><input type="text" name="email" id = "email"/ style="margin-top: 20px;"/>
                    <span class="form-message" id="form-msg-5"></span>
                    </td>
                </tr>
                <tr>
                <tr>
                    <td colspan="2">
                    <button type="button" id="btn-register">
                    	Đăng ký
                    </button>
                    <input type="reset"></td>
                </tr>
                
			</table>
			 <p class="message"> <a href="login"> Trở lại đăng nhập </a></p>
					<div>
               			<h4>Thong tin dang ky cua ban</h4>
                		<h5>${username}</h5>
       				</div>
			 
		</form>	  		
  </div> 			
</div>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js" ></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
<script src="js/common.js"></script>
<script src="js/signup.js"></script>	
</body>
</html>
