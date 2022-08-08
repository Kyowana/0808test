package com.edu.entity;


public class TrainInfo {
	

	String trip;
	
	int price;
	
	int tickets;

	public TrainInfo() {
		
	}
	
	public TrainInfo(String trip, int price, int tickets) {
		this.trip = trip;
		this.price = price;
		this.tickets = tickets;
	}
	
	public void printInfo(TrainInfo info) {
		System.out.println(info.trip + "\t" + info.price + "\t" + info.tickets);
	}

	public String getTrip() {
		return trip;
	}

	public void setTrip(String trip) {
		this.trip = trip;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getTickets() {
		return tickets;
	}

	public void setTickets(int tickets) {
		this.tickets = tickets;
	}
	
	
}
