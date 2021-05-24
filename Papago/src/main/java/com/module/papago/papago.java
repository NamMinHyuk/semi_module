package com.module.papago;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class papago {
	
	// s: 번역할 텍스트 , source: 원본 언어의 언어 코드 , target: 목적 언어의 언어 코드 
	public static String getTransSentence(String s, String source, String target) {
			
		String clientId = "WSnlxos_U7cQkENAyEDY";  // 애플리케이션을 등록하고 발급받은 클라이언트 아이디 값
		String clientSecret = "0PEMI7tXBW";   // 애플리케이션을 등록하고 발급받은 클라이언트 시크릿 값
		
		String transResult = null;   // 번역 결과
		
		try {
			   String text = URLEncoder.encode(s, "UTF-8");  // 웹으로 데이터 전송을 위해 인코딩
	           String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
	           URL url = new URL(apiURL);  
	           HttpURLConnection con = (HttpURLConnection)url.openConnection();  //  URL 주소의 원격 객체에 접속한 뒤 통신할 수 있는 URLConnnection 객체 리턴
	           con.setRequestMethod("POST"); // 전송방식
	           con.setRequestProperty("X-Naver-Client-Id", clientId);
	           con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
	           
	           // post request
	           String postParams = transmenu(source, target) + text;  // 번역할 텍스트의 언어와 번역될 텍스트의 언어
	           
	           con.setDoOutput(true);  // URL 연결을 출력용으로 사용할 경우 true, 그렇지 않을 경우 false (기본값은 false), 항상 갱신된 내용을 가져옴
	           DataOutputStream wr = new DataOutputStream(con.getOutputStream()); // 주어진 outputstream과 연결된 객체를 생성한다.
	           wr.writeBytes(postParams);  // 해당되는 자료형을 출력한다.
	           wr.flush();  // 현재 버퍼에 저장되어 있는 내용을 클라이언트로 전송하고 버퍼를 비운다.
	           wr.close();
	           int responseCode = con.getResponseCode();
	           BufferedReader br;
	           if(responseCode==200) { // 정상 호출
	               br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	           } else {  // 에러 발생
	               br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	           }
	           String inputLine;
	           StringBuffer response = new StringBuffer();
	           while ((inputLine = br.readLine()) != null) {
	               response.append(inputLine);
	           }
	           br.close(); 
	           
	           // 번역받은 내용만 골라내기 위한 코드!
	           int start = response.indexOf("translatedText") + 17;
	           int end = response.indexOf("engineType") - 3;
	           transResult = response.substring(start, end);
		}catch (Exception e) {
			System.out.println(e);
		}
		
		return transResult;
		
	}
	
	public static String transmenu(String source, String target) {
		String result = "source=" + source + "&target=" + target + "&text=";
		return result;
	}

}
