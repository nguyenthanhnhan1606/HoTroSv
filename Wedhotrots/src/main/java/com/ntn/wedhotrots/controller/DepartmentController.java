package com.ntn.wedhotrots.controller;

import com.ntn.wedhotrots.pojo.Departments;
import com.ntn.wedhotrots.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentSer;

    @GetMapping("/department")
    @CrossOrigin
    public List<Departments> getAlls(){
        return  departmentSer.getAll();
    }

    @GetMapping("/department/{id}")
    @CrossOrigin
    public Optional<Departments> getDepById(@PathVariable int id){
        return  departmentSer.getDepById(id);
    }

    @PostMapping("/department")
    @CrossOrigin
    public boolean addDep( @RequestParam Map<String,String> params, @RequestParam(required = false) MultipartFile file){
        return departmentSer.addDep(params,file);
    }

    @PutMapping("/department/update/{id}")
    @CrossOrigin
    public boolean updateDep(@PathVariable int id, @RequestParam Map<String,String> params, @RequestParam(required = false)MultipartFile file){
        return departmentSer.updateDep(id,params,file);
    }


}
