package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    //멤버 리포지토리에서 회원을 찾아야함
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //AppConfig 하면서 주석처리

    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    /*
    역할과 구현을 충실히 분리
    다형성 활용, 인터페이스와 구현 객체 분리함
    OCP, DIP 원칙을 준수한것처럼 보이지만 아님
    추상 인터페이스 : DiscountPolicy 의존
    구체(구현) 클래스 : FixDiscountPolicy, RateDiscountPolicy 의존 > DIP 위반

    DIP 위반을 고치려면 인터페이스에만 의존해야함.
     */

    //private DiscountPolicy discountPolicy;
    //AppConfig 하면서 주석처리
    /*
    위의 코드로 두고 그대로 실행하면 NPE 발생함.
    이를 해결하기 위해 누군가가 클라이언트인 OrderServiceImpl에 DiscountPolicy의 구현 객체를 대신 생성하고 주입해주어야 함.
     */

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
    //final 설정이 되어있으면 무조건 생성자를 통해 할당되어야 함
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    /*
    생성자로 설정해줌으로써, 각 클래스가 뭘 의존하는 지 전혀 모름, 그저 인터페이스만 의존함
     -> DIP 준수
     */


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); //사용자를 가져와서 (VIP 등급을 알아야함)
        int discountPrice = discountPolicy.discount(member, itemPrice);
        /*
        할인을 던짐
        OrderService 입장에선 할인금액 모름. 그냥 member와 가격을 던지면 할인된거 알아서 리턴받아 오는거임
        설계가 잘된 예시
        단일 책임 원칙이 지켜진 것.
         */

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //객체가 싱글톤으로 만들어지는 지 테스트용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}
