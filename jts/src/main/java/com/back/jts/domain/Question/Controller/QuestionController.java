package com.back.jts.domain.Question.Controller;

import com.back.jts.domain.Question.Entity.Question;
import com.back.jts.domain.Question.Repository.QuestionRepository;
import com.back.jts.domain.Question.Service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class QuestionController {
    @Autowired
    private final QuestionService questionService;



    @GetMapping("/question/list")
    @ResponseBody
    public String list() throws IOException {
        List<Question> questions = questionService.getList();
        
        StringBuilder tableRows = new StringBuilder();
        for (Question question : questions) {
            String subject = question.getSubject() != null ? question.getSubject() : "제목 없음";
            String createdDate = question.getCreatedDate() != null 
                ? question.getCreatedDate().toString() 
                : "";
            
            tableRows.append(String.format(
                "    <tbody>\n    <td>%d</td>\n    <th>%s</th>\n    <th>%s</th>\n    </tbody>\n",
                question.getId(),
                subject,
                createdDate
            ));
        }
        
        ClassPathResource resource = new ClassPathResource("templates/questionlist.html");
        String htmlTemplate = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        
        return htmlTemplate.replace("{{QUESTION_ROWS}}", tableRows.toString());
    }
}
