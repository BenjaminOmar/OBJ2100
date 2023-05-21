package phones;

class BasicMobilePhone implements MobilePhone {

    @Override
    public void makeACall(Long number, Integer countryCode) {
        System.out.println(String.format("I can call to number %d-%d", countryCode, number));
    }

    @Override
    public boolean sendSMS(String message) {
        System.out.println("I can send SMS");
        return true;
    }

    public void supportClassicGames() {
        System.out.println("I can support classic games");
    }
}
