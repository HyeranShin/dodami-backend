package com.soma.dodam.dodami.web;

import com.soma.dodam.dodami.auth.Auth;
import com.soma.dodam.dodami.auth.AuthAspect;
import com.soma.dodam.dodami.domain.User;
import com.soma.dodam.dodami.dto.ExceptionDto;
import com.soma.dodam.dodami.dto.request.ModUserInfoReqDto;
import com.soma.dodam.dodami.dto.response.ProfileResDto;
import com.soma.dodam.dodami.dto.request.SignInReqDto;
import com.soma.dodam.dodami.dto.request.SignUpReqDto;
import com.soma.dodam.dodami.service.JwtService;
import com.soma.dodam.dodami.service.UserService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(description = "유저 REST API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    @ApiOperation(value = "회원 가입", notes = "유효성 검사를 수행합니다. 하단 Models의 SignUpReqDto를 참고하세요.\n성공 시 토큰을 헤더에 담아 반환합니다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "회원 가입 성공"),
            @ApiResponse(code = 400, message = "잘못된 요쳥(유효성 검사 에러 / 이미 가입된 정보)", response = ExceptionDto.class),
            @ApiResponse(code = 500, message = "내부 서버 오류")
    })
    @PostMapping("")
    public ResponseEntity<Void> signUp(@RequestBody SignUpReqDto signUpReqDto) {
        User user = userService.signUp(signUpReqDto);
        String token = jwtService.create(user.getIdx());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("token", token);
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).build();
    }

    @ApiOperation(value = "로그인", notes = "성공 시 토큰을 헤더에 담아 반환합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "로그인 성공"),
            @ApiResponse(code = 400, message = "잘못된 요청", response = ExceptionDto.class),
            @ApiResponse(code = 500, message = "내부 서버 오류")
    })
    @PostMapping("/login")
    public ResponseEntity<Void> signIn(@RequestBody SignInReqDto signInReqDto) {
        User user = userService.signIn(signInReqDto);
        String token = jwtService.create(user.getIdx());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("token", token);
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).build();
    }

//    @ApiOperation(value = "프로필 조회")
//    @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, dataType = "string", paramType = "header")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "프로필 조회 성공"),
//            @ApiResponse(code = 401, message = "권한 없음", response = ExceptionDto.class),
//            @ApiResponse(code = 500, message = "내부 서버 오류")
//    })
//    @Auth
//    @GetMapping("/profile")
//    public ResponseEntity<ProfileResDto> getProfile(HttpServletRequest httpServletRequest) {
//        User user = (User)httpServletRequest.getAttribute(AuthAspect.USER_KEY);
//        return ResponseEntity.status(HttpStatus.OK).body(userService.getProfile(user));
//    }
//
//    @ApiOperation(value = "회원 탈퇴")
//    @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, dataType = "string", paramType = "header")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "회원 탈퇴 성공"),
//            @ApiResponse(code = 401, message = "권한 없음", response = ExceptionDto.class),
//            @ApiResponse(code = 500, message = "내부 서버 오류")
//    })
//    @Auth
//    @DeleteMapping("")
//    public ResponseEntity<Void> withdraw(HttpServletRequest httpServletRequest) {
//        User user = (User)httpServletRequest.getAttribute(AuthAspect.USER_KEY);
//        userService.withdraw(user.getIdx());
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }
//
//    @ApiOperation(value = "회원 정보 수정", notes = "유효성 검사를 수행합니다. 하단의 Models를 참고하세요.")
//    @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, dataType = "string", paramType = "header")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "회원 정보 수정 성공"),
//            @ApiResponse(code = 400, message = "잘못된 요청", response = ExceptionDto.class),
//            @ApiResponse(code = 401, message = "권한 없음", response = ExceptionDto.class),
//            @ApiResponse(code = 500, message = "내부 서버 오류")
//    })
//    @Auth
//    @PutMapping("")
//    public ResponseEntity<Void> modifyUserInfo(HttpServletRequest httpServletRequest,
//                                               @RequestBody ModUserInfoReqDto modUserInfoReqDto) {
//        User user = (User)httpServletRequest.getAttribute(AuthAspect.USER_KEY);
//        userService.modifyUserInfo(user.getIdx(), modUserInfoReqDto);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }

}
