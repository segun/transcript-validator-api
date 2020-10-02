package ng.com.idempotent.transcriptvalidator.service;

import java.util.List;

import javax.ws.rs.NotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ng.com.idempotent.transcriptvalidator.exception.AlreadyExistsException;
import ng.com.idempotent.transcriptvalidator.models.Course;
import ng.com.idempotent.transcriptvalidator.models.Student;
import ng.com.idempotent.transcriptvalidator.repository.CourseRepository;
import ng.com.idempotent.transcriptvalidator.repository.StudentRepository;
import ng.com.idempotent.transcriptvalidator.requestobjects.CourseRequestObject;

@Component
public class CourseService {
    Logger logger = LoggerFactory.getLogger(DepartmentService.class.getName());

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    StudentRepository studentRepository;

    public Course saveCourse(CourseRequestObject cro) throws AlreadyExistsException {
        Course exists = null;
        try {
            exists = courseRepository.findStudentCourseForYear(cro);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        if(exists == null) {
            Student student = studentRepository.findById(cro.getStudentId()).orElseThrow(() -> new NotFoundException(String.format("Student with id %d does not exist", cro.getStudentId())));
            Course course = new Course();
            course.setCourseCode(cro.getCourseCode());        
            course.setCourseName(cro.getCourseName());
            course.setCreditUnit(cro.getCreditUnit());
            course.setCummulativePoint(cro.getCummulativePoint());
            course.setGrade(cro.getGrade());
            course.setGradePoint(cro.getGradePoint());
            course.setGradePointAverage(cro.getGradePointAverage());
            course.setScore(cro.getScore());
            course.setSemester(cro.getSemester());
            course.setStatus(cro.getStatus());
            course.setStudent(student);
            course.setYear(cro.getYear());

            Course saved = courseRepository.save(course);
            return saved;
        } else {
            logger.info("Course exists, throwing AlreadyExistsException");
            throw new AlreadyExistsException("Course Already Exists");
        }    
    }
}
