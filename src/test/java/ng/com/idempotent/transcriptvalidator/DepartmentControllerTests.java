package ng.com.idempotent.transcriptvalidator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import ng.com.idempotent.transcriptvalidator.models.Department;
import ng.com.idempotent.transcriptvalidator.models.Faculty;
import ng.com.idempotent.transcriptvalidator.models.School;
import ng.com.idempotent.transcriptvalidator.requestobjects.DepartmentRequestObject;
import ng.com.idempotent.transcriptvalidator.requestobjects.FacultyRequestObject;
import ng.com.idempotent.transcriptvalidator.requestobjects.SchoolRequestObject;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = {"test"})
public class DepartmentControllerTests {
    @LocalServerPort
    private int port;

    private RestTemplate testRestTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
    private long facultyId;
    private long facultyCode;
    private static long schoolCode;
    private static long schoolId;


    @BeforeEach
    public void addSchool() {
        String url = "http://localhost:" + port + "/school";		
        SchoolRequestObject sro = new SchoolRequestObject();
        schoolCode = new Random().nextLong();
        sro.setSchoolCode("" + schoolCode);
        sro.setSchoolName("Lautech-" + schoolCode);

        School c = testRestTemplate.postForObject(url, sro, School.class);
        schoolId = c.getId();
        assertNotNull(c);

        url = "http://localhost:" + port + "/faculty";		
        FacultyRequestObject fro = new FacultyRequestObject();
        facultyCode = new Random().nextLong();
        fro.setFacultyCode("" + facultyCode);
        fro.setFacultyName("Facculty of Science-" + facultyCode);
        fro.setSchoolId(schoolId);
        Faculty f = testRestTemplate.postForObject(url, fro, Faculty.class);
        facultyId = f.getId();
        assertNotNull(f);        
    }

    @Test
    public void getDepartments() {
        String url = "http://localhost:" + port + "/department";
        DepartmentRequestObject dro = new DepartmentRequestObject();
        for(int i = 0; i < 10; i++) {
            dro.setDepartmentCode("1234-" + i);
            dro.setDepartmentName("Department-" + i);
            dro.setFacultyId(facultyId);
            testRestTemplate.postForObject(url, dro, Department.class);
        }

        url = "http://localhost:" + port + "/departments";
        List<Department> departments = testRestTemplate.getForObject(url, List.class);
        System.out.println(departments);
        assertFalse(departments.isEmpty());
        assertTrue(departments.size() >= 10);
    }

    @Test
    public void testNewDepartment() {
        String url = "http://localhost:" + port + "/department";	
        DepartmentRequestObject dro = new DepartmentRequestObject();
        dro.setDepartmentCode("1234");
        dro.setDepartmentName("Department");
        dro.setFacultyId(facultyId);
        Department d = testRestTemplate.postForObject(url, dro, Department.class);
        assertNotNull(d);
        assertEquals(d.getDepartmentCode(), dro.getDepartmentCode());
        assertEquals(d.getDepartmentName(), dro.getDepartmentName());

        try {
            d = testRestTemplate.postForObject(url, dro, Department.class);
        } catch(Exception e) {
            e.printStackTrace();
            assertTrue(e.getMessage().contains("already exists"));
        }
    }

}