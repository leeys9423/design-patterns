package main.java.singleton;

public class SettingsV3 {

    public static volatile SettingsV3 instance;

    private SettingsV3() {}

    // double checked locking
    // instance가 있는 경우는
    // sychronized 블럭을 사용하지 않게 되므로 V1 코드보다 효율적
    public static SettingsV3 getInstance() {
        if (instance == null) {
            synchronized (SettingsV3.class) {
                if (instance == null) {
                    instance = new SettingsV3();
                }
            }
        }

        return instance;
    }
}
