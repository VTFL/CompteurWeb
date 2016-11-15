/**
 * Created by Lucas-PC on 02/11/2016.
 */
import beans.Compteur;
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


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        DatabaseManager db = new DatabaseManager();
        String modif = "";
        modif = request.getParameter("modif");

        if(modif.equals("modif")) {



            int id = Integer.parseInt(request.getParameter("id"));
            int idSession = Integer.parseInt(request.getParameter("idSession"));



            String titre = request.getParameter("titre");

            String pays = request.getParameter("langue");

            String date = request.getParameter("echeance");


            Compteur cpt = new Compteur(id,titre,pays,date,idSession);
            request.setAttribute("test",cpt);

            db.modifierCompteur(cpt);

        }

        HashMap<String,Integer> hm = db.getLocales();
        request.setAttribute("locales",hm);

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
