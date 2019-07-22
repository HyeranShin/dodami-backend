package com.soma.dodam.dodami.repository;

import com.soma.dodam.dodami.domain.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {
}
