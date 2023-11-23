package com.seven.nungil.controller;

import com.seven.nungil.dto.PlaceCancelRequestDTO;
import com.seven.nungil.dto.PlaceRegisterResponse;
import com.seven.nungil.dto.PlaceRequestDTO;
import com.seven.nungil.service.PlaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/place")
@RequiredArgsConstructor
@Api(tags = "Place", description = "위치 추천자 관련 API")
public class PlaceController {
    private final PlaceService placeService;

    @PostMapping("/register")
    @ApiOperation(value = "새로운 위치 등록")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "위치 등록 성공"),
            @ApiResponse(code = 400, message = "입력 정보 오류")
    })
    public ResponseEntity<PlaceRegisterResponse> registerPlace(@RequestBody @Validated PlaceRequestDTO placeRequestDTO) {
        PlaceRegisterResponse response = placeService.registerPlace(placeRequestDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/cancel")
    @ApiOperation(value = "추천 위치 등록 취소")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "추천 위치 삭제 성공"),
        @ApiResponse(code = 400, message = "추천 위치 삭제 실패")
    })
    public ResponseEntity<Void> cancelRecommendedPlace(@RequestBody @Validated PlaceCancelRequestDTO placeCancelRequestDTO) {
        placeService.cancelRecommendedPlace(placeCancelRequestDTO);
        return ResponseEntity.ok().build();
    }
}
