package com.example.fitnesscourseservice.Service.ServiceImpl;

import com.example.fitnesscourseservice.Entity.Course;
import com.example.fitnesscourseservice.Repository.CourseRepository;
import com.example.fitnesscourseservice.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author nyx
 * @title: CourseServiceImpl
 * @projectName fitness-course-service
 * @description: TODO
 * @date 2021/5/517:10
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> findByName(String name) {
        return courseRepository.findCourseByName(name);
    }

    @Override
    public List<Course> findByCategory(String category) {
        return courseRepository.findCourseByCategory(category);
    }

    @Override
    public Optional<Course> findByCourseId(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public List<Course> findAllCourse() {
        return courseRepository.findAll();
    }
}
