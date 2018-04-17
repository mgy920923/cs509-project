package trip;

import flight.Flight;
import static java.lang.Math.toIntExact;

import java.util.Date;

import flight.Flights;
import preferences.RetailCustomerPreferences;
import seat.SeatClass;

public class Trip {
	TripType type;
	Trip followingTrip;
	public Flights flights;
	
	public Trip(){
		flights = new Flights();
	}
	
	public void addFlight(Flight flight){
		flights.add(flight);
	}
	
	public Flights getFlights(){
		return this.flights;
	}
	
	public Flight getFlight(int index){
		return flights.get(index);
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		
		
		sb.append("List of flights of this trip:\n");
		
		for(Flight flight : flights){
			sb.append(flight.toString());
		}
		
		
		return sb.toString();
	}
	
	public int getDuration(){
		return toIntExact((getArrivalTime() - getDepartureTime())  / (1000 * 60));
	}
	
	public double getPrice(SeatClass seatClass){
		double price = 0;
		for (Flight flight : flights){
			price += flight.getPrice(seatClass);
		}
		return price;
	}
	
	public long getDepartureTime(){
		return flights.get(0).getDepartureTimeGMT().getTime().getTime();
	}
	
	public long getArrivalTime(){
		return flights.get(flights.size()-1).getArrivalTimeGMT().getTime().getTime();
	}
	
	public String getDepartureAirportCode(){
		return this.getFlights().get(0).getDepartureAirport().code();
	}
	
	public String getArrivalAirportCode(){
		return this.getFlights().get(this.getFlights().size()-1).getArrivalAirport().code();
	}
	
	public int getAvailableSeats(SeatClass tripClass){
		int availableSeats = -1;
		
		switch(tripClass){
			case COACH:
				availableSeats = this.flights.get(0).getCoachSeating().getNumberOfSeats();
				for (Flight flight : flights){
					availableSeats = Math.min(availableSeats, flight.getCoachSeating().getNumberOfSeats());
				}
				break;
			case FIRSTCLASS:
				availableSeats = this.flights.get(0).getFirstClassSeating().getNumberOfSeats();
				for (Flight flight : flights){
					availableSeats = Math.min(availableSeats, flight.getFirstClassSeating().getNumberOfSeats());
				}
				break;
		}
		
		return availableSeats;
	}
}
