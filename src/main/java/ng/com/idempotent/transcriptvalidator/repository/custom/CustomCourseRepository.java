package ng.com.idempotent.transcriptvalidator.repository.custom;

import java.util.List;

import ng.com.idempotent.transcriptvalidator.models.Course;
import ng.com.idempotent.transcriptvalidator.requestobjects.CourseRequestObject;

public interface CustomCourseRepository {
    Course findStudentCourseForYear(CourseRequestObject cro);
    List<Course> findCoursesByStudentId(long studentId);
}
