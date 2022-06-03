isAuthentication()

var spanHTML = document.getElementById('current-user-login')
var spanHTML2 = document.getElementById('current-user-login2')
const user = JSON.parse(localStorage.getItem('currentUser'))
console.log(spanHTML)
spanHTML.innerText = user.fullName
spanHTML2.innerText = user.fullName