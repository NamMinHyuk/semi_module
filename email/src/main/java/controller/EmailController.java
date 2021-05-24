package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gmail.MailSend;

@WebServlet("/emailsend.do")
public class EmailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String command = request.getParameter("command");

		if (command.equals("mailsend")) {
			String email = request.getParameter("email"); // 수신자
			String from = "codesendtest@gmail.com"; // 발신자
			String cc = ""; // 참조
			String subject = "이메일 인증번호 입니다.";
			String content = request.getParameter("email_key");
			try {
				MailSend ms = new MailSend();
				ms.sendEmail(from, email, cc, subject, content);
				System.out.println("전송 성공");
				response.sendRedirect("send_num.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
