package com.seven.nungil.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlaceCancelRequestDTO {
	@ApiModelProperty(value = "placeId", example = "1")
	@NotNull
	private Long placeId;

	@ApiModelProperty(value = "추천 장소 취소 비밀번호", example = "000000")
	@Size(min = 6, max = 6, message = "퀴즈 취소 비밀번호는 반드시 6자리여야 합니다.")
	@NotBlank
	private String placePasswd;

}

