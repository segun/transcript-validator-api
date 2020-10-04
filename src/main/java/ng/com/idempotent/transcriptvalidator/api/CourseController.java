package ng.com.idempotent.transcriptvalidator.api;

import java.util.List;

import javax.print.attribute.standard.Media;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ng.com.idempotent.transcriptvalidator.exception.AlreadyExistsException;
import ng.com.idempotent.transcriptvalidator.models.Course;
import ng.com.idempotent.transcriptvalidator.repository.CourseRepository;
import ng.com.idempotent.transcriptvalidator.requestobjects.CourseRequestObject;
import ng.com.idempotent.transcriptvalidator.service.CourseService;

@RestController
public class CourseController {
    @Autowired
    CourseService courseService;

    @Autowired
    CourseRepository courseRepository;

    @PostMapping(path = "/course", consumes = { MediaType.APPLICATION_JSON }, produces = { MediaType.APPLICATION_JSON })
    public ResponseEntity<Course> addNewCourse(@RequestBody CourseRequestObject cro) throws AlreadyExistsException {
        return new ResponseEntity<>(courseService.saveCourse(cro), HttpStatus.CREATED);
    }

    @GetMapping(path = "/courses", produces = { MediaType.APPLICATION_JSON })
    public ResponseEntity<List<Course>> getCourses() {
        return new ResponseEntity<>(courseRepository.findAll(), HttpStatus.OK);       
    }

    @GetMapping(path = "/courses/{studentId}", produces = { MediaType.APPLICATION_JSON })
    public ResponseEntity<List<Course>> getStudentCourses(@PathVariable("studentId") long studentId) {
        return new ResponseEntity<>(courseRepository.findCoursesByStudentId(studentId), HttpStatus.OK);
    }
}
