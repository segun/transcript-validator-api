package ng.com.idempotent.transcriptvalidator.repository.custom.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ng.com.idempotent.transcriptvalidator.models.Department;
import ng.com.idempotent.transcriptvalidator.repository.custom.CustomDepartmentRepository;

public class CustomDepartmentRepositoryImpl implements CustomDepartmentRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Department finDepartment(long facultyId, String departmentName, String departmentCode) {
        String sql = "SELECT * FROM department d " + "INNER JOIN faculty f ON d.faculty_id = f.id "
                + "WHERE d.department_name = :dn " + "AND d.department_code = :dc " + "AND f.id = :fid";
        Query query = entityManager.createNativeQuery(sql, Department.class);
        query.setParameter("dn", departmentName);
        query.setParameter("dc", departmentCode);
        query.setParameter("fid", facultyId);
        return (Department) query.getSingleResult();
    }
}
