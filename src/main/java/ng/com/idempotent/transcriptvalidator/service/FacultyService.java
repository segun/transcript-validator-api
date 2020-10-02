package ng.com.idempotent.transcriptvalidator.service;

import javax.ws.rs.NotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ng.com.idempotent.transcriptvalidator.exception.AlreadyExistsException;
import ng.com.idempotent.transcriptvalidator.models.Faculty;
import ng.com.idempotent.transcriptvalidator.models.School;
import ng.com.idempotent.transcriptvalidator.repository.FacultyRepository;
import ng.com.idempotent.transcriptvalidator.repository.SchoolRepository;
import ng.com.idempotent.transcriptvalidator.requestobjects.FacultyRequestObject;

@Component
public class FacultyService {
    @Autowired
    FacultyRepository repository;
    @Autowired
    SchoolRepository schoolRepository;

    Logger logger = LoggerFactory.getLogger(FacultyService.class.getName());

    public Faculty saveFaculty(FacultyRequestObject fro) throws AlreadyExistsException {
        Faculty exists = null;
        try {
            exists = getFaculty(fro.getSchoolId(), fro.getFacultyName(), fro.getFacultyCode());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        if(exists == null) {
            School school = schoolRepository.findById(fro.getSchoolId()).orElseThrow(() -> new NotFoundException(String.format("School with ID : %d can not be found", fro.getSchoolId())));
            Faculty faculty = new Faculty();
            faculty.setFacultyCode(fro.getFacultyCode());
            faculty.setFacultyName(fro.getFacultyName());
            faculty.setSchool(school);

            return repository.save(faculty);
        } else {
            throw new AlreadyExistsException(String.format("Faculty with name %s and/or code %s already exists", fro.getFacultyName(), fro.getFacultyCode()));
        }
    }

    public Faculty getFaculty(long schoolId, String facultyName, String facultyCode) throws Exception {
        try {
            return repository.findFaculty(schoolId, facultyCode, facultyName);
        } catch (Exception e) {
            throw new Exception(
                    String.format("Can not find faculty with name %s and code %s", facultyName, facultyCode));
        }
    }
}