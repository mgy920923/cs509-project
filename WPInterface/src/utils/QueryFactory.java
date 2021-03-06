/**
 * 
 */
package utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author blake
 * @version 1.2
 *
 */
public class QueryFactory {
	
	/**
	 * Return a query string that can be passed to HTTP URL to request list of flights
	 * 
	 * @param airportCode code of the airport
	 * @param departureDate date of departure
	 * @param teamName is the name of the team to specify the data copy on server
	 * @return the query String which can be appended to URL to form HTTP GET request
	 */
	public static String getFlightsFromDeparture(String airportCode, Calendar departureDate, String teamName) {
		String month = Integer.toString(departureDate.get(Calendar.MONTH)+ 1);
		if(month.length()<2) month = "0"+month;
		String url = "?team=" + teamName + "&action=list"
				+ "&list_type=departing"
				+ "&airport="+airportCode+""
				+ "&day="+departureDate.get(1)+"_"+month+"_"+departureDate.get(5);
		
		return url;
	}
	
	public static String getFlightsFromArrival(String airportCode, Calendar arrivalDate, String teamName) {
		String month = Integer.toString(arrivalDate.get(Calendar.MONTH)+ 1);
		if(month.length() < 2) month = "0" + month;
		String url = "?team=" + teamName + "&action=list" +
				"&list_type=arriving" + "&airport=" + airportCode + 
				"&day=" + arrivalDate.get(1) + "_" + month + "_" + arrivalDate.get(5);
		
		return url;
	}
	
	/**
	 * Return a query string that can be passed to HTTP URL to request list of airports
	 * 
	 * @param teamName is the name of the team to specify the data copy on server
	 * @return the query String which can be appended to URL to form HTTP GET request
	 */
	public static String getAirports(String teamName) {
		return "?team=" + teamName + "&action=list&list_type=airports";
	}
	
	/**
	 * Return a query string that can be passed to HTTP URL to request list of airplanes
	 * 
	 * @param teamName is the name of the team to specify the data copy on server
	 * @return the query String which can be appended to URL to form HTTP GET request
	 */
	public static String getAirplanes(String teamName) {
		return "?team=" + teamName + "&action=list&list_type=airplanes";
	}
	
	/**
	 * Lock the server database so updates can be written
	 * 
	 * @param teamName is the name of the team to acquire the lock
	 * @return the String written to HTTP POST to lock server database 
	 */
	public static String lock (String teamName) {
		return "team=" + teamName + "&action=lockDB";
	}
	
	/**
	 * update info to reserve seat(s)
	 * 
	 * @param teamName is the name of the team holding the lock, xmlFlights is the sending message
	 * @return the String written to the HTTP POST to reserve seat(s)
	 */
	public static String reserve (String teamName, String xmlFlights) {
		return "team=" + teamName + "&action=buyTickets" + "&&flightData=" + xmlFlights;
	}
	
	/**
	 * Unlock the server database after updates are written
	 * 
	 * @param teamName is the name of the team holding the lock
	 * @return the String written to the HTTP POST to unlock server database
	 */
	public static String unlock (String teamName) {
		return "team=" + teamName + "&action=unlockDB";
	}
	

}
