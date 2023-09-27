package com.ntn.wedhotrots.controller;

import com.ntn.wedhotrots.pojo.Banner;
import com.ntn.wedhotrots.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BannerController {
    @Autowired
    private BannerService bannerSer;

    @GetMapping("/banner")
    @CrossOrigin
    public List<Banner> getAlls(@RequestParam Map<String, String> params){
        return bannerSer.getALls(params);
    }

    @GetMapping("/banner/page")
    @CrossOrigin
    public double Page(){
        double count = bannerSer.getALls(null).size();
        return Math.ceil(count/2);
    }



    @GetMapping("/banner/{id}")
    @CrossOrigin
    public Optional<Banner> getBannerById(@PathVariable int id){
        return bannerSer.getBannerById(id);
    }

    @PostMapping("/banner")
    @CrossOrigin
    public boolean addBanner(@RequestParam Map<String, String> params,@RequestParam MultipartFile file){
        return bannerSer.addBanner(params,file);
    }

    @PutMapping ("/banner/update/{id}")
    @CrossOrigin
    public boolean updateBanner(@PathVariable int id, @RequestParam Map<String, String> params, @RequestParam(required = false) MultipartFile file){
        return bannerSer.updateBanner(id,params,file);
    }

    @PutMapping("/banner/recyclebin/{id}")
    @CrossOrigin
    public boolean RecycleBin(@PathVariable int id){
        return  bannerSer.RecycleBin(id);
    }
}
