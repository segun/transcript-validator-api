package ng.com.idempotent.transcriptvalidator.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ng.com.idempotent.transcriptvalidator.models.Faculty;

public class CustomFactoryRepositoryImpl implements CustomFactoryRepository {
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public Faculty findFaculty(long schoolId, String facultyCode, String facultyName) {
        String hqlString = "FROM faculty f INNER JOIN school s " 
            + "WHERE f.schoolId = s.id AND "            
            + "f.facultyName = :fn AND "
            + "f.facultyCode = :fc AND "
            + "s.schoolId = :sid";
        Query query = entityManager.createQuery(hqlString, Faculty.class);
        query.setParameter("fc", facultyCode);
        query.setParameter("fn", facultyName);
        query.setParameter("sid", schoolId);
        return (Faculty) query.getSingleResult();
    }
}