package com.soma.dodam.dodami.web;

import com.soma.dodam.dodami.auth.Auth;
import com.soma.dodam.dodami.auth.AuthAspect;
import com.soma.dodam.dodami.domain.User;
import com.soma.dodam.dodami.dto.ExceptionDto;
import com.soma.dodam.dodami.dto.SuggestionReqDto;
import com.soma.dodam.dodami.service.SuggestionService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(description = "건의사항 REST API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/suggestion")
public class SuggestionController {

    private final SuggestionService suggestionService;

    @ApiOperation(value = "건의사항 작성")
    @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    @ApiResponses({
            @ApiResponse(code = 201, message = "건의사항 작성 성공"),
            @ApiResponse(code = 400, message = "건의사항 작성 실패", response = ExceptionDto.class),
            @ApiResponse(code = 401, message = "권한 없음", response = ExceptionDto.class),
            @ApiResponse(code = 500, message = "내부 서버 에러")
    })
    @Auth
    @PostMapping("")
    public ResponseEntity<Void> postSuggestion(HttpServletRequest httpServletRequest,
                                               @RequestBody SuggestionReqDto suggestionReqDto) {
        User user = (User)httpServletRequest.getAttribute(AuthAspect.USER_KEY);
        suggestionReqDto.setUserIdx(user.getIdx());
        suggestionService.saveSuggestion(suggestionReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
