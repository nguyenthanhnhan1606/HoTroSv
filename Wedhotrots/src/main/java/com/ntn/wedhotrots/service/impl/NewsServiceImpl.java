package com.ntn.wedhotrots.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ntn.wedhotrots.pojo.News;
import com.ntn.wedhotrots.repository.NewsRepository;
import com.ntn.wedhotrots.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsRepository newsRepo;
    @Autowired
    private Cloudinary cloudinary;


    @Override
    public List<News> getAlls(Map<String,String> params) {
        Pageable pageable = Pageable.unpaged();
        String search="";

        if (params != null) {
            String pageStr = params.get("page");
            if (pageStr != null && !pageStr.isEmpty()) {
                int page = Integer.parseInt(pageStr);
                page -=1;
                pageable = PageRequest.of(page, 10, Sort.by("id").descending());
            }
            search = params.get("search");
        }
        if (search != null && !search.isEmpty()) {
            return newsRepo.findNews(pageable, search);
        }
        return newsRepo.findNews(pageable, "");
    }


    @Override
    public List<News> getNewsDH(Map<String,String> params) {
        Pageable pageable = Pageable.unpaged();
        String search="";

        if (params != null) {
            String pageStr = params.get("page");
            if (pageStr != null && !pageStr.isEmpty()) {
                int page = Integer.parseInt(pageStr);
                page -=1;
                pageable = PageRequest.of(page, 10, Sort.by("id").descending());
            }
            search = params.get("search");
        }
        if (search != null && !search.isEmpty()) {
            return newsRepo.findNewsDH(pageable, search);
        }
        return newsRepo.findNewsDH(pageable, "");
    }

    @Override
    public List<News> getNewsSDH(Map<String,String> params) {
        Pageable pageable = Pageable.unpaged(); // Default to unpaged
        String search="";

        if (params != null) {
            String pageStr = params.get("page");
            if (pageStr != null && !pageStr.isEmpty()) {
                int page = Integer.parseInt(pageStr);
                page -=1;
                pageable = PageRequest.of(page, 10, Sort.by("id").descending());
            }
             search = params.get("search");
        }
        if (search != null && !search.isEmpty()) {
            return newsRepo.findNewsSDH(pageable, search);
        }
        return newsRepo.findNewsSDH(pageable, "");
    }

    @Override
    public List<News> getNewsTT(Map<String,String> params) {
        Pageable pageable = Pageable.unpaged(); // Default to unpaged
        String search="";

        if (params != null) {
            String pageStr = params.get("page");
            if (pageStr != null && !pageStr.isEmpty()) {
                int page = Integer.parseInt(pageStr);
                page -=1;
                pageable = PageRequest.of(page, 10, Sort.by("id").descending());
            }
            search = params.get("search");
        }
        if (search != null && !search.isEmpty()) {
            return newsRepo.findNewsTT(pageable, search);
        }
        return newsRepo.findNewsTT(pageable, "");
    }

    @Override
    public List<News> getNewsTX(Map<String,String> params) {
        Pageable pageable = Pageable.unpaged(); // Default to unpaged
        String search="";

        if (params != null) {
            String pageStr = params.get("page");
            if (pageStr != null && !pageStr.isEmpty()) {
                int page = Integer.parseInt(pageStr);
                page -=1;
                pageable = PageRequest.of(page, 10, Sort.by("id").descending());
            }
            search = params.get("search");
        }
        if (search != null && !search.isEmpty()) {
            return newsRepo.findNewsTX(pageable, search);
        }
        return newsRepo.findNewsTX(pageable, "");
    }

    @Override
    public Optional<News> getNewsById(int id) {
        return  newsRepo.findById(id);
    }

    @Override
    public boolean updateNews(int id, Map<String, String> params, MultipartFile file) {
        Optional<News> newsOptional = newsRepo.findById(id);

        if (newsOptional.isPresent()) {
            News n = newsOptional.get();
            n.setContent(params.get("content"));
            n.setTitle(params.get("title"));
            n.setNgaycapnhat(new Date());
            n.setType(params.get("type"));
            if (file != null && !file.isEmpty()) {
                try {
                    Map res = this.cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                    n.setImage(res.get("secure_url").toString());
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            newsRepo.save(n);
            return true;
        }
        return false;
    }

    @Override
    public boolean addNews(Map<String, String> params, MultipartFile file) {
        try{
            News n = new News();
            n.setContent(params.get("content"));
            n.setTitle(params.get("title"));
            n.setNgaycapnhat(new Date());
            n.setNgaytao(new Date());
            n.setType(params.get("type"));
            n.setActive((byte) 1);
            if (file != null && !file.isEmpty()) {
                try {
                    String url = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
                    n.setImage(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            newsRepo.save(n);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean recycleBin(int id) {
        Optional<News> n = newsRepo.findById(id);
        if (n.isPresent()) {
            News news = n.get();
            news.setActive((byte) 0);
            try {
                newsRepo.save(news);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }
}
