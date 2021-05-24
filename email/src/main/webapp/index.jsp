<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	function sendmailkey() {
		var email = $('input[name=email]').val();
		var email_key = randomkey();
		$('input[name=email_key]').val(email_key);
		if ($('input[name=email]').val().trim() == ""
				|| $('input[name=email]').val() == null) {
			alert("이메일을 입력해 주세요");
		} else {
			open("emailsend.do?command=mailsend&email=" + email + "&email_key="
					+ email_key, "", "width=300 , height= 200");
		}
	}

	function mailcheck() {
		if ($('input[name=email_user]').val() == $('input[name=email_key]')
				.val()) {
			$('#mailchk').html('이메일 인증 완료');
			$('#mailchk').attr('color', '#199894b3');
			$('input[name=email_certification]').val('true');
		} else {
			$('#mailchk').html('인증번호가 틀립니다.');
			$('#mailchk').attr('color', '#f82a2aa3');
			$('input[name=email_certification]').val('false');
		}
	}

	function randomkey() {
		var chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXTZ";
		var string_length = 10;
		var randomstring = '';
		for (var i = 0; i < string_length; i++) {
			var rnum = Math.floor(Math.random() * chars.length);
			randomstring += chars.substring(rnum, rnum + 1);
		}
		return randomstring;
	}
</script>

<body>



	<h1>이메일</h1>
	<input type="text" name="email" />
	<input type="button" name="email_send" value="인증번호 받기"
		onclick="sendmailkey();" />
	<h1>인증번호</h1>

	<input type="text" name="email_user" />
	<input type="hidden" name="email_key" value="">
	<input type="hidden" name="email_certification" value="false">
	<input type="button" value="인증하기" onclick="mailcheck();" />
	<font id="mailchk" size="2"></font>



</body>
</html>