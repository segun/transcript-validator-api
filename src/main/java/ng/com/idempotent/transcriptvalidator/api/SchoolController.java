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
import ng.com.idempotent.transcriptvalidator.models.School;
import ng.com.idempotent.transcriptvalidator.repository.SchoolRepository;
import ng.com.idempotent.transcriptvalidator.requestobjects.SchoolRequestObject;
import ng.com.idempotent.transcriptvalidator.service.SchoolService;

@RestController
public class SchoolController {
    @Autowired
    SchoolService schoolService;
    @Autowired
    private SchoolRepository schoolRepository;

    @PostMapping(path = "/school", consumes = { MediaType.APPLICATION_JSON }, produces = { MediaType.APPLICATION_JSON })
    public ResponseEntity<School> addNewSchool(@RequestBody SchoolRequestObject sro) throws AlreadyExistsException {
        return new ResponseEntity<>(schoolService.saveSchool(sro), HttpStatus.CREATED);
    }

    @GetMapping(path = "/schools", produces = { MediaType.APPLICATION_JSON })
    public ResponseEntity<List<School>> getSchools() {
        return new ResponseEntity<>(schoolRepository.findAll(), HttpStatus.OK);
    }
}