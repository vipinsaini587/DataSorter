package com.DataSorter.DataSorter.repository;

import com.DataSorter.DataSorter.entities.Cases;
import com.DataSorter.DataSorter.entities.SingleNumberCsv;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SingleNumberCsvRepository extends MongoRepository<SingleNumberCsv,String> {
    @Query(value = "{'Case_number' : ?0}", delete = true)
    Long deleteByCaseNumber(String Case_number);

    @Query("{'case_number':?0}")
    List<SingleNumberCsv> singleCaseData(String case_number);

    @Query("{ 'case_number': ?0, 'sourceIP': ?1}")
    List<SingleNumberCsv> findBySourceIP(String case_number, String sourceIP);

    @Query("{'startDateIpAllocation': {$gte: ?0, $lte:?1 },'case_number': ?2}")
    List<SingleNumberCsv> startDateIpAllocation(String startDate, String endDate, String case_number);
}
