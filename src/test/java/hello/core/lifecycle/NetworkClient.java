package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient implements InitializingBean, DisposableBean {

    /*
    데이터베이스 커넥션 풀이나 네트워크 소켓처럼 애플리케이션 시작 시점에
    필요한 연결을 미리 해두고, 애플리케이션 종료 시점에 연결을 모두 종료하려면,
    객체 초기화 종료 작업 필요 -> 스프링을 통해 어떻게 진행하는지
    */

    //spring bean:
    // 스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 ->
    // 초기화 콜백 -> 사용 -> 소멸전 콜백 -> 스프링 종료
    private String url;

    public NetworkClient(){
        System.out.println("url = " + url);
    }

    public void setUrl(String url){
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect(){
        System.out.println("connect: " + url);
    }

    public void call(String message){
        System.out.println("call = " + url + ", message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect(){
        System.out.println("close, url = " + url);
    }

    @Override
    public void afterPropertiesSet() throws Exception{
        connect();
        call("초기화 연결 메세지");
    }

    @Override
    public void destroy() throws Exception {
        disconnect();
    }
}
