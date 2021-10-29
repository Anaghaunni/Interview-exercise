package com.acme.mytrader.price;

import com.acme.mytrader.execution.ExecutionService;

public class PriceListenerImpl implements PriceListener{

	  private String security;
	  private double triggerLevel;
	  private int purchaseQuantity;
	  private ExecutionService executionService;
	  private boolean tradeSuccess;
	  

	public PriceListenerImpl(String security, double triggerLevel, int purchaseQuantity,
			ExecutionService executionService, boolean tradeSuccess) {
		super();
		this.security = security;
		this.triggerLevel = triggerLevel;
		this.purchaseQuantity = purchaseQuantity;
		this.executionService = executionService;
		this.tradeSuccess = tradeSuccess;
	}

	@Override
	public void priceUpdate(String security, double price) {
		if (this.security.equals(security) && (price < this.triggerLevel)) {
		      executionService.buy(security, price, purchaseQuantity);
		      tradeSuccess = true;
		    }
	}
	

	public String getSecurity() {
		return security;
	}


	public void setSecurity(String security) {
		this.security = security;
	}


	public double getTriggerLevel() {
		return triggerLevel;
	}


	public void setTriggerLevel(double triggerLevel) {
		this.triggerLevel = triggerLevel;
	}


	public int getPurchaseQuantity() {
		return purchaseQuantity;
	}


	public void setPurchaseQuantity(int purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}


	public ExecutionService getExecutionService() {
		return executionService;
	}


	public void setExecutionService(ExecutionService executionService) {
		this.executionService = executionService;
	}

	public boolean isTradeSuccess() {
		return tradeSuccess;
	}

	public void setTradeSuccess(boolean tradeSuccess) {
		this.tradeSuccess = tradeSuccess;
	}
}
