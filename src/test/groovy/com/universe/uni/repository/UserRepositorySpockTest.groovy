package com.universe.uni.repository

import com.universe.uni.domain.SnsType
import com.universe.uni.domain.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import spock.lang.Specification
import spock.lang.Unroll

@DataJpaTest
class UserRepositorySpockTest extends Specification {

    @Autowired
    private UserRepository userRepository

    @Unroll
    def "유저는 SNS 정보를 사용하여 생성할 수 있다"() {
        given: "유저의 SNS 정보가 주어지면"
        String expectedSnsAuthCode = "SNS_AUTH_CODE"

        and: "유저 Entity 를 생성하고"
        User user = User.builder()
                .snsType(expectedSnsType)
                .snsAuthCode(expectedSnsAuthCode)
                .build()

        when: "유저를 저장하면"
        User actualUser = userRepository.save(user)

        then: "유저의 정보가 저장된다"
        actualUser.id != null
        actualUser.snsType == expectedSnsType
        actualUser.snsAuthCode == expectedSnsAuthCode

        where:
        expectedSnsType | _
        SnsType.KAKAO   | _
        SnsType.GOOGLE  | _
        SnsType.APPLE   | _
    }

    def "유저 닉네임을 변경할 수 있다"() {
        given: "새롭게 변경할 닉네임이 주어지고"
        String expectedNickname = "test"

        and: "변경할 유저를 조회하여"
        User user = userRepository.findById(1L).get()

        when: "이름을 바꾸면"
        user.updateNickname(expectedNickname)

        then: "DB에 유저의 이름이 변경되어 저장된다"
        userRepository.findById(1L).get().nickname == expectedNickname
        noExceptionThrown()
    }

    def "유저 닉네임을 10글자 이상으로 변경할 수 없다"() {
        given: "새로운 닉네임으로 10글자 이상이 주어지고"
        String nickname = "12345678901"

        and: "변경할 유저를 조회하여"
        User user = userRepository.findById(1L).get()

        when: "유저 이름을 변경하면"
        user.updateNickname(nickname)

        then: "IllegalArgumentException 이 던져진다."
        def exception = thrown(IllegalArgumentException.class)
        exception.message == "nickname exceeds the maximum length of 10 characters"

        and: "유저 이름은 변경되지 않는다."
        userRepository.findById(1L).get().nickname != nickname
    }

    def "이미 가입된 유저의 SNS 와 인증번호로 유저를 검색할 수 있다"() {
        given: "이미 가입된 유저의 SNS 인증번호와 SNS Type 이 주어지면"
        String snsAuthCode = "1"

        expect: "유저를 찾을 수 있다"
        User user = userRepository.findBySnsAuthCode(snsAuthCode).get()
        user.id == 1L
    }
}
