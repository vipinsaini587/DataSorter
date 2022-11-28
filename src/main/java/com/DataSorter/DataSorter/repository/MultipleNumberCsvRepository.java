package com.DataSorter.DataSorter.repository;

import com.DataSorter.DataSorter.entities.Cases;
import com.DataSorter.DataSorter.entities.MultipleNumberCsv;
import com.DataSorter.DataSorter.entities.SingleNumberCsv;
import com.DataSorter.DataSorter.entities.SingleNumberFiles;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MultipleNumberCsvRepository extends MongoRepository<MultipleNumberCsv,String> {

    @Query(value="{'case_number' : ?0}", delete = true)
    Long deleteByCaseNumber (String case_number);

    @Query("{'case_number' : ?0}")
    List<MultipleNumberCsv> findByCaseNumber (String case_number);

    @Query("{'case_number' : ?0,'landLine': ?1}")
    List<MultipleNumberCsv> findByLandline(String case_number, String landLine);

    @Query("{'session_start': {$gte: ?0, $lte:?1 },'case_number': ?2}")
    List<MultipleNumberCsv> sessionStart(String startDate, String endDate, String case_number);
}
