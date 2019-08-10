package com.soma.dodam.dodami.repository;

import com.soma.dodam.dodami.domain.Letter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LetterRepository extends JpaRepository<Letter, Long> {

    List<Letter> findByUserIdx(Long userIdx);

    Optional<Object> findByUserIdxAndIdx(Long userIdx, Long idx);
}
