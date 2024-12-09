package main.java.singleton;

public class SettingsV4 {

    private SettingsV4() {}

    // static inner 클래스 사용
    private static class SettingsHolder {
        private static final SettingsV4 INSTANCE = new SettingsV4();
    }

    public static SettingsV4 getInstance() {
        return SettingsHolder.INSTANCE;
    }
}
