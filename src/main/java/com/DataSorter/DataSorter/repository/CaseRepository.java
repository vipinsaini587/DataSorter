package com.DataSorter.DataSorter.repository;

import com.DataSorter.DataSorter.entities.Cases;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

public interface CaseRepository extends MongoRepository<Cases, String> {
    @Query("{'case_number':?0}")
    Cases findByCaseNumber(String case_number);

    @Query(value="{'case_number' : ?0}", delete = true)
    String deleteByCaseNumber (String case_number);

}
