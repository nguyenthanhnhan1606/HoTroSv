package com.ntn.wedhotrots.controller;

import com.ntn.wedhotrots.DTO.ReplyDTO;
import com.ntn.wedhotrots.pojo.Reply;
import com.ntn.wedhotrots.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class ReplyController {
    @Autowired
    private ReplyService replySer;

    @PostMapping("/reply")
    @CrossOrigin
    public ResponseEntity<String> addRep(@RequestBody ReplyDTO r){
        boolean flag = replySer.addRep(r);
        if(flag)
            return  new ResponseEntity<>("success", HttpStatus.OK);
        return  new ResponseEntity<>("fail", HttpStatus.OK);
    }
}
