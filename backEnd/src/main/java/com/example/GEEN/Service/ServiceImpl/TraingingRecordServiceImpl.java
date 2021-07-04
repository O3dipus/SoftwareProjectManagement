package com.example.GEEN.Service.ServiceImpl;

import com.example.GEEN.Entity.TrainingRecord;
import com.example.GEEN.Repository.TrainingPlanRepository;
import com.example.GEEN.Repository.TrainingRecordRepository;
import com.example.GEEN.Service.TrainingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class TraingingRecordServiceImpl implements TrainingRecordService {
    @Autowired
    private TrainingRecordRepository trainingRecordRepository;
    @Override
    public List<TrainingRecord> findTrainingRecordByAccountName(String accountName,String category) {
        return trainingRecordRepository.findByAccountNameAndCategoryOrderByDateDesc(accountName,category);
    }

    @Override
    public void addTrainingRecord(TrainingRecord trainingRecord) {
        trainingRecordRepository.save(trainingRecord);
    }

    @Override
    public Optional<TrainingRecord> findTrainingRecordById(Long id) {
        return trainingRecordRepository.findById(id);
    }

    @Override
    public List<TrainingRecord> findTrainingRecordByAccountNameAndTime(String accountName, Date date) {
        return trainingRecordRepository.findByAccountNameAndDate(accountName,date);
    }

    @Override
    public void deleteTrainingPlan(Long id) {
        trainingRecordRepository.deleteById(id);
    }

}
