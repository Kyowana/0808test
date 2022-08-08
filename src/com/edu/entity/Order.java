package com.edu.entity;

public class Order {
	
	String userName;
	
	TrainInfo train;
	
	
	public Order(String name, TrainInfo train) {
		this.userName = name;
		this.train = train;
	}
	
	public void printInfo(String userName) {
		System.out.println(train.trip + "\t價格: " + train.price + "\t購買數量: " + train.tickets);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public TrainInfo getTrain() {
		return train;
	}

	public void setTrain(TrainInfo train) {
		this.train = train;
	}
	
	

}
