package com.soma.dodam.dodami.repository;

import com.soma.dodam.dodami.domain.ContentsVoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContentsVoiceRepository extends JpaRepository<ContentsVoice, Long> {
    Optional<ContentsVoice> findByUserIdxAndVoiceModelIdxAndContentsIdx(Long userIdx, Long voiceModelIdx, Long contentsIdx);
}
