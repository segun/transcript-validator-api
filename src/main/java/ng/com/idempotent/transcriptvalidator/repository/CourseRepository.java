package ng.com.idempotent.transcriptvalidator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ng.com.idempotent.transcriptvalidator.models.Course;
import ng.com.idempotent.transcriptvalidator.repository.custom.CustomCourseRepository;

public interface CourseRepository extends JpaRepository<Course, Long>, CustomCourseRepository {
    
}