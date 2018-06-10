package com.aws.codestar.projecttemplates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.aws.codestar.projecttemplates.controller.ApplicationConstants;

/**
 * Basic Spring web service MatchingNGO    .
 */
public class MatchingNGO {
	static Map<Float, NGODTO> matchedNGO = new TreeMap<Float, NGODTO>();
	static NGODTO nGODTO = null;
	static MatchingNGO matchedNGOObj = null;
	static Logger log = Logger.getLogger(MatchingNGO.class.getName());
	static Float drivingDistance = 0.0f;
	static StringBuilder urlPath ;
	static URL url = null;
	static HttpURLConnection conn = null;
	

	private MatchingNGO() {
	}

	
	/**
	 * getMatchingNGO: This method matches the donar in the database
	 * @param donorAddress
	 * @return
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public static  NGODTO getMatchingNGO(String donorAddress) throws ParseException, IOException
	{
		
		Map<Integer,NGODTO> ngoMap=DatabaseConnection.fetchNGODetails();
		for (Map.Entry<Integer, NGODTO> ngoEntrySet : ngoMap.entrySet())
		{   urlPath= new StringBuilder();
		   urlPath.append(ApplicationConstants.GOOGLE_API_BASE_URL);
		   urlPath.append(donorAddress);
		   urlPath.append(ApplicationConstants.DESTINATION);
			nGODTO=ngoEntrySet.getValue();	
			generateResponse(donorAddress,nGODTO );
		}
		return  (NGODTO) matchedNGO.values().toArray()[0];
	}



/**
 * 
 * @param outputString
 * @throws ParseException
 */
	private static void jsonParsor(StringBuilder outputString) throws ParseException {
		JSONParser parser=new JSONParser();
		JSONObject jsonObj=(JSONObject) parser.parse(outputString.toString());
		if(jsonObj!=null && !jsonObj.isEmpty())
		{
		JSONArray jsonRowArray=(JSONArray) jsonObj.get("rows");
		JSONObject jsonElementObj=(JSONObject) jsonRowArray.get(0);
		JSONArray jsonDurationArray=(JSONArray) jsonElementObj.get("elements");
	    JSONObject jsonDurationObj=(JSONObject) jsonDurationArray.get(0);
		JSONObject jsonDistanceObj=(JSONObject) jsonDurationObj.get("distance");
		if(jsonDistanceObj==null)
		{
			System.exit(0);
		}
		else
		{
		String distance=(String) jsonDistanceObj.get("text");
		Float fromToDistance=getDistance(distance);
		if(fromToDistance<=nGODTO.getPreferences()) {
			matchedNGO.put(fromToDistance, nGODTO);
		}
		}
		}
		
	}
	

	/**
	 * 
	 * @param distance
	 * @return
	 */
	public static Float getDistance(String distance) {

		if (distance.contains("km")) {
			String s = distance.replace("km", "").trim();
			drivingDistance = Float.parseFloat(s);
		} else
		{
			drivingDistance = Float.parseFloat(distance.replace("m", "".trim()));
			drivingDistance=drivingDistance/1000;
		}
		return drivingDistance;
	}

	/**
	 * 
	 * @param address
	 * @return
	 */
	public static String validteAddress(String address) {
		if (address.contains(" ")) {
			address = address.replace(" ", "%20");
		}
		return address;
	}
	/**
	 * 
	 * @param donorAddress
	 * @param ngoDetails
	 * @throws IOException
	 * @throws ParseException
	 */
	private static void generateResponse(String donorAddress, NGODTO ngoDetails) throws IOException, ParseException
	{
		donorAddress=validteAddress(donorAddress);
		String ngoAddress=validteAddress(ngoDetails.getNgoAddress());
		try { 
			url = new URL(urlPath+ngoAddress+ApplicationConstants.MODE);
			conn = (HttpURLConnection) url.openConnection();
			
		
	} catch (IOException  e) {
		   e.getMessage();
	}
	if(conn!=null)
	{
	conn.setRequestMethod("GET");
	String line="";
	StringBuilder outputString = new StringBuilder();
	BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	while ((line = reader.readLine()) != null) {
		outputString.append(line);
	}
	jsonParsor(outputString);}
}
	
}
