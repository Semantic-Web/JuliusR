import java.text.DecimalFormat; 

import javax.swing.JOptionPane; 

import org.geonames.PostalCodeSearchCriteria; 
import org.geonames.InvalidParameterException; 
import org.geonames.PostalCode; 
import org.geonames.Timezone;
import org.geonames.WeatherObservation; 
import org.geonames.WebService; 
 
 

public class TZone 
{public static void main(String[] args)  
	 	{ 
		 
			// Request username	 
	 		String user = JOptionPane.showInputDialog("Enter username"); 
		 
	 		// Populate username based on input	 
	 		WebService.setUserName(user);  
		 		 
	 		PostalCodeSearchCriteria searchCriteria = new PostalCodeSearchCriteria(); 
		 
		// Request postalcode and set postalcode as searchCriteria 
	 		String postalCode = JOptionPane.showInputDialog("Enter your zip code"); 
			searchCriteria.setPostalCode(postalCode);  
			 
			try { 
	 			// Set country code as searchCrtieria 
				String countryCode = "US"; 
				searchCriteria.setCountryCode(countryCode); 
	 		} catch (InvalidParameterException e1) { 
	 			// TODO Auto-generated catch block 
	 			e1.printStackTrace(); 
	 		} 
	 		 
	 		try { 
	 			// Use search criteria to obtain nearest latitude and longitude of postal code 
	 			PostalCode searchResult = WebService.findNearbyPostalCodes(searchCriteria).get(0); 
	 			double latitude = searchResult.getLatitude();  
	 			double longitude = searchResult.getLongitude(); 
	 			 
	 			// Use longitude and latitude coordinates to search for location
	 			WeatherObservation location = new WeatherObservation();  
	 			location = WebService.findNearByWeather(latitude, longitude); 
	 			Timezone timez = new Timezone();
	 			timez=  WebService.timezone(latitude, longitude);
	 			
	 			// Set default decimal format  
	 			DecimalFormat df = new DecimalFormat("##.###"); 
	 			 
	 			// Print out time zone information  
	 			System.out.println("****** Time zone Information ******"); 
	 			System.out.println("Time: "+timez.getTime()+""); 
	 			System.out.println("Time zone ID: "+timez.getTimezoneId()+""); 
	 			System.out.println("Country Code: "+timez.getCountryCode()+"");  
	 			System.out.println("Latitude: "+df.format(location.getLatitude())); 
	 			System.out.println("Longitude: "+df.format(location.getLatitude())); 
	 		 
	 			
	 		} catch (Exception e) { 
	 			// TODO Auto-generated catch block 
	 			e.printStackTrace(); 
	 		 
	 		}//end try	 
	 	}//end main 
	 }//end class 



