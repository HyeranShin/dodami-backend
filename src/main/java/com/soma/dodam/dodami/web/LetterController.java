package com.soma.dodam.dodami.web;

import com.soma.dodam.dodami.auth.Auth;
import com.soma.dodam.dodami.auth.AuthAspect;
import com.soma.dodam.dodami.domain.User;
import com.soma.dodam.dodami.dto.ExceptionDto;
import com.soma.dodam.dodami.dto.request.LetterReqDto;
import com.soma.dodam.dodami.dto.response.LetterResDto;
import com.soma.dodam.dodami.service.LetterService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(description = "편지 REST API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/letter")
public class LetterController {

    private final LetterService letterService;

    @ApiOperation(value = "편지 작성")
    @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    @ApiResponses({
            @ApiResponse(code = 201, message = "편지 작성 성공"),
            @ApiResponse(code = 400, message = "편지 작성 실패", response = ExceptionDto.class),
            @ApiResponse(code = 401, message = "권한 없음", response = ExceptionDto.class),
            @ApiResponse(code = 500, message = "내부 서버 에러")
    })
    @Auth
    @PostMapping("")
    public ResponseEntity<Void> writeLetter(HttpServletRequest httpServletRequest,
                                            @RequestBody LetterReqDto letterReqDto) {
        User user = (User)httpServletRequest.getAttribute(AuthAspect.USER_KEY);
        letterReqDto.setUserIdx(user.getIdx());
        letterService.writeLetter(letterReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "편지 조회")
    @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    @ApiResponses({
            @ApiResponse(code = 200, message = "편지 조회 성공"),
            @ApiResponse(code = 204, message = "편지 조회 결과 없음", response = Object.class),
            @ApiResponse(code = 401, message = "권한 없음", response = ExceptionDto.class),
            @ApiResponse(code = 500, message = "내부 서버 에러")
    })
    @Auth
    @GetMapping("")
    public ResponseEntity<List<LetterResDto>> getLetterList(HttpServletRequest httpServletRequest) {
        User user = (User)httpServletRequest.getAttribute(AuthAspect.USER_KEY);
        return ResponseEntity.ok().body(letterService.getLetterList(user.getIdx()));
    }

    @ApiOperation(value = "편지 삭제")
    @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    @ApiResponses({
            @ApiResponse(code = 200, message = "편지 삭제 성공"),
            @ApiResponse(code = 400, message = "편지 삭제 실패", response = ExceptionDto.class),
            @ApiResponse(code = 401, message = "권한 없음", response = ExceptionDto.class),
            @ApiResponse(code = 500, message = "내부 서버 에러")
    })
    @Auth
    @DeleteMapping("/{idx}")
    public ResponseEntity<Void> deleteLetter(HttpServletRequest httpServletRequest,
                                             @PathVariable Long idx) {
        User user = (User)httpServletRequest.getAttribute(AuthAspect.USER_KEY);
        letterService.deleteLetter(user.getIdx(), idx);
        return ResponseEntity.ok().build();
    }
}
