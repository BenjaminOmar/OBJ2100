package phones;

public interface MobilePhone {
	
    void makeACall(Long number, Integer countryCode);
    
    boolean sendSMS(String message);
}
