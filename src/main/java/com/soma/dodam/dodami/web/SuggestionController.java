package com.soma.dodam.dodami.web;

import com.soma.dodam.dodami.auth.Auth;
import com.soma.dodam.dodami.auth.AuthAspect;
import com.soma.dodam.dodami.domain.User;
import com.soma.dodam.dodami.dto.ExceptionDto;
import com.soma.dodam.dodami.dto.SuggestionReqDto;
import com.soma.dodam.dodami.service.SuggestionService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(description = "건의사항 REST API")
@RestController
@RequestMapping("/api/suggestion")
public class SuggestionController {

    SuggestionService suggestionService;

    public SuggestionController(SuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }

    @ApiOperation(value = "건의사항 작성")
    @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "건의사항 작성 성공"),
            @ApiResponse(code = 400, message = "건의사항 작성 실패", response = ExceptionDto.class),
            @ApiResponse(code = 401, message = "잘못된 토큰 ", response = ExceptionDto.class),
            @ApiResponse(code = 500, message = "내부 서버 에러")
    })
    @Auth
    @PostMapping("")
    public ResponseEntity<Void> postSuggestion(HttpServletRequest httpServletRequest,
                                               @RequestBody SuggestionReqDto suggestionReqDto) {
        User user = (User) httpServletRequest.getAttribute(AuthAspect.USER_KEY);
        suggestionReqDto.setUserIdx(user.getIdx());
        suggestionService.saveSuggestion(suggestionReqDto);
        return ResponseEntity.ok().build();
    }
}
