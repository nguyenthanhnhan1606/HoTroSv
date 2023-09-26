package com.ntn.wedhotrots.controller;

import com.ntn.wedhotrots.pojo.Livestreams;
import com.ntn.wedhotrots.service.LivestreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LivestreamController {
    @Autowired
    private LivestreamService liveSer;

    @GetMapping("/live")
    @CrossOrigin
    public List<Livestreams> getAlls(@RequestParam Map<String,String> params){
        return  liveSer.getAlls(params);
    }

    @GetMapping("/pagelive")
    @CrossOrigin
    public double page(){
        double count = liveSer.getAlls(null).size();
        return Math.ceil(count/2);
    }

    @GetMapping("/live/{id}")
    @CrossOrigin
    public Livestreams getLiveById(@PathVariable int id){
        return  liveSer.getLiveById(id);
    }

    @PostMapping ("/live")
    @CrossOrigin
    public boolean addLive(@RequestBody Livestreams l){
        return  liveSer.addLive(l);
    }

    @PutMapping("/live/{id}")
    @CrossOrigin
    public boolean updateLive(@PathVariable int id,@RequestBody Livestreams l){
        return  liveSer.updateLive(id,l);
    }

    @PutMapping("/live/recyclebin/{id}")
    @CrossOrigin
    public boolean RecycleBin(@PathVariable int id){
        return  liveSer.recycleBin(id);
    }
}
