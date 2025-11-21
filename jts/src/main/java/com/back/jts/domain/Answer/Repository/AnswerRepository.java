package com.back.jts.domain.Answer.Repository;

import com.back.jts.domain.Answer.Entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
