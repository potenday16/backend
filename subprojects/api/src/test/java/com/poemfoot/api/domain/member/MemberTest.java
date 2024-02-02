package com.poemfoot.api.domain.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberTest {

    Member member;

    @BeforeEach
    void beforeEach() {
        member = new Member("dsfa",DeviceOsType.IOS,"nickname");
    }

    @Test
    void maxNumberCountUpTest() {
        Long maxNumber = member.plusMaxNumber();

        assertThat(maxNumber).isEqualTo(member.getMaxNumber());
    }
}
