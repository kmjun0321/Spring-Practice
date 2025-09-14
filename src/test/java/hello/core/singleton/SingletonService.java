package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {

    }

    public void login() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
/*
static 영역에 객체 instance를 미리 하나 생성함
이 객체는 getInstance()로만 조회 가능
딱 1개의 객체 인스턴스만 존재하기 때문에 생성자를 private로 막아서 혹시라도 new로 객체 인스턴스가 생성되는것을 막음.

 */
