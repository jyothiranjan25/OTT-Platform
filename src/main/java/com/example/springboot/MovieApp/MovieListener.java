package com.example.springboot.MovieApp;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class MovieListener implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        MovieListener.applicationContext = applicationContext;
    }

    @PrePersist
    public void prePersist(Movie movie) {
        if(movie.getTitle().isEmpty()){
            throw new RuntimeException("Movie name cannot be empty");
        }
    }

    @PreUpdate
    public void preUpdate(Movie movie) {
        if(movie.getTitle().isEmpty()){
            throw new RuntimeException("Movie name cannot be empty");
        }
    }

    @PreRemove
    public void preRemove(Movie movie) {
        if(movie.getId() == 1){
            throw new RuntimeException("Cannot delete movie with id 1");
        }
    }
}
