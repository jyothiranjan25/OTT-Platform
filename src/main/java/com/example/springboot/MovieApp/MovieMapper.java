package com.example.springboot.MovieApp;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieDTO map(Movie movie);

    @InheritConfiguration
    List<MovieDTO> map(List<Movie> movies);

    @InheritInverseConfiguration
    Movie map(MovieDTO movieDTO);
}
