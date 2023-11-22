package com.seven.nungil.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class RecommendedPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="place_id")
    private Long placeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="user_id")
    private User user;

    private Float latitude;

    private Float longitude;

    private String placeName;

    private String placeProvider;

    private String placeDescription;

    private String quiz;

    private String quizHint;

    private String quizAnswer;

	private String placePasswd;

	@Builder
	public RecommendedPlace(User user, Float latitude, Float longitude, String placeName, String placeProvider,
		String placeDescription, String quiz, String quizHint, String quizAnswer, String placePasswd) {
		this.user = user;
		this.latitude = latitude;
		this.longitude = longitude;
		this.placeName = placeName;
		this.placeProvider = placeProvider;
		this.placeDescription = placeDescription;
		this.quiz = quiz;
		this.quizHint = quizHint;
		this.quizAnswer = quizAnswer;
		this.placePasswd = placePasswd;
	}

}
