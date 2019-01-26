package br.com.holtz.microservice.currencyexchangeservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.holtz.microservice.currencyexchangeservice.model.ExchangeValue;
import br.com.holtz.microservice.currencyexchangeservice.repository.ExchangeValueRepository;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private ExchangeValueRepository eValueRep;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		
		ExchangeValue exchangeValue = eValueRep.findByFromAndTo(from, to);
		
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		
		return exchangeValue;
		
	}
	
}
