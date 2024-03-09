package com.universe.uni.repository

import com.universe.uni.domain.InviteCodeStrategy
import com.universe.uni.domain.entity.Couple
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionTemplate
import spock.lang.Specification

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.concurrent.ExecutionException
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CoupleRepositoryTest extends Specification {

    @Autowired
    private CoupleRepository coupleRepository

    @Autowired
    PlatformTransactionManager transactionManager

    def "커플 아이디를 바탕으로 커플을 조회할 수 있다"() {
        given:
        Long coupleId = 1L
        when:
        Couple couple = coupleRepository.findById(coupleId).get()
        then:
        couple.id == coupleId
        noExceptionThrown()
    }

    def "커플은 기념일을 바탕으로 생성되어야 한다"() {
        setup:
        InviteCodeStrategy inviteCodeStrategy = Mock()
        and:
        LocalDate date = LocalDate.parse("1010-11-11", DateTimeFormatter.ISO_LOCAL_DATE)

        when:
        Couple couple = Couple.builder()
            .startDate(date)
            .inviteCode(inviteCodeStrategy.generate())
            .build()
        and:
        coupleRepository.saveAndFlush(couple)

        then:
        inviteCodeStrategy.generate() >> "testCode0"
        and:
        coupleRepository.findByInviteCode("testCode0").get().id == 1002L

    }

    def "커플의 기념일 정보를 수정할 수 있다"() {
        given:
        Long coupleId = 1L
        and:
        LocalDate date = LocalDate.parse("0001-11-11", DateTimeFormatter.ISO_LOCAL_DATE)

        when:
        Couple couple = coupleRepository.findById(coupleId).get()
        couple.updateStartDate(date)
        Couple savedCouple = coupleRepository.saveAndFlush(couple)

        then:
        savedCouple.getStartDate() == date
    }

    def "커플 초대 코드를 바탕으로 초대코드를 가진 커플을 조회할 수 있다"() {
        given: "커플 초대 코드가 주어지면"
        String inviteCode = "123456789"

        expect: "초대 코드에 해당하는 커플을 조회할 수 있다"
        Couple couple = coupleRepository.findByInviteCode(inviteCode).get()
        couple.id == 1L
    }

    def "동일한 커플을 동시에 수정하면 OptimisticLockingFailureException 이 발생한다" () {
        given: "동일한 커플 아이디가 주어지고"
        Long coupleId = 1L

        when: "동시에 두 커플이 수정되면"
        ExecutorService executor = Executors.newFixedThreadPool(2)
        TransactionTemplate template = new TransactionTemplate(transactionManager)
        Future updateCase1 = executor.submit {
            template.execute(status -> {
                Couple couple = coupleRepository.findWithOptimisticForceIncrementById(coupleId).get()
                couple.decreaseHeartToken()
                coupleRepository.saveAndFlush(couple)
            })
        }
        Future updateCase2 = executor.submit {
            template.execute(status -> {
                Couple couple = coupleRepository.findWithOptimisticForceIncrementById(coupleId).get()
                couple.decreaseHeartToken()
                coupleRepository.saveAndFlush(couple)
            })
        }

        then: "ObjectOptimisticLockingFailureException 예외가 발생한다"
        try {
            updateCase1.get()
            updateCase2.get()
            assert false // 예외가 발생하지 않으면 테스트 실패
        } catch (ExecutionException e) {
            assert e.getCause() instanceof OptimisticLockingFailureException
        }

        cleanup:
        executor.shutdown()
    }
}