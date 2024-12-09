package main.java.singleton;

public class SettingsV1 {

    private static SettingsV1 instance;

    private SettingsV1() {
    }

    // 멀티 쓰레드 환경에서의 불안전한 인스턴스 생성 코드
    public static synchronized SettingsV1 getInstance() {
        if (instance == null) {
            instance = new SettingsV1();
        }

        return instance;
    }
}
