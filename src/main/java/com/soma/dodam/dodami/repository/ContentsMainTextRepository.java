package com.soma.dodam.dodami.repository;

import com.soma.dodam.dodami.domain.ContentsMainText;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentsMainTextRepository extends JpaRepository<ContentsMainText, Long> {

    List<ContentsMainText> findAllByContentsIdx(Long contentsIdx);
}
