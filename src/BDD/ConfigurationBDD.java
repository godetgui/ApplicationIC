package BDD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConfigurationBDD {
	
	/* Connexion à la base de données */
	String url = "jdbc:mysql://localhost/VillesFrance2?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	String driver = "com.mysql.cj.jdbc.Driver";
	String utilisateur = "user";
	String motDePasse = "user";
	Connection connexion = null;
    Statement statement = null;
    ResultSet resultat = null;
    
public Statement connection() throws SQLException {
	
		try {
		    Class.forName( driver );
		} catch ( ClassNotFoundException e ) {
			e.printStackTrace();
		}
		
		
			
		    connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
		    return statement = connexion.createStatement();

	
	}


	public double getLatitudeByCity(String city) throws SQLException {
		ResultSet resultat = null;
		resultat = connection().executeQuery("SELECT Latitude FROM ville_france WHERE Nom_commune = "+"'"+city+"'");
		double latitude=0;
		while ( resultat.next() ) {
			latitude = Double.parseDouble(resultat.getString( "Latitude" ));
		}
		return latitude;
	}
	
	public double getLongitudeByCity(String city) throws SQLException {
		ResultSet resultat = null;
		resultat = connection().executeQuery("SELECT Longitude FROM ville_france WHERE Nom_commune = "+"'"+city+"'");
		double latitude=0;
		while ( resultat.next() ) {
			latitude = Double.parseDouble(resultat.getString( "Longitude" ));
		}
		return latitude;

	}

}
