package com.soma.dodam.dodami.repository;

import com.soma.dodam.dodami.domain.ContentsImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentsImgRepository extends JpaRepository<ContentsImg, Long> {

    List<ContentsImg> findAllByContentsIdx(Long contentsIdx);
}
