package com.ntn.wedhotrots.controller;

import com.ntn.wedhotrots.pojo.Faquestion;
import com.ntn.wedhotrots.service.FaQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class FaQuestionController {
    @Autowired
    private FaQuestionService faQuestionService;

    @GetMapping("/faquestions/{id}")
    @CrossOrigin
    public Optional<Faquestion> getAlls(@PathVariable int id){
        return faQuestionService.getFAQByiD(id);
    }
    @GetMapping("/faquestions")
    @CrossOrigin
    public List<Faquestion> getAlls(@RequestParam Map<String,String> params){
        return faQuestionService.getAlls(params);
    }
    @GetMapping("/pagefaquestions")
    @CrossOrigin
    public double getPageTT(){
        double count = faQuestionService.getAlls(null).size();
        return Math.ceil(count/25);
    }

    @PostMapping("/faquestions")
    @CrossOrigin
    public boolean addFAQ(@RequestBody Faquestion f){
        return faQuestionService.addFAQ(f);
    }

    @PutMapping("/faquestions/{id}")
    @CrossOrigin
    public boolean updateFAQ(@PathVariable int id,@RequestBody Faquestion f){
        return faQuestionService.updateFAQ(id,f);
    }

    @DeleteMapping("/faquestions/{id}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFAQ(@PathVariable int id){
        faQuestionService.deleteFAQ(id);
    }
}
