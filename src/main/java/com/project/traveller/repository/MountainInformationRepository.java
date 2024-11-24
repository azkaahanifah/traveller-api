package com.project.traveller.repository;

import com.project.traveller.entity.MountainInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MountainInformationRepository extends JpaRepository<MountainInformationEntity, Long> {

    String FIND_BY_NAME = "SELECT * FROM mountain WHERE name = :mountainName";

    @Query(value = FIND_BY_NAME, nativeQuery = true)
    MountainInformationEntity findByName(@Param("mountainName") String name);
}
