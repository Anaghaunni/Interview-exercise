package com.acme.mytrader.strategy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.acme.mytrader.execution.ExecutionServiceImpl;
import com.acme.mytrader.price.PriceListenerImpl;

public class PriceListenerImplTest {

	@Test
	  public void testInitializeStateForBuyPriceListener() {
	    ExecutionServiceImpl executionService = Mockito.mock(ExecutionServiceImpl.class);

	    PriceListenerImpl priceListener = new PriceListenerImpl("IBM", 50.00, 100, executionService,
	        false);

	    assertThat(priceListener.getSecurity()).isEqualTo("IBM");
	    assertThat(priceListener.getTriggerLevel()).isEqualTo(50.00);
	    assertThat(priceListener.getPurchaseQuantity()).isEqualTo(100);
	    assertThat(priceListener.isTradeSuccess()).isFalse();
	  }
	
	@Test
	  public void testThresholdIsMet() {
	    ExecutionServiceImpl executionService = Mockito.mock(ExecutionServiceImpl.class);
	    ArgumentCaptor<String> acString = ArgumentCaptor.forClass(String.class);
	    ArgumentCaptor<Double> acDouble = ArgumentCaptor.forClass(Double.class);
	    ArgumentCaptor<Integer> acInteger = ArgumentCaptor.forClass(Integer.class);

	    PriceListenerImpl priceListener = new PriceListenerImpl("IBM", 50.00, 100, executionService,
	        false);
	    priceListener.priceUpdate("IBM", 25.00);

	    verify(executionService, times(1))
	        .buy(acString.capture(), acDouble.capture(), acInteger.capture());
	    assertThat(acString.getValue()).isEqualTo("IBM");
	    assertThat(acDouble.getValue()).isEqualTo(25.00);
	    assertThat(acInteger.getValue()).isEqualTo(100);
	    assertThat(priceListener.isTradeSuccess()).isTrue();
	  }
	
	@Test
	  public void testThresholdIsNotMet() {
	    ExecutionServiceImpl executionService = Mockito.mock(ExecutionServiceImpl.class);
	    ArgumentCaptor<String> acString = ArgumentCaptor.forClass(String.class);
	    ArgumentCaptor<Double> acDouble = ArgumentCaptor.forClass(Double.class);
	    ArgumentCaptor<Integer> acInteger = ArgumentCaptor.forClass(Integer.class);

	    PriceListenerImpl priceListener = new PriceListenerImpl("IBM", 50.00, 100, executionService,
	        false);
	    priceListener.priceUpdate("IBM", 55.00);

	    verify(executionService, times(0))
	        .buy(acString.capture(), acDouble.capture(), acInteger.capture());
	    assertThat(priceListener.isTradeSuccess()).isFalse();
	  }
	
	@Test
	  public void testwhenSecurityIsDifferent() {
	    ExecutionServiceImpl executionService = Mockito.mock(ExecutionServiceImpl.class);
	    ArgumentCaptor<String> acString = ArgumentCaptor.forClass(String.class);
	    ArgumentCaptor<Double> acDouble = ArgumentCaptor.forClass(Double.class);
	    ArgumentCaptor<Integer> acInteger = ArgumentCaptor.forClass(Integer.class);

	    PriceListenerImpl priceListener = new PriceListenerImpl("APPL", 50.00, 100, executionService,
	        false);
	    priceListener.priceUpdate("IBM", 55.00);

	    verify(executionService, times(0))
	        .buy(acString.capture(), acDouble.capture(), acInteger.capture());
	    assertThat(priceListener.isTradeSuccess()).isFalse();
	  }
	
}
