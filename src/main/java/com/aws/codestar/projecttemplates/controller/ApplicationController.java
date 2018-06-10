package com.aws.codestar.projecttemplates.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.aws.codestar.projecttemplates.MatchingNGO;
import com.aws.codestar.projecttemplates.NGODTO;
import com.aws.codestar.projecttemplates.SendSMS;

/**
 * Basic Spring web service controller that handles all GET requests.
 */
@RestController
@RequestMapping("/")
public class ApplicationController {


	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<NGODTO> helloWorldGet(@RequestParam(value = "source") String source) {

		NGODTO ngoDTO = new NGODTO();
		try {
			ngoDTO = MatchingNGO.getMatchingNGO(source);
		} catch (Exception e) {
			e.getMessage();
		}
		return ResponseEntity.ok(ngoDTO);
	}
	
	@RequestMapping("/sendMsg")
	public ResponseEntity<String> sendSms(@RequestParam(value = "mobileNumber") String mobileNumber) {

			AmazonSNS snsClient =  AmazonSNSClientBuilder.standard().withRegion(Regions.EU_WEST_1).build();
			 String message = "Thank you. Salvation Army accepted your request";
		    
		        Map<String, MessageAttributeValue> smsAttributes =
		                new HashMap<String, MessageAttributeValue>();
		        smsAttributes.put("AWS.SNS.SMS.SenderID", new MessageAttributeValue()
		                .withStringValue("Akshya Patra") 
		                .withDataType("String"));
		        smsAttributes.put("AWS.SNS.SMS.MaxPrice", new MessageAttributeValue()
		                .withStringValue("0.50") 
		                .withDataType("Number"));
		        smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue()
		                .withStringValue("Promotional") 
		                .withDataType("String"));
		        
		        SendSMS.sendSMSMessage(snsClient, message, mobileNumber, smsAttributes);
		
		return ResponseEntity.ok("sent");
	}
}