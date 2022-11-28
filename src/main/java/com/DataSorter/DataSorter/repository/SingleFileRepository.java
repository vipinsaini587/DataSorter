package com.DataSorter.DataSorter.repository;

import com.DataSorter.DataSorter.entities.Cases;
import com.DataSorter.DataSorter.entities.SingleNumberCsv;
import com.DataSorter.DataSorter.entities.SingleNumberFiles;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SingleFileRepository extends MongoRepository<SingleNumberFiles, String> {

//    sort by source ip and case number

    @Query("{'case_number':?0}")
    List<SingleNumberFiles> findBy(String case_number);

//     delete by case number

    @Query(value="{'Case_number' : ?0}", delete = true)
    Long deleteByCaseNumber (String Case_number);

//    update case by case number (case id)

}
