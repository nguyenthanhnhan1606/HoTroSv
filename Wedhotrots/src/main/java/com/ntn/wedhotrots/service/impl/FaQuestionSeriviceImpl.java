package com.ntn.wedhotrots.service.impl;

import com.ntn.wedhotrots.pojo.Faquestion;
import com.ntn.wedhotrots.repository.FaQuestionRepository;
import com.ntn.wedhotrots.service.FaQuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaQuestionSeriviceImpl implements FaQuestionService {
    @Autowired
    private FaQuestionRepository faQuestionRepository;
    @Override
    public List<Faquestion> getAlls() {
        return (List<Faquestion>) faQuestionRepository.findAll();
    }
}
