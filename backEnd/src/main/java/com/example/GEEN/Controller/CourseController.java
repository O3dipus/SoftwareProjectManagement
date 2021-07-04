package com.example.GEEN.Controller;

import com.example.GEEN.Entity.Course;
import com.example.GEEN.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

/**
 * @author nyx
 * @title: CourseController
 * @projectName fitness-course-service
 * @description:
 * @date 2021/5/517:12
 */
@RestController
@RequestMapping("/api/v1/")
@CrossOrigin
public class CourseController {
    @Autowired
    private CourseService courseService;

    @RequestMapping("/course/{id}")
    public Optional<Course> getById(@PathVariable("id") Long id) {
        return courseService.findByCourseId(id);
    }
    @RequestMapping("/course")
    public List<Course> getCourse(@RequestParam(value = "name",required = false) String name,
                                  @RequestParam(value = "category",required = false)String category) {
        if(name==null&&category==null) {
            return courseService.findAllCourse();
        }
        else if(name==null) {
            return courseService.findByCategory(category);
        }
        else if(category==null) {
            return courseService.findByName(name);

        }
        else {
            return null;
        }
    }
    @RequestMapping("/course/search")
    public List<Course> fuzzySearch(@RequestParam(value = "param") String param) {
        return courseService.search("%"+param+"%","%"+param+"%");
    }
}
