package com.ntn.wedhotrots.repository;

import com.ntn.wedhotrots.pojo.Livestreams;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivestreamRepository extends JpaRepository<Livestreams,Integer> {

    @Query("SELECT l FROM Livestreams l WHERE l.active=1 AND l.title LIKE %:search%")
    List<Livestreams> findAll(Pageable pageable,String search);
}
