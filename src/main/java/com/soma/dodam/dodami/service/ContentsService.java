package com.soma.dodam.dodami.service;

import com.soma.dodam.dodami.domain.Contents;
import com.soma.dodam.dodami.domain.ContentsImg;
import com.soma.dodam.dodami.domain.ContentsMainText;
import com.soma.dodam.dodami.dto.response.ContentsResDto;
import com.soma.dodam.dodami.exception.NoResultException;
import com.soma.dodam.dodami.exception.NotExistException;
import com.soma.dodam.dodami.repository.ContentsImgRepository;
import com.soma.dodam.dodami.repository.ContentsMainTextRepository;
import com.soma.dodam.dodami.repository.ContentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContentsService {

    private final ContentsRepository contentsRepository;
    private final ContentsMainTextRepository contentsMainTextRepository;
    private final ContentsImgRepository contentsImgRepository;

    public List<ContentsResDto> getContentsList(Integer categoryIdx) {
        List<ContentsResDto> contentsList = contentsRepository.findAllByCategoryIdx(categoryIdx)
                .stream()
                .map(contents -> new ContentsResDto(contents,
                        contentsMainTextRepository.findAllByContentsIdx(contents.getIdx()),
                        contentsImgRepository.findAllByContentsIdx(contents.getIdx())))
                .collect(Collectors.toList());

        if(contentsList.size() == 0) {
            throw new NoResultException("categoryIdx", "컨텐츠 조회 결과가 없습니다.");
        }

        return contentsList;
    }

    public ContentsResDto getSpecificContents(Long contentsIdx) {
        Contents contents = contentsRepository.findById(contentsIdx)
                .orElseThrow(() -> new NotExistException("contentsIdx", "존재하지 않는 컨텐츠입니다."));

        List<ContentsMainText> contentsMainText = contentsMainTextRepository.findAllByContentsIdx(contentsIdx);
        List<ContentsImg> contentsImg = contentsImgRepository.findAllByContentsIdx(contentsIdx);

        return new ContentsResDto(contents, contentsMainText, contentsImg);
    }

    public List<ContentsResDto> getNewContentsList() {
//        Pageable pageable = PageRequest.of(1, 10, new Sort(Sort.Direction.DESC, "createdDate"));
//        Page<Contents> contentsResDtoPage = contentsRepository.findAll(pageable);

//        List<Contents> contentsResDtoPage = contentsRepository.findAllOrderByCreatedDate(Sort.Direction.DESC);

        List<Contents> contentsResDtoPage = contentsRepository.findAll(new Sort(Sort.Direction.DESC, "createdDate"));
        List<ContentsResDto> newContentsList = contentsResDtoPage
                .stream()
                .map(contents -> new ContentsResDto(contents,
                        contentsMainTextRepository.findAllByContentsIdx(contents.getIdx()),
                        contentsImgRepository.findAllByContentsIdx(contents.getIdx())))
                .collect(Collectors.toList());

        if(newContentsList.size() == 0) {
            new NoResultException("", "컨텐츠 데이터가 없습니다.");
        }

        return newContentsList;
    }
}
