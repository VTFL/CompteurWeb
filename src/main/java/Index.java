/**
 * Created by Lucas-PC on 02/11/2016.
 */
import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Index extends HttpServlet {

	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response )
			throws ServletException, IOException {
		this.getServletContext()
				.getRequestDispatcher( "/index.jsp" )
				.forward( request, response );
	}

}
