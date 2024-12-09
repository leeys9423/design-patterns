# Singleton pattern
## 의도
오직 한 개의 클래스 인스턴스만 갖도록 보장하고, 이에 대한 전역적인 접근점을 제공

## 동기
어떤 클래스는 정확히 하나의 인스턴스만을 갖도록 하는 것이 좋다.<br>
시스템에 많은 프린터가 있다 하더라도, 프린터 스풀은 오직 하나여야 한다.<br>
또한 파일 시스템도, 윈도우 관리자도 오직 하나여야 한다.<br>
클래스 자신이 자기의 유일한 인스턴스로 접근하는 방법을 자체적으로 관리하는 것이 좋은 방법이다.

## 활용성
1. 클래스의 인스턴스가 오직 하나여야 함을 보장하고, 잘 정의된 접근점(access point)으로 모든 사용자가 접근할 수 있도록 해야 할 때
2. 유일한 인스턴스가 서브클래싱으로 확장되어야 하며, 사용자는 코드의 수정없이 확장된 서브클래스의 인스턴스를 사용할 수 있어야 할 때

## 예시 코드
1. SettingV1 - 멀티스레드 환경을 고려하여 인스턴스 접근시 synchronized 키워드를 붙여놨지만, 락이 걸리므로 성능상 비효율을 초래
2. SettingV2 - 이른 초기화(Eager Initialization)로 인한 비용이 비싸거나 잘 사용하지 않은 클래스라면 비효율적
3. SettingV3 - synchronized 키워드를 메서드 안에 배치함으로써, 메서드 자체에 synchronized 를 걸어주는 것보다 효율적 + 멤버 변수에 volatile 키워드를 기입해야함
4. SettingV4 - 정적 중첩 클래스를 사용하여 가장 효율적으로 만들 수 있음.
> 2번과 4번의 차이점 : <br>
> SettingsV4: getInstance() 호출 시점에 SettingsHolder 클래스가 로딩되며 INSTANCE 초기화 (정적 중첩 클래스의 특징 - 실제 사용할 때까지 lazy loading) <br>
SettingsV2: JVM이 클래스를 로딩하는 시점에 즉시 INSTANCE 초기화

## 장점
1. 객체 접근 통제 : 단 하나의 인스턴스만 존재하도록 보장하고 관리할 수 있음
2. 범위 제한 : 전역 변수처럼 사용하되 더 안전하게 관리 가능
3. 기능 확장 용이 : 객체의 기능을 추가하거나 수정하기 쉬움
   ```java
    public class Settings { 
        private static Settings instance;
        private String configPath;  // 나중에 추가된 설정
        private int timeout;        // 나중에 추가된 설정
    
        public void setConfigPath(String path) {
            this.configPath = path;
        }
    }
    ```
4. 인스턴스 수 조절 : 나중에 필요하면 여러 개의 인스턴스를 허용하도록 변경 가능
    ```java
    public class LimitedPool {
        private static final int MAX_INSTANCES = 3;
        private static List<LimitedPool> instances = new ArrayList<>();
    
        public static LimitedPool getInstance() {
            if (instances.size() < MAX_INSTANCES) {
                instances.add(new LimitedPool());
            }
   
            return instances.get(0);
        }
    }
    ```
5. 유연한 설계 : 정적 메서드만 사용하는 것보다 디자인 패턴으로 구현하여 더 유연하게 활용 가능
    ```java
    interface DatabaseConnection {
        void connect();
    }
    
    public class SingletonDB implements DatabaseConnection {
        private static SingletonDB instance;
    
        public void connect() {
        // 구현
        }
    }
    ```

## 모르는 단어 정리
1. 서브 클래싱 : 기존 클래스를 상속받아 새로운 클래스를 만드는 것을 의미
   ```java
   class Animal{}
   class Dog extends Animal{} // Dog가 Animal의 서브클래스
   ```
2. volatile 변수의 가시성 보장 : 멀티스레드 환경에서 한 스레드가 변수를 수정했을 때 다른 스레드가 이 수정된 값을 즉시 볼 수 있도록 보장하는 것