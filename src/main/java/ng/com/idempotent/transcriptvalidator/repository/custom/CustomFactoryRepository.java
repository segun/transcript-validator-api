package ng.com.idempotent.transcriptvalidator.repository.custom;

import ng.com.idempotent.transcriptvalidator.models.Faculty;

public interface CustomFactoryRepository {
    Faculty findFaculty(long schoolId, String facultyCode, String facultyName);
}