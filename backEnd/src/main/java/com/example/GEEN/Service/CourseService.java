package com.example.GEEN.Service;

import com.example.GEEN.Entity.Course;

import java.util.List;
import java.util.Optional;

/**
 * @author nyx
 * @title: CourseService
 * @projectName fitness-course-service
 * @description: TODO
 * @date 2021/5/517:08
 */
public interface CourseService {
    public List<Course> findByName(String name);
    public List<Course> findByCategory(String category);
    public Optional<Course> findByCourseId(Long id);
    public List<Course> findAllCourse();
    public List<Course> search(String name,String category);
}
