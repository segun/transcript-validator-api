package ng.com.idempotent.transcriptvalidator.repository.custom.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ng.com.idempotent.transcriptvalidator.models.Course;
import ng.com.idempotent.transcriptvalidator.repository.custom.CustomCourseRepository;
import ng.com.idempotent.transcriptvalidator.requestobjects.CourseRequestObject;

public class CustomCourseRepositoryImpl implements CustomCourseRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Course findStudentCourseForYear(CourseRequestObject cro) {
        String sql = "SELECT * FROM course c INNER JOIN student s on s.id = c.student_id "
                + "WHERE c.course_code = :cc AND c.year = :year AND c.semester = :sem AND c.student_id = :sid";

        Query query = entityManager.createNativeQuery(sql, Course.class);
        query.setParameter("cc", cro.getCourseCode());
        query.setParameter("year", cro.getYear());
        query.setParameter("sem", cro.getSemester());
        query.setParameter("sid", cro.getStudentId());
        return (Course) query.getSingleResult();
    }

    @Override
    public List<Course> findCoursesByStudentId(long studentId) {
        String sql = "SELECT * from course c INNER JOIN student s on s.id = c.student_id "
            + "WHERE c.student_id = :sid";
        Query query = entityManager.createNativeQuery(sql, Course.class);
        query.setParameter("sid", studentId);
        return (List<Course>) query.getResultList();        
    }
}