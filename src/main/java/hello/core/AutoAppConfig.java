package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
    //spring bean 등록 시 @Bean이나 XML <bean>을 통해 설정 정보에 직접 등록
    // -> AppConfig
    //스프링은 설정 정보가 없어도 자동으로 스프링 빈을 등록하는 컴포넌트 스캔이라는 기능 제공
    //또한 의존관계도 자동으로 주입하는 @Autowired라는 기능 제공


}
