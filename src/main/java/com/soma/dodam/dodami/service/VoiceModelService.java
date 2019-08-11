package com.soma.dodam.dodami.service;

import com.soma.dodam.dodami.domain.VoiceModel;
import com.soma.dodam.dodami.dto.request.LearningProgressReqDto;
import com.soma.dodam.dodami.dto.request.ModVoiceModelImgReqDto;
import com.soma.dodam.dodami.dto.request.ModVoiceModelNameReqDto;
import com.soma.dodam.dodami.dto.request.VoiceModelReqDto;
import com.soma.dodam.dodami.dto.response.VoiceModelResDto;
import com.soma.dodam.dodami.exception.*;
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
        isNotEmptyVoiceModelName(voiceModelReqDto.getName());

        if(voiceModelRepository.findByUserIdxAndName(voiceModelReqDto.getUserIdx(), voiceModelReqDto.getName()).isPresent()) {
            throw new AlreadyExistException("name", "음성 모델 이름이 중복됩니다.");
        }
        return voiceModelRepository.save(voiceModelReqDto.toVoiceModel());
    }

    public List<VoiceModelResDto> getVoiceModelList(Long userIdx) {

        List<VoiceModelResDto> foundVoiceModelList = voiceModelRepository.findByUserIdx(userIdx)
                .stream()
                .map(voiceModel -> new VoiceModelResDto(voiceModel))
                .collect(Collectors.toList());

        if(foundVoiceModelList.size() == 0) {
            throw new NoResultException("userIdx", "음성 모델 조회 결과가 없습니다.");
        }

        return foundVoiceModelList;
    }

    public void updateLearningProgress(LearningProgressReqDto learningProgressReqDto) {
        if(learningProgressReqDto.getProgress() == null) {
            throw new NotExistException("progress", "학습 진행 상황을 입력해주세요.");
        }

        if(learningProgressReqDto.getProgress() < 0 || learningProgressReqDto.getProgress() > 100) {
            throw new InvalidValueException("progress", "1~100 사이의 숫자가 아닙니다.");
        }

        isExistingVoiceModel(learningProgressReqDto.getIdx());

        if(voiceModelRepository.findByUserIdxAndIdx(learningProgressReqDto.getUserIdx(), learningProgressReqDto.getIdx()).isPresent()) {
            voiceModelRepository.save(voiceModelRepository.findByIdx(learningProgressReqDto.getIdx()).updateProgress(learningProgressReqDto.getProgress()));
        }
        else {
            throw new NotMatchException("idx", "다른 유저의 음성 모델 idx 입니다.");
        }

    }

    public void modifyVoiceModelName(Long userIdx, ModVoiceModelNameReqDto modVoiceModelNameReqDto) {
        isExistingVoiceModel(modVoiceModelNameReqDto.getIdx());
        isNotEmptyVoiceModelName(modVoiceModelNameReqDto.getName());

        if(voiceModelRepository.findByUserIdxAndName(userIdx, modVoiceModelNameReqDto.getName()).isPresent()) {
            throw new AlreadyExistException("name", "음성 모델 이름이 중복됩니다.");
        }

        if(voiceModelRepository.findByUserIdxAndIdx(userIdx, modVoiceModelNameReqDto.getIdx()).isPresent()) {
            voiceModelRepository.save(voiceModelRepository.findByIdx(modVoiceModelNameReqDto.getIdx()).updateName(modVoiceModelNameReqDto.getName()));
        }
        else {
            throw new NotMatchException("idx", "다른 유저의 음성 모델 idx 입니다.");
        }
    }

    public void modifyVoiceModelImg(Long userIdx, ModVoiceModelImgReqDto modVoiceModelImgReqDto) {
        isExistingVoiceModel(modVoiceModelImgReqDto.getIdx());

        if(voiceModelRepository.findByUserIdxAndIdx(userIdx, modVoiceModelImgReqDto.getIdx()).isPresent()) {
            voiceModelRepository.save(voiceModelRepository.findByIdx(modVoiceModelImgReqDto.getIdx()).updateImgUrl(modVoiceModelImgReqDto.getImgUrl()));
        }
        else {
            throw new NotMatchException("idx", "다른 유저의 음성 모델 idx 입니다.");
        }
    }

    public void deleteVoiceModel(Long userIdx, Long idx) {
        isExistingVoiceModel(idx);

        if(voiceModelRepository.findByUserIdxAndIdx(userIdx, idx).isPresent()) {
            voiceModelRepository.deleteById(idx);
        }
        else {
            throw new NotMatchException("idx", "다른 유저의 음성 모델 idx 입니다.");
        }
    }

    private void isExistingVoiceModel(Long idx) {
        if(!voiceModelRepository.findById(idx).isPresent()) {
            throw new NotExistException("idx", "해당하는 음성 모델이 존재하지 않습니다.");
        }
    }

    private void isNotEmptyVoiceModelName(String name) {
        if (name == null || name.equals("")) {
            throw new InvalidValueException("name", "음성 모델의 이름은 공백이 될 수 없습니다.");
        }
    }
}
