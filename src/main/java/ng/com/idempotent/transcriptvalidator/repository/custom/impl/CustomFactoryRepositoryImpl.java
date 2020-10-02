package ng.com.idempotent.transcriptvalidator.repository.custom.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ng.com.idempotent.transcriptvalidator.models.Faculty;
import ng.com.idempotent.transcriptvalidator.repository.custom.CustomFactoryRepository;

public class CustomFactoryRepositoryImpl implements CustomFactoryRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Faculty findFaculty(long schoolId, String facultyCode, String facultyName) {
        String sql = "SELECT * FROM faculty f " + "INNER JOIN school s ON f.school_id = s.id "
                + "WHERE f.faculty_name = :fn " + "AND f.faculty_code = :fc " + "AND s.id = :sid";
        Query query = entityManager.createNativeQuery(sql, Faculty.class);
        query.setParameter("fc", facultyCode);
        query.setParameter("fn", facultyName);
        query.setParameter("sid", schoolId);
        return (Faculty) query.getSingleResult();
    }
}