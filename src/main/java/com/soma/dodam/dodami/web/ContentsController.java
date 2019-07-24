package com.soma.dodam.dodami.web;

import com.soma.dodam.dodami.auth.Auth;
import com.soma.dodam.dodami.auth.AuthAspect;
import com.soma.dodam.dodami.domain.User;
import com.soma.dodam.dodami.dto.ExceptionDto;
import com.soma.dodam.dodami.dto.LetterReqDto;
import com.soma.dodam.dodami.service.ContentsService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(description = "컨텐츠 REST API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/contents")
public class ContentsController {

    private final ContentsService contentsService;

    @ApiOperation(value = "편지 작성")
    @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    @ApiResponses({
            @ApiResponse(code = 201, message = "편지 작성 성공"),
            @ApiResponse(code = 400, message = "편지 작성 실패", response = ExceptionDto.class),
            @ApiResponse(code = 401, message = "권한 없음", response = ExceptionDto.class),
            @ApiResponse(code = 500, message = "내부 서버 에러")
    })
    @Auth
    @PostMapping("/letter")
    public ResponseEntity<Void> writeLetter(HttpServletRequest httpServletRequest, @RequestBody LetterReqDto letterReqDto) {
        User user = (User)httpServletRequest.getAttribute(AuthAspect.USER_KEY);
        letterReqDto.setUserIdx(user.getIdx());
        contentsService.writeLetter(letterReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
