package main.java.singleton;

public class App {

    public static void main(String[] args) {
        SettingsV1 settings = SettingsV1.getInstance();
        System.out.println(settings == SettingsV1.getInstance());
    }
}
