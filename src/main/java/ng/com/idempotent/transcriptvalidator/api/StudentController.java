package ng.com.idempotent.transcriptvalidator.api;

import java.util.List;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ng.com.idempotent.transcriptvalidator.exception.AlreadyExistsException;
import ng.com.idempotent.transcriptvalidator.models.Student;
import ng.com.idempotent.transcriptvalidator.repository.StudentRepository;
import ng.com.idempotent.transcriptvalidator.requestobjects.StudentRequestObject;
import ng.com.idempotent.transcriptvalidator.service.StudentService;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    StudentRepository studentRepository;

    @PostMapping(path = "/student", consumes = { MediaType.APPLICATION_JSON }, produces = {
            MediaType.APPLICATION_JSON })
    public ResponseEntity<Student> addNewStudent(@RequestBody StudentRequestObject sro) throws AlreadyExistsException {
        return new ResponseEntity<>(studentService.saveStudent(sro), HttpStatus.CREATED);
    }

    @GetMapping(path = "/students", produces = { MediaType.APPLICATION_JSON })
    public ResponseEntity<List<Student>> getStudents() {
        return new ResponseEntity<>(studentRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/student/{id}", produces = { MediaType.APPLICATION_JSON })
    public ResponseEntity<Student> getStudent(@PathVariable("id") Long id) {
        return new ResponseEntity<>(studentRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Student with id: %d not found", id))), HttpStatus.OK);
    }
}
