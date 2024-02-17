package com.universe.uni.repository

import com.universe.uni.domain.entity.Couple
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import spock.lang.Specification

@DataJpaTest
class CoupleRepositoryTest extends Specification {

    @Autowired
    private CoupleRepository coupleRepository

    def "커플 초대 코드를 바탕으로 초대코드를 가진 커플을 조회할 수 있다"() {
        given: "커플 초대 코드가 주어지면"
        String inviteCode = "123456789"

        expect: "초대 코드에 해당하는 커플을 조회할 수 있다"
        Couple couple = coupleRepository.findByInviteCode(inviteCode).get()
        couple.id == 1L
    }
}
