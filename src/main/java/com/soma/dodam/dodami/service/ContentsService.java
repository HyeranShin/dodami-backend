package com.soma.dodam.dodami.service;

import com.soma.dodam.dodami.domain.Letter;
import com.soma.dodam.dodami.dto.request.LetterReqDto;
import com.soma.dodam.dodami.exception.NotExistException;
import com.soma.dodam.dodami.repository.LetterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContentsService {

    private final LetterRepository letterRepository;

    public Letter writeLetter(LetterReqDto letterReqDto) {
        if(letterReqDto.getTitle().equals("")) {
            throw new NotExistException("title", "편지 제목이 없습니다.");
        }
        if(letterReqDto.getContent().equals("")) {
            throw new NotExistException("content", "편지 내용이 없습니다.");
        }
        return letterRepository.save(letterReqDto.toLetter());
    }
}
