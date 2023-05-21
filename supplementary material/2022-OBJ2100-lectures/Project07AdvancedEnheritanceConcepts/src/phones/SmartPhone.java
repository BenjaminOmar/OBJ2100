package phones;

class SmartPhone implements MobilePhone {

    @Override
    public void makeACall(Long number, Integer countryCode) {
        System.out.println(String.format("I can make audio and video call to %d-%d", countryCode, number));
    }
 

    @Override
    public boolean sendSMS(String message) {
        System.out.println("I can send SMS");
        return true;
    }
 

    public void canCapturePhoto() {
        System.out.println("I can take HD photo");
    }
}
