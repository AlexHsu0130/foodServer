package controllor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import init.utils.ImageUtil;
import model.Member;
import repository.MemberDao;
import repository.impl.MemberDaoMySqlImpl;

@SuppressWarnings("serial")
@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=utf-8";

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			{
		try {
		request.setCharacterEncoding("utf-8");
//		Gson gson = new Gson();
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		
		BufferedReader br = request.getReader();
		StringBuffer jsonIn = new StringBuffer();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("=====input========================================");
		System.out.println("input: " + jsonIn);
		
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(),
				JsonObject.class);
		MemberDao memberDao = new MemberDaoMySqlImpl();
		String action = jsonObject.get("action").getAsString();
		
		if (action.equals("userLogin") || action.equals("checkAccount")) {
			String userAccount = jsonObject.get("UserAccount").getAsString();
			String userPassword = jsonObject.get("UserPassword").getAsString();
			boolean inputOk = false;
			if (action.equals("userLogin")) {
				inputOk = memberDao.userLogin(userAccount, userPassword);
			} else if (action.equals("checkAccount")) {
				inputOk = memberDao.checkAccount(userAccount);
			}
			writeText(response, String.valueOf(inputOk));
			
		} else if (action.equals("getUserDate")) {
			String userAccount = jsonObject.get("UserAccount").getAsString();
			Member member = memberDao.getUserDateNoPortrait(userAccount);			
			writeText(response, gson.toJson(member));
			
		} else if (action.equals("getImage")) {
			OutputStream os = response.getOutputStream();
			String userAccount = jsonObject.get("UserAccount").getAsString();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			byte[] portrait = memberDao.getPortrait(userAccount);
			if (portrait != null) {
				portrait = ImageUtil.shrink(portrait, imageSize);
				response.setContentType("portrait/jpeg");
				response.setContentLength(portrait.length);
			}
			os.write(portrait);
		
		} else if (action.equals("updateMemberDate") || action.equals("insertMemberDate")) {
			String stringMember = jsonObject.get("Member").getAsString();
			Member member = gson.fromJson(stringMember, Member.class);
			String imageBase64 = jsonObject.get("updatePortrait").getAsString();
			System.out.println("1------------imageBase64------" + imageBase64);
			byte[] image = Base64.getMimeDecoder().decode(imageBase64);
			System.out.println("1------------------------");
			System.out.println(image.toString());
			
			int count = 0;
			if (action.equals("insertMemberDate")) {
//				count = memberDao.insertMemberDate(member, null);
			} else if (action.equals("updateMemberDate")) {
				System.out.println("2------------------------");
				count = memberDao.updateMemberDate(member, image);
			}
			writeText(response, String.valueOf(count));
		} else {
			writeText(response, "");			
		}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userAccount = request.getParameter("userAccount");
			MemberDaoMySqlImpl mInfo = new MemberDaoMySqlImpl();
			Member member = mInfo.getUserDateNoPortrait(userAccount.trim());
			Gson gson =new Gson();
			String data = gson.toJson(member);
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
		
	

	private void writeText(HttpServletResponse response, String outText)
			throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		out.print(outText);
		System.out.println("=====output========================================");
		System.out.println("output: " + outText);
	}
}



