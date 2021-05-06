package com.example.fitnesscourseservice.Entity;

import javax.persistence.*;

/**
 * @author nyx
 * @title: Course
 * @projectName fitness-course-service
 * @description: TODO
 * @date 2021/5/517:02
 */
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String name;
    public String content;
    public String category;

    public Course() {

    }
    public Course(String name,String content,String category) {
        this.name=name;
        this.content=content;
        this.category=category;
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public String getCategory() {
        return category;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setName(String name) {
        this.name = name;
    }
}
