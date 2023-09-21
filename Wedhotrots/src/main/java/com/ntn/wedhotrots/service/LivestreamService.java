package com.ntn.wedhotrots.service;

import com.ntn.wedhotrots.pojo.Livestreams;

import java.util.List;
import java.util.Map;

public interface LivestreamService {
    List<Livestreams> getAlls();
    boolean addLive(Map<String, String> params);
}
