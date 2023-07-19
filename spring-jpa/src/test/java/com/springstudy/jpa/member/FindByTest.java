package com.springstudy.jpa.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FindByTest {

    @Autowired
    FindByService findByService;

    @BeforeEach
    void setUp() {
        findByService.saveAll();
    }

    @Test
    @DisplayName("Pk로 조회 하면 더티체킹 일괄수행")
    void findByPkAndUpdateTest() {
        findByService.findByPkAndUpdate();
    }

    @Test
    @DisplayName("Pk 아닌 값으로 조회 하면 더티체킹 바로 수행")
    void findByNonePkAndUpdateTest() {
        findByService.findByNonePkAndUpdate();
    }

    @Test
    @DisplayName("Pk로 In 절 조회 하면 더티체킹 일괄수행")
    void findAllByPkInAndUpdateTest() {
        findByService.findAllByPkInAndUpdate();
    }

    @Test
    @DisplayName("Pk 아닌 값으로 In 절 조회 하면 더티체킹 바로 수행")
    void findAllByNonePkInAndUpdateTest() {
        findByService.findAllByNonePkInAndUpdate();
    }

    @Test
    @DisplayName("Pk 아닌 값으로 조회 하면 쿼리는 한번 생성")
    void findByPkTest() {
        findByService.findByPk();
    }

    @Test
    @DisplayName("Pk 아닌 값으로 조회 하면 쿼리는 수행한 만큼 생성")
    void findByNonePkTest() {
        findByService.findByNonePk();
    }

    @AfterEach
    void clean() {
        findByService.deleteAll();
    }
}
