package com.example.GEEN.Repository;

import com.example.GEEN.Entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author hello
 * @title: CommentRepository
 * @projectName fitness-course-service
 * @description: TODO
 * @date 2021/5/1718:49
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    public Page<Comment> findCommentByCourseIdOrderByTimeDesc(Long courseId, Pageable pageable);
}
