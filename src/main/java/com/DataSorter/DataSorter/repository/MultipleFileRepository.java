package com.DataSorter.DataSorter.repository;

import com.DataSorter.DataSorter.entities.MultipleNumberCsv;
import com.DataSorter.DataSorter.entities.MultipleNumberFiles;
import com.DataSorter.DataSorter.entities.SingleNumberFiles;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    public interface MultipleFileRepository extends MongoRepository<MultipleNumberFiles, String> {

    @Query("{'case_number':?0}")
    List<MultipleNumberFiles> findBy(String case_number);

    @Query(value="{'Case_number' : ?0}", delete = true)
    Long deleteByCaseNumber (String Case_number);
    }

