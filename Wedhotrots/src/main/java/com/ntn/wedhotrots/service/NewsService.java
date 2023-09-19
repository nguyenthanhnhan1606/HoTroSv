package com.ntn.wedhotrots.service;

import com.ntn.wedhotrots.pojo.News;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface NewsService {
    List<News> getAlls();

    List<News> getNewsCXD();

    List<News> getNewsDH();
    List<News> getNewsSDH(Map<String,String> params);
    List<News> getNewsTT();
    List<News> getNewsTX();
    Optional<News> getNewsById(int id);

    boolean updateNews(int id, Map<String, String> params, MultipartFile file);

    boolean addNews(Map<String, String> params, MultipartFile file);

    boolean duyetNews(int id, String trangthai);

    boolean recycleBin(int id);
}
