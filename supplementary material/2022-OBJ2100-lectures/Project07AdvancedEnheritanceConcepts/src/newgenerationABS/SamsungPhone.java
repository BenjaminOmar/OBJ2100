package newgenerationABS;

public class SamsungPhone extends AndroidPhone{
	    @Override
	    //Overridden from AndroidPhone abstract class
	    public void capturePhoto() {
	        System.out.println("SamsungPhone can capture photo");
	    }
	 
	    @Override
	    //Method contract from the MobilePhone interface
	    public void makeACall(Long number, Integer countryCode) {
	        System.out.println(String.format("SamsungPhone can make audio and video calls to 0%d-%d", countryCode, number));
	    }
	 
	    @Override
	    //Method contract from MobilePhone interface
	    public boolean sendSMS(String message) {
	        System.out.println(String.format("SamsungAPhone can send text message - %s", message));
	        return true;
	    }
	
}
