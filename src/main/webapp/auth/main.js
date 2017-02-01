document.querySelector('#login-btn').onclick = function() {
	// 이메일 저장이 체크되어 있으면 쿠키에 저장하고, 
	// 체크되어 있지 않으면 쿠키에서 제거한다.
	if (document.querySelector('#save-email').checked) {
    setCookie('email', document.querySelector('#email').value, 30);
  } else {
    setCookie('email', '', 0);
  }
	
	var param = {
		email: document.querySelector('#email').value,
		password: document.querySelector('#password').value
	};
	
	var userTypeList = document.querySelectorAll('input[name=user-type]');
	for (var i = 0; i < userTypeList.length; i++) {
	  if (userTypeList[i].checked) {
		  param.userType = userTypeList[i].value;
		  break;
	  }	
	}
	
	post('login.json', param, function(jsonText) {
		var ajaxResult = JSON.parse(jsonText);
		
		if (ajaxResult.status == "success") {
			location.href = "../student/main.html";	
			return;
		}
		
		alert(ajaxResult.data);
		
	});
}

// email 쿠키가 있다면 값을 넣는다.
document.querySelector('#email').value = getCookie('email').replace(/"/g, '');
