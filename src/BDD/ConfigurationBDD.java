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
		
		/* Chargement du driver JDBC pour MySQL */
		try {
		    Class.forName( driver );
		} catch ( ClassNotFoundException e ) {
			e.printStackTrace();
		}
		
		
			
		    connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
		    return statement = connexion.createStatement();
//		    resultat = statement.executeQuery( "SELECT * FROM ville_france WHERE Code_commune_INSEE = 95014;" );
//		    System.out.println("Résultat");
//		    
//		    while ( resultat.next() ) {
//		        int codeCommune = resultat.getInt( "Code_commune_INSEE" );
//		        String nomCommune = resultat.getString( "Nom_commune" );
//		        String codePostal = resultat.getString( "code_postal" );
//		        String LibelleAcheminement = resultat.getString( "Libelle_acheminement" );
//		        String ligne5 = resultat.getString( "Ligne_5" );
//		        String latitude = resultat.getString( "Latitude" );
//		        String longitude = resultat.getString( "Longitude" );
//		        
//		        System.out.println(codeCommune+" "+nomCommune+" "+codePostal+" "+LibelleAcheminement+" "+ligne5+" "+latitude+" "+longitude);
//
//
//		    }
	
	}


	public double getLatitudeByCity(String city) throws SQLException {
		String cityBDD = city;
		ResultSet resultat = null;
		resultat = connection().executeQuery("SELECT Latitude FROM ville_france WHERE Nom_commune = "+"'"+cityBDD+"'");
		double latitude=0;
		while ( resultat.next() ) {
			latitude = Double.parseDouble(resultat.getString( "Latitude" ));
		}
		return latitude;
	}
	
	public double getLongitudeByCity(String city) throws SQLException {
		String cityBDD = city;
		ResultSet resultat = null;
		resultat = connection().executeQuery("SELECT Longitude FROM ville_france WHERE Nom_commune = "+"'"+cityBDD+"'");
		double latitude=0;
		while ( resultat.next() ) {
			latitude = Double.parseDouble(resultat.getString( "Longitude" ));
		}
		return latitude;

	}

}
