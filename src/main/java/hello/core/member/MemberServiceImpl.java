package hello.core.member;

public class MemberServiceImpl implements MemberService{

    //private final MemberRepository memberRepository = new MemoryMemberRepository();

    /*
    AppConfig 생성하면서 위의 코드 주석처리
    이후 생성자 만듦
     */
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //객체가 싱글톤으로 만들어지는 지 테스트용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
