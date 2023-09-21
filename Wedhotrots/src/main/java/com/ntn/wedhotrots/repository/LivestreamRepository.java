package com.ntn.wedhotrots.repository;

import com.ntn.wedhotrots.pojo.Livestreams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivestreamRepository extends JpaRepository<Livestreams,Integer> {
}
