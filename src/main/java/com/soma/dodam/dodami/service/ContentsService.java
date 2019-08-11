package com.soma.dodam.dodami.service;

import com.soma.dodam.dodami.domain.Contents;
import com.soma.dodam.dodami.domain.ContentsImg;
import com.soma.dodam.dodami.dto.response.ContentsResDto;
import com.soma.dodam.dodami.exception.NoResultException;
import com.soma.dodam.dodami.exception.NotExistException;
import com.soma.dodam.dodami.repository.ContentsImgRepository;
import com.soma.dodam.dodami.repository.ContentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContentsService {

    private final ContentsRepository contentsRepository;
    private final ContentsImgRepository contentsImgRepository;

    public List<ContentsResDto> getContentsList(Integer categoryIdx) {
        List<ContentsResDto> contentsList = contentsRepository.findByCategoryIdx(categoryIdx)
                .stream()
                .map(contents -> new ContentsResDto(contents, contentsImgRepository.findAllByContentsIdx(contents.getIdx())))
                .collect(Collectors.toList());

        if(contentsList.size() == 0) {
            throw new NoResultException("categoryIdx", "컨텐츠 조회 결과가 없습니다.");
        }

        return contentsList;
    }

    public ContentsResDto getSpecificContents(Long contentsIdx) {
        Contents contents = contentsRepository.findById(contentsIdx)
                .orElseThrow(() -> new NotExistException("contentsIdx", "존재하지 않는 컨텐츠입니다."));

        List<ContentsImg> contentsImg = contentsImgRepository.findAllByContentsIdx(contentsIdx);

        return new ContentsResDto(contents, contentsImg);
    }
}
