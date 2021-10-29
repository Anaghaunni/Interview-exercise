package com.acme.mytrader.price;

import java.util.Random;

public class PriceSourceImpl implements PriceSource{
	

	@Override
	public void addPriceListener(PriceListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePriceListener(PriceListener listener) {
		// TODO Auto-generated method stub
		
	}
	
	public double getPriceofStock(String stockName) {
		Random rand = new Random();
		return rand.nextDouble()*50;//can have a logic here
		
	}

}
