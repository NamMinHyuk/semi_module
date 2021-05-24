
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<%
	String pay_method = (String)request.getAttribute("pay_method");
	String product = (String)request.getAttribute("product");
    String name = (String)request.getAttribute("name");
    int totalPrice = (int)request.getAttribute("price"); 
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js" type="text/javascript"></script>
<script type="text/javascript">

	$(function(){
	    IMP.init('imp92199014'); // 가맹점 식별코드
	    var msg;
	    
	    IMP.request_pay({
	        pg : 'inicis',										// PG사 설정(KG 이니시스로 설정)
	        pay_method : '<%=pay_method%>',						// 결제수단 설정
	        merchant_uid : 'merchant_' + new Date().getTime(),  
	        name : '<%=product%>',								// 상품명
	        amount : '<%=totalPrice%>',							// 가격
	        buyer_email : 'abcabc@naver.com',					// 구매자 이메일
	        buyer_name : '<%=name%>',							// 구매자 이름
	        buyer_tel : '010-1231-4567',						// 구매자 전화번호
	        buyer_addr : '서울 강남구',							// 구매자 주소
	        buyer_postcode : '123-456',							// 구매자 우편번호
	        m_redirect_url : 'https://www.naver.com' 			// 모바일 결제 시, 결제가 끝나고 랜딩되는 url 
	    }, function(rsp) {
	        if ( rsp.success ) {
	            msg = '결제가 완료되었습니다.';
                // msg += '\n고유ID : ' + rsp.imp_uid;
                // msg += '\n상점 거래ID : ' + rsp.merchant_uid;
                msg += '\n결제 금액 : ' + rsp.paid_amount + '원';
                // msg += '\n카드 승인번호 : ' + rsp.apply_num;
	            //성공시 이동할 페이지
	            location.href='pay.do?command=paysuccess';
	        } else {
	            msg = '결제에 실패하였습니다.';
	            msg += '\n에러내용 : ' + rsp.error_msg;
	            //실패시 이동할 페이지
	            location.href='pay.do?command=payfail';
	        }
	        alert(msg);
	    });
	    
	});

</script>
</head>
<body>
</body>
</html>