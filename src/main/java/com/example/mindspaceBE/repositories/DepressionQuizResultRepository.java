package com.example.mindspaceBE.repositories;

import com.example.mindspaceBE.models.entities.DepressionQuizResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepressionQuizResultRepository extends JpaRepository<DepressionQuizResult, Long> {
}
