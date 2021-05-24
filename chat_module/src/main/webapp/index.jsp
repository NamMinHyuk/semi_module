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
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

	<div id="_chatbox" style="width:500px; height: 500px;">
        <fieldset>
            <div id="messageWindow" style="width:500px; height: 450px; border :1px solid black;"></div>
            <br /> <input id="inputMessage" type="text" onkeyup="enterkey()" />
            <input type="submit" value="send" onclick="send()" />
        </fieldset>
    </div>
	<script type="text/javascript">
		var textarea = document.getElementById("messageWindow");
		// 웹소켓 연결
		// ws : 웹소켓 서버 연결을 위한 프로토콜로 일반 통신용으로 사용된다
		var webSocket = new WebSocket('ws://localhost:8787/chat_module/chat_module_test');
		var inputMessage = document.getElementById('inputMessage');
		//웹 소켓 통신 중 실패하면 에러 발생하면 뜸 
		webSocket.onerror = function (event) {
			onError(event);
		}
		//웹 소켓 접근 성공 시 실행
		webSocket.onopen = function (event) {
			onOpen(event);
		}
		//웹 소켓으로 부터 message를 받으면 실행
		webSocket.onmessage = function (event){
			onMessage(event);
		}
		function onOpen(event) {
			alert("접속 성공");
		}
		function onError(event) {
			alert("접속 실패");
		}
		function onMessage(event) {
			// event.data를 통해 서버에서 보내 준 message를 저장
			var chatmsg = event.data;
			$("#messageWindow").html($("#messageWindow").html()
                    + "<p class='chat_content'>" + chatmsg + "</p>");
		}
		function send() {
			if(inputMessage.value == ""){
			} else {
				$("#messageWindow").html($("#messageWindow").html()
	                    + "<p class='chat_content'>" + inputMessage.value + "</p>");
			}
			// 서버에 데이터 전송하는 메소드 send
			webSocket.send(inputMessage.value);
			inputMessage.value="";
		}
		function enterkey() {
	        if (window.event.keyCode == 13) {
	            send();
	        }
	    }
	</script>
</body>
</html>