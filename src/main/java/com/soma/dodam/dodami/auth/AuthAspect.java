package com.soma.dodam.dodami.auth;

import com.soma.dodam.dodami.domain.User;
import com.soma.dodam.dodami.exception.UnAuthenticationException;
import com.soma.dodam.dodami.repository.UserRepository;
import com.soma.dodam.dodami.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
@Aspect
public class AuthAspect {

    public final static String USER_KEY = "dodami-user";
    private final static String AUTHORIZATION = "Authorization";
    private final HttpServletRequest httpServletRequest;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    /**
     * Repository 의존성 주입
     */
    public AuthAspect(final HttpServletRequest httpServletRequest, final UserRepository userRepository, final JwtService jwtService) {
        this.httpServletRequest = httpServletRequest;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    /**
     * 토큰 유효성 검사
     * @param pjp
     * @return
     * @throws Throwable
     */
    //항상 @annotation 패키지 이름을 실제 사용할 annotation 경로로 맞춰줘야 한다.
    @Around("@annotation(com.soma.dodam.dodami.auth.Auth)")
    public Object around(final ProceedingJoinPoint pjp) throws Throwable {
        final String jwt = httpServletRequest.getHeader(AUTHORIZATION);
        httpServletRequest.setAttribute(USER_KEY, checkAuth(jwt));
        return pjp.proceed(pjp.getArgs());
    }

    private User checkAuth(final String jwtToken) throws Throwable {
        //토큰 존재 여부 확인
        if (jwtToken == null) throw new UnAuthenticationException("token", "유효하지 않은 토큰입니다.");
        //토큰 해독
        final JwtService.Token token = jwtService.decode(jwtToken);
        //토큰 검사
        if (token == null) throw new UnAuthenticationException("token", "유효하지 않은 토큰입니다.");
        else {
            final User user = userRepository.findByIdx(token.getUser_idx());
            //유효 사용자 검사
            if (user == null) throw new UnAuthenticationException("idx", "해당하는 유저가 존재하지 않습니다.");
            return user;
        }
    }
}