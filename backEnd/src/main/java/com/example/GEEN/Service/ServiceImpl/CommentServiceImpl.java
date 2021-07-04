package com.example.GEEN.Service.ServiceImpl;

import com.example.GEEN.Entity.Comment;
import com.example.GEEN.Repository.CommentRepository;
import com.example.GEEN.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author nyx
 * @title: CommentServiceImpl
 * @projectName fitness-course-service
 * @description: TODO
 * @date 2021/5/1718:53
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Override
    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Page<Comment> findComment(Long courseId, Pageable page) {
        return commentRepository.findCommentByCourseIdOrderByTimeDesc(courseId,page);
    }
}
