package com.soma.dodam.dodami.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(description = "음성 모델 REST API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/void-model")
public class VoiceModelController {

//    @ApiOperation(value = "음성 모델 추가")
//    @PostMapping("/")
//    public void addVoiceModel() {
//    }
//
//    @ApiOperation(value = "음성 모델 조회")
//    @GetMapping("/")
//    public void getVoiceModelList() {
//    }
}
