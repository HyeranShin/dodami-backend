package com.soma.dodam.dodami.service;

import com.soma.dodam.dodami.domain.User;
import com.soma.dodam.dodami.domain.VoiceModel;
import com.soma.dodam.dodami.dto.request.VoiceModelReqDto;
import com.soma.dodam.dodami.dto.response.VoiceModelResDto;
import com.soma.dodam.dodami.exception.AlreadyExistException;
import com.soma.dodam.dodami.exception.NotExistException;
import com.soma.dodam.dodami.repository.VoiceModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class VoiceModelService {

    private final VoiceModelRepository voiceModelRepository;

    public VoiceModel addVoiceModel(VoiceModelReqDto voiceModelReqDto) {
        if(voiceModelReqDto.getName().equals("")) {
            throw new NotExistException("name", "음성 모델 이름이 없습니다.");
        }
        if(voiceModelRepository.findByUserIdxAndName(voiceModelReqDto.getUserIdx(), voiceModelReqDto.getName()).isPresent()) {
            throw new AlreadyExistException("name", "음성 모델 이름이 중복됩니다.");
        }
        return voiceModelRepository.save(voiceModelReqDto.toVoiceModel());
    }

    public List<VoiceModelResDto> getVoiceModelList(User user) {
        return voiceModelRepository.findByUserIdx(user.getIdx())
                .stream()
                .map(voiceModel -> new VoiceModelResDto(voiceModel))
                .collect(Collectors.toList());
    }
}
