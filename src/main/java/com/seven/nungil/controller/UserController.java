package com.seven.nungil.controller;

import com.seven.nungil.dto.PlaceResponse;
import com.seven.nungil.dto.QuizResponse;
import com.seven.nungil.dto.UserRegisterResponse;
import com.seven.nungil.dto.UserRequestDTO;
import com.seven.nungil.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Api(tags = "User", description = "사용자 관련 API")
public class UserController {
    private final UserService userService;
    @PostMapping("/register")
    @ApiOperation(value = "새로운 사용자 등록")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "사용자 등록 성공"),
            @ApiResponse(code = 400, message = "입력 정보 오류")
    })
    public ResponseEntity<UserRegisterResponse> registerUser(@RequestBody UserRequestDTO userRequestDTO) {
        UserRegisterResponse response = userService.userRegister(userRequestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}/places/count")
    @ApiOperation(value = "사용자의 추천위치 개수 반환")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "추천 위치 개수 반환 성공"),
            @ApiResponse(code = 404, message = "userId 오류")
    })
    public ResponseEntity<Integer> getPlaceCount(@PathVariable Long userId) {
        Integer placeCount = userService.getPlaceCount(userId);
        return ResponseEntity.ok(placeCount);
    }

    @GetMapping("/places/{placeId}/quiz")
    @ApiOperation(value = "해당 추천 위치의 퀴즈 정보 반환")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "퀴즈 정보 반환 성공"),
            @ApiResponse(code = 404, message = "placeId 오류")
    })
    public ResponseEntity<QuizResponse> getQuiz(@PathVariable Long placeId) {
        QuizResponse quizResponse = userService.getQuiz(placeId);
        return ResponseEntity.ok(quizResponse);
    }

    @GetMapping("/{userId}/places")
    @ApiOperation(value = "사용자가 추천받은 위치를 전부 리스트 형태로 반환")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "추천 위치 리스트 반환 성공"),
            @ApiResponse(code = 404, message = "userId 오류")
    })
    public ResponseEntity<List<PlaceResponse>> getPlaces(@PathVariable Long userId) {
        List<PlaceResponse> places = userService.getPlaces(userId);
        return ResponseEntity.ok(places);
    }

    @GetMapping("/{placeId}/place")
    @ApiOperation(value = "사용자가 추천받은 특정 위치를 반환")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "추천 위치 반환 성공"),
            @ApiResponse(code = 404, message = "placeId 오류")
    })
    public ResponseEntity<PlaceResponse> getPlace(@PathVariable Long placeId) {
        PlaceResponse place = userService.getPlace(placeId);
        return ResponseEntity.ok(place);
    }
}
