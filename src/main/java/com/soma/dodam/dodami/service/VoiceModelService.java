package com.soma.dodam.dodami.service;

import com.soma.dodam.dodami.domain.User;
import com.soma.dodam.dodami.domain.VoiceModel;
import com.soma.dodam.dodami.dto.request.VoiceModelModReqDto;
import com.soma.dodam.dodami.dto.request.VoiceModelReqDto;
import com.soma.dodam.dodami.dto.response.VoiceModelResDto;
import com.soma.dodam.dodami.exception.AlreadyExistException;
import com.soma.dodam.dodami.exception.NotExistException;
import com.soma.dodam.dodami.exception.NotMatchException;
import com.soma.dodam.dodami.exception.UnAuthenticationException;
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

    public List<VoiceModelResDto> getVoiceModelList(Long userIdx) {
        return voiceModelRepository.findByUserIdx(userIdx)
                .stream()
                .map(voiceModel -> new VoiceModelResDto(voiceModel))
                .collect(Collectors.toList());
    }

    public void modifyVoiceModel(Long userIdx, VoiceModelModReqDto voiceModelModReqDto) {
        if(!voiceModelRepository.findById(voiceModelModReqDto.getIdx()).isPresent()) {
            throw new NotExistException("idx", "해당하는 음성 모델이 존재하지 않습니다.");
        }
        if(voiceModelRepository.findByUserIdxAndIdx(userIdx, voiceModelModReqDto.getIdx()).isPresent()) {
            voiceModelRepository.save(voiceModelRepository.findByIdx(voiceModelModReqDto.getIdx()).updateName(voiceModelModReqDto.getName()));
        }
        else {
            throw new NotMatchException("idx", "다른 유저의 음성 모델 idx 입니다.");
        }
    }

    public void deleteVoiceModel(Long userIdx, Long idx) {
        if(!voiceModelRepository.findById(idx).isPresent()) {
            throw new NotExistException("idx", "해당하는 음성 모델이 존재하지 않습니다.");
        }
        if(voiceModelRepository.findByUserIdxAndIdx(userIdx, idx).isPresent()) {
            voiceModelRepository.deleteById(idx);
        }
        else {
            throw new NotMatchException("idx", "다른 유저의 음성 모델 idx 입니다.");
        }
    }
}
