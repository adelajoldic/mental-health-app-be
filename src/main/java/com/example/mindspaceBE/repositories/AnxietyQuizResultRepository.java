package com.example.mindspaceBE.repositories;

import com.example.mindspaceBE.models.entities.AnxietyQuizResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnxietyQuizResultRepository extends JpaRepository<AnxietyQuizResult, Long> {
}