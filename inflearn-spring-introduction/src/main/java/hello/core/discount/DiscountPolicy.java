package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {

    /*
     * @return 할인 대상 금액
     * 1,000원이 할인됐으면 그 금액을 알려줌
     */
    int discount(Member member, int price);
}
