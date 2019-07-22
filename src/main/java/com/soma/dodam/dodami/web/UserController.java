package com.soma.dodam.dodami.web;

import com.soma.dodam.dodami.domain.User;
import com.soma.dodam.dodami.dto.ExceptionDto;
import com.soma.dodam.dodami.dto.SignUpReqDto;
import com.soma.dodam.dodami.service.JwtService;
import com.soma.dodam.dodami.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "유저 REST API")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @ApiOperation(value = "회원 가입", notes = "유효성 검사를 수행합니다. 하단의 Models를 참고하세요.\n성공 시 토큰을 헤더에 담아 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "회원 가입 성공", response = void.class),
            @ApiResponse(code = 400, message = "유효성 검사 에러", response = ExceptionDto.class),
            @ApiResponse(code = 500, message = "내부 서버 에러")
    })
    @PostMapping("")
    public ResponseEntity<Void> signUp(@Validated @RequestBody SignUpReqDto signUpReqDto) {
        User user = userService.signUp(signUpReqDto);
        String token = jwtService.create(user.getIdx());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("token", token);
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).build();
    }
}
