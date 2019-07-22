package com.soma.dodam.dodami.repository;

import com.soma.dodam.dodami.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(String id);

    Optional<User> findByPhone(String phone);

    Optional<User> findByIdAndPassword(String id, String password);

    User findByIdx(Long idx);
}
