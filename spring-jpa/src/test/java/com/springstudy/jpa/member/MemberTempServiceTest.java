package com.springstudy.jpa.member;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberTempServiceTest {

    @Autowired
    private TempMemberRepository tempMemberRepository;

    private MemberTempService memberTempService;

    private static List<String> ids;

//    @BeforeEach
//    void setUp() {
////        ids = new ArrayList<>();
////        IntStream.range(0, 100)
////                .forEach(i -> ids.add(RandomStringUtils.randomAlphanumeric(10)));
//        memberTempService = new MemberTempService(tempMemberRepository);
//    }

    @Test
    @Rollback(value = false)
    void test2() throws InterruptedException {
        TempMember tempMember = new TempMember(3L, "test");
        tempMemberRepository.save(tempMember);

    }

//    @Test
//    void test3() {
//        TempMember temp = tempMemberRepository.findByNickname("test1").orElseThrow();
//        org.assertj.core.api.Assertions.assertThat(temp.getNickname()).isEqualTo("test1");
//        temp = tempMemberRepository.findByNickname("test").orElseThrow();
//        org.assertj.core.api.Assertions.assertThat(temp.getNickname()).isEqualTo("test");
//    }
}
