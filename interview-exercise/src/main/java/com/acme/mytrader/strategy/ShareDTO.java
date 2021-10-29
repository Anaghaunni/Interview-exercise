package com.acme.mytrader.strategy;

public class ShareDTO {
	
	  private String security;
	  private double priceThreshold;
	  private int volume;
	  
	public ShareDTO(String security, double priceThreshold, int volume) {
		super();
		this.security = security;
		this.priceThreshold = priceThreshold;
		this.volume = volume;
	}

	public String getSecurity() {
		return security;
	}

	public void setSecurity(String security) {
		this.security = security;
	}

	public double getPriceThreshold() {
		return priceThreshold;
	}

	public void setPriceThreshold(double priceThreshold) {
		this.priceThreshold = priceThreshold;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}
	  
}
