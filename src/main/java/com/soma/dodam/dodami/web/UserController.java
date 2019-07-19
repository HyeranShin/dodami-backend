package com.soma.dodam.dodami.web;

import com.soma.dodam.dodami.domain.User;
import com.soma.dodam.dodami.dto.SignUpReqDto;
import com.soma.dodam.dodami.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "회원 가입", notes = "유효성 검사를 통과해야 합니다. 하단의 Model을 참고하세요.")
    @PostMapping("")
    public String signUp(@Validated @RequestBody SignUpReqDto signUpReqDto) {
        User user = userService.signUp(signUpReqDto);
        return user.getName()+"님 회원 가입 성공";
    }
}
