package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BDD.ConfigurationBDD;

/**
 * Servlet implementation class ChercherDistance
 */
@WebServlet("/ChercherDistance")
public class ChercherDistance extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE_FORM = "/WEB-INF/chercherDistance.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChercherDistance() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//HttpSession session = request.getSession();
		System.out.println(request.getAttribute("distance"));
		
		//session.getAttribute("distance");
		request.getRequestDispatcher(VUE_FORM).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String villeDepart = request.getParameter("villeDepart");
		String villeArrivee = request.getParameter("villeArrivee");
		System.out.println(villeDepart);
		System.out.println(villeArrivee);
		
		ConfigurationBDD configBDD = new ConfigurationBDD();
		try {
			double latitudeDepart = configBDD.getLatitudeByCity(villeDepart);
			double longitudeDepart = configBDD.getLongitudeByCity(villeDepart);
			double latitudeArrivee = configBDD.getLatitudeByCity(villeArrivee);
			double longitudeArrivee = configBDD.getLongitudeByCity(villeArrivee);
			System.out.println(latitudeDepart);
			System.out.println(longitudeDepart);
			System.out.println(latitudeArrivee);
			System.out.println(longitudeArrivee);
			
			
			double distance = this.calculerDistance(latitudeDepart, longitudeDepart, latitudeArrivee, longitudeArrivee);
			System.out.println("distance");
			System.out.println(distance);
			//session.setAttribute("distance", distance);
			request.setAttribute("distance", distance);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.doGet(request, response);
		
	}
	
	
	
	private double calculerDistance(double lat1, double lon1, double lat2, double lon2) {
		if ((lat1 == lat2) && (lon1 == lon2)) {
			return 0;
		} else {
			double theta = lon1 - lon2;
			double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
					+ Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515 * 1.609344;
			return (dist);
		}
	}

}
