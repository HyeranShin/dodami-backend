package com.soma.dodam.dodami.repository;

import com.soma.dodam.dodami.domain.User;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @After
    public void cleanup() {
        userRepository.deleteAll();
    }

    @Test
    public void 회원가입() {
        //given
        userRepository.save(User.builder()
                .id("dodam")
                .password("dodam123")
                .name("도담이")
                .phone("010-1234-1234")
                .build());

        //when
        List<User> userList = userRepository.findAll();

        //then
        User user = userList.get(0);
        assertThat(user.getId(), is("dodam"));
        assertThat(user.getPassword(), is("dodam123"));
        assertThat(user.getName(), is("도담이"));
        assertThat(user.getPhone(), is("010-1234-1234"));
    }

    @Test
    public void BaseTimeEntity() {
        //given
        LocalDateTime now = LocalDateTime.now();
        userRepository.save(User.builder()
                .id("dodam")
                .password("dodam123")
                .name("도담이")
                .phone("010-1234-1234")
                .build());

        //when
        List<User> userList = userRepository.findAll();

        //then
        User user = userList.get(0);
        assertTrue(user.getCreatedDate().isAfter(now));
        assertTrue(user.getModifiedDate().isAfter(now));
    }
}