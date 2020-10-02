package ng.com.idempotent.transcriptvalidator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ng.com.idempotent.transcriptvalidator.models.Department;
import ng.com.idempotent.transcriptvalidator.repository.custom.CustomDepartmentRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long>, CustomDepartmentRepository {    
       List<Department> findByDepartmentName(String departmentName);
       List<Department> findByDepartmentCode(String departmentCode);
}
