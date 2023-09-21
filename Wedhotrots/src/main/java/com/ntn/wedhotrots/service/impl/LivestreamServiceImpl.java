package com.ntn.wedhotrots.service.impl;

import com.cloudinary.utils.ObjectUtils;
import com.ntn.wedhotrots.pojo.Banner;
import com.ntn.wedhotrots.pojo.Livestreams;
import com.ntn.wedhotrots.repository.LivestreamRepository;
import com.ntn.wedhotrots.service.LivestreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class LivestreamServiceImpl implements LivestreamService {
    @Autowired
    private LivestreamRepository liveRepo;


    @Override
    public List<Livestreams> getAlls() {
        return liveRepo.findAll();
    }

    @Override
    public boolean addLive(Map<String, String> params) {
        try{
            Livestreams livestreams = new Livestreams();
            livestreams.setActive((byte)1);
            livestreams.setTitle(params.get("title"));
            livestreams.setDescription(params.get("description"));
//            livestreams.setNgay(LocalDate.now());
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
}
