package ng.com.idempotent.transcriptvalidator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ng.com.idempotent.transcriptvalidator.models.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByMatricNumber(String matricNumber);
}
