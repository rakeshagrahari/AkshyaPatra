package com.aws.codestar.projecttemplates;
import java.util.Map;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
/**
 * 
 * @author C35427
 *
 */
public class SendSMS {

/**
 * 
 * @param snsClient
 * @param message
 * @param phoneNumber
 * @param smsAttributes
 */
	public static void sendSMSMessage(AmazonSNS snsClient, String message, 
			String phoneNumber, Map<String, MessageAttributeValue> smsAttributes) {

		snsClient.publish(new PublishRequest()
				.withMessage(message)
				.withPhoneNumber("+31622373777")
				.withMessageAttributes(smsAttributes));
	}

}
