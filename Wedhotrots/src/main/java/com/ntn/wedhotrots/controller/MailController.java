package com.ntn.wedhotrots.controller;

import com.ntn.wedhotrots.service.MailService;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class MailController {
    @Autowired
    private MailService mailSer;

    @PostMapping("/send-mail")
    @CrossOrigin
    public void send(@RequestParam Map<String,String> params){
        mailSer.sendEmail(params);
    }
}
