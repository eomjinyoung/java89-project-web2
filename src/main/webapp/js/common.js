window.addEventListener("load", function() {
	// header.html을 가져와서 붙인다.
	get('../header.html', function(result) {
	  // 서버에서 로그인 사용자 정보를 가져온다.
	  get('../auth/loginUser.json', function(jsonText) {
		var ajaxResult = JSON.parse(jsonText);
		
		document.querySelector('#header').innerHTML = result;

		if (ajaxResult.status == "fail") { // 로그인 되지 않았으면,
			// 로그온 상태 출력 창을 감춘다.
			document.querySelector('#logon-div').style.display = 'none';
			
			// 로그인 버튼의 클릭 이벤트 핸들러 등록하기
			document.querySelector('#login-btn').onclick = function(event) {
				event.preventDefault()
				location.href = '../auth/main.html'
			};
			return;
		}
		
		// 로그인 되었으면, 로그오프 상태 출력 창을 감춘다. 
		document.querySelector('#logoff-div').style.display = 'none';
		document.querySelector('#logon-div img').src = 
			'../upload/' + ajaxResult.data.photoPath;
		document.querySelector('#logon-div span').textContent = 
			ajaxResult.data.name;
		
		// 로그아웃 버튼의 클릭 이벤트 핸들러 등록하기
		document.querySelector('#logout-btn').onclick = function(event) {
			event.preventDefault()
			get('../auth/logout.json', function(jsonText) {
				location.href = '../auth/main.html'
			});
		};
	  });
	});
	
	// sidebar.html을 가져와서 붙인다.
	get('../sidebar.html', function(result) {
	  document.querySelector('#sidebar').innerHTML = result;
	});
	
	// footer.html을 가져와서 붙인다.
	get('../footer.html', function(result) {
	  document.querySelector('#footer').innerHTML = result;
	});
});