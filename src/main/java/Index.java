/**
 * Created by Lucas-PC on 02/11/2016.
 */
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Index { //extends HttpServlet {


	/*@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response )
			throws ServletException, IOException {
		this.getServletContext()
				.getRequestDispatcher( "/index.jsp" )
				.forward( request, response );
	}*/

	/*@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response )
			throws ServletException, IOException {
		//HashMap<String,Integer> hmLocal = null;
        String hmLocal;
		hmLocal = parse();
		request.setAttribute( "local", hmLocal );
		this.getServletContext().getRequestDispatcher( "/WEB-INF/test.jsp" ).forward( request, response );
	}*/

	//private HashMap<String,Integer> parse()  {
    private String parse() {

		String line ="";
		String capitale ="";
		String pays="";
		String[] tabListeCap;

        InputStream f = this.getClass().getResourceAsStream("/cities.txt");
        InputStream f2 = this.getClass().getResourceAsStream("/ListeCapitale.csv");

        Scanner sc = new Scanner(f);
        Scanner sc2 = new Scanner(f2);

        HashMap<String,Integer> hmRes = new HashMap<String, Integer>();
		HashMap<String,String> hmCap = new HashMap<String, String>();

        String listeCap = sc2.nextLine();

		while (sc2.hasNextLine()) {

			listeCap = sc2.nextLine();
			listeCap = listeCap.replace("\"", "");
			tabListeCap = listeCap.split(",");

			hmCap.put(tabListeCap[1],tabListeCap[0]);
		}

		while (sc.hasNextLine()) {
			line = sc.next();
			pays ="";
            String signe = "#";

			if(line.contains("GMT")) {

				String[] temp = line.split(",");

				capitale = temp[0].replace("\"","");

				for(Map.Entry<String, String> entry : hmCap.entrySet()) {

					String cap = entry.getKey();
					String country = entry.getValue();

					if(cap.equals(capitale))
						pays = country;
				}

				if (! pays.equals("")) {

					Integer intGmt = new Integer(0);

					if (temp[1].contains("+")) {
						signe = "\\+";
						String gmt = temp[1].split(signe)[1];
						gmt = gmt.split(":")[0];
						intGmt = new Integer(gmt);
					} else {
						if (temp[1].contains("-")) {
							signe = "-";
							String gmt = temp[1].split(signe)[1];
							gmt = gmt.split(":")[0];
                            int tmp = Integer.parseInt(gmt)*-1;
							intGmt = new Integer(tmp);
						}
					}

                    hmRes.put(pays, intGmt);
				}
			}
			sc.nextLine();
		}

		return "Argentina : " + hmRes.get("Argentina") + "";
	}

    public static void main(String[] args) throws FileNotFoundException {

        String line ="";
        String capitale ="";
        String pays="";
        String[] tabListeCap;

        /*InputStream f = this.getClass().getResourceAsStream("/cities.txt");
        InputStream f2 = this.getClass().getResourceAsStream("/ListeCapitale.csv");*/

        Scanner sc = null;
        try {
            sc = new Scanner(new File("src/main/resources/cities.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner sc2 = new Scanner(new File("src/main/resources/ListeCapitale.csv"));

        HashMap<String,Integer> hmRes = new HashMap<String, Integer>();
        HashMap<String,String> hmCap = new HashMap<String, String>();

        String listeCap = sc2.nextLine();

        while (sc2.hasNextLine()) {

            listeCap = sc2.nextLine();
            listeCap = listeCap.replace("\"", "");
            tabListeCap = listeCap.split(",");

            hmCap.put(tabListeCap[1],tabListeCap[0]);
        }

        while (sc.hasNextLine()) {
            line = sc.next();
            pays ="";
            String signe = "#";

            if(line.contains("GMT")) {

                String[] temp = line.split(",");

                capitale = temp[0].replace("\"","");

                for(Map.Entry<String, String> entry : hmCap.entrySet()) {

                    String cap = entry.getKey();
                    String country = entry.getValue();

                    if(cap.equals(capitale))
                        pays = country;
                }

                if (! pays.equals("")) {

                    Integer intGmt = new Integer(0);

                    if (temp[1].contains("+")) {
                        signe = "\\+";
                        String gmt = temp[1].split(signe)[1];
                        gmt = gmt.split(":")[0];
                        intGmt = new Integer(gmt);
                    } else {
                        if (temp[1].contains("-")) {
                            signe = "-";
                            String gmt = temp[1].split(signe)[1];
                            gmt = gmt.split(":")[0];
                            System.out.println("Test : " + gmt);
                            int tmp = Integer.parseInt(gmt)*-1;
                            intGmt = new Integer(tmp);
                        }
                    }

                    hmRes.put(pays, intGmt);
                }
            }
            sc.nextLine();
        }
    }
}
