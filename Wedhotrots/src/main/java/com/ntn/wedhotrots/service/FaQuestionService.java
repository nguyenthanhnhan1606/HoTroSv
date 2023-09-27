package com.ntn.wedhotrots.service;


import com.ntn.wedhotrots.pojo.Faquestion;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface FaQuestionService {
    Optional<Faquestion> getFAQByiD(int id);
    List<Faquestion> getAlls(Map<String, String> params);
    boolean addFAQ(Faquestion f);
    boolean updateFAQ(int id,Faquestion f);

    void deleteFAQ(int id);
}
