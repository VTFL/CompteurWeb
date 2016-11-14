import beans.Compteur;
import db.DatabaseManager;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Florian on 14/11/2016.
 */
public class ModifTimer extends HttpServlet {

    @Override
    public void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        DatabaseManager db = new DatabaseManager();
        HashMap<String,Integer> hm = db.getLocales();
        request.setAttribute("locales",hm);
        int id = Integer.parseInt(request.getParameter("id"));
        Compteur c = db.getCompteur(id);
        request.setAttribute("compteur",c);

        this.getServletContext()
                .getRequestDispatcher( "/modifForm.jsp" )
                .forward( request, response );
    }
}
