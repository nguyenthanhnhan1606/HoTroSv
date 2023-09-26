package com.ntn.wedhotrots.controller;

import com.ntn.wedhotrots.pojo.Questions;
import com.ntn.wedhotrots.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuestionController {
    @Autowired
    private QuestionService questionSer;

    @GetMapping("questionlive/{id}")
    @CrossOrigin
    public List<Questions> getQuestionByIdLive(@PathVariable int id){
        return this.questionSer.getQuestionByIdLive(id);
    }

    @PostMapping("/question")
    @CrossOrigin
    public boolean addQuestion(@RequestBody Questions q){
        return questionSer.addQuestion(q);
    }
}
