package newgenerationABS;

import phones.MobilePhone;

abstract class AndroidPhone implements MobilePhone {
    /**
     * Concrete method in the abstract class
     */
    public final void displayPhoneOSType() {
        System.out.println("I run on Android OS");
    }
 
    /**
     * An abstract method. Inheriting class should provide the definition
     */
    public abstract void capturePhoto();
}
