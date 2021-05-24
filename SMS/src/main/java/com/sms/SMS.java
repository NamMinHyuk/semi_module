package com.sms;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


public class SMS {

	public static void sendSMS(String phonenumber, String content) throws UnsupportedEncodingException {
		String hostNameUrl = "https://sens.apigw.ntruss.com";				// 호스트 URL
		String requestUrl = "/sms/v2/services/";							// 요청 URL
		String requestUrlType = "/messages";								// 요청 URL
		String accessKey = "MgnNQBLZWYDmVIWB9A86";							// 접근키(네이버 클라우드 플랫폼에서 받아옴)			
		String secretKey = "bukxizrgBdgLY4Gs6qIynKtLnqOeLjfCrNoS9Lh5";		// 비밀키(네이버 클라우드 플랫폼에서 받아옴)
		String serviceId = "ncp:sms:kr:266284043003:semiproject";			// 서비스id(네이버 클라우드 플랫폼에서 받아옴)
		String timestamp = Long.toString(System.currentTimeMillis());
		requestUrl += serviceId + requestUrlType;
		String apiUrl = hostNameUrl + requestUrl;
		
		JsonObject bodyJson = new JsonObject();
		JsonObject toJson = new JsonObject();
		JsonArray Jsarr = new JsonArray();
			    
		// json 형식으로 메시지 전송 요청
		
		toJson.addProperty("subject", "test");			// 메시지 제목 (적용안됨/lms만 적용)
		toJson.addProperty("content", content);  		// 메시지 내용	(실제내용)
		toJson.addProperty("to", phonenumber);			// 수신번호
		Jsarr.add(toJson);	
		
		bodyJson.addProperty("type", "SMS");			// 메시지 type sms/lms
		bodyJson.addProperty("countryCode", "82");		// 국가 전화번호
		bodyJson.addProperty("contentType", "COMM");	// 메시지 내용 type ad/comm
		bodyJson.addProperty("from", "01064244977");	// 발신번호 (사전에 인증/등록된 번호만 사용가능)
		bodyJson.addProperty("subject", "test");		// 메시지 제목(적용안됨/lms만 적용)
		bodyJson.addProperty("content", "test2");		// 메시지 내용
		bodyJson.add("messages", Jsarr);
		
		String body = bodyJson.toString();
		
		System.out.println(body);
		System.out.println(requestUrl);
		System.out.println(apiUrl);
		
		try {	
			URL url = new URL(apiUrl);
			HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
			
			urlConnection.setRequestMethod("POST");	

			urlConnection.setRequestProperty("Content-Type", "application/json");
			urlConnection.setRequestProperty("x-ncp-apigw-timestamp", timestamp);
			urlConnection.setRequestProperty("x-ncp-iam-access-key", accessKey);
			urlConnection.setRequestProperty("x-ncp-apigw-signature-v2", makeSignature(requestUrl, timestamp, accessKey, secretKey));
			urlConnection.setDoInput(true);
			urlConnection.setUseCaches(false);
			urlConnection.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
			
			wr.write(body.getBytes());
			wr.flush();
			wr.close();
			
			int responseCode = urlConnection.getResponseCode();
			BufferedReader br;
			System.out.println("responseCode" + " " + responseCode);
			System.out.println("responsemessage : " + urlConnection.getResponseMessage());
			if(responseCode==202) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));	
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			}
			
			String inputLine;
			StringBuffer response = new StringBuffer();
			while((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			
			System.out.println(response.toString());
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	// sens api 전용 시그니처 키 생성
	public static String makeSignature(String url, String timestamp, String accessKey, String secretKey) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
		String space = " ";					
		String newLine = "\n";				
		String method = "POST";
		
		String message = new StringBuilder()
			.append(method)
			.append(space)
			.append(url)
			.append(newLine)
			.append(timestamp)
			.append(newLine)
			.append(accessKey)
			.toString();

		SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256"); 
		Mac mac = Mac.getInstance("HmacSHA256"); 
		mac.init(signingKey); 
		
		byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8")); 
		String encodeBase64String = Base64.encodeBase64String(rawHmac);
		
	  return encodeBase64String;
	}
	
}
