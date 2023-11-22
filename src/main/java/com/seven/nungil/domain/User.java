package com.seven.nungil.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="user_id")
    private Long userId;

    private String userName;

    private String placeTheme;

    private Float latitude;

    private Float longitude;

    private Integer placeCount;

    @OneToMany(mappedBy = "user")
    private List<RecommendedPlace> placeList = new ArrayList<>();

    public void plusPlaceCount() {
        this.placeCount = placeCount + 1;
    }

    public void minusPlaceCount() {
        this.placeCount = placeCount - 1;
    }
    @Builder
    public User(String userName, String placeTheme, Float latitude, Float longitude, Integer placeCount, List<RecommendedPlace> placeList){
        this.userName = userName;
        this.placeTheme = placeTheme;
        this.latitude = latitude;
        this.longitude = longitude;
        this.placeCount = placeCount;
        this.placeList = placeList;
    }
}
