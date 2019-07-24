package com.soma.dodam.dodami.service;

import com.soma.dodam.dodami.domain.Suggestion;
import com.soma.dodam.dodami.dto.SuggestionReqDto;
import com.soma.dodam.dodami.exception.NotExistException;
import com.soma.dodam.dodami.repository.SuggestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SuggestionService {

    private final SuggestionRepository suggestionRepository;

    public Suggestion saveSuggestion(SuggestionReqDto suggestionReqDto) {
        if(suggestionReqDto.getContent().equals("")) {
            throw new NotExistException("content", "건의사항 내용이 없습니다.");
        }
        return suggestionRepository.save(suggestionReqDto.toSuggestion());
    }
}
