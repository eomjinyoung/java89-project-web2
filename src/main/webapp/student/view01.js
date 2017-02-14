$('#photo').fileupload({
    url: '../common/fileupload.json', // 서버에 요청할 URL
    dataType: 'json',         // 서버가 보낸 응답이 JSON임을 지정하기
    sequentialUploads: true,  // 여러 개의 파일을 업로드 할 때 순서대로 요청하기.
    singleFileUploads: false, // 한 요청에 여러 개의 파일을 전송시키기. 기본은 true.
    autoUpload: true,        // 파일을 추가할 때 자동 업로딩 여부 설정. 기본은 true.
    disableImageResize: /Android(?!.*Chrome)|Opera/
        .test(window.navigator && navigator.userAgent), // 안드로이드와 오페라 브라우저는 크기 조정 비활성 시키기
    previewMaxWidth: 800,   // 미리보기 이미지 너비
    previewMaxHeight: 800,  // 미리보기 이미지 높이 
    previewCrop: true,      // 미리보기 이미지를 출력할 때 원본에서 지정된 크기로 자르기
    done: function (e, data) { // 서버에서 응답이 오면 호출된다. 각 파일 별로 호출된다.
        $('#photo-path').val(data.result.data[0]);
    }
});

try {
  var memberNo = location.href.split('?')[1].split('=')[1];
} catch (error) {
	var memberNo = -1;
}

if (memberNo > 0) {
	prepareViewForm();
} else {
	prepareNewForm();
}

function prepareViewForm() {
	// 등록 버튼은 감춘다.
	$('.new-form').css('display', 'none');
	
	//학생 목록 가져와서 tr 태그를 만들어 붙인다.
	$.getJSON('detail.json?memberNo=' + memberNo, function(ajaxResult) {
	  var status = ajaxResult.status;
	  
	  if (status != "success") {
		  alert(ajaxResult.data);
		  return;
	  }
	  
	  var student = ajaxResult.data;
	  console.log(student);
	  
	  $('#email').val(student.email);
	  $('#name').val(student.name);
	  $('#tel').val(student.tel);
	  if (student.working) {
	    $('#working').attr('checked', 'checked');
	  } else {
		$('#not-working').attr('checked', 'checked');
	  }
	  $('#grade').val(student.grade);
	  $('#school-name').val(student.schoolName);
	  $('#photo-img').attr('src', '../upload/' + student.photoPath);
	});
	
	// 삭제, 변경 버튼을 클릭 했을 때 호출될 함수(클릭 이벤트 핸들러) 등록
	$('#delete-btn').click(function() {
	  $.getJSON('delete.json?memberNo=' + memberNo, function(ajaxResult) {
		  if (ajaxResult.status != "success") { 
			  alert(ajaxResult.data);
			  return;
		  }
		  location.href = 'main.html';
	  }); // getJSON()
	}); // click()
	
	$('#update-btn').click(function() {
	    var param = {
	    	"memberNo": memberNo,	
    		"name": $('#name').val(),
    		"tel": $('#tel').val(),
    		"email": $('#email').val(),
    		"password": $('#password').val(),
    		"working": $('#working').is(':checked'),
    		"grade": $('#grade').val(),
    		"schoolName": $('#school-name').val()
	    };
	    
	    $.post('update.json', param, function(ajaxResult) {
	    	if (ajaxResult.status != "success") {
	    		alert(ajaxResult.data);
	    		return;
	    	}
	    	location.href = 'main.html';
	    }, 'json');
	    
	}); // click()
	
} // prepareViewForm()

function prepareNewForm() {
	// 변경,삭제 버튼을 감춘다.
    $('.view-form').css('display', 'none');
  
    $('#add-btn').click(function() {
    	var param = {
    		"name": $('#name').val(),
    		"tel": $('#tel').val(),
    		"email": $('#email').val(),
    		"password": $('#password').val(),
    		"working": $('#working').is(':checked'),
    		"grade": $('#grade').val(),
    		"schoolName": $('#school-name').val(),
    		"photoPath": $('#photo-path').val()
	    };
    	console.log(param);
	    $.post('add.json', param, function(ajaxResult) {
	        if (ajaxResult.status != "success") {
	          alert(ajaxResult.data);
	          return;
	        }
	        location.href = 'main.html';
	    }, 'json'); // post();
	    
	}); // click()
}

// 목록 버튼을 클릭했을 때 호출될 함수(이벤트 핸들러) 등록!
$('#list-btn').click(function() {
	location.href = 'main.html';
});






