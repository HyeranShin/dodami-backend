package com.soma.dodam.dodami.web;

import com.soma.dodam.dodami.auth.Auth;
import com.soma.dodam.dodami.auth.AuthAspect;
import com.soma.dodam.dodami.domain.User;
import com.soma.dodam.dodami.dto.ExceptionDto;
import com.soma.dodam.dodami.dto.response.ContentsResDto;
import com.soma.dodam.dodami.dto.response.ContentsVoiceResDto;
import com.soma.dodam.dodami.service.ContentsService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(description = "컨텐츠 REST API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/contents")
public class ContentsController {

    private final ContentsService contentsService;


    @ApiOperation(value = "컨텐츠 음성 조회")
    @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    @GetMapping("/{voiceModelIdx}/{contentsIdx}")
    @Auth
    public ResponseEntity<ContentsVoiceResDto> getContentsVoice(HttpServletRequest httpServletRequest,
                                                                @PathVariable Long voiceModelIdx,
                                                                @PathVariable Long contentsIdx) {
        User user = (User) httpServletRequest.getAttribute(AuthAspect.USER_KEY);
        return ResponseEntity.ok().body(contentsService.getContentsVoice(user.getIdx(), voiceModelIdx, contentsIdx));
    }

    @ApiOperation(value = "컨텐츠 조회"
//            , notes = "컨텐츠 사진이 있는 경우에만 imgUrlResDto를 반환합니다."
    )
    @ApiImplicitParam(name = "categoryIdx", value = "(1: 동화, 2: 소설, 3: 뉴스)", dataType = "int", required = true)
    @ApiResponses({
            @ApiResponse(code = 200, message = "컨텐츠 조회 성공"),
            @ApiResponse(code = 204, message = "컨텐츠 조회 결과 없음", response = Object.class),
            @ApiResponse(code = 401, message = "권한 없음", response = ExceptionDto.class),
            @ApiResponse(code = 500, message = "내부 서버 에러")
    })
    @GetMapping("/{categoryIdx}")
    public ResponseEntity<List<ContentsResDto>> getContentsList(@PathVariable Integer categoryIdx) {
        return ResponseEntity.ok().body(contentsService.getContentsList(categoryIdx));
    }

    @ApiOperation(value = "특정 컨텐츠 조회"
//            , notes = "컨텐츠 사진이 있는 경우에만 imgUrlResDto를 반환합니다."
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "특정 컨텐츠 조회 성공"),
            @ApiResponse(code = 400, message = "특정 컨텐츠 조회 실패", response = ExceptionDto.class),
            @ApiResponse(code = 401, message = "권한 없음", response = ExceptionDto.class),
            @ApiResponse(code = 500, message = "내부 서버 에러")
    })
    @GetMapping("/specific/{contentsIdx}")
    public ResponseEntity<ContentsResDto> getSpecificContents(@PathVariable Long contentsIdx) {
        return ResponseEntity.ok().body(contentsService.getSpecificContents(contentsIdx));
    }

    @ApiOperation(value = "신규 컨텐츠 조회"
            , notes = "컨텐츠 사진이 있는 경우에만 imgUrlResDto를 반환합니다."
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "신규 컨텐츠 조회 성공"),
            @ApiResponse(code = 204, message = "신규 컨텐츠 조회 결과 없음", response = Object.class),
            @ApiResponse(code = 401, message = "권한 없음", response = ExceptionDto.class),
            @ApiResponse(code = 500, message = "내부 서버 에러")
    })
    @GetMapping("/new")
    public ResponseEntity<List<ContentsResDto>> getNewContentsList() {
        return ResponseEntity.ok().body(contentsService.getNewContentsList());
    }
}
