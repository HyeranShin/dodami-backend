package com.soma.dodam.dodami.repository;

import com.soma.dodam.dodami.domain.PlayingStory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlayingStoryRepository extends JpaRepository<PlayingStory, Long> {

    void deleteByUserIdxAndContentsIdx(Long userIdx, Long contentsIdx);

    Optional<PlayingStory> findByUserIdxAndContentsIdx(Long userIdx, Long contentsIdx);

    List<PlayingStory> findAllByUserIdx(Long idx);
}
