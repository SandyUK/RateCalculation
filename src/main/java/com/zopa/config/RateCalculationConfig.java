package com.zopa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.zopa.service.OfferService;

/**
 * The spring configuration for the Rate-Calculation-System.
 */
@Configuration
@ComponentScan(basePackages = "com.zopa.service")
public class RateCalculationConfig {

	@Autowired
	public OfferService rateCalculationService;

}
