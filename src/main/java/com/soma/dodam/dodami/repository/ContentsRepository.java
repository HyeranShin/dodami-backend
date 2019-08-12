package com.soma.dodam.dodami.repository;

import com.soma.dodam.dodami.domain.Contents;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentsRepository extends JpaRepository<Contents, Long> {

    List<Contents> findByCategoryIdx(Integer categoryIdx);

    Page<Contents> findAll(Pageable request);
}
