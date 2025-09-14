package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class ConfigurationSingletonTest {

    /*
    AppConfig.class
    @Bean memberService 호출 -> memberRepository 호출 -> new MemoryMemberRepository 생성
    @Bean orderService 호출 -> memberRepository, discountPolicy 호출 -> new MemoryMemberRepository, new RateDiscountPolicy() 생성

    이를 보면 종합 2개의 MemoryMemberRepository 객체를 생성하는것 처럼 보임
     -> 이는 싱글톤 패턴에서 하나의 객체만 생성한것인지 검증
     */

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        //테스트 용으로 Impl에 직접 구현체를 만들어 두었음. Interface에 없으므로 Impl을 직접 호출
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository = " + memberRepository2);
        //객체를 각자 별도로 만들었지만, 동일한 객체로 생성됨.

        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);
        System.out.println("memberRepository = " + memberRepository);
        //근데 직접 생성한 빈도 위의 두개와 동일함.
        // 총 3번을 호출했으나, 모두 동일한 인스턴스

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    public void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean.getClass() = " + bean.getClass());
    }
}
