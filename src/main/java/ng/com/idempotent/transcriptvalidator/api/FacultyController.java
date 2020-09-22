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
import ng.com.idempotent.transcriptvalidator.models.Faculty;
import ng.com.idempotent.transcriptvalidator.repository.FacultyRepository;
import ng.com.idempotent.transcriptvalidator.requestobjects.FacultyRequestObject;
import ng.com.idempotent.transcriptvalidator.service.FacultyService;

@RestController
public class FacultyController {
    @Autowired
    FacultyService facultyService;
    @Autowired
    FacultyRepository facultyRepository;

    @PostMapping(path = "/faculty", consumes = { MediaType.APPLICATION_JSON }, produces = {
            MediaType.APPLICATION_JSON })
    public ResponseEntity<Faculty> addNewFaculty(@RequestBody FacultyRequestObject fro) throws AlreadyExistsException {
        return new ResponseEntity<>(facultyService.saveFaculty(fro), HttpStatus.CREATED);
    }

    @GetMapping(path = "faculties", produces = { MediaType.APPLICATION_JSON})
    public ResponseEntity<List<Faculty>> getFaculties() {
        return new ResponseEntity<>(facultyRepository.findAll(), HttpStatus.OK);
    }
}