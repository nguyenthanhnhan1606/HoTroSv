package com.ntn.wedhotrots.repository;

import com.ntn.wedhotrots.pojo.Banner;
import com.ntn.wedhotrots.pojo.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import org.springframework.data.domain.Pageable;

public interface NewsRepository extends JpaRepository<News, Integer> {
    @Query("SELECT n FROM News n WHERE n.active = 1 AND n.title LIKE %:search%")
    public List<News> findNews(Pageable pageable, String search);
    @Query("SELECT n FROM News n WHERE n.active =1 and n.type='Tuyển sinh đại học' and n.title LIKE %:search% ORDER BY n.id DESC ")
    public List<News> findNewsDH(Pageable pageable,String search);
    @Query("SELECT n FROM News n WHERE n.active =1 and n.type='Tuyển sinh sau đại học' and n.title LIKE %:search% ORDER BY n.id DESC ")
    public List<News> findNewsSDH(Pageable pageable,String search);
    @Query("SELECT n FROM News n WHERE n.active =1 and n.type='Tuyển sinh trực tuyến' and n.title LIKE %:search% ORDER BY n.id DESC ")
    public List<News> findNewsTT(Pageable pageable,String search);
    @Query("SELECT n FROM News n WHERE n.active =1 and n.type='Tuyển sinh đào tạo từ xa' and n.title LIKE %:search% ORDER BY n.id DESC ")
    public List<News> findNewsTX(Pageable pageable,String search);
}
