package com.example.GEEN.Repository;

import com.example.GEEN.Entity.TrainingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TrainingRecordRepository extends JpaRepository<TrainingRecord,Long> {
    public List<TrainingRecord> findByAccountNameAndCategoryOrderByDateDesc(String accountName,String category);
    public List<TrainingRecord> findByAccountNameAndDate(String accountName,Date date);
}
