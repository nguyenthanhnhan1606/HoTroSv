package com.ntn.wedhotrots.repository;

import com.ntn.wedhotrots.pojo.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Integer> {

    @Query("SELECT c FROM Comments c WHERE c.idNews.id = :id ORDER BY c.id DESC ")
    List<Comments> getCommentsByIdNews(int id);
}
