package _02_login.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;

import _01_register.repository.MemberDao;

@WebServlet("/_02_login/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userAccount = request.getParameter("userAccount");
		System.out.println("收到post請求");
		System.out.println(userAccount);
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		MemberDao mInfo = ctx.getBean(MemberDao.class);
		System.out.println(mInfo);
		System.out.println(mInfo.getUserDateNoPortrait(userAccount.trim()));
		Gson gson = new Gson();
		String data = gson.toJson(mInfo.getUserDateNoPortrait(userAccount.trim()));
		response.setContentType("appliction/json; charset=UTF8");

		try (PrintWriter out = response.getWriter();) {
			// 不要送非Jason資料到前端
			// Thread.sleep(1000);
			out.print(data);
			System.out.println(data);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
