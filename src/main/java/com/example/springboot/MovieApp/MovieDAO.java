package com.example.springboot.MovieApp;

import java.util.List;

public interface MovieDAO {
    List<Movie> get(MovieDTO movieDTO);
}
