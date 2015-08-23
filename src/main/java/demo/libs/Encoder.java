package demo.libs;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class Encoder {

	 public static String hashString(String message, String algorithm){
        MessageDigest digest;
        byte[] hashedBytes;
		try {
			digest = MessageDigest.getInstance(algorithm);
			hashedBytes = digest.digest(message.getBytes("UTF-8"));
			return convertByteArrayToHexString(hashedBytes);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	 }
	 
	
	public static String getRandomMd5(){
		Long time = (long) (new Date().getTime() + Math.random()*9);
		return Encoder.hashString(String.valueOf(time), "md5");
	}
	
    private static String convertByteArrayToHexString(byte[] arrayBytes) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arrayBytes.length; i++) {
            stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }
        return stringBuffer.toString();
    }
}
