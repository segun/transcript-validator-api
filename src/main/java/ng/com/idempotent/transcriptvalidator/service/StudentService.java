package ng.com.idempotent.transcriptvalidator.service;

import java.util.List;

import javax.ws.rs.NotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ng.com.idempotent.transcriptvalidator.exception.AlreadyExistsException;
import ng.com.idempotent.transcriptvalidator.models.Department;
import ng.com.idempotent.transcriptvalidator.models.Faculty;
import ng.com.idempotent.transcriptvalidator.models.School;
import ng.com.idempotent.transcriptvalidator.models.Student;
import ng.com.idempotent.transcriptvalidator.repository.DepartmentRepository;
import ng.com.idempotent.transcriptvalidator.repository.FacultyRepository;
import ng.com.idempotent.transcriptvalidator.repository.SchoolRepository;
import ng.com.idempotent.transcriptvalidator.repository.StudentRepository;
import ng.com.idempotent.transcriptvalidator.requestobjects.StudentRequestObject;

@Component
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    SchoolRepository schoolRepository;
    @Autowired
    FacultyRepository facultyRepository;

    Logger logger = LoggerFactory.getLogger(DepartmentService.class.getName());

    public Student saveStudent(StudentRequestObject sro) throws AlreadyExistsException {
        List<Student> exists = studentRepository.findByMatricNumber(sro.getMatricNumber());
        if(exists.isEmpty()) {
            logger.info("Student with matric number not found. Can add new student"); 
            Student student = new Student();
            Department department = departmentRepository.findById(sro.getDepartmentId()).orElseThrow(() -> new NotFoundException(String.format("Department with id %d not found", sro.getDepartmentId())));
            Faculty faculty = facultyRepository.findById(sro.getFacultyId()).orElseThrow(() -> new NotFoundException(String.format("Faculty with id %d not found", sro.getFacultyId())));
            School school = schoolRepository.findById(sro.getSchoolId()).orElseThrow(() -> new NotFoundException(String.format("School with id %d not found", sro.getSchoolId())));

            student.setDepartment(department);
            student.setGender(Student.Gender.valueOf(sro.getGender()));
            student.setGraduatingYear(sro.getGraduatingYear());
            student.setMatricNumber(sro.getMatricNumber());
            student.setSchool(school);
            student.setStudentName(sro.getStudentName());
            student.setYearOfAdmission(sro.getYearOfAdmission());
            student.setFaculty(faculty);
            return studentRepository.save(student);
        } else {
            throw new AlreadyExistsException(String.format("Student with matric number %s already exists", sro.getMatricNumber()));
        }
    }
}
