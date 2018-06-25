package _02_login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.google.gson.Gson;

import _01_register.model.MemberBean;
import _01_register.service.MemberService;

@WebServlet("/_02_login/password.do")
public class ChangePWServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userAccount = request.getParameter("userAccount");
		String userPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
//		String userAccount = "﻿taipeitec00801@gmail.com";
//		String userPassword = "123";
		System.out.println("收到post請求");
		System.out.println("userAccount " + userAccount);
		System.out.println("oldPassword " + userPassword);
		System.out.println("newPassword " + newPassword);
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		MemberService mInfo = ctx.getBean(MemberService.class);
		MemberBean mb = mInfo.checkACPassword(userAccount.trim() ,userPassword.trim());
		System.out.println("Data:" + mb);
		String str = "修改成功";
		String str1 = "修改失敗";
		if (mb != null) {
			mb.setUserPassword(newPassword);
			int n = mInfo.updateMemberDate(mb);
			response.setContentType("appliction/json; charset=UTF8");
			try (PrintWriter out = response.getWriter();) {
				out.print(str);
				System.out.println(str);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try (PrintWriter out = response.getWriter();) {
				out.print(str1);
				System.out.println(str1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
