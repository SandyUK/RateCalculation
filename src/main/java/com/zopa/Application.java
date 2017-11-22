package com.zopa;

import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * The entry point of the Rate-Calculation-System.
 * 
 * Assumption:
 * <ul>
 * <li>The system does not bundle multiple lenders' offers together to match
 * requested loan. In other words, the loan is provided by only one offer.</li>
 * <li>If a lender's offer is more than the requested amount of the loan, the
 * system will give the requested amount rather than the whole offer.</li>
 * <ul>
 * 
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) throws Exception {
		new SpringApplicationBuilder(Application.class).bannerMode(Banner.Mode.OFF).run(args);

	}

	@Override
	public void run(String... args) throws Exception {

		// access command line arguments
	}
}