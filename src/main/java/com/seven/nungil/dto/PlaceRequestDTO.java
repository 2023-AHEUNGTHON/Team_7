package com.seven.nungil.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlaceRequestDTO {
    @ApiModelProperty(value = "userId", example = "1")
    private Long userId;

    @ApiModelProperty(value = "추천 위치 명", example = "대나무숲")
    private String placeName;

    @ApiModelProperty(value = "추천자", example = "유승한")
    private  String placeProvider;

    @ApiModelProperty(value = "추천 위치 위도", example = "37.4990004")
    private Float latitude;

    @ApiModelProperty(value = "추천 위치 경도", example = "127.0326401")
    private Float longitude;

    @ApiModelProperty(value = "메시지", example = "너랑 내가 처음 만났던 곳이야")
    private String placeDescription;

    @ApiModelProperty(value = "퀴즈", example = "너랑 사귄 날")
    private String quiz;

    @ApiModelProperty(value = "퀴즈 정답", example = "22.03.21")
    private String quizAnswer;
    @ApiModelProperty(value = "퀴즈 비밀번호", example = "000000")
    private String placePasswd;

}

