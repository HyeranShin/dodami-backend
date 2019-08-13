package com.soma.dodam.dodami.web;

import com.soma.dodam.dodami.auth.Auth;
import com.soma.dodam.dodami.auth.AuthAspect;
import com.soma.dodam.dodami.domain.User;
import com.soma.dodam.dodami.dto.ExceptionDto;
import com.soma.dodam.dodami.dto.request.SubscriptionReqDto;
import com.soma.dodam.dodami.dto.response.SubscriptionResDto;
import com.soma.dodam.dodami.service.SubscriptionService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(description = "구독권 REST API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subscription")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @ApiOperation(value = "전체 구독권 정보 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "구독권 정보 조회 성공"),
            @ApiResponse(code = 500, message = "내부 서버 에러")
    })
    @GetMapping("")
    public ResponseEntity<List<SubscriptionResDto>> getSubscriptionInfo() {
        return ResponseEntity.ok().body(subscriptionService.getSubscriptionInfo());
    }

    @ApiOperation(value = "특정 구독권 정보 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "구독권 정보 조회 성공"),
            @ApiResponse(code = 500, message = "내부 서버 에러")
    })
    @GetMapping("/{subscriptionIdx}")
    public ResponseEntity<SubscriptionResDto> getSpecificSubscriptionInfo(@PathVariable Integer subscriptionIdx) {
        return ResponseEntity.ok().body(subscriptionService.getSpecificSubscriptionInfo(subscriptionIdx));
    }

    @ApiOperation(value = "구독권 구매")
    @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    @ApiResponses({
            @ApiResponse(code = 200, message = "구독권 구매 성공"),
            @ApiResponse(code = 400, message = "구독권 구매 실패", response = ExceptionDto.class),
            @ApiResponse(code = 401, message = "권한 없음", response = ExceptionDto.class),
            @ApiResponse(code = 500, message = "내부 서버 에러")
    })
    @Auth
    @PostMapping("")
    public ResponseEntity<Void> purchaseSubscription(HttpServletRequest httpServletRequest,
                                                     @RequestBody SubscriptionReqDto subscriptionReqDto) {
        User user = (User)httpServletRequest.getAttribute(AuthAspect.USER_KEY);
        subscriptionService.purchaseSubscription(user.getIdx(), subscriptionReqDto.getIdx());
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "구독권 해지")
    @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    @ApiResponses({
            @ApiResponse(code = 200, message = "구독권 해지 성공"),
            @ApiResponse(code = 400, message = "구독권 해지 실패", response = ExceptionDto.class),
            @ApiResponse(code = 401, message = "권한 없음", response = ExceptionDto.class),
            @ApiResponse(code = 500, message = "내부 서버 에러")
    })
    @Auth
    @DeleteMapping("")
    public ResponseEntity<Void> cancelSubscription(HttpServletRequest httpServletRequest) {
        User user = (User)httpServletRequest.getAttribute(AuthAspect.USER_KEY);
        subscriptionService.cancelSubscription(user);
        return ResponseEntity.ok().build();
    }
}
