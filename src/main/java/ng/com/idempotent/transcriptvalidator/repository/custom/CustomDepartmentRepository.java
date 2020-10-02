package ng.com.idempotent.transcriptvalidator.repository.custom;

import ng.com.idempotent.transcriptvalidator.models.Department;

public interface CustomDepartmentRepository {
    Department finDepartment(long facultyId, String departmentName, String departmentCode);
}
