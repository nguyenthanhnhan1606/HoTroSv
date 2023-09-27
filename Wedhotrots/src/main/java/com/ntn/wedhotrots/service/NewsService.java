package com.ntn.wedhotrots.service;

import com.ntn.wedhotrots.pojo.News;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface NewsService {
    List<News> getAlls(Map <String, String> params);


    List<News> getNewsDH(Map<String,String> params);
    List<News> getNewsSDH(Map<String,String> params);
    List<News> getNewsTT(Map<String,String> params);
    List<News> getNewsTX(Map<String,String> params);
    Optional<News> getNewsById(int id);

    boolean updateNews(int id, Map<String, String> params, MultipartFile file);

    boolean addNews(Map<String, String> params, MultipartFile file);



    boolean recycleBin(int id);
}
