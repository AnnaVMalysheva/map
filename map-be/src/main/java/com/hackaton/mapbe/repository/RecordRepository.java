package com.hackaton.mapbe.repository;

import com.hackaton.mapbe.entity.Record;
import com.vividsolutions.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

    @Query(value = "select  * from record limit 100", nativeQuery = true)
    List<Record> findReports();
}
