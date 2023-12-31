package com.generation.javago.model.entity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.generation.javago.model.library.BaseEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RoomBooking extends BaseEntity
{
	private LocalDate checkin_date,checkout_date;
	private Integer n_guest;
	private boolean saved;
	
	@Getter(AccessLevel.NONE)
    private Double price;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "customer_id")
	Customer customer;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "room_id")
	Room room;
	
	@ManyToMany()
	@JoinTable
	(
		name = "roombooking_to_season", 
		joinColumns = @JoinColumn(name = "id_roombooking"), 
		inverseJoinColumns = @JoinColumn(name = "id_season")
	)
	private List<Season> seasons;

	

	@Override
	public List<String> getErrors() {
		List<String> errors = new ArrayList<String>();
		
		if(checkin_date==null || checkin_date.isAfter(checkout_date))
			errors.add("Missing or invalid value for field 'checkin_date'");
		if(checkout_date==null || checkout_date.isBefore(checkin_date))
			errors.add("Missing or invalid value for field 'checkout_date'");
		
		return errors;
	}
	
	/*
	 * This method let us calculate the total price of the booking
	 * day by day, considering the various discounts/price increase, depending on 
	 * the seasons of the days of the booking.
	 */
	public Double getPrice() 
	{	
		Double res = 0.0;
		
		for(int i = 0;i<=getDaysOfStay();i++ )
		{
			res += calculateDayCost(i);
		}
		return res;
	}
	
	public void setPrice()
	{
		this.price=this.getPrice(); 
	}
	
	/*
	 * This method calculates the cost per day of the staying.
	 */
	private Double calculateDayCost(int index) 
	{
		LocalDate currentDay = checkin_date.plusDays(index);
		
		Double res = room.getBasePrice();
		
		for(Season season : seasons)
		{
			if	(
					(currentDay.isAfter(season.getStarting_date()) || currentDay.isEqual(season.getStarting_date()))
							&& 
					(currentDay.isBefore(season.getEnd_date()) || currentDay.isEqual(season.getEnd_date()))
				)
			{
				res += room.getBasePrice()*(season.getPrice_modifer()/100.0);
			}
		}
		
		return res;
		
	}

	/*
	 * This method calculates the number of days between two dates
	 */
	public Integer getDaysOfStay()
	{
		if(this.isValid())
		{
			return (int)ChronoUnit.DAYS.between(checkin_date, checkout_date);
		}
		return null;
	}
	
	
	/*
	 * This method fills an array whith the date of every day of the stay
	 */
	public LocalDate[] daysOfStayWithDate() 
	{
		LocalDate[] datesOfStay = new LocalDate[getDaysOfStay()];
		
		for(int i=0; i<datesOfStay.length;i++)
		{
			datesOfStay[i] =  checkin_date.plusDays(i);
		}
		
		return datesOfStay;
	}
	
	/*
	 * This method sets the seasons in which the booking is present
	 */
	public void setBookingSeasons(List<Season> allSeasons)
	{
		LocalDate[] days =  daysOfStayWithDate(); 
		
		Set<Season> bookingSeasons = new HashSet<Season>();
		
		for(Season season : allSeasons)
		{
			for(LocalDate day : days)
			{
				if	(
						day.isEqual(season.getStarting_date()) 												|| 
						(
							day.isAfter(season.getStarting_date()) && day.isBefore(season.getEnd_date())
						) 																					||
						day.isEqual(season.getEnd_date())
					)
				{
					bookingSeasons.add(season);
				}
				
			}
		}
		
		this.setSeasons(new ArrayList<Season>(bookingSeasons));
	}
	
	
	
	public List<LocalDate> getDaysOfBookings(){
		List<LocalDate> datesOfStay = new 	ArrayList<LocalDate>();
		
		for(int i=0; i<=getDaysOfStay();i++)
		{
			datesOfStay.add(checkin_date.plusDays(i)); 
		}
		
		return datesOfStay;
	}
	
	public boolean isBooked(List<LocalDate> lista) {
	    for (LocalDate data : getDaysOfBookings()) {
	        for (LocalDate checkDate : lista) {
	            if (data.isEqual(checkDate)) {
	                return true; // Trovata una data in comune
	            }
	        }
	    }
	    return false;
	}
}
