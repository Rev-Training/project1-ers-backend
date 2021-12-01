package service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder
{
    static BCryptPasswordEncoder encoder;
    
    static {
	encoder = new BCryptPasswordEncoder(13);
    }
    
    public static final String encodePassword(String inputPassword)
    {
	String outputHash;
	do
	{
	  outputHash = encoder.encode(inputPassword);
	} while (encoder.upgradeEncoding(outputHash));
	return outputHash;
    }
    
    public static final boolean verifyPassword(String inputPassword, String encodedPassword) {
	return encoder.matches(inputPassword, encodedPassword);
    }
}
