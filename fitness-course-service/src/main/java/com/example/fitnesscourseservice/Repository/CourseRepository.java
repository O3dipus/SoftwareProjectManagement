package com.example.fitnesscourseservice.Repository;

import com.example.fitnesscourseservice.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author nyx
 * @title: CourseRepository
 * @projectName fitness-course-service
 * @description: TODO
 * @date 2021/5/517:07
 */
@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    public List<Course> findCourseByCategory(String category);
    public List<Course> findCourseByName(String name);
}
