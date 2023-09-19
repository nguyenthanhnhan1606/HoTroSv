package com.ntn.wedhotrots.service;

import com.ntn.wedhotrots.pojo.Departments;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DepartmentService {
    List<Departments> getAll();
    Optional<Departments> getDepById(int id);

    boolean updateDep(int id, Map<String, String> params, MultipartFile file);

    boolean addDep(Map<String, String> params, MultipartFile file);
}
