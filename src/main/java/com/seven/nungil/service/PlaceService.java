package com.seven.nungil.service;

import com.seven.nungil.domain.RecommendedPlace;
import com.seven.nungil.domain.User;
import com.seven.nungil.dto.PlaceRegisterResponse;
import com.seven.nungil.dto.PlaceRequestDTO;
import com.seven.nungil.repository.RecommendedPlaceRepository;
import com.seven.nungil.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlaceService {
    private final RecommendedPlaceRepository placeRepository;
    private final UserRepository userRepository;

    /**
     * 추천 장소를 생성하는 메서드이다.
     *
     * @param PlaceRequestDTO 추천 장소 생성 시 요구되는 정보
     * @return place Id
     */
    @Transactional
    public PlaceRegisterResponse placeRegister(PlaceRequestDTO placeRequestDTO){
        User user = userRepository.findById(placeRequestDTO.getUserId())
                .orElseThrow(()->new IllegalArgumentException());
        user.plusPlaceCount();
        Random random = new Random();
        String answer = new String(placeRequestDTO.getQuizAnswer());
        int length = answer.length();
        int halfLength =  length/2;

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

        RecommendedPlace place = RecommendedPlace.builder()
                .user(user)
                .placeName(placeRequestDTO.getPlaceName())
                .placeProvider(placeRequestDTO.getPlaceProvider())
                .placeDescription(placeRequestDTO.getPlaceDescription())
                .latitude(placeRequestDTO.getLatitude())
                .longitude(placeRequestDTO.getLongitude())
                .quiz(placeRequestDTO.getQuiz())
                .quizAnswer(placeRequestDTO.getQuizAnswer())
                .quizHint(hint)
                .build();

        RecommendedPlace newPlace = placeRepository.save(place);

        return new PlaceRegisterResponse(newPlace.getPlaceId());

    }
}
