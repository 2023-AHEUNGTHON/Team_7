package com.seven.nungil.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlaceCancelRequestDTO {
    @ApiModelProperty(value = "userId", example = "1")
    private Long userId;
    @ApiModelProperty(value = "placeId", example = "1")
    private Long placeId;
    @ApiModelProperty(value = "추천 장소 취소 비밀번호", example = "000000")
    private String placePasswd;

}

