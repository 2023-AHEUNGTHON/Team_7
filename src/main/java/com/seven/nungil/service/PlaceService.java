package com.seven.nungil.service;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seven.nungil.domain.RecommendedPlace;
import com.seven.nungil.domain.User;
import com.seven.nungil.dto.PlaceCancelRequestDTO;
import com.seven.nungil.dto.PlaceRegisterResponse;
import com.seven.nungil.dto.PlaceRequestDTO;
import com.seven.nungil.exception.notfound.NotFoundException;
import com.seven.nungil.exception.unauthorized.UnauthorizedException;
import com.seven.nungil.repository.RecommendedPlaceRepository;
import com.seven.nungil.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlaceService {
	private final RecommendedPlaceRepository placeRepository;
	private final UserRepository userRepository;
	private final Random random = new Random();

	/**
	 * 추천 장소를 생성하는 메서드이다.
	 *
	 * @param placeRequestDTO 추천 장소 생성 시 요구되는 정보
	 * @return place Id
	 */
	@Transactional
	public PlaceRegisterResponse registerPlace(PlaceRequestDTO placeRequestDTO) {
		User user = userRepository.findById(placeRequestDTO.getUserId())
			.orElseThrow(() -> new NotFoundException("User Not Found " + placeRequestDTO.getUserId()));
		user.plusPlaceCount();

		RecommendedPlace.RecommendedPlaceBuilder builder = RecommendedPlace.builder()
			.user(user)
			.placeName(placeRequestDTO.getPlaceName())
			.placeProvider(placeRequestDTO.getPlaceProvider())
			.placeDescription(placeRequestDTO.getPlaceDescription())
			.latitude(placeRequestDTO.getLatitude())
			.longitude(placeRequestDTO.getLongitude())
			.quiz(null)
			.quizAnswer(null)
			.quizHint(null)
			.placePasswd(placeRequestDTO.getPlacePasswd());

		if (placeRequestDTO.getQuiz() != null) {
			String hint = getHint(placeRequestDTO);
			builder.quiz(placeRequestDTO.getQuiz())
				.quizAnswer(placeRequestDTO.getQuizAnswer())
				.quizHint(hint);
		}

		RecommendedPlace newPlace = placeRepository.save(builder.build());

		return new PlaceRegisterResponse(newPlace.getPlaceId());

	}

	private String getHint(PlaceRequestDTO placeRequestDTO) {
		String answer = placeRequestDTO.getQuizAnswer();
		int length = answer.length();
		int halfLength = length / 2;

		Set<Integer> randomPositions = new HashSet<>();
		while (randomPositions.size() < halfLength) {
			int randomPosition = random.nextInt(length);
			randomPositions.add(randomPosition);
		}
		StringBuilder replacedStr = new StringBuilder(answer);

		for (int position : randomPositions) {
			replacedStr.setCharAt(position, '_');
		}
		String hint = replacedStr.toString();
		return hint;
	}

	/**
	 * 추천 장소를 취소하는 메서드이다.
	 *
	 * @param placeCancelRequestDTO 추천 장소 취소 시 요구되는 정보
	 */
	@Transactional
	public void cancelRecommendedPlace(PlaceCancelRequestDTO placeCancelRequestDTO) {
		RecommendedPlace recommendedPlace = placeRepository.findById(placeCancelRequestDTO.getPlaceId())
			.orElseThrow(() -> new NotFoundException("Place Not Found " + placeCancelRequestDTO.getPlaceId()));

		if (!recommendedPlace.getPlacePasswd().equals(placeCancelRequestDTO.getPlacePasswd())) {
			throw new UnauthorizedException("Place Password Not Matched");
		}
		recommendedPlace.getUser().minusPlaceCount();
		placeRepository.delete(recommendedPlace);
	}
}
