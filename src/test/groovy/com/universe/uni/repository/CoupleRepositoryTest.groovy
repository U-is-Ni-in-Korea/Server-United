package com.universe.uni.repository

import com.universe.uni.domain.entity.Couple
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.orm.ObjectOptimisticLockingFailureException
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionTemplate
import spock.lang.Specification

import javax.persistence.OptimisticLockException
import java.util.concurrent.ExecutionException
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

@DataJpaTest
class CoupleRepositoryTest extends Specification {

    @Autowired
    private CoupleRepository coupleRepository

    @Autowired
    PlatformTransactionManager transactionManager

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
                coupleRepository.save(couple)
            })
        }
        Future updateCase2 = executor.submit {
            template.execute(status -> {
                Couple couple = coupleRepository.findWithOptimisticForceIncrementById(coupleId).get()
                couple.decreaseHeartToken()
                coupleRepository.save(couple)
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
