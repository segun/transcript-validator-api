package ng.com.idempotent.transcriptvalidator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ng.com.idempotent.transcriptvalidator.exception.AlreadyExistsException;
import ng.com.idempotent.transcriptvalidator.models.School;
import ng.com.idempotent.transcriptvalidator.repository.SchoolRepository;
import ng.com.idempotent.transcriptvalidator.requestobjects.SchoolRequestObject;

@Component
public class SchoolService {
    @Autowired
    private SchoolRepository schoolRepository;

    public School saveSchool(SchoolRequestObject sro) throws AlreadyExistsException {
        School school = new School();
        school.setSchoolCode(sro.getSchoolCode());
        school.setSchoolName(sro.getSchoolName());
        List<School> existsSchoolName = schoolRepository.findBySchoolName(school.getSchoolName());
        List<School> existsSchoolCode = schoolRepository.findBySchoolCode(school.getSchoolCode());
        if(existsSchoolName.isEmpty() && existsSchoolCode.isEmpty()) {
            return schoolRepository.save(school);
        } else {
            throw new AlreadyExistsException(String.format("School with name %s and/or code %s already exists", school.getSchoolName(), school.getSchoolCode()));
        }
    }
}