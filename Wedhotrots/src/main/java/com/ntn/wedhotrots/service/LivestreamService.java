package com.ntn.wedhotrots.service;

import com.ntn.wedhotrots.pojo.Livestreams;

import java.util.List;
import java.util.Map;

public interface LivestreamService {
    Livestreams getLiveById(int id);
    List<Livestreams> getAlls(Map<String, String> params);

    boolean recycleBin(int id);
    boolean addLive(Livestreams l);
    boolean updateLive(int id,Livestreams l);


}
