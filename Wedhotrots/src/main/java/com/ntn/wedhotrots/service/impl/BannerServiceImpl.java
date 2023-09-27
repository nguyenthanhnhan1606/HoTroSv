package com.ntn.wedhotrots.service.impl;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ntn.wedhotrots.pojo.Banner;
import com.ntn.wedhotrots.repository.BannerRepository;
import com.ntn.wedhotrots.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerRepository bannerRepo;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Banner> getALls(Map<String,String> params) {
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
            return bannerRepo.findBanners(pageable, search);
        }
        return bannerRepo.findBanners(pageable, "");
    }

    @Override
    public boolean updateBanner(int id, Map<String, String> params, MultipartFile file) {
        Optional<Banner> bannerOptional = bannerRepo.findById(id);

        if (bannerOptional.isPresent()) {
            Banner banner = bannerOptional.get();
            banner.setName(params.get("name"));
            banner.setDescription(params.get("description"));

            if (file != null && !file.isEmpty()) {
                try {
                    Map res = this.cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                    banner.setImage(res.get("secure_url").toString());
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }

            bannerRepo.save
                    (banner);

            return true;
        }

        return false;
    }

    @Override
    public boolean addBanner(Map<String, String> params, MultipartFile file) {
        try{
            Banner banner = new Banner();
            banner.setName(params.get("name"));
            banner.setActive((byte) 1);
            banner.setDescription(params.get("description"));
            if (file != null && !file.isEmpty()) {
                try {
                    String url = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
                    banner.setImage(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            bannerRepo.save(banner);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
       return false;
    }

    @Override
    public boolean RecycleBin(int id) {
        Optional<Banner> bn = bannerRepo.findById(id);
        if (bn.isPresent()) {
            Banner banner = bn.get();
            banner.setActive((byte) 0);
            try {
                bannerRepo.save(banner);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }


    @Override
    public Optional<Banner> getBannerById(int id) {
        return bannerRepo.findById(id);
    }
}
