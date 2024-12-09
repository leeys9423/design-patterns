package main.java.singleton;

public class Settings {

    private static Settings instance;

    private Settings() {
    }

    // 멀티 쓰레드 환경에서의 불안전한 인스턴스 생성 코드
    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }

        return instance;
    }
}
