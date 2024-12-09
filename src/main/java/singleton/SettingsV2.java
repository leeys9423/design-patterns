package main.java.singleton;

public class SettingsV2 {

    // 이른 초기화(Eager Initialization)
    // 이른 초기화 비용이 비싸거나 잘 사용하지 않으면 비효율적
    private static final SettingsV2 INSTANCE = new SettingsV2();

    private SettingsV2() {
    }


    public static SettingsV2 getInstance() {
        return INSTANCE;
    }
}
