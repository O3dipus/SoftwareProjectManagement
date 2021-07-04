package com.example.GEEN.Service;

import com.example.GEEN.Entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author nyx
 * @title: CommentService
 * @projectName fitness-course-service
 * @description: TODO
 * @date 2021/5/1718:51
 */
public interface CommentService {
    public void addComment(Comment comment);
    public void deleteComment(Long id);
    public Page<Comment> findComment(Long courseId, Pageable page);
}
