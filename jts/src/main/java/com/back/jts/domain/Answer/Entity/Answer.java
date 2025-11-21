package com.back.jts.domain.Answer.Entity;

import com.back.jts.domain.Question.Entity.Question;
import com.back.jts.global.BaseEntity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Answer extends BaseEntity {
    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    private Question question;

}
