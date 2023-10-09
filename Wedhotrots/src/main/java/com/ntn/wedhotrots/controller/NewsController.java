package com.ntn.wedhotrots.controller;

import com.ntn.wedhotrots.pojo.News;
import com.ntn.wedhotrots.service.NewsService;
import org.apache.http.conn.util.PublicSuffixList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class NewsController {
    @Autowired
    private NewsService newsSer;

    @GetMapping("/news")
    @CrossOrigin
    public List<News> getAlls(@RequestParam Map<String, String> params){
        return newsSer.getAlls(params);
    }

    @GetMapping("/news/page")
    @CrossOrigin
    public double page(@RequestParam Map<String, String> params){
        double count = newsSer.getAlls(null).size();
        return Math.ceil(count/10);
    }

    @GetMapping("/news/{id}")
    @CrossOrigin
    public Optional<News> getNewsById(@PathVariable int id){
        return newsSer.getNewsById(id);
    }


    //ĐẠI HỌC
    @GetMapping("/newsdh")
    @CrossOrigin
    public List<News> getNewsDH(@RequestParam Map<String, String>parmas){
        return newsSer.getNewsDH(parmas);
    }

    @GetMapping("/newspagedh")
    @CrossOrigin
    public double getPageDH(){
        double count = newsSer.getNewsDH(null).size();
        return Math.ceil(count/10);
    }

    //SAU ĐẠI HỌC
    @GetMapping("/newssdh")
    @CrossOrigin
    public List<News> getNewsSDH(@RequestParam Map<String, String> parmas){
        return newsSer.getNewsSDH(parmas);
    }

    @GetMapping("/newspagesdh")
    @CrossOrigin
    public double getPageSDH(){
        double count = newsSer.getNewsSDH(null).size();
        return Math.ceil(count/10);
    }

    //TRỰC TUYẾN
    @GetMapping("/newstt")
    @CrossOrigin
    public List<News> getNewTT(@RequestParam Map<String, String> params){
        return newsSer.getNewsTT(params);
    }

    @GetMapping("/newspagett")
    @CrossOrigin
    public double getPageTT(){
        double count = newsSer.getNewsTT(null).size();
        return Math.ceil(count/10);
    }

    //TỪ XA
    @GetMapping("/newstx")
    @CrossOrigin
    public List<News> getNewsTX(@RequestParam Map<String, String> params){
        return newsSer.getNewsTX(params);
    }

    @GetMapping("/newspagetx")
    @CrossOrigin
    public double getPageTX(){
        double count = newsSer.getNewsTX(null).size();
        return Math.ceil(count/10);
    }





    @PostMapping("/news")
    @CrossOrigin
    public boolean addNews(@RequestParam Map<String,String> params, MultipartFile file){
        return newsSer.addNews(params,file);
    }

    @PutMapping("/news/update/{id}")
    @CrossOrigin
    public boolean updateNews(@PathVariable int id, @RequestParam Map<String, String> params, @RequestParam(required = false)MultipartFile file){
        return newsSer.updateNews(id,params,file);
    }


    @PutMapping("/news/recyclebin/{id}")
    @CrossOrigin
    public boolean RecycleBin(@PathVariable int id){
        return newsSer.recycleBin(id);
    }
}
