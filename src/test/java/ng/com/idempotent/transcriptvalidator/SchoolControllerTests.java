package ng.com.idempotent.transcriptvalidator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import ng.com.idempotent.transcriptvalidator.models.School;
import ng.com.idempotent.transcriptvalidator.requestobjects.SchoolRequestObject;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = {"test"})
public class SchoolControllerTests {
    
    @LocalServerPort
    private int port;

    RestTemplate testRestTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

    @Test
    public void testGetSchools() {
        String url = "http://localhost:" + port + "/school";		
        SchoolRequestObject sro = new SchoolRequestObject();
        for(int i = 0; i < 10; i++) {
            String code = "1234-" + i;
            String name = "School-" + i;
            sro.setSchoolCode(code);
            sro.setSchoolName(name);
            testRestTemplate.postForObject(url, sro, School.class);
        }

        url = "http://localhost:" + port + "/schools";
        List<School> schools = (List<School>) testRestTemplate.getForObject(url, List.class);
        System.out.println(schools);
        assertFalse(schools.isEmpty());
        assertTrue(schools.size() >= 10);
    }

    @Test
    public void testNewSchool() {
        String url = "http://localhost:" + port + "/school";		
        SchoolRequestObject sro = new SchoolRequestObject();
        sro.setSchoolCode("1234");
        sro.setSchoolName("Lautech");

        School c = testRestTemplate.postForObject(url, sro, School.class);
        assertNotNull(c);
        assertEquals(c.getSchoolCode(), sro.getSchoolCode());
        assertEquals(c.getSchoolName(), sro.getSchoolName());

        SchoolRequestObject exists = new SchoolRequestObject();
        exists.setSchoolCode("1234");
        exists.setSchoolName("Unilag");
        try {
            testRestTemplate.postForEntity(url, exists, School.class);
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(e.getMessage().contains("already exists"));
        }

        exists.setSchoolName("Lautech");
        exists.setSchoolCode("4321");
        try {
            testRestTemplate.postForEntity(url, exists, School.class);
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(e.getMessage().contains("already exists"));
        }

        exists.setSchoolCode("1234");
        exists.setSchoolName("Lautech");
        try {
            testRestTemplate.postForEntity(url, exists, School.class);
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(e.getMessage().contains("already exists"));
        }
    }
}
