package com.ntn.wedhotrots.service.impl;


import com.ntn.wedhotrots.pojo.Livestreams;
import com.ntn.wedhotrots.repository.LivestreamRepository;
import com.ntn.wedhotrots.service.LivestreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LivestreamServiceImpl implements LivestreamService {
    @Autowired
    private LivestreamRepository liveRepo;


    @Override
    public Livestreams getLiveById(int id) {
        Optional<Livestreams> l = liveRepo.findById(id);
        if(l.isPresent())
            return l.get();
        return null;
    }

    @Override
    public List<Livestreams> getAlls(Map<String,String> params) {
        Pageable pageable = Pageable.unpaged(); // Default to unpaged
        String search="";

        if (params != null) {
            String pageStr = params.get("page");
            if (pageStr != null && !pageStr.isEmpty()) {
                int page = Integer.parseInt(pageStr);
                page -=1;
                pageable = PageRequest.of(page, 3, Sort.by("id").descending());
            }
            search = params.get("search");
        }
        if (search != null && !search.isEmpty()) {
            return liveRepo.findAll(pageable, search);
        }
        return liveRepo.findAll(pageable, "");
    }

    @Override
    public boolean recycleBin(int id) {
        try{
            Optional<Livestreams> l = liveRepo.findById(id);
            if(!l.isPresent())
                return false;
            Livestreams live = l.get();
            live.setActive((byte)0);
            liveRepo.save(live);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addLive(Livestreams l) {
        try{
            l.setActive((byte)1);
            liveRepo.save(l);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateLive(int id,Livestreams l) {
        try{
            Optional<Livestreams> l1 = liveRepo.findById(id);
            if (!l1.isPresent())
                return false;
            Livestreams live = l1.get();
            live.setNgay(l.getNgay());
            live.setTitle(l.getTitle());
            live.setThoigian(l.getThoigian());
            live.setPath(l.getPath());
            live.setDescription(l.getDescription());
            liveRepo.save(live);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
}
