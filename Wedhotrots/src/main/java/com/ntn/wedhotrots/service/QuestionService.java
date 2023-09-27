package com.ntn.wedhotrots.service;

import com.ntn.wedhotrots.pojo.Questions;

import java.util.List;

public interface QuestionService {

    boolean addQuestion(Questions q);
    List<Questions> getQuestionByIdLive(int id);


}
