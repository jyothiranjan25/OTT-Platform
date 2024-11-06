package com.example.springboot.MovieApp;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class MovieDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime releaseDate;
    private String genre;
    private String imagePosterPath;
    private LocalDateTime createdAt;
    private Double rating;
    private Integer pageOffset;
    private Integer pageSize;
}
