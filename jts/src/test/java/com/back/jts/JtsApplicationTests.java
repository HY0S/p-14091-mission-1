package com.back.jts;

import com.back.jts.domain.Question.Entity.Question;
import com.back.jts.domain.Question.Repository.QuestionRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class JtsApplicationTests {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private QuestionRepository questionRepository;

    @Test
    @DisplayName("Create dummyData")
    @BeforeAll
    void initData() {
        Question q1 = new Question("sbb가 무엇인가요?", "sbb에 대해 알고 싶습니다");
        questionRepository.save(q1);

        Question q2 = new Question("스프링부트 모델 질문입니다.","id는 자동으로 생성 되나요");
        questionRepository.save(q2);
    }

    @Test
    @DisplayName("check dummyData")
    @Order(1)
    void testJpa(){
        List<Question> all = questionRepository.findAll();
        assertEquals(2,all.size());

        Question q1 = all.get(0);
        assertEquals("sbb가 무엇인가요?",q1.getSubject());
    }

    @Test
    @DisplayName("Find dummyData Subject")
    @Order(2)
    void testJpa2(){
        Question q1 = questionRepository.findBySubject("sbb가 무엇인가요?");
        assertEquals("sbb에 대해 알고 싶습니다", q1.getContent());
    }

    @Test
    @DisplayName("Find dummyData Subject & Content")
    @Order(3)
    void testJpa3(){
        Question q = questionRepository.findBySubjectAndContent("sbb가 무엇인가요?", "sbb에 대해 알고 싶습니다");
        assertEquals("sbb가 무엇인가요?", q.getSubject());
    }

    @Test
    @DisplayName("Find dummyData subString")
    @Order(4)
    void testJpa4(){
        List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
        Question q = qList.get(0);
        assertEquals("sbb가 무엇인가요?", q.getSubject());
    }


    @Test
    @DisplayName("change dummyData")
    @Order(5)
    void testJpa5(){
        Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
        q.setSubject("수정된 제목");
        this.questionRepository.save(q);
    }


    @Test
    @DisplayName("delete dummyData")
    @Order(6)
    void deleteData(){
        this.questionRepository.deleteAll();
    }

}
