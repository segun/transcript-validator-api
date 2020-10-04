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

import ng.com.idempotent.transcriptvalidator.models.Course;
import ng.com.idempotent.transcriptvalidator.models.Course.Status;
import ng.com.idempotent.transcriptvalidator.models.Department;
import ng.com.idempotent.transcriptvalidator.models.Faculty;
import ng.com.idempotent.transcriptvalidator.models.School;
import ng.com.idempotent.transcriptvalidator.models.Student;
import ng.com.idempotent.transcriptvalidator.models.Student.Gender;
import ng.com.idempotent.transcriptvalidator.requestobjects.CourseRequestObject;
import ng.com.idempotent.transcriptvalidator.requestobjects.DepartmentRequestObject;
import ng.com.idempotent.transcriptvalidator.requestobjects.FacultyRequestObject;
import ng.com.idempotent.transcriptvalidator.requestobjects.SchoolRequestObject;
import ng.com.idempotent.transcriptvalidator.requestobjects.StudentRequestObject;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = { "test" })
public class CourseControllerTests {
    @LocalServerPort
    private int port;

    private RestTemplate testRestTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

    private static long schoolId;
    private static long schoolCode;
    private static long facultyId;
    private static long facultyCode;
    private static long departmentId;
    private static long departmentCode;
    private static long studentId;

    @BeforeEach
    public void prepareCourse() {
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

        url = "http://localhost:" + port + "/student";
        StudentRequestObject ssro = new StudentRequestObject();
        ssro.setDepartmentId(departmentId);
        ssro.setFacultyId(facultyId);
        ssro.setSchoolId(schoolId);
        long rand = new Random().nextLong();
        ssro.setGender(Gender.FEMALE.toString());
        ssro.setGraduatingYear(2022);
        ssro.setMatricNumber("37371-" + rand);
        ssro.setStudentName("Michelle Omowunmi Oboho");
        ssro.setYearOfAdmission(2016);

        Student s = testRestTemplate.postForObject(url, ssro, Student.class);
        studentId = s.getId();
        assertNotNull(s);

    }

    @Test
    public void testGetStudentCourses() {
        String url = "http://localhost:" + port + "/course";
        for (int i = 0; i < 10; i++) {
            CourseRequestObject cro = new CourseRequestObject();
            long rand = new Random().nextLong();
            cro.setCourseCode("CSE101-" + rand);
            cro.setCourseName("Computer Science 101-" + rand);
            cro.setCreditUnit(5);
            cro.setCummulativePoint(5);
            cro.setGrade('A');
            cro.setGradePoint(5);
            cro.setGradePointAverage(5.0);
            cro.setScore(85);
            cro.setSemester("1st");
            cro.setStatus(Status.PASS);
            cro.setStudentId(studentId);
            cro.setYear(2020);

            Course c = testRestTemplate.postForObject(url, cro, Course.class);
            assertNotNull(c);
            assertEquals(c.getCourseCode(), cro.getCourseCode());
            assertEquals(c.getCourseName(), cro.getCourseName());
            assertEquals(c.getCreditUnit(), cro.getCreditUnit());
            assertEquals(c.getCummulativePoint(), cro.getCummulativePoint());
            assertEquals(c.getGrade(), cro.getGrade());
            assertEquals(c.getGradePoint(), cro.getGradePoint());
            assertEquals(c.getGradePointAverage(), cro.getGradePointAverage());
            assertEquals(c.getScore(), cro.getScore());
            assertEquals(c.getSemester(), cro.getSemester());
            assertEquals(c.getStatus(), cro.getStatus());
            assertEquals(c.getStudent().getId(), cro.getStudentId());
            assertEquals(c.getYear(), cro.getYear());
        }

        url = "http://localhost:" + port + "/courses/" + studentId;
        List<Course> courses = testRestTemplate.getForObject(url, List.class);
        assertFalse(courses.isEmpty());
        System.out.println(courses);
        assertTrue(courses.size() >= 10);
    }

    @Test
    public void testGetCourses() {
        String url = "http://localhost:" + port + "/course";
        for (int i = 0; i < 10; i++) {
            CourseRequestObject cro = new CourseRequestObject();
            long rand = new Random().nextLong();
            cro.setCourseCode("CSE101-" + rand);
            cro.setCourseName("Computer Science 101-" + rand);
            cro.setCreditUnit(5);
            cro.setCummulativePoint(5);
            cro.setGrade('A');
            cro.setGradePoint(5);
            cro.setGradePointAverage(5.0);
            cro.setScore(85);
            cro.setSemester("1st");
            cro.setStatus(Status.PASS);
            cro.setStudentId(studentId);
            cro.setYear(2020);

            Course c = testRestTemplate.postForObject(url, cro, Course.class);
            assertNotNull(c);
            assertEquals(c.getCourseCode(), cro.getCourseCode());
            assertEquals(c.getCourseName(), cro.getCourseName());
            assertEquals(c.getCreditUnit(), cro.getCreditUnit());
            assertEquals(c.getCummulativePoint(), cro.getCummulativePoint());
            assertEquals(c.getGrade(), cro.getGrade());
            assertEquals(c.getGradePoint(), cro.getGradePoint());
            assertEquals(c.getGradePointAverage(), cro.getGradePointAverage());
            assertEquals(c.getScore(), cro.getScore());
            assertEquals(c.getSemester(), cro.getSemester());
            assertEquals(c.getStatus(), cro.getStatus());
            assertEquals(c.getStudent().getId(), cro.getStudentId());
            assertEquals(c.getYear(), cro.getYear());
        }
        url = "http://localhost:" + port + "/courses";
        List<Course> courses = testRestTemplate.getForObject(url, List.class);
        assertFalse(courses.isEmpty());
        assertTrue(courses.size() >= 10);
    }

    @Test
    public void testAddNewCourse() {
        String url = "http://localhost:" + port + "/course";
        CourseRequestObject cro = new CourseRequestObject();
        cro.setCourseCode("CSE101");
        cro.setCourseName("COmputer Science 101");
        cro.setCreditUnit(5);
        cro.setCummulativePoint(5);
        cro.setGrade('A');
        cro.setGradePoint(5);
        cro.setGradePointAverage(5.0);
        cro.setScore(85);
        cro.setSemester("1st");
        cro.setStatus(Status.PASS);
        cro.setStudentId(studentId);
        cro.setYear(2020);

        Course c = testRestTemplate.postForObject(url, cro, Course.class);
        assertNotNull(c);
        assertEquals(c.getCourseCode(), cro.getCourseCode());
        assertEquals(c.getCourseName(), cro.getCourseName());
        assertEquals(c.getCreditUnit(), cro.getCreditUnit());
        assertEquals(c.getCummulativePoint(), cro.getCummulativePoint());
        assertEquals(c.getGrade(), cro.getGrade());
        assertEquals(c.getGradePoint(), cro.getGradePoint());
        assertEquals(c.getGradePointAverage(), cro.getGradePointAverage());
        assertEquals(c.getScore(), cro.getScore());
        assertEquals(c.getSemester(), cro.getSemester());
        assertEquals(c.getStatus(), cro.getStatus());
        assertEquals(c.getStudent().getId(), cro.getStudentId());
        assertEquals(c.getYear(), cro.getYear());
    }
}
