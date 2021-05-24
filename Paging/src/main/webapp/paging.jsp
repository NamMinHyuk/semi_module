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


  	  <!-- value에 서블릿 주소 넣기 -->
	  <c:url var = "action" value="/page.do" />

	  <!-- 이전 페이지 블록으로 이동 -->
	  <c:if test="${param.nowBlock > 1}">
		    <a href="${action}?command=${param.command}&nowPage=${param.blockBegin-1}">◀</a>
	  </c:if>
	
	  <!-- 현재 페이지 블록에 해당하는 페이지 출력 -->
	  <c:forEach begin="${param.blockBegin}" end="${param.blockEnd}" var="index" >
		    <c:choose>
			    <c:when test="${param.nowPage eq index}">
				     ${index}
			    </c:when>
			    <c:otherwise>
            		 <a href="${action}?command=${param.command}&nowPage=${index}">${index}</a>
			    </c:otherwise>
		    </c:choose>
	  </c:forEach>

	  <!-- 다음 페이지 블록으로 이동 -->
	  <c:if test="${param.nowBlock < param.totalBlock}">
		    <a href="${action}?command=${param.command}&nowPage=${param.blockEnd+1}">▶</a>
	  </c:if>


</body>

</html>