package com.example.fitnesscourseservice.Controller;

import com.example.fitnesscourseservice.Entity.Course;
import com.example.fitnesscourseservice.Service.CourseService;
import com.example.fitnesscourseservice.Service.NonStaticResourceHttpRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
public class CourseController {
    @Autowired
    private CourseService courseService;

    private final NonStaticResourceHttpRequestHandler nonStaticResourceHttpRequestHandler;

    public CourseController(NonStaticResourceHttpRequestHandler nonStaticResourceHttpRequestHandler) {
        this.nonStaticResourceHttpRequestHandler = nonStaticResourceHttpRequestHandler;
    }
    @GetMapping("/course/video")
    public void getVideo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sourcePath = ClassUtils.getDefaultClassLoader().getResource("").getPath().substring(1);//获取resources文件夹的绝对地址
        String id = request.getParameter("id");
        String realPath = sourcePath +"static/video/"+id+".mp4";
        System.out.println(realPath);
        Path filePath = Paths.get(realPath);
        if(Files.exists(filePath)) {
            String mimeType = Files.probeContentType(filePath);
            if(!StringUtils.isEmpty(mimeType)) {
                response.setContentType(mimeType);

            }
            request.setAttribute(NonStaticResourceHttpRequestHandler.ATTR_FILE,filePath);
            nonStaticResourceHttpRequestHandler.handleRequest(request,response);

        }
        else {

            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        }
    }


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
}
