package com.example.GEEN.Entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @author nyx
 * @title: Comment
 * @projectName fitness-course-service
 * @description: TODO
 * @date 2021/5/1718:44
 */
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String accountName;
    public Long courseId;
    public String content;
    @CreationTimestamp
    public Date time;

    public Comment() {

    }
    public Comment(String accountName,Long courseId,String content) {
        this.accountName=accountName;
        this.courseId=courseId;
        this.content=content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public Long getId() {
        return id;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Long getCourseId() {
        return courseId;
    }

    public Date getTime() {
        return time;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
