<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>페이징 모듈화</h1>
	
	<table border="1">
		<col width="500" />
		
		<tr>
			<th>제목</th>
		</tr>
		
		<c:choose>
			<c:when test="${empty list }">
				<tr>
					<th>---------작성된 글이 없습니다.--------</th>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.title }</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
	
	<jsp:include page="/paging.jsp">
		<jsp:param value="${BoardCommand }" name="command"/>
		<jsp:param value="${dto.nowBlock}" name="nowBlock" />
		<jsp:param value="${dto.blockBegin }" name="blockBegin"/>
		<jsp:param value="${dto.blockEnd }" name="blockEnd"/>
	  	<jsp:param value="${dto.nowPage}" name="nowPage" />
	 	<jsp:param value="${dto.blockBegin}" name="blockBegin" />
	  	<jsp:param value="${dto.blockEnd}" name="blockEnd" />
	  	<jsp:param value="${dto.totalBlock}" name="totalBlock" />
	</jsp:include>
	

</body>
</html>