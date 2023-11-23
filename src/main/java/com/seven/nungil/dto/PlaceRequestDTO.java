package com.seven.nungil.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlaceRequestDTO {
	@ApiModelProperty(value = "userId", example = "1")
	@NotNull
	private Long userId;

	@ApiModelProperty(value = "추천 위치 명", example = "대나무숲")
	@NotBlank
	@Size(max=40)
	private String placeName;

	@ApiModelProperty(value = "추천자", example = "유승한")
	@NotBlank
	@Size(max=20)
	private String placeProvider;

	@ApiModelProperty(value = "추천 위치 위도", example = "37.4990004")
	@NotNull
	private Float latitude;

	@ApiModelProperty(value = "추천 위치 경도", example = "127.0326401")
	@NotNull
	private Float longitude;

	@ApiModelProperty(value = "메시지", example = "너랑 내가 처음 만났던 곳이야")
	@NotEmpty
	@Size(min = 1, max = 1000)
	private String placeDescription;

	@ApiModelProperty(value = "퀴즈", example = "너랑 사귄 날")
	@Size(max=200)
	private String quiz;

	@ApiModelProperty(value = "퀴즈 정답", example = "22.03.21")
	@Size(max=50)
	private String quizAnswer;

	@ApiModelProperty(value = "퀴즈 비밀번호", example = "000000")
	@Size(min = 6, max = 6, message = "퀴즈 비밀번호는 반드시 6자리여야 합니다.")
	private String placePasswd;

}

