package com.example.GEEN.Repository;

import com.example.GEEN.Entity.Course;
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
    public List<Course> findCourseByNameLikeOrCategoryLike(String name,String category);
}
