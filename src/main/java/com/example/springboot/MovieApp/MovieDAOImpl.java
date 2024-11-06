package com.example.springboot.MovieApp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MovieDAOImpl implements MovieDAO {


    @PersistenceContext
    private EntityManager entityManager;

public List<Movie> get(MovieDTO dto) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Movie> criteriaQuery = criteriaBuilder.createQuery(Movie.class);
            Root<Movie> root = criteriaQuery.from(Movie.class);

            List<Predicate> predicates = buildPredicates(dto, criteriaBuilder, root);

            criteriaQuery.where(predicates.toArray(new Predicate[0]));

            TypedQuery<Movie> query = entityManager.createQuery(criteriaQuery);

            return query.getResultList();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private List<Predicate> buildPredicates( MovieDTO dto, CriteriaBuilder criteriaBuilder, Root<Movie> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (dto.getId() != null) {
            predicates.add(criteriaBuilder.equal(root.get("id"), dto.getId()));
        }
        if (dto.getTitle() != null) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), dto.getTitle().toLowerCase()));
        }
        if (dto.getGenre() != null) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("genre")), dto.getGenre().toLowerCase()));
        }
        if (dto.getRating() != null) {
            predicates.add(criteriaBuilder.equal(root.get("rating"), dto.getRating()));
        }
        if (dto.getReleaseDate() != null) {
            predicates.add(criteriaBuilder.equal(root.get("releaseDate"), dto.getReleaseDate()));
        }
        return predicates;
    }
}
