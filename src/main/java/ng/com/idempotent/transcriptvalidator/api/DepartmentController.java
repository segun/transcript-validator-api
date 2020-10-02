package ng.com.idempotent.transcriptvalidator.api;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ng.com.idempotent.transcriptvalidator.exception.AlreadyExistsException;
import ng.com.idempotent.transcriptvalidator.models.Department;
import ng.com.idempotent.transcriptvalidator.repository.DepartmentRepository;
import ng.com.idempotent.transcriptvalidator.requestobjects.DepartmentRequestObject;
import ng.com.idempotent.transcriptvalidator.service.DepartmentService;

@RestController
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;
    @Autowired
    DepartmentRepository departmentRepository;

    @PostMapping(path = "/department", consumes = { MediaType.APPLICATION_JSON }, produces = {
            MediaType.APPLICATION_JSON })
    public ResponseEntity<Department> addNewDepartment(@RequestBody DepartmentRequestObject dro)
            throws AlreadyExistsException {
        return new ResponseEntity<>(departmentService.saveDepartment(dro), HttpStatus.CREATED);
    }

    @GetMapping(path = "/departments", produces = { MediaType.APPLICATION_JSON })
    public ResponseEntity<List<Department>> getDepartments() {
        return new ResponseEntity<>(departmentRepository.findAll(), HttpStatus.OK);
    }
}
