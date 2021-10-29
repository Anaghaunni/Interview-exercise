package com.acme.mytrader.strategy;

import com.acme.mytrader.execution.ExecutionServiceImpl;
import com.acme.mytrader.price.PriceListenerImpl;
import com.acme.mytrader.price.PriceSourceImpl;


/**
 * <pre>
 * User Story: As a trader I want to be able to monitor stock prices such
 * that when they breach a trigger level orders can be executed automatically
 * </pre>
 */
public class TradingStrategy {
	private ExecutionServiceImpl tradeExecutionService;
	private PriceSourceImpl priceSource;
	
	
	public TradingStrategy(ExecutionServiceImpl tradeExecutionService, PriceSourceImpl priceSource) {
		super();
		this.tradeExecutionService = tradeExecutionService;
		this.priceSource = priceSource;
	}
	
	public void setBuy(ShareDTO share) {
			PriceListenerImpl priceListener = new PriceListenerImpl(share.getSecurity(), share.getPriceThreshold(), share.getVolume(), tradeExecutionService,false);
			priceSource.addPriceListener(priceListener);
			double price = priceSource.getPriceofStock(share.getSecurity());
			if(priceSource.getPriceofStock(share.getSecurity()) > 0) {
				priceListener.priceUpdate(share.getSecurity(), price);
			}
	}
	
	public static void main(String[] args) {
		TradingStrategy tradingStrategy = new TradingStrategy(new ExecutionServiceImpl(), new PriceSourceImpl()); 
		ShareDTO ibm = new ShareDTO("IBM", 100.00, 12);
		tradingStrategy.setBuy(ibm);
	}
	
	public ExecutionServiceImpl getTradeExecutionService() {
		return tradeExecutionService;
	}
	public void setTradeExecutionService(ExecutionServiceImpl tradeExecutionService) {
		this.tradeExecutionService = tradeExecutionService;
	}
	public PriceSourceImpl getPriceSource() {
		return priceSource;
	}
	public void setPriceSource(PriceSourceImpl priceSource) {
		this.priceSource = priceSource;
	}
	
}
