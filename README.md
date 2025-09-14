# Spring-Practice 🌱

김영한 스프링 강의 - 기본편 실습 저장소입니다.  
Spring Core 원리를 학습하며 작성한 예제 코드를 관리합니다.

---

## 📖 목차
- [소개](#-소개)
- [기술 스택](#-기술-스택)
- [프로젝트 구조](#-프로젝트-구조)
- [실행 방법](#-실행-방법)
- [학습 내용](#-학습-내용)
- [학습 기록](#-학습-기록)
---

## 🌟 소개
스프링의 핵심 원리(스프링 컨테이너, 의존관계 주입, 싱글톤, 컴포넌트 스캔 등)를  
예제 코드를 통해 연습하는 프로젝트입니다.

---

## 🛠 기술 스택
- **Java 17**
- **Spring Framework**
- **Gradle**
- **IntelliJ IDEA**

---

## 📂 프로젝트 구조
```bash
src
 ├─ main
 │   └─ java
 │       └─ hello.core
 │            ├─ AppConfig.java
 │            ├─ member
 │            ├─ order
 │            └─ discount
 └─ test
     └─ java
         └─ hello.core
```

## 🚀 실행 방법
아직까진 main에서 실행


---

## 📝 학습 기록
1일차 (2025-09-12)

 - https://start.spring.io/ 를 통해 프로젝트 생성

 - 아직 스프링은 사용하지 않고, 기본 Java 코드로만 연습

 - 연습 과정에서 SOLID 원칙 공부

---
2일차 (2025-09-13)

 - AppConfig를 통해 DIP 원칙 준수
 - AppConfig를 통해관심사 분리
 -  AppConfig는 공연 기획자라고 생각하면 된다.
 -  각 구체 클래스를 선택하고 할당해줌.

 -  구체 파일(Impl)이 이전에는 MemoryMemberRepository를 직접 의존
 -  AppConfig로 변경 후에는 memberRepository 인터페이스만 의존 (DIP)
 -  이제 구체 파일은 Memory를 의존하는지, JDBC를 의존하는지, 다른걸 의존하는지 모름 > 실행에만 집중함

MemberServiceImpl 예시

기존 코드

    private final MemberRepository memberRepository = new MemoryMemberRepository();


변경된 코드

    기존 코드 주석처리

    //private final MemberRepository memberRepository = new MemoryMemberRepository();

    AppConfig 생성하면서 위의 코드 주석처리
    이후 생성자 만듦

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

**스프링 전환**

    AppConfig에
    
    @Configuration
    
    각 메서드에 @Bean 추가 > 스프링 컨테이너에 등록됨
.

    MemberApp에
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    //@Bean을 관리해줌
    MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
    
    OrderApp에
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    MemberService memberService = applicationContext.getBean("memberService",MemberService.class);
    OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
    
    추가
    
    ApplicationContext가 Bean을 관리해줌

---
3일차 (2025-09-14)

ApplicationContext...Test.java

Bean을 조회하는 여러가지 방법

>ApplicationContextBasicFindTest - 기본적인 빈 조회 방법
> 
>ApplicationContextInfoTest - 모든 빈 조회
> 
>ApplicationContextSameBeanFindTest - 동일한 타입 조회시 오류 상황
> 
>ApplicationContextExtedsFindTest - 상속된 빈 조회

---

xml로 Bean 등록하기
>appConfig.xml
> 
>XmlAppContextTest.java 추가

---
BeanDefinition 정보

 - BeanClassName: 생성할 빈의 클래스 명(자바 설정 처럼 팩토리 역할의 빈을 사용하면 없음)
 - factoryBeanName: 팩토리 역할의 빈을 사용할 경우 이름, 예) appConfig
 - factoryMethodName: 빈을 생성할 팩토리 메서드 지정, 예) memberService
 - Scope: 싱글톤(기본값)
 - lazyInit: 스프링 컨테이너를 생성할 때 빈을 생성하는 것이 아니라, 실제 빈을 사용할 때 까지 최대한 생성을 지연
처리 하는지 여부
 - InitMethodName: 빈을 생성하고, 의존관계를 적용한 뒤에 호출되는 초기화 메서드 명
 - DestroyMethodName: 빈의 생명주기가 끝나서 제거하기 직전에 호출되는 메서드 명
 - Constructor arguments, Properties: 의존관계 주입에서 사용한다. (자바 설정 처럼 팩토리 역할의 빈을 사용
하면 없음)

```declarative
BeanDefinitionTest 의 findApplicationBean() 메서드를 실행하면 다음과 같음

beanDefinitionName = appConfig beanDefinition = Generic bean: class=hello.core.AppConfig$$SpringCGLIB$$0; scope=singleton; abstract=false; lazyInit=null; autowireMode=0; dependencyCheck=0; autowireCandidate=true; primary=false; fallback=false; factoryBeanName=null; factoryMethodName=null; initMethodNames=null; destroyMethodNames=null
beanDefinitionName = memberService beanDefinition = Root bean: class=null; scope=; abstract=false; lazyInit=null; autowireMode=3; dependencyCheck=0; autowireCandidate=true; primary=false; fallback=false; factoryBeanName=appConfig; factoryMethodName=memberService; initMethodNames=null; destroyMethodNames=[(inferred)]; defined in hello.core.AppConfig
beanDefinitionName = memberRepository beanDefinition = Root bean: class=null; scope=; abstract=false; lazyInit=null; autowireMode=3; dependencyCheck=0; autowireCandidate=true; primary=false; fallback=false; factoryBeanName=appConfig; factoryMethodName=memberRepository; initMethodNames=null; destroyMethodNames=[(inferred)]; defined in hello.core.AppConfig
beanDefinitionName = orderService beanDefinition = Root bean: class=null; scope=; abstract=false; lazyInit=null; autowireMode=3; dependencyCheck=0; autowireCandidate=true; primary=false; fallback=false; factoryBeanName=appConfig; factoryMethodName=orderService; initMethodNames=null; destroyMethodNames=[(inferred)]; defined in hello.core.AppConfig
beanDefinitionName = discountPolicy beanDefinition = Root bean: class=null; scope=; abstract=false; lazyInit=null; autowireMode=3; dependencyCheck=0; autowireCandidate=true; primary=false; fallback=false; factoryBeanName=appConfig; factoryMethodName=discountPolicy; initMethodNames=null; destroyMethodNames=[(inferred)]; defined in hello.core.AppConfig

```