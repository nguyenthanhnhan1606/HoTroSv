package com.ntn.wedhotrots.service.impl;

import com.ntn.wedhotrots.pojo.Faquestion;
import com.ntn.wedhotrots.repository.FaQuestionRepository;
import com.ntn.wedhotrots.service.FaQuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FaQuestionSeriviceImpl implements FaQuestionService {
    @Autowired
    private FaQuestionRepository faQuestionRepository;

    @Override
    public Optional<Faquestion> getFAQByiD(int id) {
        return faQuestionRepository.findById(id);
    }

    @Override
    public List<Faquestion> getAlls(Map<String, String> params) {
        Pageable pageable = Pageable.unpaged(); // Default to unpaged
        String search="";

        if (params != null) {
            String pageStr = params.get("page");
            if (pageStr != null && !pageStr.isEmpty()) {
                int page = Integer.parseInt(pageStr);
                page -=1;
                pageable = PageRequest.of(page, 25, Sort.by("id").descending());
            }
            search = params.get("search");
        }
        if (search != null && !search.isEmpty()) {
            return faQuestionRepository.getAlls(pageable, search);
        }
        return faQuestionRepository.getAlls(pageable, "");
    }

    @Override
    public boolean addFAQ(Faquestion f) {
        try {
            Faquestion faq= new Faquestion();
            faq.setAnswers(f.getAnswers());
            faq.setQuestion(f.getQuestion());
            faQuestionRepository.save(faq);
            return  true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateFAQ(int id,Faquestion f) {
        try {
            Optional<Faquestion> f1 = faQuestionRepository.findById(id);
            if(f1.isPresent()){
                Faquestion faq=f1.get();
                faq.setAnswers(f.getAnswers());
                faq.setQuestion(f.getQuestion());
                faQuestionRepository.save(faq);
                return  true;
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;    }

    @Override
    public void deleteFAQ(int id) {
        faQuestionRepository.deleteById(id);
    }
}
