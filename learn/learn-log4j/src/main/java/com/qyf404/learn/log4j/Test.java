package com.qyf404.learn.log4j;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {
	private static final Logger logger = LoggerFactory.getLogger(Test.class);

	public static void main(String[] args) { // Add a bunch of logging
												// statements ...
		logger.debug("Hello, my name is Homer Simpson.");
		logger.debug("Hello, my name is Lisa Simpson.");
		logger.debug("Hello, my name is Marge Simpson.");
		logger.debug("Hello, my name is Bart Simpson.");
		logger.debug("Hello, my name is Maggie Simpson.");

		logger.info("We are the Simpsons!");
		logger.info("Mmmmmm .... Chocolate.");
		logger.info("Homer likes chocolate");
		logger.info("Doh!");
		logger.info("We are the Simpsons!");

		logger.warn("Bart: I am through with working! Working is for chumps!"
				+ "Homer: Son, I'm proud of you. I was twice your age before "
				+ "I figured that out.");
		logger.warn("Mmm...forbidden donut.");
		logger.warn("D'oh! A deer! A female deer!");
		logger.warn("Truly, yours is a butt that won't quit."
				+ "- Bart, writing as Woodrow to Ms. Krabappel.");

		logger.error("Dear Baby, Welcome to Dumpsville. Population: you.");
		logger.error("Dear Baby, Welcome to Dumpsville. Population: you.",
				new IOException("Dumpsville, USA"));
		logger.error("Mr. Hutz, are you aware you're not wearing pants?");
		logger.error("Mr. Hutz, are you aware you're not wearing pants?",
				new IllegalStateException("Error !!"));

//		logger.fatal("Eep.");
//		logger.fatal("Mmm...forbidden donut.", new SecurityException(
//				"Fatal Exception"));
//		logger.fatal("D'oh! A deer! A female deer!");
//		logger.fatal("Mmmmmm .... Chocolate.", new SecurityException(
//				"Fatal Exception"));
	}
}
