package com.soma.dodam.dodami.web;

import com.soma.dodam.dodami.auth.Auth;
import com.soma.dodam.dodami.dto.ExceptionDto;
import com.soma.dodam.dodami.dto.response.ResourceResDto;
import com.soma.dodam.dodami.service.S3FileUploadService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Api(description = "리소스 REST API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/image")
public class ResourceController {

    private final S3FileUploadService s3FileUploadService;

    @ApiOperation(value = "리소스 등록", notes = "성공 시 S3에 저장된 리소스 url을 바디에 담아 반환합니다.")
    @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    @ApiResponses({
            @ApiResponse(code = 201, message = "리소스 저장 성공"),
            @ApiResponse(code = 401, message = "권한 없음", response = ExceptionDto.class),
            @ApiResponse(code = 500, message = "내부 서버 에러")
    })
    @Auth
    @PostMapping
    public ResponseEntity<ResourceResDto> uploadResource(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResourceResDto(s3FileUploadService.upload(multipartFile)));
    }
}
