package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	private static final Logger LOGGER = Logger.getLogger( ChercherDistance.class.getName() );
       
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
		System.out.println(request.getAttribute("distance"));
		request.getRequestDispatcher(VUE_FORM).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String villeDepart = request.getParameter("villeDepart");
		String villeArrivee = request.getParameter("villeArrivee");
		
		ConfigurationBDD configBDD = new ConfigurationBDD();
		try {
			double latitudeDepart = configBDD.getLatitudeByCity(villeDepart);
			double longitudeDepart = configBDD.getLongitudeByCity(villeDepart);
			double latitudeArrivee = configBDD.getLatitudeByCity(villeArrivee);
			double longitudeArrivee = configBDD.getLongitudeByCity(villeArrivee);
			
			
			double distance = this.calculerDistance(latitudeDepart, longitudeDepart, latitudeArrivee, longitudeArrivee);
			request.setAttribute("distance", distance);
		} catch (SQLException e) {
			LOGGER.log(Level.FINE, "bug: "+e);
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
