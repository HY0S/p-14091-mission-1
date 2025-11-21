package com.back.jts.domain.Question.Controller;

import com.back.jts.domain.Question.Service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RequiredArgsConstructor
@Controller
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/question/list")
    public String list(Model model) {
        model.addAttribute("questionList", questionService.getList());
        return "questionlist";
    }

    @GetMapping(value = "/question/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        model.addAttribute("question", questionService.getQuestion(id));
        return "question_detail";
    }
}
