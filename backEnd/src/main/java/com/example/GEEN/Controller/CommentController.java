package com.example.GEEN.Controller;

import com.example.GEEN.Entity.Comment;
import com.example.GEEN.Service.CommentService;
import com.example.GEEN.Util.WordFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * @author nyx
 * @title: CommentController
 * @projectName fitness-course-service
 * @description: TODO
 * @date 2021/5/1718:55
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping("/course/comment")
    public Stream<Comment> findComment(@RequestParam("courseId") Long courseId, @RequestParam("page") Integer page) {
        Pageable pageable = PageRequest.of(page,10);   // page 从 0 开始 ，10 是指每个page的大小，这个意思是按照订单日期排序分页查询，每次查询三个数据
        return commentService.findComment(courseId,pageable).get();
    }
    @RequestMapping("/course/addComment")
    public void addComment(@RequestParam("accountName") String accountName,
                                    @RequestParam("courseId") Long courseId,
                                    @RequestParam("content") String content
                                    ) throws IOException {

        String goodComment = WordFilter.replaceWords(content);
        Comment comment = new Comment(accountName,courseId,goodComment);
        commentService.addComment(comment);


    }
    @RequestMapping("/course/deleteComment/{id}")
    public void deleteComment(@PathVariable("id") Long id) {
        commentService.deleteComment(id);
    }
}
