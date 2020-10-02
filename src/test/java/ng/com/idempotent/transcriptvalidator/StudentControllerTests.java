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
import ng.com.idempotent.transcriptvalidator.models.Student;
import ng.com.idempotent.transcriptvalidator.models.Student.Gender;
import ng.com.idempotent.transcriptvalidator.requestobjects.DepartmentRequestObject;
import ng.com.idempotent.transcriptvalidator.requestobjects.FacultyRequestObject;
import ng.com.idempotent.transcriptvalidator.requestobjects.SchoolRequestObject;
import ng.com.idempotent.transcriptvalidator.requestobjects.StudentRequestObject;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = { "test" })
public class StudentControllerTests {
    @LocalServerPort
    private int port;

    private RestTemplate testRestTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
    private long facultyId;
    private long facultyCode;
    private long schoolCode;
    private long schoolId;
    private long departmentCode;
    private long departmentId;

    @BeforeEach
    public void prepareStudent() {
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

        url = "http://localhost:" + port + "/department";
        DepartmentRequestObject dro = new DepartmentRequestObject();
        departmentCode = new Random().nextLong();
        dro.setDepartmentCode("" + departmentCode);
        dro.setDepartmentName("Department-" + departmentCode);
        dro.setFacultyId(facultyId);
        Department d = testRestTemplate.postForObject(url, dro, Department.class);
        departmentId = d.getId();
        assertNotNull(d);
    }

    @Test
    public void testAddStudent() {
        String url = "http://localhost:" + port + "/student";
        StudentRequestObject sro = new StudentRequestObject();
        sro.setDepartmentId(departmentId);
        sro.setFacultyId(facultyId);
        sro.setSchoolId(schoolId);
        long rand = new Random().nextLong();
        sro.setGender(Gender.FEMALE.toString());
        sro.setGraduatingYear(2022);
        sro.setMatricNumber("37371-" + rand);
        sro.setStudentName("Michelle Omowunmi Oboho");
        sro.setYearOfAdmission(2016);

        Student s = testRestTemplate.postForObject(url, sro, Student.class);
        assertNotNull(s);
        assertEquals(s.getStudentName(), sro.getStudentName());
        assertEquals(s.getGender(), Gender.FEMALE);
        assertEquals(s.getGraduatingYear(), sro.getGraduatingYear());
        assertEquals(s.getMatricNumber(), sro.getMatricNumber());
        assertEquals(s.getYearOfAdmission(), sro.getYearOfAdmission());
        assertEquals(s.getDepartment().getId(), departmentId);
        assertEquals(s.getFaculty().getId(), facultyId);
        assertEquals(s.getSchool().getId(), schoolId);
    }

    @Test
    public void getStudentById() {
        String url = "http://localhost:" + port + "/student";
        StudentRequestObject sro = new StudentRequestObject();
        sro.setDepartmentId(departmentId);
        sro.setFacultyId(facultyId);
        sro.setSchoolId(schoolId);
        long rand = new Random().nextLong();
        sro.setGender(Gender.FEMALE.toString());
        sro.setGraduatingYear(2022);
        sro.setMatricNumber("37371-" + rand);
        sro.setStudentName("Michelle Omowunmi Oboho");
        sro.setYearOfAdmission(2016);

        Student s = testRestTemplate.postForObject(url, sro, Student.class);
        assertNotNull(s);
        assertEquals(s.getStudentName(), sro.getStudentName());
        assertEquals(s.getGender(), Gender.FEMALE);
        assertEquals(s.getGraduatingYear(), sro.getGraduatingYear());
        assertEquals(s.getMatricNumber(), sro.getMatricNumber());
        assertEquals(s.getYearOfAdmission(), sro.getYearOfAdmission());
        assertEquals(s.getDepartment().getId(), departmentId);
        assertEquals(s.getFaculty().getId(), facultyId);
        assertEquals(s.getSchool().getId(), schoolId);

        url = "http://localhost:" + port + "/student/" + s.getId();
        s = testRestTemplate.getForObject(url, Student.class);
        assertNotNull(s);
        assertEquals(s.getStudentName(), sro.getStudentName());
        assertEquals(s.getGender(), Gender.FEMALE);
        assertEquals(s.getGraduatingYear(), sro.getGraduatingYear());
        assertEquals(s.getMatricNumber(), sro.getMatricNumber());
        assertEquals(s.getYearOfAdmission(), sro.getYearOfAdmission());
        assertEquals(s.getDepartment().getId(), departmentId);
        assertEquals(s.getFaculty().getId(), facultyId);
        assertEquals(s.getSchool().getId(), schoolId);
    }
    
    @Test
    public void testGetStudents() {
        String url = "http://localhost:" + port + "/student";
        for (int i = 0; i < 10; i++) {
            StudentRequestObject sro = new StudentRequestObject();
            long rand = new Random().nextLong() % 123456;
            sro.setDepartmentId(departmentId );
            sro.setFacultyId(facultyId);
            sro.setSchoolId(schoolId );
            sro.setGender(Gender.FEMALE.toString());
            sro.setGraduatingYear(2022);
            sro.setMatricNumber("37371-" + rand);
            sro.setStudentName("Michelle Omowunmi Oboho - " + rand);
            sro.setYearOfAdmission(2016);

            Student s = testRestTemplate.postForObject(url, sro, Student.class);
            assertNotNull(s);
            assertEquals(s.getStudentName(), sro.getStudentName());
            assertEquals(s.getGender(), Gender.FEMALE);
            assertEquals(s.getGraduatingYear(), sro.getGraduatingYear());
            assertEquals(s.getMatricNumber(), sro.getMatricNumber());
            assertEquals(s.getYearOfAdmission(), sro.getYearOfAdmission());
            assertEquals(s.getDepartment().getId(), departmentId);
            assertEquals(s.getFaculty().getId(), facultyId);
            assertEquals(s.getSchool().getId(), schoolId);
        }

        url = "http://localhost:" + port + "/students";
        List<Student> students = testRestTemplate.getForObject(url, List.class);
        System.out.println(students);
        assertFalse(students.isEmpty());
        assertTrue(students.size() >= 10);
    }
}
