package com.ntn.wedhotrots.repository;

import com.ntn.wedhotrots.pojo.Departments;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Departments, Integer> {
    @Query("SELECT d FROM Departments d WHERE d.active = 1 AND d.tenkhoa LIKE %:search%")
    public List<Departments> findAlls(Pageable pageable, String search);
}
