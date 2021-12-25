window.addEventListener("load", function(event){
	//DOM 객체 찾아오기
	var updateform = document.getElementById("updateform");
	
	var email = document.getElementById("email");
	var pw = document.getElementById("pw");
	var pw1 = document.getElementById("pw1");
	var nickname = document.getElementById("nickname");
	var image = document.getElementById("image");
	
	var msg = document.getElementById("msg");
	var pwmsg = document.getElementById("pwmsg");

	
	var updatebtn = document.getElementById("updatebtn");
	var mainbtn = document.getElementById("mainbtn");
	var loginbtn = document.getElementById("loginbtn");
	
 	mainbtn.addEventListener("click", function(event){
 		location.href="../";
	});
	
	loginbtn.addEventListener("click", function(event){
 		location.href="login";
	});
	
	image.addEventListener("change", function(event) {
		if (this.files && this.files[0]) {
			var filename = this.files[0].name;
			var reader = new FileReader();
			reader.addEventListener("load", function(e) {
				img.src = e.target.result;
			});
			reader.readAsDataURL(this.files[0]);
		}
	})
 	

	var flag = false;
	updatebtn.addEventListener("click", function(event){
		if (pw.value.trim().length < 1) {
			pwmsg.innerHTML += '비밀번호는 필수 입력입니다.<br/>';
			flag = true;
		} else {
			var pwRegExp = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=]).*$/;
			if (!pwRegExp.test(pw.value.trim())) {
					pwmsg.innerHTML = '비밀번호는 숫자와 영문 대소문자 그리고 특수문자가 포함되어야 합니다.<br/>';
					flag = true;
			} else {
				if (pw.value.trim() !== pw1.value.trim()) {
					pwmsg.innerHTML += '2개의 비밀번호는 같아야 합니다.<br/>';
					flag = true;
				}
			}
		}
 	
		if (flag == true) {
			return;
			event.preventDefault();
		}
		
		var url="update";
		var request=new XMLHttpRequest();
		  	
		request.open("post", url, true);
		var formdata = new FormData(updateform);
		request.send(formdata);
		request.addEventListener('load', function(e){
			 var map = JSON.parse(e.target.responseText);
			 if(map.result == true){
			 	location.href = "../";
			 }else{
			 	msg.innerHTML = "수정 실패";
			 }
		})
	});
	
});