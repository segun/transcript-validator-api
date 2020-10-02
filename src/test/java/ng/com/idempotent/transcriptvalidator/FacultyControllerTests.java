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

import ng.com.idempotent.transcriptvalidator.models.Faculty;
import ng.com.idempotent.transcriptvalidator.models.School;
import ng.com.idempotent.transcriptvalidator.requestobjects.FacultyRequestObject;
import ng.com.idempotent.transcriptvalidator.requestobjects.SchoolRequestObject;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = {"test"})
public class FacultyControllerTests {
    
    @LocalServerPort
    private int port;
    private static long schoolCode;
    private static long schoolId;

    private RestTemplate testRestTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

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
    }

    @Test
    public void testGetFaculties() {
        String url = "http://localhost:" + port + "/faculty";		
        FacultyRequestObject fro = new FacultyRequestObject();
        for(int i = 0; i < 10; i++) {
            String code = "1234-" + i;
            String name = "Faculty-" + i;
            fro.setFacultyCode(code);
            fro.setFacultyName(name);
            fro.setSchoolId(schoolId);

            testRestTemplate.postForObject(url, fro, Faculty.class);
        }

        url = "http://localhost:" + port + "/faculties";
        List<Faculty> faculties = testRestTemplate.getForObject(url, List.class);
        System.out.println(faculties);
        assertFalse(faculties.isEmpty());
        assertTrue(faculties.size() >= 10);
    }

    @Test
    public void testNewFaculty() {
        String url = "http://localhost:" + port + "/faculty";		
        FacultyRequestObject fro = new FacultyRequestObject();
        fro.setFacultyCode("1234");
        fro.setFacultyName("Lautech");
        fro.setSchoolId(schoolId);

        Faculty f = testRestTemplate.postForObject(url, fro, Faculty.class);
        assertNotNull(f);
        assertEquals(f.getFacultyCode(), fro.getFacultyCode());
        assertEquals(f.getFacultyName(), fro.getFacultyName());
        assertEquals(f.getSchool().getId(), fro.getSchoolId());

        fro.setFacultyCode("1234");
        fro.setFacultyName("Lautech");
        fro.setSchoolId(schoolId);

        try {
            testRestTemplate.postForEntity(url, fro, Faculty.class);
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(e.getMessage().contains("already exists"));
        }

        fro.setFacultyCode("4321");
        fro.setFacultyName("Unilag");
        fro.setSchoolId(9999);

        try {
            testRestTemplate.postForEntity(url, fro, Faculty.class);
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(e.getMessage().contains("can not be found"));
        }        
    }
}
