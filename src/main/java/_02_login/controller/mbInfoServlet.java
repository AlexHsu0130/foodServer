package _02_login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.google.gson.Gson;

import _01_register.model.MemberBean;
import _01_register.service.MemberService;

@WebServlet("/_02_login/login.do")
public class mbInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//=================會員資料查詢=================
		String userAccount = request.getParameter("userAccount");
		System.out.println("收到post請求");
		System.out.println(userAccount);
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		MemberService mInfo = ctx.getBean(MemberService.class);
		System.out.println("Data:"+mInfo.getUserDateNoPortrait(userAccount.trim()));
		MemberBean mb = mInfo.getUserDateNoPortrait(userAccount.trim());
		MemberBean mb1 = new MemberBean();
		mb1.setUserAccount(mb.getUserAccount());
		mb1.setNickName(mb.getNickName());
		mb1.setBirthday(mb.getBirthday());
		//Birthday資料型態修改為String
		Gson gson = new Gson();
		String data = gson.toJson(mb1);
		response.setContentType("appliction/json; charset=UTF8");
		try (PrintWriter out = response.getWriter();) {
			out.print(data);
			System.out.println(data);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
