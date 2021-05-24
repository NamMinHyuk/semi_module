package com.sms;

import java.io.UnsupportedEncodingException;

public class smsTest {

	public static void main(String[] args) {
		String phone = "01071826592";
		String content = "message";
		try {
			SMS.sendSMS(phone, content);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
