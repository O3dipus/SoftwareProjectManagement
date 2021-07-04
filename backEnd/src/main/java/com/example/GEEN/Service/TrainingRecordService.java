package com.example.GEEN.Service;

import com.example.GEEN.Entity.TrainingRecord;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TrainingRecordService {
    public List<TrainingRecord> findTrainingRecordByAccountName(String accountName,String category);
    public void addTrainingRecord(TrainingRecord trainingRecord);
    public Optional<TrainingRecord> findTrainingRecordById(Long id);
    public List<TrainingRecord> findTrainingRecordByAccountNameAndTime(String accountName, Date date);
    public void deleteTrainingPlan(Long id);
}
