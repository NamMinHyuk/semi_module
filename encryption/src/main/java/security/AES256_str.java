package security;

import javax.crypto.*;
import javax.crypto.spec.*;
import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

public class AES256_str {
	
	private static String key = "key"; // 대칭키 생성

	// 암호화

	public static String Encrypt(String text) throws Exception {

		byte[] keyBytes= new byte[16];
	    byte[] b= key.getBytes("UTF-8");	// 대칭키를 바이트 배열로 변환
	    int len= b.length;

	    if (len > keyBytes.length) {
	    	len = keyBytes.length;
	    }

	    System.arraycopy(b, 0, keyBytes, 0, len);

	    SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
	    // AES 알고리즘을 적용하여 키 생성
	    IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);

	    // AES256 으로 암호화
	    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	    cipher.init(Cipher.ENCRYPT_MODE,keySpec,ivSpec);

	    byte[] results = cipher.doFinal(text.getBytes("UTF-8"));	// 암호문을 바이트 배열로 받기
	    BASE64Encoder encoder = new BASE64Encoder();

	    return encoder.encode(results);								// 바이트 배열을 문자열로 변환하여 리턴
	   }
	
		// 복호화
	
		public static String Decrypt(String text) throws Exception {

			

			byte[] keyBytes= new byte[16];
		    byte[] b= key.getBytes("UTF-8");	// 대칭키를 바이트 배열로 변환
		    int len= b.length;

		    if (len > keyBytes.length) {
		    	len = keyBytes.length;
		    }

		    System.arraycopy(b, 0, keyBytes, 0, len);

		    SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
		    // AES 알고리즘을 적용하여 키 생성
		    IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);

		    // AES256 으로 복호화
		    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		    cipher.init(Cipher.DECRYPT_MODE,keySpec,ivSpec);

		    BASE64Decoder decoder = new BASE64Decoder();
		    byte [] results = cipher.doFinal(decoder.decodeBuffer(text));	// 복호화 된 결과를 바이트 배열로 받기

		    return new String(results,"UTF-8"); 		// 복호화된 바이트 배열을 문자열로 변환하여 리턴
		}
		
}
