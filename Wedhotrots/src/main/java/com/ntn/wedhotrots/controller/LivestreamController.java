package com.ntn.wedhotrots.controller;

import com.ntn.wedhotrots.pojo.Livestreams;
import com.ntn.wedhotrots.service.LivestreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LivestreamController {
    @Autowired
    private LivestreamService liveSer;

    @GetMapping("/live")
    @CrossOrigin
    public List<Livestreams> getAlls(){
        return  liveSer.getAlls();
    }
}
