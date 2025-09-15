package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core.member",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        //@Configuration으로 등록된건 자동 등록 안되게 필터 지정 (AppConfig나 Test로 만든 Config 등)
)
public class AutoAppConfig {

}
