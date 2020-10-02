package ng.com.idempotent.transcriptvalidator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ng.com.idempotent.transcriptvalidator.models.School;

public interface SchoolRepository extends JpaRepository<School, Long> {
    List<School> findBySchoolName(String schoolName);
    List<School> findBySchoolCode(String schoolCode);    
}