package com.ntn.wedhotrots.service.impl;

import com.ntn.wedhotrots.pojo.Livestreams;
import com.ntn.wedhotrots.pojo.Questions;
import com.ntn.wedhotrots.repository.LivestreamRepository;
import com.ntn.wedhotrots.repository.QuestionRepository;
import com.ntn.wedhotrots.service.LivestreamService;
import com.ntn.wedhotrots.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepo;
    @Autowired
    private LivestreamRepository liveRepo;

    @Override
    public boolean addQuestion(Questions q) {
        try{
            q.setNgaytao(new Date());
            questionRepo.save(q);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Questions> getQuestionByIdLive(int id) {
        return this.questionRepo.getQuestionsByIdLive(id);
    }
}
