package com.ntn.wedhotrots.controller;

import com.ntn.wedhotrots.pojo.Faquestion;
import com.ntn.wedhotrots.service.FaQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
//@RequestMapping("/api")
public class FaQuestionController {
    @Autowired
    private FaQuestionService faQuestionService;

    @GetMapping("/test")
    @CrossOrigin
    public List<Faquestion> getAlls(){
        List<Faquestion> faquestions = faQuestionService.getAlls();
        if(faquestions == null){
            faquestions = new ArrayList<>();
        }
        return faquestions;
    }
}
