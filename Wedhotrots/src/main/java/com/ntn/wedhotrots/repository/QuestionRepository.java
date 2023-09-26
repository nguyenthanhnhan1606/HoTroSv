package com.ntn.wedhotrots.repository;

import com.ntn.wedhotrots.pojo.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Questions,Integer> {
    @Query("SELECT q FROM Questions q WHERE q.idLive.id=:id")
    List<Questions> getQuestionsByIdLive(int id);
}
