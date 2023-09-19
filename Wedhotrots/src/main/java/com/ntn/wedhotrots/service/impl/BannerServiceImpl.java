package com.ntn.wedhotrots.service.impl;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ntn.wedhotrots.pojo.Banner;
import com.ntn.wedhotrots.repository.BannerRepository;
import com.ntn.wedhotrots.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Banner> getALls() {
        return (List<Banner>) bannerRepo.findBanners();
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
