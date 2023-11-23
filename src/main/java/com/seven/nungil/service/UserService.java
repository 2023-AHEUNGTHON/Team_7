package com.seven.nungil.service;

import com.seven.nungil.domain.RecommendedPlace;
import com.seven.nungil.domain.User;
import com.seven.nungil.dto.PlaceResponse;
import com.seven.nungil.dto.QuizResponse;
import com.seven.nungil.dto.UserRegisterResponse;
import com.seven.nungil.dto.UserRequestDTO;
import com.seven.nungil.exception.notfound.NotFoundException;
import com.seven.nungil.repository.RecommendedPlaceRepository;
import com.seven.nungil.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final RecommendedPlaceRepository placeRepository;

    /**
     * 사용자를 생성하는 메서드이다.
     *
     * @param userRequestDTO 사용자 생성 시 요구되는 정보
     * @return user Id
     */
    @Transactional
    public UserRegisterResponse registerUser(UserRequestDTO userRequestDTO){
        User user = User.builder()
                .userName(userRequestDTO.getUserName())
                .placeTheme(userRequestDTO.getPlaceTheme())
                .latitude(userRequestDTO.getLatitude())
                .longitude(userRequestDTO.getLongitude())
                .placeCount(0)
                .build();
        User newUser = userRepository.save(user);

        return new UserRegisterResponse(newUser.getUserId());

    }
    /**
     *  추천 위치 개수를 반환하는 메서드이다.
     *
     * @param userId
     * @return placeCount 추천 위치 개수
     */
    public Integer getPlaceCount(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new NotFoundException("User Not Found " + userId));
        return user.getPlaceCount();
    }

    /**
     *  퀴즈 정보를 반환하는 메서드이다.
     *
     * @param placeId 추천 위치 id
     * @return quizResponse  퀴즈 정보
     */
    public QuizResponse getQuiz(Long placeId){
        RecommendedPlace place = placeRepository.findById(placeId)
                .orElseThrow(()-> new NotFoundException("Place Not Found " + placeId));
        return new QuizResponse(place.getQuiz(),place.getQuizAnswer(),place.getQuizAnswer().length(),place.getQuizHint());
    }
    /**
     *  추천 위치 리스트를 반환하는 메서드이다.
     *
     * @param userId 사용자 id
     * @return PlaceResponse 리스트
     */
    public List<PlaceResponse> getPlaces(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new NotFoundException("User Not Found " + userId));
        List<RecommendedPlace> placeList= placeRepository.findRecommendedPlacesByUser(user);

        return placeList.stream()
                .map(place -> new PlaceResponse(
                        place.getPlaceId(),
                        place.getLatitude(),
                        place.getLongitude(),
                        place.getPlaceName(),
                        place.getPlaceProvider(),
			    		place.getQuiz() != null))
                .collect(Collectors.toList());
    }

    /**x
     *  특정 추천 위치를 반환하는 메서드이다.
     *
     * @param placeId 추천 위치 id
     * @return RcommendePlace 추천 위치 정보
     */
    public PlaceResponse getPlace(Long placeId){
        RecommendedPlace place = placeRepository.findById(placeId)
                .orElseThrow(()-> new NotFoundException("Place Not Found " + placeId));
        return new PlaceResponse(place.getPlaceId(),place.getLatitude(),place.getLongitude(), place.getPlaceName(),
            place.getPlaceProvider(), place.getQuiz() != null);
    }
}
