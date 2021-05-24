<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>결제 모듈화</h1>

	<form action="pay.do" method="post">
		<input type="hidden" name="command" value="pay" />
		<table border="1">
			<tr>
				<th>결제수단</th>
				<td>
					<select name="pay_method">
						<option value="card">신용카드</option>
						<option value="trans">실시간 계좌이체</option>
						<option value="vbank">가상계좌</option>
						<option value="phone">휴대폰 소액 결제</option>
						<option value="cultureland">문화상품권</option>
						<option value="smartculture">스마트 문화상품권</option>
						<option value="happymoney">해피머니</option>
						<option value="booknlife">도서 문화상품권</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>상품명</th>
				<td><input type="text" name="product" /></td>
			</tr>
			<tr>
				<th>가격</th>
				<td><input type="number" name="price" /></td>
			</tr>
			<tr>
				<th>구매자 이름</th>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="submit" value="결제하기">
				</td>
			</tr>
		</table>
	</form>

</body>
</html>