package newgenerationABS;

public class SamsungPhoneDemo {
	
	    public static void main(String[] args) {
	        System.out.println("This class demonstrates the use of abstract class in Java");
	        System.out.println("----------------------------------------------------------");
	        AndroidPhone samsungPhone = new SamsungPhone();
	        //Final method inherited from AndroidPhone abstract class
	        samsungPhone.displayPhoneOSType();
	 
	        samsungPhone.makeACall(1234567890L, 91);
	 
	        samsungPhone.sendSMS("Hello, world!!!!");
	 
	        samsungPhone.capturePhoto();
	        System.out.println("----------------------------------------------------------");
	    }
	
}
