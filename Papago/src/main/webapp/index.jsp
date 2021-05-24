<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

	<h1>파파고 모듈화</h1>
	

			<span>입력 언어</span>
			<select name="source">
				<option value="ko">한국어</option>
				<option value="en">영어</option>
				<option value="ja">일본어</option>
				<option value="zh-CN">중국어 간체</option>
				<option value="zh-TW">중국어 번체</option>
				<option value="vi">베트남어</option>
				<option value="id">인도네시아어</option>
				<option value="th">태국어</option>
				<option value="de">독일어</option>
				<option value="ru">러시아어</option>
				<option value="es">스페인어</option>
				<option value="it">이탈리아어</option>
				<option value="fr">프랑스어</option>
			</select>
			<br/>
			<textarea id="send_text" name="content" cols="40" rows="4" placeholder="번역할 내용을 입력하세요."></textarea>
			<input type="button" value="번역하기" onclick="send()" />
			<br/>
			
			<br> <span>번역 언어</span>
			<select name="target">
				<option value="ko">한국어</option>
				<option value="en">영어</option>
				<option value="ja">일본어</option>
				<option value="zh-CN">중국어 간체</option>
				<option value="zh-TW">중국어 번체</option>
				<option value="vi">베트남어</option>
				<option value="id">인도네시아어</option>
				<option value="th">태국어</option>
				<option value="de">독일어</option>
				<option value="ru">러시아어</option>
				<option value="es">스페인어</option>
				<option value="it">이탈리아어</option>
				<option value="fr">프랑스어</option>
			</select>
			<br/>
			<textarea id="result_text" name="content" cols="40" rows="4" placeholder="번역 결과" readonly="readonly"></textarea>

	<script type="text/javascript">
		function send() {
			var msg = $("#send_text").val();
			var source = $('select[name=source]').val();
			var target = $('select[name=target]').val();
			$.ajax({
				url:"papago.do",
				method:"post",
				data:{command:"translation",msg:msg,source:source,target:target},
				dataType:"text",
				success:function(msg){
					result_text.value=msg;
				},
				error(){
					alert("통신 실패");
				}
			});
		}
	</script>


</body>
</html>