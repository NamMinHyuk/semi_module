<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<%
response.setContentType("text/html; charset=UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#naver_id_login {
		padding: 0;
		width: 222px;
		height: 49px;
	}
	#naver_id_login_img {
		width: 222px;
		height: 49px;
	}
</style>
<script type="text/javascript" src="js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type="text/javascript">
	$(function() {
		Kakao.init('5c95aa7ad857e55475bed627ba9faf99');		// 카카오 developer javascript key
		Kakao.isInitialized();								// 초기화 함수 실행
		Kakao.Auth.createLoginButton({ 						// 로그인 버튼 생성
			container : '#kakao_id_login',
			success : function(authObj) {				
				Kakao.API.request({							// REST API 호출
					url : '/v2/user/me',					// 사용자 정보 가져오기 GET/POST
					success : function(response) {
						var id = response.id;
						var nickname = response.properties.nickname;
						var form = document.createElement('form');
						var objs_1;
						objs_1 = document.createElement('input');
						objs_1.setAttribute('type', 'hidden');
						objs_1.setAttribute('name', 'id');
						objs_1.setAttribute('value', id);
						form.appendChild(objs_1);
						objs_2 = document.createElement('input');
						objs_2.setAttribute('type', 'hidden');
						objs_2.setAttribute('name', 'nickname');
						objs_2.setAttribute('value', nickname);
						form.appendChild(objs_2);
						var objs_4 = document.createElement('input');
						objs_4.setAttribute('type', 'hidden');
						objs_4.setAttribute('name', 'command');
						objs_4.setAttribute('value', 'login');
						form.appendChild(objs_4);
						form.setAttribute('method', 'post');
						form.setAttribute('action', "login.do");
						document.body.appendChild(form);
						form.submit();
					},
					fail : function(error) {
						console.log("request fail", error);
					}
				});
			},
			fail : function(err) {
				alert(JSON.stringify(err));
			},
		});
	});
</script>
</head>
<body>
	<div id="kakao_padding">
		<div id="kakao_id_login"></div>
	</div>
	<div id="naver_id_login"></div>
	<script type="text/javascript">
		var naver_id_login = new naver_id_login("_8W84KFoLj6ciIDcb0y2", "http://localhost:8787/Login/naver_callback.jsp"); // key값으로 객체 생성, callback url
		var state = naver_id_login.getUniqState();								
		naver_id_login.setButton("green", 10, 40); 		
		naver_id_login.setDomain("http://localhost:8787/Login/naver_callback.jsp"); 
		naver_id_login.setState(state);
		naver_id_login.init_naver_id_login();
	</script>
</body>
</html>