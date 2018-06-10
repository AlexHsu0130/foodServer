package lab02.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import lab02.MemberDao;
import lab02.model.MemberBean_HO73;

/**
 * Servlet implementation class GetMemberPageServlet
 */
@WebServlet("/lab02/getMemberPage")
public class GetMemberPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageNo = 1;
		String page = request.getParameter("page");
		if(page != null) {
			try {
				pageNo = Integer.parseInt(page.trim());
			} catch (Exception e) {
				e.printStackTrace();
			}
			MemberDao mdao = new MemberDao();
			mdao.setPageNo(pageNo);
			List<MemberBean_HO73> list = mdao.getPageMembers();
			Gson gson =new Gson();
			String data = gson.toJson(list);
			response.setContentType("appliction/json; charset=UTF8");

			
			try(
				PrintWriter out = response.getWriter();	
			) {
				//不要送非Jason資料到前端
//				Thread.sleep(1000);
				out.print(data);
				System.out.println(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}

	
}
