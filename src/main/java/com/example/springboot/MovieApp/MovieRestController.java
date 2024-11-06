package com.example.springboot.MovieApp;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
@CrossOrigin
public class MovieRestController {

    private final MovieService movieService;
    private final MovieMapper movieMapper;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<MovieDTO> get(MovieDTO movieDTO) {
        return movieService.get(movieDTO);
    }

    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    public MovieDTO getById(@RequestParam Long id) {
        Movie movies = movieService.getById(id);
        return movieMapper.map(movies);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public MovieDTO create(@RequestBody MovieDTO movieDTO) {
        Movie movie = movieService.create(movieDTO);
        return  movieMapper.map(movie);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PATCH)
    public MovieDTO update(@RequestBody MovieDTO movieDTO) {
        Movie movie = movieService.update(movieDTO);
        return movieMapper.map(movie);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(@RequestBody MovieDTO movieDTO) {
        return movieService.delete(movieDTO);
    }

    @RequestMapping(value = "/saveImage", method = RequestMethod.PUT)
    public MovieDTO uploadDataWithImage(@ModelAttribute MovieDTO movieDTO, @RequestParam("image") MultipartFile image) {
        Movie movie = movieService.uploadDataWithImage(movieDTO, image);
        return  movieMapper.map(movie);
    }
}