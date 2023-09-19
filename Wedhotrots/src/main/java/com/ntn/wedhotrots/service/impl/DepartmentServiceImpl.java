package com.ntn.wedhotrots.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ntn.wedhotrots.pojo.Banner;
import com.ntn.wedhotrots.pojo.Departments;
import com.ntn.wedhotrots.repository.DepartmentRepository;
import com.ntn.wedhotrots.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepo;
    @Autowired
    private Cloudinary cloudinary;


    @Override
    public List<Departments> getAll() {
        return departmentRepo.findAlls();
    }

    @Override
    public Optional<Departments> getDepById(int id) {
        return departmentRepo.findById(id);
    }

    @Override
    public boolean updateDep(int id, Map<String, String> params, MultipartFile file) {
        Optional<Departments> dOptional = departmentRepo.findById(id);
        if (dOptional.isPresent()) {
            try {
                Departments d = dOptional.get();
                d.setActive((byte) 1);
                d.setMota(params.get("mota"));
                d.setMotaCTDT(params.get("motaCTDT"));
                d.setTrungbinhdiem(Double.parseDouble(params.get("trungbinhdiem")));
                d.setTenkhoa(params.get("tenkhoa"));
                d.setWebsite(params.get("website"));
                if (file != null && !file.isEmpty()) {
                    try {
                        String url = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
                        d.setVideo(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return false;
                    }
                }
                departmentRepo.save(d);
                return true;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean addDep(Map<String, String> params, MultipartFile file) {
        try {
            Departments d = new Departments();
            d.setActive((byte) 1);
            d.setMota(params.get("mota"));
            d.setMotaCTDT(params.get("motaCTDT"));
            d.setTrungbinhdiem(Double.parseDouble(params.get("trungbinhdiem")));
            d.setTenkhoa(params.get("tenkhoa"));
            d.setWebsite(params.get("website"));
            if (file != null && !file.isEmpty()) {
                try {
                    Map res = this.cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                    d.setVideo(res.get("secure_url").toString());
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            departmentRepo.save(d);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
