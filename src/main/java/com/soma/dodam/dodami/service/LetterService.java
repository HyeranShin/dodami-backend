package com.soma.dodam.dodami.service;

import com.soma.dodam.dodami.domain.Letter;
import com.soma.dodam.dodami.dto.request.LetterReqDto;
import com.soma.dodam.dodami.dto.response.LetterResDto;
import com.soma.dodam.dodami.exception.NoResultException;
import com.soma.dodam.dodami.exception.NotExistException;
import com.soma.dodam.dodami.exception.NotMatchException;
import com.soma.dodam.dodami.repository.LetterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LetterService {

    private final LetterRepository letterRepository;

    public void writeLetter(LetterReqDto letterReqDto) {
        if(letterReqDto.getTitle().equals("")) {
            throw new NotExistException("title", "편지 제목이 없습니다.");
        }
        if(letterReqDto.getContent().equals("")) {
            throw new NotExistException("content", "편지 내용이 없습니다.");
        }

        letterRepository.save(letterReqDto.toLetter());
    }

    public List<LetterResDto> getLetterList(Long userIdx) {
        List<LetterResDto> foundLetterList = letterRepository.findByUserIdx(userIdx)
                .stream()
                .map(letter -> new LetterResDto(letter))
                .collect(Collectors.toList());

        if(foundLetterList.size() == 0) {
            throw new NoResultException("userIdx", "편지 조회 결과가 없습니다.");
        }

        return foundLetterList;
    }

    public void deleteLetter(Long userIdx, Long idx) {
        if(!letterRepository.findById(idx).isPresent()) {
            throw new NotExistException("idx", "해당하는 편지가 존재하지 않습니다.");
        }
        if(letterRepository.findByUserIdxAndIdx(userIdx, idx).isPresent()) {
            letterRepository.deleteById(idx);
        }
        else {
            throw new NotMatchException("idx", "다른 유저의 편지 idx 입니다.");
        }
    }
}
