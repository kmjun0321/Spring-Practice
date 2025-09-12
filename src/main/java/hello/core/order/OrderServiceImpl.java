package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    //멤버 리포지토리에서 회원을 찾아야함
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

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
}
