package com.soma.dodam.dodami.web;

import com.soma.dodam.dodami.auth.Auth;
import com.soma.dodam.dodami.auth.AuthAspect;
import com.soma.dodam.dodami.domain.User;
import com.soma.dodam.dodami.dto.ExceptionDto;
import com.soma.dodam.dodami.dto.request.LearningProgressReqDto;
import com.soma.dodam.dodami.dto.request.ModVoiceModelImgReqDto;
import com.soma.dodam.dodami.dto.request.ModVoiceModelNameReqDto;
import com.soma.dodam.dodami.dto.request.VoiceModelReqDto;
import com.soma.dodam.dodami.dto.response.VoiceModelResDto;
import com.soma.dodam.dodami.service.S3FileUploadService;
import com.soma.dodam.dodami.service.VoiceModelService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Api(description = "음성 모델 REST API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/voice-model")
public class VoiceModelController {

    private final VoiceModelService voiceModelService;
    private final S3FileUploadService s3FileUploadService;

    @ApiOperation(value = "음성 모델 추가")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "name", value = "이름", required = true, example = "아빠")
    })
    @ApiResponses({
            @ApiResponse(code = 201, message = "음성 모델 추가 성공"),
            @ApiResponse(code = 400, message = "음성 모델 추가 실패", response = ExceptionDto.class),
            @ApiResponse(code = 401, message = "권한 없음", response = ExceptionDto.class),
            @ApiResponse(code = 500, message = "내부 서버 에러")
    })
    @Auth
    @PostMapping("")
    public ResponseEntity<Void> addVoiceModel(HttpServletRequest httpServletRequest,
                                        String name,
                                        @RequestPart(value = "profile", required = false) final MultipartFile photo) throws IOException {
        VoiceModelReqDto voiceModelReqDto = new VoiceModelReqDto();

        voiceModelReqDto.setName(name);
        if(photo != null) {
            voiceModelReqDto.setImgUrl(s3FileUploadService.upload(photo));
        }
        User user = (User)httpServletRequest.getAttribute(AuthAspect.USER_KEY);
        voiceModelReqDto.setUserIdx(user.getIdx());
        voiceModelService.addVoiceModel(voiceModelReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "학습 진행 상황 변경")
    @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    @ApiResponses({
            @ApiResponse(code = 201, message = "학습 진행 상황 변경 성공"),
            @ApiResponse(code = 400, message = "학습 진행 상황 변경 실패", response = ExceptionDto.class),
            @ApiResponse(code = 401, message = "권한 없음", response = ExceptionDto.class),
            @ApiResponse(code = 500, message = "내부 서버 에러")
    })
    @Auth
    @PutMapping("/{idx}/progress")
    public ResponseEntity<Void> updateLearningProgress(HttpServletRequest httpServletRequest,
                                                       @PathVariable Long idx,
                                                       @RequestBody LearningProgressReqDto learningProgressReqDto) {
        User user = (User)httpServletRequest.getAttribute(AuthAspect.USER_KEY);
        learningProgressReqDto.setUserIdx(user.getIdx());
        learningProgressReqDto.setIdx(idx);
        voiceModelService.updateLearningProgress(learningProgressReqDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ApiOperation(value = "음성 모델 조회")
    @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    @ApiResponses({
            @ApiResponse(code = 200, message = "음성 모델 조회 성공"),
            @ApiResponse(code = 204, message = "음성 모델 조회 결과 없음", response = Object.class),
            @ApiResponse(code = 401, message = "권한 없음", response = ExceptionDto.class),
            @ApiResponse(code = 500, message = "내부 서버 에러")
    })
    @Auth
    @GetMapping("")
    public ResponseEntity<List<VoiceModelResDto>> getVoiceModelList(HttpServletRequest httpServletRequest) {
        User user = (User)httpServletRequest.getAttribute(AuthAspect.USER_KEY);
        return ResponseEntity.ok().body(voiceModelService.getVoiceModelList(user.getIdx()));
    }

    @ApiOperation(value = "음성 모델 이름 수정")
    @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    @ApiResponses({
            @ApiResponse(code = 200, message = "음성 모델 이름 수정 성공"),
            @ApiResponse(code = 400, message = "음성 모델 이름 수정 실패", response = ExceptionDto.class),
            @ApiResponse(code = 401, message = "권한 없음", response = ExceptionDto.class),
            @ApiResponse(code = 500, message = "내부 서버 에러")
    })
    @Auth
    @PutMapping("/{idx}/name")
    public ResponseEntity<Void> modifyVoiceModelName(HttpServletRequest httpServletRequest,
                                                     @PathVariable Long idx,
                                                     @RequestBody ModVoiceModelNameReqDto modVoiceModelNameReqDto) {
        User user = (User)httpServletRequest.getAttribute(AuthAspect.USER_KEY);
        modVoiceModelNameReqDto.setIdx(idx);
        voiceModelService.modifyVoiceModelName(user.getIdx(), modVoiceModelNameReqDto);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "음성 모델 이미지 수정")
    @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    @ApiResponses({
            @ApiResponse(code = 200, message = "음성 모델 이미지 수정 성공"),
            @ApiResponse(code = 400, message = "음성 모델 이미지 수정 실패", response = ExceptionDto.class),
            @ApiResponse(code = 401, message = "권한 없음", response = ExceptionDto.class),
            @ApiResponse(code = 500, message = "내부 서버 에러")
    })
    @Auth
    @PutMapping("/{idx}/image")
    public ResponseEntity<Void> modifyVoiceModelImg(HttpServletRequest httpServletRequest,
                                                    @PathVariable Long idx,
                                                    @RequestBody ModVoiceModelImgReqDto modVoiceModelImgReqDto) {
        User user = (User)httpServletRequest.getAttribute(AuthAspect.USER_KEY);
        modVoiceModelImgReqDto.setIdx(idx);
        voiceModelService.modifyVoiceModelImg(user.getIdx(), modVoiceModelImgReqDto);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "음성 모델 삭제")
    @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    @ApiResponses({
            @ApiResponse(code = 200, message = "음성 모델 삭제 성공"),
            @ApiResponse(code = 400, message = "음성 모델 삭제 실패", response = ExceptionDto.class),
            @ApiResponse(code = 401, message = "권한 없음", response = ExceptionDto.class),
            @ApiResponse(code = 500, message = "내부 서버 에러")
    })
    @Auth
    @DeleteMapping("/{idx}")
    public ResponseEntity<Void> deleteVoiceModel(HttpServletRequest httpServletRequest,
                                                 @PathVariable Long idx) {
        User user = (User)httpServletRequest.getAttribute(AuthAspect.USER_KEY);
        voiceModelService.deleteVoiceModel(user.getIdx(), idx);
        return ResponseEntity.ok().build();
    }
}
