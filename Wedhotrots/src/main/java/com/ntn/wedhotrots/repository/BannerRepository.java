package com.ntn.wedhotrots.repository;

import com.ntn.wedhotrots.pojo.Banner;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface BannerRepository extends JpaRepository<Banner,Integer> {
    @Query("SELECT b FROM Banner b WHERE b.active = 1 AND b.name LIKE %:search%")
    public List<Banner> findBanners(Pageable pageable, String search);
}
