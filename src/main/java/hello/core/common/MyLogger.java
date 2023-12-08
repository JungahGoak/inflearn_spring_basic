package hello.core.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.service.annotation.PostExchange;

import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    //동시에 여러 HTTP 요청이 오면 어떤 요청인지 알기 어려움 -> request 스코프 사용
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message){
        System.out.println("message = " + message + "uuid = " + uuid);
    }

    @PostConstruct
    public void init(){
        uuid = UUID.randomUUID().toString();
        System.out.println("init uuid = " + uuid + this);
    }

    @PreDestroy
    public void close(){
        System.out.println("close uuid = " + uuid + this);
    }
}
