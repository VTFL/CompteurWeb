/**
 * Created by Lucas-PC on 02/11/2016.
 */
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class Index extends HttpServlet {

	public static int userID =0;
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response )
			throws ServletException, IOException {
		String[] listpays = {"France","Royaume-Uni","USA","Russie","Yemen"};
		request.setAttribute("listpays",listpays);
		Cookie[] cookies = request.getCookies();
		boolean cookieExist = false;
		for(Cookie cookie : cookies){
			if(cookie.getName().equals("userID")){
				cookieExist = true;
			}
		}
		if(!cookieExist){
			Cookie c = new Cookie("userID",""+userID);
			response.addCookie(c);
			++userID;
		}

		this.getServletContext()
				.getRequestDispatcher( "/index.jsp" )
				.forward( request, response );
	}


}
