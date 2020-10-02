package ng.com.idempotent.transcriptvalidator.service;

import javax.ws.rs.NotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ng.com.idempotent.transcriptvalidator.exception.AlreadyExistsException;
import ng.com.idempotent.transcriptvalidator.models.Department;
import ng.com.idempotent.transcriptvalidator.models.Faculty;
import ng.com.idempotent.transcriptvalidator.repository.DepartmentRepository;
import ng.com.idempotent.transcriptvalidator.repository.FacultyRepository;
import ng.com.idempotent.transcriptvalidator.requestobjects.DepartmentRequestObject;

@Component
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    FacultyRepository facultyRepository;

    Logger logger = LoggerFactory.getLogger(DepartmentService.class.getName());

    public Department saveDepartment(DepartmentRequestObject dro) throws AlreadyExistsException {
        Department exists = null;
        try {
            exists = departmentRepository.finDepartment(dro.getFacultyId(), dro.getDepartmentName(), dro.getDepartmentCode());
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        if(exists == null) {
            logger.info("Department doesn't exist, adding new department");
            Faculty faculty = facultyRepository.findById(dro.getFacultyId()).orElseThrow(() -> new NotFoundException(String.format("Faculty with ID : %d can not be found", dro.getFacultyId())));
            Department department = new Department();
            department.setFaculty(faculty);
            department.setDepartmentCode(dro.getDepartmentCode());
            department.setDepartmentName(dro.getDepartmentName());

            return departmentRepository.save(department);
        } else {
            logger.info("Department exists, throwing AlreadyExistsException");
            throw new AlreadyExistsException(String.format("Department with name %s and/or code %s already exists", dro.getDepartmentName(), dro.getDepartmentCode()));
        }
    }
}
