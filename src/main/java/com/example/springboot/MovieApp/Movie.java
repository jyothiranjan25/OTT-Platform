package com.example.springboot.MovieApp;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "movies", indexes = {
        @Index(name = "idx_title", columnList = "title"),
        @Index(name = "idx_genre", columnList = "genre")
},uniqueConstraints = {
        @UniqueConstraint(name = "uk_title", columnNames = "title")
})
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@EntityListeners(MovieListener.class)
public class Movie implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @Column(name = "release_date")
    private LocalDateTime releaseDate;

    @NotBlank(message = "Genre is required")
    private String genre;

    @Column(name = "image_poster_path")
    private String imagePosterPath;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
}
