/**
 * Created by Lucas-PC on 02/11/2016.
 */
import db.DatabaseManager;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class Index extends HttpServlet {

    public static int userID =0;
    @Override
    public void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        DatabaseManager db = new DatabaseManager();
        HashMap<String,Integer> hm = db.getLocales();
        request.setAttribute("locales",hm);

        if(ajoutCookieUserID(request.getCookies()) != null) {
            response.addCookie(ajoutCookieUserID(request.getCookies()));
        }

        this.getServletContext()
                .getRequestDispatcher( "/index.jsp" )
                .forward( request, response );
    }

	/*@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response )
			throws ServletException, IOException {
		//HashMap<String,Integer> hmLocal = null;
        String hmLocal;
		hmLocal = parse();
		request.setAttribute( "local", hmLocal );
		this.getServletContext().getRequestDispatcher( "/WEB-INF/test.jsp" ).forward( request, response );
	}*/


    public Cookie ajoutCookieUserID(Cookie[] cookies){
        boolean cookieExist = false;
        Cookie c=null;
        if(cookies == null ||cookies.length == 0){
            c = new Cookie("userID", "" + userID);
            c.setMaxAge(60 * 60 * 24);
            ++userID;
        }else{
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userID")) {
                    cookieExist = true;
                }
            }
            if (!cookieExist) {
                c = new Cookie("userID", "" + userID);
                c.setMaxAge(60 * 60 * 24);
                ++userID;
            }
        }

        return c;
    }


}
