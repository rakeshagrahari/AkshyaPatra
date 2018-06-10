package com.aws.codestar.projecttemplates;
import java.util.LinkedHashMap;
import java.util.Map;

public class DatabaseConnection {

	public static Map<Integer, NGODTO> ngoDetails = new LinkedHashMap<>();	

	/**
	 * getMatchingNGO: This method matches fetchNGODetails
	 */
	public static Map<Integer, NGODTO> fetchNGODetails() {
		NGODTO foodOfLife = new NGODTO();
		NGODTO slavation = new NGODTO();
		foodOfLife.setNgoName("FoodForLife");
		foodOfLife.setNgoAddress("1187LE");
		foodOfLife.setMobileNumber(622373777);
		foodOfLife.setPreferences(5);
		slavation.setNgoName("Salvation Army");
		slavation.setNgoAddress("1184VE");
		slavation.setMobileNumber(686153848);
		slavation.setPreferences(10);
		ngoDetails.put(foodOfLife.getMobileNumber(), foodOfLife);
		ngoDetails.put(slavation.getMobileNumber(), slavation);
		return ngoDetails;
	}
	}
