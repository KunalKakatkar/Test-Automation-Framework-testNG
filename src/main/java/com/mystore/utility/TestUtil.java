package com.mystore.utility;

import java.util.Random;

public class TestUtil {
	
	public static long PAGE_LOAD_TIMEOUT = 40;
	public static long IMPLICITLY_WAIT = 15;
	public static long EXPLICITLY_WAIT = 50;
	private static final String ORDER_SUCCESS_MESSAGE = "Your order on My Shop is complete.";
	private static final String PROFILE_NAME="Kunal Kakatkar";
	private long randomNumber;
	
	public String getSuccessMsg() {
		return ORDER_SUCCESS_MESSAGE;
	}
	
	private static final String INDEX_PAGE_TITLE = "My Shop";
	
	public String getIndexPageTitle() {
		return INDEX_PAGE_TITLE;
	}

	public String getProfileName() {
		return PROFILE_NAME;
	}
	
	public long getRandomNumber() {
		Random random = new Random();
		randomNumber = 1000+random.nextInt(9000);
		return randomNumber;
	}

}
