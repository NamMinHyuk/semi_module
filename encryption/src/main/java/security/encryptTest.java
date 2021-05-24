package security;

public class encryptTest {

	public static void main(String[] args) {
		String text = "Hello, World!"; // 암호화 할 문자열
		String enc = null;  // 암호화 적용 된 문자열
		String dec = null;  // 복호화 된 문자열 
		try {
			System.out.println(text);
			enc = AES256_str.Encrypt(text);
			System.out.println(enc);
			dec = AES256_str.Decrypt(enc);
			System.out.println(dec);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
