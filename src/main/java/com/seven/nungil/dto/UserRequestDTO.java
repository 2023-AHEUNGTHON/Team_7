package com.seven.nungil.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequestDTO {

    @ApiModelProperty(value = "사용자 명", example = "홍길동")
    private String userName;

    @ApiModelProperty(value = "추천 받는 장소 테마", example = "분위기 좋은 카페")
    private String placeTheme;

    @ApiModelProperty(value = "사용자 현재 위도", example = "37.4990004")
    private Float latitude;

    @ApiModelProperty(value = "사용자 현재 경도", example = "127.0326401")
    private Float longitude;


}
