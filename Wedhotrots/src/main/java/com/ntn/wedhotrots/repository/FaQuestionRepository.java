package com.ntn.wedhotrots.repository;


import com.ntn.wedhotrots.pojo.Faquestion;
import com.ntn.wedhotrots.pojo.Questions;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaQuestionRepository extends JpaRepository<Faquestion,Integer> {
    @Query("SELECT f FROM Faquestion f WHERE f.question LIKE %:search% ")
    List<Faquestion> getAlls(Pageable pageable, String search);


}
