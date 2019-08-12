package com.soma.dodam.dodami.service;

import com.soma.dodam.dodami.domain.Contents;
import com.soma.dodam.dodami.domain.PlayingStory;
import com.soma.dodam.dodami.dto.request.PlayedTimeReqDto;
import com.soma.dodam.dodami.dto.response.PlayingStoryResDto;
import com.soma.dodam.dodami.exception.InvalidValueException;
import com.soma.dodam.dodami.exception.NoResultException;
import com.soma.dodam.dodami.exception.NotExistException;
import com.soma.dodam.dodami.repository.ContentsRepository;
import com.soma.dodam.dodami.repository.PlayingStoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PlayingStoryService {

    private final PlayingStoryRepository playingStoryRepository;
    private final ContentsRepository contentsRepository;

    public void updatePlayedTime(Long userIdx, Long contentsIdx, PlayedTimeReqDto playedTimeReqDto) {
        Contents contents = contentsRepository.findById(contentsIdx)
                .orElseThrow(() -> new NotExistException("contentsIdx", "존재하지 않는 컨텐츠입니다."));
        if(contents.getPlayTime() < playedTimeReqDto.getPlayedTime()) {
            throw new InvalidValueException("playedTime", "재생한 시간이 컨텐츠의 총 재생 시간을 초과하였습니다.");
        }
        PlayingStory playingStory = PlayingStory.builder()
                .userIdx(userIdx)
                .contentsIdx(contentsIdx)
                .playedTime(playedTimeReqDto.getPlayedTime())
                .build();
        playingStoryRepository.save(playingStory);
    }

    public void completeStory(Long userIdx, Long contentsIdx) {
        contentsRepository.findById(contentsIdx)
                .orElseThrow(() -> new NotExistException("contentsIdx", "존재하지 않는 컨텐츠입니다."));
        if(playingStoryRepository.findByUserIdxAndContentsIdx(userIdx, contentsIdx).isPresent()) {
            playingStoryRepository.deleteByUserIdxAndContentsIdx(userIdx, contentsIdx);
            return;
        }
        else {
            return;
        }
    }

    public List<PlayingStoryResDto> getPlayingList(Long idx) {
        List<PlayingStoryResDto> playingList = playingStoryRepository.findAllByUserIdx(idx)
                .stream()
                .map(playing -> new PlayingStoryResDto(playing))
                .collect(Collectors.toList());

        if(playingList.size() == 0) {
            new NoResultException("userIdx", "플레이중인 스토리가 없습니다.");
        }

        return playingList;
    }
}
