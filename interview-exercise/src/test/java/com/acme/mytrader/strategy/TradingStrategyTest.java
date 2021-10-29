package com.acme.mytrader.strategy;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.acme.mytrader.execution.ExecutionServiceImpl;
import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.PriceSourceImpl;

public class TradingStrategyTest {

	@Test
	public void testBuySuccess() {
		ExecutionServiceImpl tradeExecutionService = Mockito.mock(ExecutionServiceImpl.class);
		PriceSourceImpl priceSource = new MockPriceSource("IBM", 25.00);
		 ArgumentCaptor<String> securityCaptor = ArgumentCaptor.forClass(String.class);
		 ArgumentCaptor<Double> priceCaptor = ArgumentCaptor.forClass(Double.class);
		 ArgumentCaptor<Integer> volumeCaptor = ArgumentCaptor.forClass(Integer.class);
		    
		TradingStrategy tradingStrategy = new TradingStrategy(tradeExecutionService, priceSource);
		ShareDTO input = new ShareDTO("IBM", 50.00, 10);
		tradingStrategy.setBuy(input);
		verify(tradeExecutionService,times(1)).buy(securityCaptor.capture(), priceCaptor.capture(), volumeCaptor.capture());
		
	}

	@Test
	public void testBuyFail() {
		ExecutionServiceImpl tradeExecutionService = Mockito.mock(ExecutionServiceImpl.class);
		PriceSourceImpl priceSource = new MockPriceSource("IBM", 25.00);

		TradingStrategy tradingStrategy = new TradingStrategy(tradeExecutionService, priceSource);
		ShareDTO input = new ShareDTO("IBM", 20.00, 10);
		tradingStrategy.setBuy(input);
		verifyZeroInteractions(tradeExecutionService);
	}

	private class MockPriceSource extends PriceSourceImpl {

		String security;
		double price;

		MockPriceSource(String security, double price) {
			this.security = security;
			this.price = price;
		}

		@Override
		public void addPriceListener(PriceListener listener) {

		}

		@Override
		public void removePriceListener(PriceListener listener) {

		}


	}
}
