package ng.com.idempotent.transcriptvalidator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ng.com.idempotent.transcriptvalidator.models.Faculty;
import ng.com.idempotent.transcriptvalidator.models.School;
import ng.com.idempotent.transcriptvalidator.repository.custom.CustomFactoryRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Long>, CustomFactoryRepository {
    List<Faculty> findByFacultyCode(String facultyCode);
    List<Faculty> findByFacultyName(String facultyName);
    List<Faculty> findBySchool(School school);    
}