package com.ntn.wedhotrots.service;


import com.ntn.wedhotrots.pojo.Banner;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BannerService {
    List<Banner> getALls(Map<String, String> params);

    boolean updateBanner(int id, Map<String, String> params, MultipartFile file);

    boolean addBanner(Map<String,String> params, MultipartFile file);

    boolean RecycleBin(int id);
    Optional<Banner> getBannerById(int id);
}
