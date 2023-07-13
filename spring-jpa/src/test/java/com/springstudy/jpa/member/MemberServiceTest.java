package com.springstudy.jpa.member;

import static org.junit.jupiter.api.Assertions.*;

import antlr.collections.impl.IntRange;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    Member1Repository memberRepository;

    @Autowired
    MemberService memberService;

    @Test
    public void pk로_조회하면_update_일괄수행() {
        memberService.test_PK로_조회();
    }

    @Test
    public void pk아닌값으로_조회하면_update_바로수행() {
        memberService.test_NONE_PK로_조회();
    }
}
