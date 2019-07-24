package com.soma.dodam.dodami.repository;

import com.soma.dodam.dodami.domain.Letter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LetterRepository extends JpaRepository<Letter, Long> {
}
