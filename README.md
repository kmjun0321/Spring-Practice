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
